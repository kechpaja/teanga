package parsing;

public abstract class Mistake {
	
	private final int startindex_;
	private final int endindex_;
	private final String message_;
	
	public Mistake(int startindex, int endindex, String message) {
		startindex_ = startindex;
		endindex_ = endindex;
		message_ = message;
	}
	
	// gets the start index of the error in the given sentence
	public int getStartIndex() {
		return startindex_;
	}
	
	// gets the end index of the error in the given sentence
	public int getEndIndex() {
		return endindex_;
	}
	
	// gets the message - a short description of what went wrong. 
	public String getMessage() {
		return message_;
	}

	// toString
	public String toString() {
		return startindex_ + " - " + endindex_ + ": " + message_;
	}
	
	// will be overridden in fatal class...
	public boolean isFatal() {
		return false;
	}
}
