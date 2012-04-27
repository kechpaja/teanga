package annie;

import java.io.*;

public class TestMyDictionary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			MyDictionary dict = new MyDictionary("data/dictionary.txt");
			String line = r.readLine();
			
			// loop, reading in user input and looking it up as a word. 
			while (line != null) {
				System.out.println(dict.getWord(line, true));
				
				line = r.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
