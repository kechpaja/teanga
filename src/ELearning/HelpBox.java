package ELearning;

import java.util.*;
import java.io.*;

public class HelpBox {
	private List<String> helpLinesV = new ArrayList<String>();
	private List<String> helpLinesG = new ArrayList<String>();
	
	public HelpBox(String helpFileV, String helpFileG){
		try{
			BufferedReader helpReaderV = new BufferedReader(new FileReader(helpFileV));
			BufferedReader helpReaderG = new BufferedReader(new FileReader(helpFileG));
			String helpLine = helpReaderV.readLine();
			while (helpLine != null){
				helpLinesV.add(helpLine);
				helpLine = helpReaderV.readLine();
			}
			String helpLine2 = helpReaderG.readLine();
			while (helpLine2 != null){
				helpLinesG.add(helpLine2);
				helpLine = helpReaderG.readLine();
				helpLine2 = helpReaderG.readLine();
			}
		}catch (IOException e){
			System.out.println("Could not find the help file");
		}
	}
	
	public String getVHelp(int level){
		return helpLinesV.get(level);
	}
	
	public String getGHelp(int level){
		return helpLinesG.get(level);
	}
	
	public static void main(String[] args){
		HelpBox hb = new HelpBox("/Users/taranoble/Desktop/testhelpv.txt", "/Users/taranoble/Desktop/testhelpg.txt");
	
	}
}
