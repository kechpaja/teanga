package parsing;

public enum Tense {
	
	// list of tenses, including infinitive and imperative
	PRESENT,
	PAST,
	FUTURE,
	CONDITIONAL,
	IMPERATIVE,
	INFINITIVE;
	
	// toString
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
