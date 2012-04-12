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
		//type_ = t.getPartOfSpeech();
		pos_ = t.getPos();
		number_ = t.getNumMarker();
		cm_ = t.getCase();
		t_ = t.getTense();
	}
	
	public String toString() {
		return "(" + pos_ + " " + number_ + " " + cm_ + " " + t_ + ": " + name_ + ")";
	}
	
}
