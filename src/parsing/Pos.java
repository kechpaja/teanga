package parsing;

public enum Pos {
	
	NOUN,
	VERB,
	ADJECTIVE,
	ADVERB,
	PRONOUN,
	PREPOSITION,
	ARTICLE,
	PUNCTUATION,
	BAD;
	// Correlatives?
	// TODO more - higher-level pos's as well. 
	
	
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

	// converts a string back into a POS
	public static Pos strToPos(String string) {
		String str = string.toUpperCase();
		if (str.equals("N") || str.equals("NOUN")) {
			return NOUN;
		} else if (str.equals("V") || str.equals("VERB")) {
			return VERB;
		} else if (str.equals("ADJ") || str.equals("ADJECTIVE")) {
			return ADJECTIVE;
		} else if (str.equals("ADV") || str.equals("ADVERB")) {
			return ADVERB;
		} else if (str.equals("P") || str.equals("PRONOUN")) {
			return PRONOUN;
		} else if (str.equals("PREP") || str.equals("PREPOSITION")) {
			return PREPOSITION;
		} else if (str.equals("ART") || str.equals("ARTICLE")) {
			return ARTICLE;
		} else if (str.equals("PUNCT") || str.equals("PUNCTUATION")) {
			return PUNCTUATION;
		} 
		
		
		else {
			// TODO some error case...
			return BAD; // This is an error
		}
		
		
		
		// TODO write this!
		
	}

}
