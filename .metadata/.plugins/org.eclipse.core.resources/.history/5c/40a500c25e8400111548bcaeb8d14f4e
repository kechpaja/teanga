package nodes;

import parsing.Token;

public class LeafNode extends Node {
	
	private String name_; // the name of this node
	
	public String getName() {
		return name_;
	}

	
	// Constructor
	public LeafNode(Token t) {
		name_ = t.getName();
		type_ = t.getPartOfSpeech();
	}
	
	public String toString() {
		return "(" + type_.toString() + ": " + name_ + ")";
	}
	
}
