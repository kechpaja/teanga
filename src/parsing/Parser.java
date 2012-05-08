package parsing;

import java.io.FileNotFoundException;
import java.util.*;
import annie.MyDictionary;
import rules.*;
import nodes.*;

// This parser will NOT handle "poetic" language - it is rather strict. But strict is good. 

public class Parser {
	
	private RuleSet rules_;
	private MyDictionary dict_;
	
	/**
	 * @author kechpaja
	 * @args String sentence
	 * The sentence to be parsed and error-checked against this instance of parser's ruleset. 
	 * 
	 * @return Response
	 * An instance of the class Response
	 * 
	 * Takes in a sentence in Esperanto, and returns a Response containing a list of mistakes
	 * in the sentence, a boolean indicating whether the sentence is correct or not, and the
	 * sentence itself. 
	 */
	public Response parse(String sentence) {
		
		List<Mistake> mistakes = new LinkedList<Mistake>();
		
		// tokenize the string.
		Tokenizer tkn = new Tokenizer(sentence, dict_);
		tkn.init(mistakes); // this will put lexical mistakes into the mistakes list
		
		// parse
		ParseTree tree = parseTokenStream(tkn, rules_.getBiRules(), rules_.getUnRules(), rules_.getTeRules(), mistakes);
		
		// visit, checking for agreement problems
		visit(tree, mistakes);
		
		// the response is created, and returned. 
		return Response.responseFactory(tree, sentence);
	}
	
	/**
	 * @author kechpaja
	 * 
	 * This is the constructor you should be using. It takes in two strings; the first is the
	 * path to the ruleset, and the second the path to the dictionary file. 
	 * 
	 */
	public Parser(String rulefile, String dictfile) throws FileNotFoundException{
		rules_ = RuleReader.ruleRead(rulefile);
		dict_ = new MyDictionary(dictfile);
	}
	
	// PRIVATE METHODS
	
	// This method should take a list of mistakes; otherwise extras might be carried over. 
	private ParseTree parseTokenStream(Tokenizer tkn, HashMap<Pos, List<BinarySyntacticRule>> rules, 
		HashMap<Pos, UnarySyntacticRule> unrules, List<TernarySyntacticRule> terules, List<Mistake> mistakes) {
		
		Node node = null;
		Stack<Node> prev = new Stack<Node>();
		
		if (tkn.hasNext()) {
			node = new LeafNode(tkn.getNextToken());
		} else {
			// nothing happens
		}
		
		boolean advanced = true;
		
		// go through, at each step if curr & next can combine by some rule,
		while (tkn.hasNext() || !prev.empty()) {
			if (!advanced) {
				break;
			}
			
			advanced = false;
			
			// match with the ternary rules...
			if (!prev.empty() && tkn.hasNext()) {
				for (TernarySyntacticRule r : terules) {
					if (r.matches(prev.peek(), node, new LeafNode(tkn.peek()))) {
						// replace constituent
						node = r.combine(prev.pop(), node, new LeafNode(tkn.getNextToken()));
						
						// advance
						advanced = true;
						break;
					} else if (prev.size() >= 2){
						Node last = prev.pop();
						if (r.matches(prev.peek(), last, node)) {
							// replace node
							node = r.combine(prev.pop(), last, node);
							
							// advance
							advanced = true;
							break;
						} else {
							// clean up
							prev.push(last);
						}
					}
				}
			}
			
			// Do below, but for prev and curr = node
			if (!advanced && !prev.empty()) {
				List<BinarySyntacticRule> lst = rules.get(prev.peek().getPos());
				if (lst != null) {
					for (BinarySyntacticRule r : rules.get(prev.peek().getPos())) {
						if (r.matches(prev.peek(), node)) {
							// replace constituent
							node = r.combine(prev.pop(), node);

							// advance
							advanced = true;
							break;
						}
					}
				}
			}
			
			// try to combine current and next
			// if possible, put in place of curr and get next into next
			// else, move curr to prev (stack) and next to curr and fetch next
			if (!advanced && tkn.hasNext()) {
				List<BinarySyntacticRule> lst = rules.get(node.getPos());
				if (lst != null) {
					for (BinarySyntacticRule r : lst) {
						if (r.matches(node, new LeafNode(tkn.peek()))) {
							// replace constituent
							node = r.combine(node, new LeafNode(tkn.getNextToken()));
						
							// advance
							advanced = true;
							break;
						}
					}
				}
			}
			
			// unary rules go in here somewhere...
			if (!advanced && unrules.containsKey(node.getPos())) {
				node = unrules.get(node.getPos()).combine(node);
				advanced = true;
			}
			
			
			if (!advanced && tkn.hasNext()) {
				// advance
				prev.push(node);
				node = new LeafNode(tkn.getNextToken());
				advanced = true;
			}
		}

		return new ParseTree(node, mistakes, prev);
	}
	
	// Takes a parse tree; visits nodes and adds semantic mistakes. 
	private void visit(ParseTree tree, List<Mistake> mistakes) {
		tree.visit(mistakes);
	}

}
