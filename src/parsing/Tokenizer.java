package parsing;

import java.util.*;

import annie.MyDictionary;

public class Tokenizer {
	
	/**
	 * This tokenizer will be a little bit different from the one used in our
	 * compiler for 31 - it will tokenize the entire string at once, and return it 
	 * as a list of tokens. Since this parser is to be called on single sentences, 
	 * there should never be so many tokens that this is a problem. 
	 */
	
	private String sentence_; // the sentence to be tokenized
	private MyDictionary dict_; // the dictionary
	private List<Token> tokens_;
	
	// initialize the tokenizer
	public void init(List<Mistake> mistakes) {
		tokens_ = new LinkedList<Token>();
		
		if (sentence_.equals("")) {
			return;
		}
		
		// create set of prepositions
		HashSet<String> setA = new HashSet<String>();
		HashSet<String> setB = new HashSet<String>();
		setA.add("al");
		setA.add("ankaŭ");
		setA.add("anstataŭ");
		setA.add("antaŭ");
		setA.add("apud");
		setA.add("ĉe");
		setA.add("ĉirkaŭ");
		setA.add("da");
		setA.add("de");
		setA.add("dum");
		setA.add("ekde");
		setA.add("ekster");
		setA.add("eksteren");
		setA.add("el");
		setB.add("en");
		setA.add("ĝis");
		setB.add("inter");
		setA.add("kontraû");
		setA.add("krom");
		setA.add("kun");
		setA.add("laŭ");
		setA.add("malgraŭ");
		setA.add("malkiel");
		setA.add("malsupren");
		setA.add("ol");
		setA.add("per");
		setA.add("plus");
		setA.add("po");
		setA.add("por");
		setA.add("post");
		setA.add("preter");
		setB.add("pri");
		setA.add("pro");
		setA.add("sen");
		setB.add("sub");
		setA.add("suben");
		setB.add("super");
		setB.add("sur");
		setA.add("tra");
		setA.add("trans");
		// fill it up
		
		HashSet<String> setNum = new HashSet<String>();
		setNum.add("nul");
		setNum.add("du");
		setNum.add("tri");
		setNum.add("kvar");
		setNum.add("kvin");
		setNum.add("ses");
		setNum.add("sep");
		setNum.add("ok");
		setNum.add("naŭ");
		setNum.add("dek");
		setNum.add("dudek");
		setNum.add("tridek");
		setNum.add("kvardek");
		setNum.add("kvindek");
		setNum.add("sesdek");
		setNum.add("sepdek");
		setNum.add("okdek");
		setNum.add("naŭdek");
		setNum.add("cent");
		setNum.add("mil");
		
		
		// split on whitespace. 
		// for each word, check ending, and create token and put in list. 
		String punct = null;
		if (sentence_.substring(sentence_.length() - 1).matches("\\p{Punct}")) {
			punct = sentence_.substring(sentence_.length() - 1);
		}
		
		sentence_ = sentence_.replaceAll("\\p{Punct}", "");
		String[] words = sentence_.toLowerCase().split("\\s");
		Token tk = null;
		int left = 0;
		for (String s : words) {
			// check pos from ending
			Pos pos = null;
			NumMarker num = null;
			Case c = null;
			Tense tense = null;
			if (s.equals("")) {
				left++;
				continue;
			}
			
			// check for weird stuff
			else if (s.equals("la")) {
				pos = Pos.ARTICLE;
			} else if (s.equals("ja")) {
				pos = Pos.ADVERB;
			} else if (s.equals("saluton") || s.equals("adiaŭ") || s.equals("pli") || s.equals("jes")) {
				pos = Pos.ADVERB;
			} else if (s.equals("kaj") || s.equals("aŭ")) {
				pos = Pos.CONJUNCTION;
			} else if (s.equals("unu")) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.SINGULAR;
			} else if (setA.contains(s)) {
				pos = Pos.PREPOSITION;
				c = Case.NOMINATIVE;
			} else if (setB.contains(s)) {
				pos = Pos.PREPOSITION;
			}
			
			// Match personal pronouns
			else if (s.equals("mi") || s.equals("ni") || s.equals("ci") || s.equals("vi") || s.equals("ri")
					|| s.equals("li") || s.equals("ŝi") || s.equals("ĝi") || s.equals("ili")) {
				pos = Pos.PRONOUN;
				c = Case.NOMINATIVE;
			} else if (s.equals("min") || s.equals("nin") || s.equals("cin") || s.equals("vin")
					|| s.equals("lin") || s.equals("ŝin") || s.equals("ĝin") || s.equals("ilin")) {
				pos = Pos.PRONOUN;
				c = Case.ACCUSATIVE;
			}
			
			// numbers
			else if (setNum.contains(s)) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.PLURAL;
			}
			
			// Match correlatives
			else if (s.equals("kial") || s.equals("tial") || s.equals("ial") || s.equals("ĉial") || s.equals("nenial")) {
				pos = Pos.ADVERB;
			} else if (s.equals("kiel") || s.equals("tiel") || s.equals("iel") || s.equals("ĉiel") || s.equals("neniel")) {
				pos = Pos.ADVERB;
			} else if (s.equals("kiam") || s.equals("tiam") || s.equals("iam") || s.equals("ĉiam") || s.equals("neniam")) {
				pos = Pos.ADVERB;
			} else if (s.equals("kiom") || s.equals("tiom") || s.equals("iom") || s.equals("ĉiom") || s.equals("neniom")) {
				pos = Pos.ADVERB;
			} else if (s.equals("kies") || s.equals("ties") || s.equals("ies") || s.equals("ĉies") || s.equals("nenies")) {
				pos = Pos.ADJECTIVE;
			}
			
			// Match regular words
			else if (s.equals("for") || s.matches(".*e$") || s.matches(".*aŭ$")) {
				pos = Pos.ADVERB;
			}
			
			// noun stuff
			else if (s.matches(".*o$")) {
				pos = Pos.NOUN;
				num = NumMarker.SINGULAR;
				c = Case.NOMINATIVE;
			} else if (s.matches(".*on$")) {
				pos = Pos.NOUN;
				num = NumMarker.SINGULAR;
				c = Case.ACCUSATIVE;
			} else if (s.matches(".*oj$")) {
				pos = Pos.NOUN;
				num = NumMarker.PLURAL;
				c = Case.NOMINATIVE;
			} else if (s.matches(".*ojn$")) {
				pos = Pos.NOUN;
				num = NumMarker.PLURAL;
				c = Case.ACCUSATIVE;
			}
			
			// adjective stuff
			else if (s.matches(".*a$")) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.SINGULAR;
				c = Case.NOMINATIVE;
			} else if (s.matches(".*an$")) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.SINGULAR;
				c = Case.ACCUSATIVE;
			} else if (s.matches(".*aj$")) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.PLURAL;
				c = Case.NOMINATIVE;
			} else if (s.matches(".*ajn$")) {
				pos = Pos.ADJECTIVE;
				num = NumMarker.PLURAL;
				c = Case.ACCUSATIVE;
			}
			
			// verb stuff
			else if (s.matches(".*as$")) {
				pos = Pos.VERB;
				tense = Tense.PRESENT;
			} else if (s.matches(".*is$")) {
				pos = Pos.VERB;
				tense = Tense.PAST;
			} else if (s.matches(".*os$")) {
				pos = Pos.VERB;
				tense = Tense.FUTURE;
			} else if (s.matches(".*us$")) {
				pos = Pos.VERB;
				tense = Tense.CONDITIONAL;
			} else if (s.matches(".*u$")) {
				pos = Pos.VERB;
				tense = Tense.IMPERATIVE;
			} else if (s.matches(".*i$")) {
				pos = Pos.VERB;
				tense = Tense.INFINITIVE;
			}
			
			// unknown POS
			else {
				pos = null;
			}
			
			// make sure the word exists in the dictionary...
			if (dict_.getWord(s, true) == null) {
				mistakes.add(new FatalMistake(left, left + s.length(), "This word is not in the dictionary."));
			}
			
			// create token 
			tk = new Token(s, pos, num, c, tense, left, left + s.length() + 1);
			left += s.length() + 1;
			
			// add to token list
			tokens_.add(tk);
		}
		
		// Add punctuation. Punctuation won't be in the dictionary. 
		if (punct != null) {
			tokens_.add(new Token(punct, Pos.PUNCTUATION, null, null, null, sentence_.length() - 1, sentence_.length()));
		}
		
	}
	
	// HasNext, returns true if the tokenizer has yet another element
	public boolean hasNext() {
		return !tokens_.isEmpty();
	}
	
	// get the next token
	public Token getNextToken() {
		return tokens_.remove(0);
	}
	
	// peek at the next token without removing it
	public Token peek() {
		return tokens_.get(0);
	}
	
	// get the sentence
	public String getSentence() {
		return sentence_;
	}
	
	// Constructor
	public Tokenizer(String sentence, MyDictionary dict) {
		sentence_ = sentence;
		dict_ = dict;
	}

}
