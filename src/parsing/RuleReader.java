package parsing;

import java.io.*;
import java.util.*;
import rules.*;
//import parsing.*;

public class RuleReader {
	
	/*
	 * Format for storing rules in a file:
	 * 
	 * binary:
	 * <syntactic rules, one per line>
	 * unary:
	 * <unary syntactic rules>
	 * ternary:
	 * <ternary syntactic rules>
	 * agreement:
	 * <agreement rules>
	 * semantic:
	 * <semantic rules>
	 * 
	 * Syntactic Rules are of this form:
	 * 
	 * Binary: A B C
	 * 
	 * Unary: A B
	 * 
	 * The words "unary" and "binary" are not included, since one can tell from the number of items...
	 * 
	 */	
	
	// a method to read in rules and put them into lists
	public static RuleSet ruleRead(String file) throws FileNotFoundException {
		RuleSet ruleset = null;
		
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			
			// create lists
			HashMap<Pos, List<BinarySyntacticRule>> birules = new HashMap<Pos, List<BinarySyntacticRule>>();
			HashMap<Pos, UnarySyntacticRule> unrules = new HashMap<Pos, UnarySyntacticRule>();
			List<TernarySyntacticRule> terules = new LinkedList<TernarySyntacticRule>();
			HashMap<Pos, List<AgreementRule>> agrules = new HashMap<Pos, List<AgreementRule>>();
			HashMap<Pos, List<SemanticRule>> semrules = new HashMap<Pos, List<SemanticRule>>();
			
			String line = r.readLine();
			
			if (line == null || !line.equals("binary:")) {
				// TODO some error message
			}
			
			line = r.readLine();
			
			// iterate through the lines of syntactic rules; 
			while (line != null && !line.equals("unary:")) {
				String[] spl = line.split("\\s");
				if (line.equals("") || line.startsWith("#")) {
					// bad input.
					//System.out.println("Skipped Comment or Blank Line.");
				} else if (spl.length != 3) {
					System.out.println("ERROR IN BINARY RULES");
					System.out.println(line);
				} else {
					Pos h = Pos.strToPos(spl[1]);
					Pos t = Pos.strToPos(spl[2]);
					Pos p = Pos.strToPos(spl[0]);
					if (!birules.containsKey(h)) {
						birules.put(h, new LinkedList<BinarySyntacticRule>());
					}
				
					birules.get(h).add(new BinarySyntacticRule(h, t, p));
				}
				
				line = r.readLine();
			}
			
			// move forward one
			line = r.readLine();
			
			// unary rules
			while (line != null && !line.equals("ternary:")) {
				String[] spl = line.split("\\s");
				if (line.equals("") || line.startsWith("#")) {
					// comment
				} else if (spl.length != 2) {
					// error
					System.out.println("ERROR IN UNARY RULES");
					// TODO get a better error message
				} else {
					Pos c = Pos.strToPos(spl[1]);
					Pos p = Pos.strToPos(spl[0]);
					//if (!birules.containsKey(c)) {
					//	unrules.put(c, new LinkedList<UnarySyntacticRule>());
					//}
				
					unrules.put(c, new UnarySyntacticRule(c, p));
				}
				line = r.readLine();
			}
			
			line = r.readLine();
			
			// ternary rules
			while (line != null && !line.equals("agreement:")) {
				String[] spl = line.split("\\s");
				if (line.equals("") || line.startsWith("#")) {
					// comment
				} else if (spl.length != 4) {
					// error
					System.out.println("ERROR IN TERNARY RULES");
				} else {
					Pos l = Pos.strToPos(spl[1]);
					Pos m = Pos.strToPos(spl[2]);
					Pos rg = Pos.strToPos(spl[3]);
					Pos p = Pos.strToPos(spl[0]);
					
					terules.add(new TernarySyntacticRule(l, m, rg, p));
				}
				line = r.readLine();
			}
			
			while (line != null && !line.equals("semantic:")) {
				if (line.startsWith("#")) {
					continue;
				}
			//	agrules.add(parseAgRule(line));
				//TODO
				line = r.readLine();
			}
			
			// move forward one
			line = r.readLine();
			
			while (line != null) {
				if (line.startsWith("#")) {
					continue;
				}
			//	semrules.add(parseSemRule(line));
				//TODO
				line = r.readLine();
			}
			
			ruleset = new RuleSet(birules, unrules, terules, agrules, semrules);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ruleset;
	}

}
