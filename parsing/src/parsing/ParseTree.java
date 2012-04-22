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
	
	
	// Getter for mistakes
	protected List<Mistake> getMistakes() {
		return mistakes_;
	}
	
	// Getter for correctness
	protected boolean isCorrect() {
		return mistakes_.isEmpty();
	}
	
	// Constructor
	protected ParseTree(Node node, List<Mistake> mistakes) {
		node_ = node;
		mistakes_ = mistakes;
	}
	
	// toString, for testing
	public String toString() {
		return node_.toString();
	}
	
	// TODO visit method, checking for correctness in agreement
	public void visit(List<AgreementRule> rules, List<Mistake> mistakes) {
		node_.visit(rules, mistakes);
	}

}
