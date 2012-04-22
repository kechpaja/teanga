package parsing;

public class Token {
	
	private String name_; // this token's face/phonetic value
	//private Type pos_; // this token's part of speech
	
	private Pos pos_;
	private NumMarker number_;
	private Case cm_;
	private Tense t_;
	
	
	
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
	
	protected Token(String name, Pos pos, NumMarker num, Case c, Tense tense) {
		name_ = name;
		pos_ = pos;
		number_ = num;
		cm_ = c;
		t_ = tense;
	}
	
	public String toString() {
		return pos_.toString() + " " + number_ + " " + cm_ + " " + t_;
	}

}
