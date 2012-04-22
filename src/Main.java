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
		PlayerStats annie= new PlayerStats("Annie", "female");
		annie.encode();
		PlayerStats annie2= new PlayerStats("Annie");
		/*File file= new File("Passwords and usernames icmshfonwo02udn");
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("");*/
		OpeningPage o= new OpeningPage();
		o.newUser("Annie", "mypassword", "female");
		System.out.println(o.correctPassword("Annie", "mypassword"));
		annie= o.newGame("Annie", "female");
	}
}
