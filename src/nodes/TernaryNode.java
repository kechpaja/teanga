package nodes;

import parsing.*;

import java.util.*;

public class TernaryNode extends Node {
	
	// children
	private Node left_;
	private Node middle_;
	private Node right_;
	
	// GETTERS
	
	public Node getLeft() {
		return left_;
	}
	
	public Node getMiddle() {
		return middle_;
	}
	
	public Node getRight() {
		return right_;
	}
	
	
	// Constructor
	public TernaryNode(Node left, Node middle, Node right, Pos pos, NumMarker num, Case c, Tense t) {
		left_ = left;
		middle_ = middle;
		right_ = right;
		pos_ = pos;
		number_ = num;
		cm_ = c;
		t_ = t;
	}

	@Override
	public int getLeftIndex() {
		return left_.getLeftIndex();
	}

	@Override
	public int getRightIndex() {
		return right_.getRightIndex();
	}
	
	// toString
	public String toString() {
		return "(" + pos_ + " " + left_ + " " + middle_ + " " + right_ + ")";
	}
	
	// visit method
	public void visit(List<Mistake> mistakes) {
		if (!left_.agreesInCase(right_)) {
			mistakes.add(new FatalMistake(getLeftIndex(), getRightIndex(), "Error - Case Agreement"));
		}
		
		// recur
		left_.visit(mistakes);
		middle_.visit(mistakes);
		right_.visit(mistakes);
	}

}
