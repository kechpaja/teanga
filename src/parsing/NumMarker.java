package parsing;

public enum NumMarker {
	
	SINGULAR,
	PLURAL;
	
	public String toString() {
		switch (this) {
		case SINGULAR: return "SINGULAR";
		case PLURAL: return "PLURAL";
		default: return "BAD";
		}
	}

}
