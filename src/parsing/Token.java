package parsing;

public class Token {
	
	private String name_; // this token's face/phonetic value
	private Pos pos_;
	private NumMarker number_;
	private Case cm_;
	private Tense t_;
	private int leftindex_;
	private int rightindex_;
	
	
	
	// GETTERS
	public String getName() {
		return name_;
	}
	
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
	
	public int getLeftIndex() {
		return leftindex_;
	}
	
	public int getRightIndex() {
		return rightindex_;
	}
	
	// constructor
	protected Token(String name, Pos pos, NumMarker num, Case c, Tense tense, int leftindex, int rightindex) {
		name_ = name;
		pos_ = pos;
		number_ = num;
		cm_ = c;
		t_ = tense;
		leftindex_ = leftindex;
		rightindex_ = rightindex;
	}
	
	// toString
	public String toString() {
		return pos_.toString() + " " + number_ + " " + cm_ + " " + t_ + " from " + leftindex_ + " to " + rightindex_;
	}

}
