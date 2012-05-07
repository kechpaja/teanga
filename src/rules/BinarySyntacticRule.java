package rules;

import parsing.*;
import nodes.*;

public class BinarySyntacticRule extends SyntacticRule {
	
	// rule itself
	private Pos head_;
	private Pos tail_;
	private Pos pos_;
	
	
	// matching method
	public boolean matches(Node n1, Node n2) {
		// This will utilize pos only; it won't check for agreement. 
		// That will be handled in the grammatical visitor. 
		return n1.getPos() == head_ && n2.getPos() == tail_;
	}
	
	// combining method
	public NonTerminalNode combine(Node n1, Node n2) {
		Case c = null;
		NumMarker number = null;
		Tense t = null; // TODO do we want to handle case here?
		if (n1.getCase() == n2.getCase()) {
			c = n1.getCase();
		}
		
		// check number
		if (n1.getNumMarker() == n2.getNumMarker()){
			number = n2.getNumMarker();
		}
		
		// check tense
		if (n1.getTense() == null) {
			t = n2.getTense();
		} else {
			t = n1.getTense();
		}
		
		// carry case up to top of DP
		if (head_ == Pos.ARTICLE) {
			c = n2.getCase();
		}
		
		return new NonTerminalNode(n1, n2, pos_, number, c, t);
	}
	
	// constructor
	public BinarySyntacticRule(Pos head, Pos tail, Pos pos) {
		head_ = head;
		tail_ = tail;
		pos_ = pos;
	}
}
