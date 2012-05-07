package parsing;

public enum NumMarker {
	
	// numbers
	SINGULAR,
	PLURAL;
	
	// toString()
	public String toString() {
		switch (this) {
		case SINGULAR: return "SINGULAR";
		case PLURAL: return "PLURAL";
		default: return "BAD";
		}
	}

}
