package parsing;

public enum Pos {
	
	NOUN,
	VERB,
	ADJECTIVE,
	ADVERB,
	PRONOUN,
	PREPOSITION,
	ARTICLE,
	PUNCTUATION;
	// Correlatives?
	
	
	public String toString() {
		switch (this) {
		case NOUN: return "NOUN";
		case VERB: return "VERB";
		case ADJECTIVE: return "ADJECTIVE";
		case ADVERB: return "ADVERB";
		case PRONOUN: return "PRONOUN";
		case PREPOSITION: return "PREPOSITION";
		case ARTICLE: return "ARTICLE";
		case PUNCTUATION: return "PUNCTUATION";
		default: return "BAD";
		}
	}

}
