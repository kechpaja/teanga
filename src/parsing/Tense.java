package parsing;

public enum Tense {
	
	PRESENT,
	PAST,
	FUTURE,
	CONDITIONAL,
	IMPERATIVE,
	INFINITIVE;
	
	// TODO imperative; infinitive?
	
	public String toString() {
		switch (this) {
		case PRESENT: return "PRESENT";
		case PAST: return "PAST";
		case FUTURE: return "FUTURE";
		case CONDITIONAL: return "CONDITIONAL";
		case IMPERATIVE: return "IMPERATIVE";
		case INFINITIVE: return "INFINITIVE";
		default: return "BAD";
		}
	}

}