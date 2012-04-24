package parsing;

import java.io.*;

public class TestIO {
	
	public static void main(String[] args) {
		
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		Parser parser = new Parser(RuleReader.ruleRead("data/parserules.txt"));
		
		try {
			String line = r.readLine();
			
			while (line != null) {
				if (line.equals("end") || line.equals("")) {
					break;
				} else {
					parser.parse(line);
				}
				line = r.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
