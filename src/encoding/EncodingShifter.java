package encoding;

public class EncodingShifter {

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
			if (ch0 == 'c')
				ret += "ĉ";
			else if (ch0 == 'C')
				ret += "Ĉ";
			else if (ch0 == 'g')
				ret += "ĝ";
			else if (ch0 == 'G')
				ret += "Ĝ";
			else if (ch0 == 'h')
				ret += "ĥ";
			else if (ch0 == 'H')
				ret += "Ĥ";
			else if (ch0 == 'j')
				ret += "ĵ";
			else if (ch0 == 'J')
				ret += "Ĵ";
			else if (ch0 == 's')
				ret += "ŝ";
			else if (ch0 == 'S')
				ret += "Ŝ";
			else if (ch0 == 'u')
				ret += "ŭ";
			else if (ch0 == 'U')
				ret += "Ŭ";
			else
				ret = text;
		} else {
			// prepare to return original input
			ret = text;
		}
		
		return ret;
	}

}
