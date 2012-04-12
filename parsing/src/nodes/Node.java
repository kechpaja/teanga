package nodes;

import parsing.*;

public abstract class Node {

	protected Type type_;
	
	// TODO update this to work with new typing system
	// these should be visible only to subclasses
	Pos pos_;
	NumMarker number_;
	Case cm_;
	Tense t_;
	
	
	// getters 
	
	public Type getType() {
		return type_;
	}
	
	// new getters

	public Pos getPos() {
		return pos_;
	}
	
	public NumMarker getNumMarker() {
		return number_;
	}
	
	public Case getCase() {
		return cm_;
	}
	
	public Tense getTense() {
		return t_;
	}

}
