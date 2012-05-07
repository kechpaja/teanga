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
	CONJUNCTION,
	
	// "higher-level" parts of speech
	NP,
	VP,
	SVP,
	PP,
	DP,
	ADJP,
	ADVP,
	S,
	
	BAD;
	
	// toString for testing
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
		case CONJUNCTION: return "CONJUNCTION";
		case NP: return "NP";
		case VP: return "VP";
		case SVP: return "SVP";
		case PP: return "PP";
		case DP: return "DP";
		case ADJP: return "ADJP";
		case ADVP: return "ADVP";
		case S: return "S";
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
		} else if (str.equals("CONJ") || str.equals("C") || str.equals("CONJUNCTION")) {
			return CONJUNCTION;
		}
		
		else if (str.equals("NP")) {
			return NP;
		} else if (str.equals("VP")) {
			return VP;
		} else if (str.equals("SVP")) {
			return SVP;
		} else if (str.equals("PP")) {
			return PP;
		} else if (str.equals("DP")) {
			return DP;
		} else if (str.equals("ADJP")) {
			return ADJP;
		} else if (str.equals("ADVP")) {
			return ADVP;
		} else if (str.equals("S")) {
			return S;
		}
		
		
		else {
			return BAD; // This is an error
		}
	}

}
