package parsing;

import java.io.*;

public class TestIO {
	
	public static void main(String[] args) {
		
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			
			Parser parser = new Parser("data/parserules.txt", "data/dictionary.txt");
			
			Response resp = null;
			
			String line = r.readLine();
			
			while (line != null) {
				resp = parser.parse(line);
				System.out.println(resp);
				line = r.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
