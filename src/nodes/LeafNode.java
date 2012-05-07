package nodes;

import java.util.List;

import parsing.Mistake;
import parsing.Token;

public class LeafNode extends Node {
	
	private String name_; // the name of this node
	private int leftindex_;
	private int rightindex_;
	
	// GETTERS
	
	public String getName() {
		return name_;
	}
	
	public int getLeftIndex() {
		return leftindex_;
	}
	
	public int getRightIndex() {
		return rightindex_;
	}
	
	// Constructor
	public LeafNode(Token t) {
		name_ = t.getName();
		pos_ = t.getPos();
		number_ = t.getNumMarker();
		cm_ = t.getCase();
		t_ = t.getTense();
		leftindex_ = t.getLeftIndex();
		rightindex_ = t.getRightIndex();
	}
	
	// toString
	public String toString() {
		return "(" + pos_ + " " + number_ + " " + cm_ + " " + t_ + ": " + name_ + ")";
	}
	
	// visit method
	public void visit(List<Mistake> mistakes) {
		// Does nothing - no syntactic errors could occur at this level
	}
	
}
