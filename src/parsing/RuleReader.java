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
	
	private BinarySyntacticRule parseBiRule(String line) {
		String[] spl = line.split("\\s"); 
		if (spl.length == 3) {
			//String[] s = spl[0].split(":");
			return new BinarySyntacticRule(Pos.strToPos(spl[1]), Pos.strToPos(spl[2]), Pos.strToPos(spl[0]));
			// binary rule
		} else {
			// TODO error of some sort
			return null;
		}
	}
	
	// parses a unary syntactic rule
	private UnarySyntacticRule parseUnRule(String line) {
		String[] spl = line.split("\\s");
		if (spl.length == 2) {
			
		} else {
			// TODO error case
			return null;
		}
	}
	
	
	// parses out an agreement rule
	private AgreementRule parseAgRule(String line) {
		return null;
	}
	
	// parses out a semantic rule
	private SemanticRule parseSemRule(String line) {
		return null;
	}
	
	// a method to read in rules and put them into lists
	public RuleSet ruleRead(String file) {
		RuleSet ruleset = null;
		
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			
			// create lists
			List<BinarySyntacticRule> birules = new LinkedList<BinarySyntacticRule>();
			List<UnarySyntacticRule> unrules = new LinkedList<UnarySyntacticRule>();
			List<AgreementRule> agrules = new LinkedList<AgreementRule>();
			List<SemanticRule> semrules = new LinkedList<SemanticRule>();
			
			String line = r.readLine();
			
			if (!line.equals("binary:")) {
				// TODO some error message
			}
			
			// iterate through the lines of syntactic rules; 
			while (!line.equals("unary:")) {
				birules.add(parseBiRule(line));
				line = r.readLine();
			}
			
			// move forward one
			line = r.readLine();
			
			while (!line.equals("agreement:")) {
				unrules.add(parseUnRule(line));
				line = r.readLine();
			}
			
			while (!line.equals("semantic:")) {
				agrules.add(parseAgRule(line));
				line = r.readLine();
			}
			
			// move forward one
			line = r.readLine();
			
			while (line != null) {
				semrules.add(parseSemRule(line));
				line = r.readLine();
			}
			
			ruleset = new RuleSet(birules, unrules, agrules, semrules);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ruleset;
	}

}
