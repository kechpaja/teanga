package nodes;

import parsing.Type;

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
	public NonTerminalNode(Node head, Node tail, Type type) {
		head_ = head;
		tail_ = tail;
		type_ = type;
	}
	
	// toString()
	public String toString() {
		return "(" + type_ + ": " + head_.toString() + " " + tail_.toString() + ")";
	}

}
