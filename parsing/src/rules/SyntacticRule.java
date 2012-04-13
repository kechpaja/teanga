package rules;

import parsing.Type;
import nodes.*;

public class SyntacticRule extends Rule {
	
	// TODO update this 
	
	// rule itself
	private Type head_;
	private Type tail_;
	private Type becomes_;
	
	
	// matching method
	public boolean matches(Node n1, Node n2) {
		return n1.getType() == head_ && n2.getType() == tail_;
	}
	
	// combining method
	public NonTerminalNode combine(Node n1, Node n2) {
		return new NonTerminalNode(n1, n2, pos_, number_, cm_, t_);
	}
	
	// Constructor
	public SyntacticRule(Type head, Type tail, Type becomes) {
		head_ = head;
		tail_ = tail;
		becomes_ = becomes;
	}

}
