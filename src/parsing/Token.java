package parsing;

public class Token {
	
	private String name_; // this token's face/phonetic value
	private Type pos_; // this token's part of speech
	
	
	// GETTERS
	public String getName() {
		return name_;
	}
	
	public Type getPartOfSpeech() {
		return pos_;
	}
	
	protected Token(String name, Type pos) {
		name_ = name;
		pos_ = pos;
	}
	
	public String toString() {
		return pos_.toString();
	}

}
