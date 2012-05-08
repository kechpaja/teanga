package parsing;

import java.io.*;
import java.util.*;
import rules.*;

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
	 * 
	 * Syntactic Rules are of this form:
	 * 
	 * Binary: A B C
	 * 
	 * Unary: A B
	 * 
	 * Ternary: A B C D
	 * 
	 * Where "A" is always the parent, and the other three are the children.
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
			
			String line = r.readLine();
			
			if (line == null || !line.equals("binary:")) {
			}
			
			line = r.readLine();
			
			// iterate through the lines of syntactic rules; 
			while (line != null && !line.equals("unary:")) {
				String[] spl = line.split("\\s");
				if (line.equals("") || line.startsWith("#")) {
					// skipping comment
				} else if (spl.length != 3) {
				} else {
					// build rule
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
				} else {
					// build rule
					Pos c = Pos.strToPos(spl[1]);
					Pos p = Pos.strToPos(spl[0]);
					unrules.put(c, new UnarySyntacticRule(c, p));
				}
				line = r.readLine();
			}
			
			line = r.readLine();
			
			// ternary rules
			while (line != null) {
				String[] spl = line.split("\\s");
				if (line.equals("") || line.startsWith("#")) {
					// comment
				} else if (spl.length != 4) {
					// error
				} else {
					// build rules
					Pos l = Pos.strToPos(spl[1]);
					Pos m = Pos.strToPos(spl[2]);
					Pos rg = Pos.strToPos(spl[3]);
					Pos p = Pos.strToPos(spl[0]);
					
					terules.add(new TernarySyntacticRule(l, m, rg, p));
				}
				line = r.readLine();
			}
			
			// build ruleset
			ruleset = new RuleSet(birules, unrules, terules);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		return ruleset;
	}

}
