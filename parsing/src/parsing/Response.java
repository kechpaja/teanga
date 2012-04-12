package parsing;

import java.util.*;

public class Response {
	
	// fields
	private boolean correct_;
	private List<Mistake> mistakes_;
	private String sentence_;
	
	// getters

	// returns true if the given sentence was correctly formed. 
	public boolean isCorrect() {
		return correct_;
	}
	
	// Returns the list of mistakes.
	// Guaranteed to return a List<Mistake> if isCorrect() returns false.
	// Behavior is undefined (aka may be null) if isCorrect() returns true. 
	public List<Mistake> getMistakes() {
		return mistakes_;
	}
	
	// gets the original sentence, as a String
	public String getSentence() {
		return sentence_;
	}
	
	// Factory method
	static protected Response responseFactory(ParseTree tree, String sentence) {
		Response response = new Response();
		response.correct_ = tree.isCorrect();
		response.mistakes_ = tree.getMistakes();
		response.sentence_ = sentence;
		return response;
		
	}
	
	// Factory method (depr.)
	static protected Response responseFactory(boolean correct, List<Mistake> mistakes, String sentence) {
		Response response = new Response();
		response.correct_ = correct;
		response.mistakes_ = mistakes;
		response.sentence_ = sentence;
		return response;
	}

}
