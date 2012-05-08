package parsing;

import java.util.*;
import nodes.*;

public class ParseTree {
	
	protected Node node_;
	protected List<Mistake> mistakes_; // we store this list of mistakes here
	protected Stack<Node> prev_;
	protected boolean atLeastOneFatal_ = false;
	
	
	// Getter for mistakes
	protected List<Mistake> getMistakes() {
		return mistakes_;
	}
	
	// Getter for correctness
	protected boolean isCorrect() {
		return mistakes_.isEmpty();
	}
	
	// Getter for fatal mistake
	protected boolean atLeastOneFatal() {
		return atLeastOneFatal_;
	}
	
	// Constructor
	protected ParseTree(Node node, List<Mistake> mistakes, Stack<Node> prev) {
		node_ = node;
		mistakes_ = mistakes;
		prev_ = prev;
		for (Mistake m : mistakes) {
			if (m.isFatal()) {
				atLeastOneFatal_ = true;
				break;
			}
		}
	}
	
	// toString, for testing
	public String toString() {
		String acc = "";
		acc += node_ + "\n";
		for (Node node : prev_) {
			acc += node + "\n";
		}
		return acc;
	}
	
	// visit method, checking for correctness in agreement
	public void visit(List<Mistake> mistakes) {
		if (node_ == null) {
			return;
		}
		
		node_.visit(mistakes); 
		
		// visit things in prev!
		// they shouldn't be there, but are worth checking. 
		for (Node node : prev_) {
			node.visit(mistakes);
			mistakes.add(new FatalMistake(node.getLeftIndex(), node.getRightIndex(), "The sentence cannot be fully parsed; the error is somewhere in here."));
		}
	}

}
