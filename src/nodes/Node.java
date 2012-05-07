package nodes;

import java.util.List;
import parsing.*;

public abstract class Node {

	//protected Type type_;
	
	// these should be visible only to subclasses
	Pos pos_;
	NumMarker number_;
	Case cm_;
	Tense t_;
	
	// GETTERS

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
	
	public abstract int getLeftIndex();
	
	public abstract int getRightIndex();
	
	public void visit(List<Mistake> mistakes) {
		// this method does nothing, will be overriden. 
	}
	
	// Agreement methods
	
	// check for case agreement
	public boolean agreesInCase(Node node) {
		if (node == null) {
			return true;
		}
		
		// now for the real stuff...
		return cm_ == null || node.getCase() == null || cm_ == node.getCase();
	}
	
	// check for number agreement
	public boolean agreesInNumber(Node node) {
		if (node == null) {
			return true;
		}
		
		return number_ == null || node.getNumMarker() == null || number_ == node.getNumMarker();
	}
	
	// check for tense agreement
	public boolean agreesInTense(Node node) {
		if (node == null) {
			return true;
		}
		
		return t_ == null || node.getTense() == null || node.getTense() == Tense.INFINITIVE 
							|| t_ == Tense.INFINITIVE;
	}

}
