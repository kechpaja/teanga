package annie;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class PlayerStats {
	// Stores all of the vocab and grammar games for each user.  
	//Instantiated with all of the SingleGames avaialable and intialized to locked, 
	//also initializes all of the other key pieces of data for each game.
	int levels=6;
	int games=3;
	private SingleGame[][] userGames = new SingleGame[levels][games];
	private Boolean[][] unlocked = {{true,true,false},{false,false,false},
			{false,false,false},{false,false,false},{false,false,false},{false,false,false}};
	//private Boolean[][] timeRelevent = {{true,false,false},{true,false,false},
			//{true,false,false},{true,false,false},{true,false,false},{true,false,false}};
	private int[][] points = {{100,100,100},{100,100,100},{100,100,100},{100,100,100},{100,100,100},{100,100,100}};
	private String userName;
	private int gender;
	private int totalPoints;
	private Cipher encrypt;
	private Cipher decrypt;
	
	public PlayerStats(String username) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		userName=username;
		username=username+"padding to make sure it is at least 24 bytes";
		PBEKeySpec keySpec = new PBEKeySpec(userName.toCharArray());
		SecretKeyFactory keyFactory =SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(keySpec);
		byte[] salt = new byte[8];
		String forSalt="alwaysth";
		salt=forSalt.getBytes();
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
		encrypt= Cipher.getInstance("PBEWithMD5AndDES");
		decrypt = Cipher.getInstance("PBEWithMD5AndDES");
		encrypt.init(Cipher.ENCRYPT_MODE, key,parameterSpec);
		decrypt.init(Cipher.DECRYPT_MODE, key,parameterSpec);
		for(int i=0; i<levels; i++)
	    {
	    	for(int j=0; j<games; j++)
		    {
	    		userGames[i][j]=new SingleGame(unlocked[i][j], 
	    				points[i][j]);
		    }
		}
		decode();
	}	

	public PlayerStats(String username, int gen) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException
	{
		gender=gen;
		userName=username;
		totalPoints=0;
		username=username+"padding to make sure it is at least 24 bytes";
		PBEKeySpec keySpec = new PBEKeySpec(userName.toCharArray());
		SecretKeyFactory keyFactory =SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(keySpec);
		byte[] salt = new byte[8];
		String forSalt="alwaysth";
		salt=forSalt.getBytes();
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
		encrypt= Cipher.getInstance("PBEWithMD5AndDES");
		decrypt = Cipher.getInstance("PBEWithMD5AndDES");
		encrypt.init(Cipher.ENCRYPT_MODE, key,parameterSpec);
		decrypt.init(Cipher.DECRYPT_MODE, key,parameterSpec);
		for(int i=0; i<levels; i++)
	    {
	    	for(int j=0; j<games; j++)
		    {
	    		userGames[i][j]=new SingleGame(unlocked[i][j],
	    				points[i][j]);
	    		/*if(i<3||(i==3&&j<2))
	    		{
	    			Random random = new Random();
	    			int t=random.nextInt(50);
	    			userGames[i][j].bestScore=100+t;
	    			userGames[i][j].unlocked=true;
	    			totalPoints=totalPoints+t+100;
	    		}*/
		    }
		}
	}		
	
	//Encodes all information and then (over)writes it to the saved file associated 
	//with this username
	public void encode() throws IOException, IllegalBlockSizeException, BadPaddingException 
	{
	    FileOutputStream fos;
	    CipherOutputStream cos;	    
	    fos = new FileOutputStream("data/Users/"+userName);
	    String toEncrypt="";
	    toEncrypt= toEncrypt+gender;
	    toEncrypt= toEncrypt+" "+totalPoints+" ";
	    for(int i=0; i<levels; i++)
	    {
	    	for(int j=0; j<games; j++)
		    {
	    		toEncrypt= toEncrypt+Boolean.toString(userGames[i][j].unlocked)+" ";
	    	    toEncrypt= toEncrypt+userGames[i][j].bestScore+" ";  
		    }  	
	    }
	    toEncrypt=toEncrypt+"So much buffer so the ending won't be messed up";
	    //tampering with this file will have no effect on the game
	    FileWriter fstream = new FileWriter("data/Users/"+userName+"temp");
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write(toEncrypt);
	    out.flush();
	    out.close();
	    FileInputStream inFile = new FileInputStream("data/Users/"+userName+"temp");

	    byte[] input = new byte[64];
	    int bytesRead;
	    while ((bytesRead = inFile.read(input)) != -1)
	    {
	    	byte[] output = encrypt.update(input, 0, bytesRead);
	    	if (output != null) fos.write(output);
	    }
	    
	    byte[] output = encrypt.doFinal();
	    if (output != null) fos.write(output);
	    
	    inFile.close();
	    fos.flush();
	    fos.close();
	}

	//Reads from the file specified, decodes, 
	//and then builds this map to match the specifications in the file
	private void decode() throws IOException, IllegalBlockSizeException, BadPaddingException 
	{
		 FileInputStream fis;
		 CipherInputStream cis;
		 
		 String t="";
		 fis = new FileInputStream("data/Users/"+userName);
		 cis = new CipherInputStream(fis, decrypt);
		 byte[] input = new byte[64];
		 int bytesRead;
		 while ((bytesRead = fis.read(input)) != -1)
		 {
		     byte[] output = decrypt.update(input, 0, bytesRead);
		     if (output != null)
		    	 t=t+new String(output);
		 }

		 byte[] output = encrypt.doFinal();
		 if (output != null)
			 t=t+new String(output);
		  
		 fis.close();
		 String[] data=t.split(" ");
		 gender= Integer.parseInt(data[0]);
		 totalPoints= Integer.parseInt(data[1]);
		 int index=2;
		 for(int i=0; i<levels; i++)
		 {
		    for(int j=0; j<games; j++)
			{
		    	SingleGame thisGame=userGames[i][j];
		    	thisGame.unlocked=Boolean.parseBoolean(data[index]);
		    	index++;
		    	thisGame.bestScore=Integer.parseInt(data[index]);
		    	index++;
		    	userGames[i][j]=thisGame;
			}
		 }	    
		 cis.close();   
	}

	public String getUsername()
	{
		return userName;
	}

	public int getGender()
	{
		return gender;
	}

	public int getPoints()
	{
		return totalPoints;
	}

	public boolean isUnlocked(int i, int j)
	{
		if(j<2)
		{
			return true;
		}
		return userGames[i][j-2].unlocked;
	}
	
	//Refreshes the entire PlayerStats file, updating total points 
	//and and top points for the given game in the given level if necessary
	public void RefreshStats(int level, int game, int score) 
	{
		totalPoints=totalPoints+userGames[level][game].updateSingleGame(score);

		Boolean before=true;
		for(int j=0; j<games-1; j++)
		{
			if(!userGames[level][j].isDefeated())
				before=false;
		}
		if(before)
		{
			userGames[level][games-1].unlocked=true;
		}
		if((game==games-1&&level!=levels-1)&&(userGames[level][game].isDefeated()))
		{
			for(int j=0; j<games-1; j++)
			{
				userGames[level+1][j].unlocked=true;
			}
		}
	}
	
	//Returns the appropriate SingleGame from the userGames array
	public SingleGame getSingleGame(int level, int game) 
	{	
		return userGames[level][game];
	}

}
