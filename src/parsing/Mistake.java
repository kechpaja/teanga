package parsing;

public interface Mistake {
	
	// gets the start index of the error in the given sentence
	public int getStartIndex();
	
	// gets the end index of the error in the given sentence
	public int getEndIndex();
	
	// gets the message - a short description of what went wrong. 
	public String getMessage();

}
