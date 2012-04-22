package nodes;

import java.util.List;

import parsing.*;
import rules.AgreementRule;

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
	
	public void visit(List<AgreementRule> rules, List<Mistake> mistakes) {
		// TODO this method does nothing, will be overriden. 
	}
	
	// Agreement methods
	public boolean agreesInCase(Node node) {
		if (node == null) {
			return true;
		}
		
		// now for the real stuff...
		return cm_ == null || node.getCase() == null || cm_ == node.getCase();
	}
	
	public boolean agreesInNumber(Node node) {
		if (node == null) {
			return true;
		}
		
		return number_ == null || node.getNumMarker() == null || number_ == node.getNumMarker();
	}
	
	public boolean agreesInTense(Node node) {
		if (node == null) {
			return true;
		}
		
		return t_ == null || node.getTense() == null || t_ == node.getTense();
	}

}
