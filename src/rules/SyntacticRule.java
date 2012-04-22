package rules;

import parsing.*;
import nodes.*;

public class SyntacticRule extends Rule {
	
	// TODO update this 
	
	// rule itself
	private Pos head_;
	private Pos tail_;
	
	private Pos pos_;
	private NumMarker number_;
	private Case cm_;
	private Tense t_;
	
	
	// matching method
	public boolean matches(Node n1, Node n2) {
		// This will utilize pos only; it won't check for agreement. 
		// That will be handled in the grammatical visitor. 
		return n1.getPos() == head_ && n2.getPos() == tail_;
	}
	
	// combining method
	public NonTerminalNode combine(Node n1, Node n2) {
		Case c = cm_;
		NumMarker number = number_;
		Tense t = t_;
		if (n1.getCase() == n2.getCase()) {
			c = n1.getCase();
		}
		if (n1.getNumMarker() == n2.getNumMarker()) {
			number = n1.getNumMarker();
		}
		if (n1.getTense() == n2.getTense()) {
			t = n1.getTense();
		}
		
		return new NonTerminalNode(n1, n2, pos_, number, c, t);
	}
	
	// Constructor
	public SyntacticRule(Pos head, Pos tail, Pos pos, NumMarker num, Case c, Tense t) {
		head_ = head;
		tail_ = tail;
		pos_ = pos;
		number_ = num;
		cm_ = c;
		t_ = t;
	}

}
