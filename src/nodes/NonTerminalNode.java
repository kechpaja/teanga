package nodes;

import java.util.List;

import parsing.*;
import rules.AgreementRule;

public class NonTerminalNode extends Node {
	
	private Node head_; // every node must have a head
	private Node tail_; // the tail, however, may be null. 
	// head and tail are just the left and right children of the node.
	// They have no relation to the linguistic notion of the head of a phrase. 
	
	
	// Getters
	public Node getHead() {
		return head_;
	}
	
	public Node getTail() {
		return tail_;
	}
	
//	public Type getType() {
	//	return type_;
//	}
	
	// Constructor
	public NonTerminalNode(Node head, Node tail, Pos pos, NumMarker num, Case c, Tense tense) {
		head_ = head;
		tail_ = tail;
		pos_ = pos;
		number_ = num;
		cm_ = c;
		t_ = tense;
	}
	
	// toString()
	public String toString() {
		return "(" + pos_ + " " + head_ + (tail_ == null ? "" : " " + tail_) + ")";
		//return "(" + pos_ + " " + number_ + " " + cm_ + " " + t_ + ": " + head_ + " " + tail_ + ")";
	}
	
	// methods to return left and right indices
	public int getLeftIndex() {
		return head_.getLeftIndex();
	}
	
	public int getRightIndex() {
		if (tail_ == null) {
			return head_.getRightIndex();
		} else {
			return tail_.getRightIndex();
		}
	}
	
	// visit method
	public void visit(List<Mistake> mistakes) {
		// check for errors at this level
		if (!head_.agreesInCase(tail_)) {
			mistakes.add(new FatalMistake(getLeftIndex(), getRightIndex(), "Error - Case Agreement"));
		}
		
		if (!head_.agreesInNumber(tail_)) {
			mistakes.add(new FatalMistake(getLeftIndex(), getRightIndex(), "Error - Number Agreement"));
		}
		
		if (!head_.agreesInTense(tail_)) {
			mistakes.add(new FatalMistake(getLeftIndex(), getRightIndex(), "Error - Tense Agreement"));
		}
		
		// recur on children
		head_.visit(mistakes);
		if (tail_ != null) {
			tail_.visit(mistakes);
		}
	}

}
