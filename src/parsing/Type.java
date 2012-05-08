package parsing;

public enum Type {
	
	// OLD AND DEPRECATED
	
	S,
	VP,
	NPNSG,
	NPASG,
	NPNPL,
	NPAPL,
	DPN,
	DPA,
	PP,
	
	// The parts of speech
	// Note: these may or may not be familiar; they encode 
	// tense and case as well as traditional POS
	//TODO these are far from comprehensive, but they will do for now. Adding more
	// is a trivial exercise.  {
		NOMSG,		// Noun in the nominative singular
		ACCSG,
		NOMPL,
		ACCPL,
		
		PRES,
		PAST,
		FUT,
		COND,
		IMP,
		INF,
		
		ADJNSG,
		ADJASG,
		ADJNPL,
		ADJAPL,
		
		ADV,
		ART,
		PREP,
		
		BAD;			// Not a known part of speech - this will be an error
	
	// toString()
	public String toString() {
		switch (this) {
		case S:
			return "S";
		case VP:
			return "VP";
		case NPNSG:
			return "NPNSG";
		case NPASG:
			return "NPASG";
		case NPNPL:
			return "NPNPL";
		case NPAPL:
			return "NPAPL";
		case DPN:
			return "DPN";
		case DPA:
			return "DPA";
		case PP:
			return "PP";
		
		case NOMSG:
			return "NOMSG";
		case ACCSG:
			return "ACCSG";
		case NOMPL:
			return "NOMPL";
		case ACCPL:
			return "ACCPL";
			
		case PRES:
			return "PRES";
		case PAST:
			return "PAST";
		case FUT:
			return "FUT";
		case COND:
			return "COND";
		case IMP:
			return "IMP";
		case INF:
			return "INF";
			
		case ADJNSG:
			return "ADJNSG";
		case ADJASG:
			return "ADJASG";
		case ADJNPL:
			return "ADJNPL";
		case ADJAPL:
			return "ADJAPL";
			
		case ADV:
			return "ADV";
		case ART:
			return "ART";
		case PREP:
			return "PREP";
		
		case BAD:
			return "BAD";
		default:
			return "BAD";
		}
	}
	
}
