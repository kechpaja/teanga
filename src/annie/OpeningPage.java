package annie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class OpeningPage {
	private LinkedList<String> usernames= new LinkedList<String>();
	private LinkedList<String> passwords= new LinkedList<String>();
	private LinkedList<String> genders= new LinkedList<String>();
	private String userNameFile="Passwordsandusernamesicmshfonwo02udn";
	
	public OpeningPage() throws IOException
	{
		readInUserAndPass();
	}

	private void readInUserAndPass() throws IOException //Reads in all userNames from a file and all passwords from a file
	{
		FileInputStream fstream = new FileInputStream(userNameFile);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null)  
		{
			usernames.add(strLine);
			if ((strLine = br.readLine()) != null)
			{
				passwords.add(strLine);
				if ((strLine = br.readLine()) != null)
				{
					genders.add(strLine);
				}
			}
		}
		if(!usernames.isEmpty())
		{
			usernames.remove(usernames.size()-1);
		}
	}

	public boolean correctPassword(String userName, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException //Checks password entered
	{
		int index=0;
		for(String u: usernames)
		{
			if(u.equals(userName))
			{
				break;
			}
			index++;
		}
		System.out.println("index: "+index);
		System.out.println("passwords.get(index): "+passwords.get(index));
		System.out.println("encrypt(password): "+encrypt(password));
		if(index==usernames.size())
			return false;
		if(passwords.get(index).equals(encrypt(password)))	
			return true;
		return false;
	}

	//returns the encrypted string (used for passwords)
	public String encrypt(String s) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
	{
		/*s=s+"padding to spare, padding to spare, lalalala, padding to spare, my love, padding to spare. Hey!" +
				"padding to spare, padding to spare, lalalala, padding to spare, my love, padding to spare!";
		PBEKeySpec keySpec = new PBEKeySpec("this is my char array that sure as hell better be 24 bytes".toCharArray());
		SecretKeyFactory keyFactory =SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(keySpec);
		byte[] salt = new byte[8];
		String forSalt="alwaysth";
		salt=forSalt.getBytes();
		//System.out.println(salt.length);
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
		Cipher encrypt= Cipher.getInstance("PBEWithMD5AndDES");
		encrypt.init(Cipher.ENCRYPT_MODE, key,parameterSpec);
		byte b1[] = Arrays.copyOfRange(s.getBytes(), 0, 64);
		byte[] output = encrypt.update(b1, 0, 64);
		System.out.println("s: "+ s+ " encrypted: "+new String(output));
		return new String(output);*/
		return s;
	}

	//Creates an instance of PlayerStats, calls Decode(username), and then returns the PlayerStats.
	public PlayerStats bootGame(String userName) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		FileWriter fstream = new FileWriter(userNameFile);
		BufferedWriter out = new BufferedWriter(fstream);
		for(int i=0; i<usernames.size(); i++)
		{
			out.write(usernames.get(i)+"\n");
			out.write(passwords.get(i)+"\n");
			out.write(usernames.get(i)+"\n");
		}
		out.flush();
		out.close();
		return new PlayerStats(userName);
	}

	public PlayerStats newGame(String userName, String gender) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IOException
	{
		FileWriter fstream = new FileWriter(userNameFile);
		BufferedWriter out = new BufferedWriter(fstream);
		for(int i=0; i<usernames.size(); i++)
		{
			out.write(usernames.get(i)+"\n");
			out.write(passwords.get(i)+"\n");
			out.write(usernames.get(i)+"\n");
		}
		out.flush();
		out.close();
		return new PlayerStats(userName, gender);
	}

	public boolean usernameAvailable(String userName) //Returns true if the username is not in use, false otherwise
	{
		return !usernames.contains(userName);
	}

	public void newUser(String userName, String password, String gender) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException
	{
		usernames.add(userName);
		passwords.add(encrypt(password));
		genders.add(gender);
	}

}