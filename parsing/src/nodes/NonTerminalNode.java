package nodes;

import parsing.*;

public class NonTerminalNode extends Node {
	
	private Node head_; // every node must have a head
	private Node tail_; // the tail, however, may be null. 
//	private Type type_;
	
	
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
		return "(" + pos_ + " " + number_ + " " + cm_ + " " + t_ + ": " + head_.toString() + " " + tail_.toString() + ")";
	}

}
