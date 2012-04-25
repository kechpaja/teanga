package parsing;

public class FatalMistake extends Mistake {
	
	// This class is used for mistakes that are truly fatal - that is, they are completely forbidden. 
	public FatalMistake(int startindex, int endindex, String message) {
		super(startindex, endindex, message);
	}
	
	// override
	public boolean isFatal() {
		return true;
	}

}
