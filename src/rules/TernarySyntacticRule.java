package rules;

import nodes.*;
import parsing.*;

public class TernarySyntacticRule extends SyntacticRule {
	
	// rule itself
	private Pos pos_;
	
	private Pos left_;
	private Pos middle_;
	private Pos right_;
	
	
	// matching method
	public boolean matches(Node n1, Node n2, Node n3) {
		return n1.getPos() == left_ && n2.getPos() == middle_ && n3.getPos() == right_;
	}
	
	
	// combining method
	public TernaryNode combine(Node n1, Node n2, Node n3) {
		Case c = null;
		NumMarker number = null;
		Tense t = null;
		if (n1.getCase() == n3.getCase()) {
			c = n1.getCase();
		}
		
		if (n1.getNumMarker() != null && n3.getNumMarker() != null) {
			number = NumMarker.PLURAL;
		}
		
		if (n1.getTense() == n3.getTense()) {
			t = n1.getTense();
		}
		
		return new TernaryNode(n1, n2, n3, pos_, number, c, t);
	}
	
	
	
	
	// constructor
	public TernarySyntacticRule(Pos left, Pos middle, Pos right, Pos pos) {
		pos_ = pos;
		left_ = left;
		middle_ = middle;
		right_ = right;
	}

}
