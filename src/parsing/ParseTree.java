package parsing;

import java.util.*;

import rules.AgreementRule;
import nodes.*;

public class ParseTree {
	
	// TODO this should hold a sentence; there's nothing else that can occur at this level. 
	// Except for a few basic interjections. 
	
	// TopLevelNode encompasses "sentence" (S), as well as some other things
	// (interjections like "yes"/"no", which are valid at this level but not really sentences,
	// there will be a few other things I'm sure...)
	protected Node node_;
	protected List<Mistake> mistakes_; // we store this list of mistakes here
	protected Stack<Node> prev_;
	protected boolean atLeastOneFatal_;
	
	
	// Getter for mistakes
	protected List<Mistake> getMistakes() {
		return mistakes_;
	}
	
	// Getter for correctness
	protected boolean isCorrect() {
		return mistakes_.isEmpty();
	}
	
	// Getter for fatal mstake
	protected boolean atLeastOneFatal() {
		return atLeastOneFatal_;
	}
	
	// Constructor
	protected ParseTree(Node node, List<Mistake> mistakes, Stack<Node> prev) {
		node_ = node;
		mistakes_ = mistakes;
		prev_ = prev;
	}
	
	// toString, for testing
	public String toString() {
		return node_.toString();
	}
	
	// visit method, checking for correctness in agreement
	public void visit(List<Mistake> mistakes) {
		node_.visit(mistakes); // TODO visit everything in all trees as well. 
		
		// TODO visit things in prev!
	}

}
