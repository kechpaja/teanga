package ELearning;

import java.util.*;
import java.io.*;

public class HelpBox {
	private List<String> helpLinesV = new ArrayList<String>();
	private List<String> helpLinesG = new ArrayList<String>();
	private List<String> helpLinesB = new ArrayList<String>();
	private List<String> helpLinesVLess = new ArrayList<String>();
	private List<String> helpLinesGLess = new ArrayList<String>();
	
	public HelpBox(String helpFileV, String helpFileG, String helpFileB, String helpFileVL, String helpFileGL){
		try{
			BufferedReader helpReaderV = new BufferedReader(new FileReader(helpFileV));
			BufferedReader helpReaderG = new BufferedReader(new FileReader(helpFileG));
			BufferedReader helpReaderB = new BufferedReader(new FileReader(helpFileB));
			BufferedReader helpReaderVL = new BufferedReader(new FileReader(helpFileVL));
			BufferedReader helpReaderGL = new BufferedReader(new FileReader(helpFileGL));

			String helpLine = helpReaderV.readLine();
			while (helpLine != null){
				helpLinesV.add(helpLine);
				helpLine = helpReaderV.readLine();
			}
			String helpLine2 = helpReaderG.readLine();
			while (helpLine2 != null){
				helpLinesG.add(helpLine2);
				helpLine2 = helpReaderG.readLine();
			}
			String helpLine3 = helpReaderB.readLine();
			while (helpLine3 != null){
				helpLinesB.add(helpLine3);
				helpLine3 = helpReaderB.readLine();
			}
			String helpLine4 = helpReaderVL.readLine();
			while (helpLine4 != null){
				helpLinesVLess.add(helpLine4);
				helpLine4 = helpReaderVL.readLine();
			}
			String helpLine5 = helpReaderGL.readLine();
			while (helpLine5 != null){
				helpLinesGLess.add(helpLine5);
				helpLine5 = helpReaderGL.readLine();
			}
		}catch (IOException e){
		}
	}
	
	public String getVHelp(int level){
		return helpLinesV.get(level);
	}
	
	public String getGHelp(int level){
		return helpLinesG.get(level);
	}
	
	public String getBHelp(int level){
		return helpLinesB.get(level);
	}
	
	public String getVLessHelp(int level){
		return helpLinesVLess.get(level);
	}
	
	public String getGLessHelp(int level){
		return helpLinesGLess.get(level);
	}
	
	public static void main(String[] args){
		//HelpBox hb = new HelpBox("/Users/taranoble/Desktop/testhelpv.txt", "/Users/taranoble/Desktop/testhelpg.txt", "b");
	
	}
}
