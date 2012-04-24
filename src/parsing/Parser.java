package parsing;

import java.util.*;

import rules.*;
import nodes.*;

// TODO I need to check for agreement issues in the parser as well. 
// "la knabo feliĉan knabinon karesas" should parse so that the adjective goes with
// the following noun; that currently isn't what would happen. 
// make two passes?
// For now, this is an edge case. However, we need to deal with it. 


// This parser will NOT handle "poetic" language - it is rather strict. But strict is good. 

// TODO make it "disprefer" rules that break agreement, but allow them as a last resort.
// If we need to use them, we'll catch them later. 

public class Parser {
	
	private RuleSet rules_;
	
	/**
	 * @author kechpaja
	 * @args String sentence
	 * The sentence to be parsed and error-checked against this instance of parser's ruleset. 
	 * 
	 * @return Response
	 * An instance of the class Response
	 * 
	 * Contract?
	 */
	public Response parse(String sentence) {
		//TODO probably runs through the list of rules, checking each one against the string. 
		// alternatively, we go through and parse the sentence (NOT like in compiler, though). 
		// Try to do bottom-up parsing, rather than top-down (maybe). If something fails, 
		// this method will exit gracefully - it should never throw an exception. 
		
		List<Mistake> mistakes = new LinkedList<Mistake>();
		
		//TODO tokenize the string. Figure out tokenizer. 
		Tokenizer tkn = new Tokenizer(sentence);
		tkn.init(mistakes); // this will put lexical mistakes into the mistakes list
		
		// parse
		ParseTree tree = parseTokenStream(tkn, rules_.getBiRules(), rules_.getUnRules(), mistakes);
		
		// visit, checking for semantic issues
		// TODO change this to check for agreement problems
		visit(tree, mistakes);
		
		System.out.println(tree); // For testing only TODO
		
		// the response is created, and returned. 
		return Response.responseFactory(tree, sentence);
	}
	
	public Parser(RuleSet rules) {
		rules_ = rules;
	}
	
	//TODO parse tree could contain a list of mistakes... and a boolean telling whether the 
	// semantic visitor would be able to make sense of it...
	
	// PRIVATE METHODS
	
	// This method should take a list of mistakes; otherwise extras might be carried over. 
	private ParseTree parseTokenStream(Tokenizer tkn, HashMap<Pos, List<BinarySyntacticRule>> rules, 
							HashMap<Pos, UnarySyntacticRule> unrules, List<Mistake> mistakes) {
		
		Node node = null;
		Stack<Node> prev = new Stack<Node>();
		
		//TODO mistakes aren't being checked yet. 
		
		if (tkn.hasNext()) {
			node = new LeafNode(tkn.getNextToken());
		} else {
			// TODO error case
		}
		
		boolean advanced = true;
		
		// TODO go through, at each step if curr & next can combine by some rule,
		while (tkn.hasNext() || !prev.empty()) {
			if (!advanced) {
				break;
			}
			
			advanced = false;
			
			System.out.println(node);
			// Do below, but for prev and curr = node
			if (!prev.empty()) {
				System.out.println("left " + node.getPos());
				
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
				System.out.println("right " + node.getPos());
				// TODO this fails if no rule matches. Need getOrElse, or something similar...
				List<BinarySyntacticRule> lst = rules.get(node.getPos());
				if (lst != null) {
					for (BinarySyntacticRule r : lst) {
						if (r.matches(node, new LeafNode(tkn.peek()))) {
							// replace contituent
							node = r.combine(node, new LeafNode(tkn.getNextToken()));
						
							// advance
							advanced = true;
						
							// TODO break?
							break;
						}
					}
				}
				
			/*	if (unrules.containsKey(node.getPos())) {
					System.out.println("unary");
					
					node = unrules.get(node.getPos()).combine(node);
					advanced = true;
				} */
			}
			
			// TODO unary rules go in here somewhere...
			// TODO TODO TODO this is very suspect! I'm not sure if it will work...
			if (!advanced && unrules.containsKey(node.getPos())) {
				System.out.println("unary");
				
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
		
		
		return new ParseTree(node, mistakes);
	}
	
	// Takes a parse tree; visits nodes and adds semantic mistakes. 
	private void visit(ParseTree tree, List<Mistake> mistakes) {
		// TODO fill out, currently does nothing. 
		tree.visit(mistakes);
	}

}
