package rules;

import nodes.*;
import parsing.*;

public class UnarySyntacticRule extends SyntacticRule {
	
	// type, and type of child node
	private Pos child_;
	private Pos pos_;
	
	
	// matching test
	public boolean matches(Node n) {
		return n.getPos() == pos_;
	}
	
	// combine method
	public NonTerminalNode combine(Node n) {
		return new NonTerminalNode(n, null, pos_, n.getNumMarker(), n.getCase(), n.getTense());
	}
	
	// constructor
	public UnarySyntacticRule(Pos child, Pos pos) {
		child_ = child;
		pos_ = pos;
	}

}
