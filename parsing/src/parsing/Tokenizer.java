package parsing;

import java.util.*;

public class Tokenizer {
	
	/**
	 * This tokenizer will be a little bit different from the one used in our
	 * compiler for 31 - it will tokenize the entire string at once, and return it 
	 * as a list of tokens. Since this parser is to be called on single sentences, 
	 * there should never be so many tokens that this is a problem. 
	 */
	
	private String sentence_; // the sentence to be tokenized
	private List<Token> tokens_;
	
	// initialize the tokenizer
	public void init(List<Mistake> mistakes) {
		//TODO split on whitespace. 
		// for each word, check ending, and create token and put in list. 
		String[] words = sentence_.toLowerCase().split("\\s");
		Token tk = null;
		tokens_ = new LinkedList<Token>();
		for (String s : words) {
			// check pos from ending
			Pos pos = null;
			NumMarker num = null;
			Case c = null;
			Tense tense = null;
			
			// check the endings
			if (s.equals("la")) {
				pos = Pos.ARTICLE;
				num = null;
				c = null;
			} else if (s.equals("en") || s.equals("al") || s.equals("de")) {
				pos = Pos.PREPOSITION;
				num = null; // sometimes we may be able to check...
				c = null; // again, not entirely sure
				//pos = Type.PREP; //TODO we need a list of prepositions to check against
			}
			
			else if (s.matches(".*e$")) {
				pos = Pos.ADVERB;
				num = null;
				c = null; // TODO maybe...
			}
			
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
			
			// TODO mark verb as finite/non-finite? 
			
			// TODO correlatives
			// TODO tenses
			
			else {
				pos = null;
			}
			
			// create token
			tk = new Token(s, pos, num, c, tense);
			
			// add to token list
			tokens_.add(tk);
		}
		
	}
	
	// Getter for list of mistakes
	
	
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
	public Tokenizer(String sentence) {
		sentence_ = sentence;
	}

}
