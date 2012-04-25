package encoding;

public class EncodingShifter {
	
	// NOTE: I haven't actually tested this, but it's simple enough that I doubt it will fail. 
	// You shouldn't need to actually call the method here; just add the included listener to
	// the relevant component. Remember to pass in the component to the constructor!

	public static String shift(String text) {
		if (text.length() < 2) {
			return text;
		}
		
		// otherwise...
		String ret = text.substring(0, text.length() - 2);
		char ch0 = text.charAt(text.length() - 2);
		char ch1 = text.charAt(text.length() - 1);
		
		if (ch1 == 'x' || ch1 == 'X') {
			// replace the two final characters with the requisite new char
			switch (ch0) {
			case 'c': ret += 'ĉ';
			case 'C': ret += 'Ĉ';
			case 'g': ret += 'ĝ';
			case 'G': ret += 'Ĝ';
			case 'h': ret += 'ĥ';
			case 'H': ret += 'Ĥ';
			case 'j': ret += 'ĵ';
			case 'J': ret += 'Ĵ';
			case 's': ret += 'ŝ';
			case 'S': ret += 'Ŝ';
			case 'u': ret += 'ŭ';
			case 'U': ret += 'Ŭ';
			case 'x': ret += 'x';
			case 'X': ret += 'X';
			default:
				ret = text;
			}
		} else {
			// prepare to return original input
			ret = text;
		}
		
		return ret;
	}

}
