package parsing;

public enum Case {
	
	NOMINATIVE,
	GENITIVE,
	ACCUSATIVE; // TODO genitive for correlatives?
	
	public String toString() {
		switch (this) {
		case NOMINATIVE: return "NOMINATIVE";
		case GENITIVE: return "GENITIVE";
		case ACCUSATIVE: return "ACCUSATIVE";
		default: return "BAD";
		}
	}

}
