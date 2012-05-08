package annie;

import java.io.*;

public class TestMyDictionary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in, "UTF8"));
			MyDictionary dict = new MyDictionary("data/dictionary.txt");
			String line = r.readLine();
			
			// loop, reading in user input and looking it up as a word. 
			while (line != null) {
				
				line = r.readLine();
			}
			
		} catch (Exception e) {
		}

	}

}
