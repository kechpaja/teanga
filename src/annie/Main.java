package annie;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Main {
	public static void main(String [ ] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		PlayerStats annie= new PlayerStats("Annie", 0);
		annie.encode();
		PlayerStats annie2= new PlayerStats("Annie");
		/*File file= new File("Passwords and usernames icmshfonwo02udn");
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("");*/
		OpeningPage o= new OpeningPage();
		if(o.usernameAvailable("Annie"))
			o.newUser("Annie", "mypassword", 0);
		//System.out.println(o.correctPassword("Annie", "mypassword"));
		annie= o.newGame("Annie", 0);
		o= new OpeningPage();
		//System.out.println(o.correctPassword("Annie", "mypassword"));
		if(o.usernameAvailable("Andrew"))
			o.newUser("Andrew", "passy", 1);
		//System.out.println("Andrew1:"+o.correctPassword("Andrew", "passy"));
		annie= o.newGame("Andrew", 1);
		o= new OpeningPage();
		//System.out.println("Annie:"+o.correctPassword("Annie", "mypassword"));
		//System.out.println("Andrew2:"+o.correctPassword("Andrew", "passy"));
	}
}
