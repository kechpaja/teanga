package parsing;

public enum Case {
	
	// cases
	NOMINATIVE,
	ACCUSATIVE;
	
	//toString
	public String toString() {
		switch (this) {
		case NOMINATIVE: return "NOMINATIVE";
		case ACCUSATIVE: return "ACCUSATIVE";
		default: return "BAD";
		}
	}

}
