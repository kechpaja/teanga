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
		
		//TODO split on whitespace. 
		// for each word, check ending, and create token and put in list. 
		String punct = null;
		if (sentence_.substring(sentence_.length() - 1).matches("\\p{Punct}")) {
			punct = sentence_.substring(sentence_.length() - 1);
			sentence_ = sentence_.substring(0, sentence_.length() - 1);
			System.out.println(sentence_);
		}
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
			
			// check the endings
			else if (s.equals("la")) {
				pos = Pos.ARTICLE;
			//	num = null;
			//	c = null;
			} else if (s.equals("en") || s.equals("al") || s.equals("de")) {
				pos = Pos.PREPOSITION;
				//TODO we need a list of prepositions to check against
			}
			
			
			// Match correlatives a, al, am, e, el, es, o, om, u
			// I think we can get away with just checking for al, am, el, es, om, u as endings to pronouns...
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
			
			// TODO maybe we need the rest... unsure. 
			
			// Match regular words
			else if (s.matches(".*e$")) {
				pos = Pos.ADVERB;
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
			
			// TODO correlatives
			
			else {
				pos = null;
			}
			
			// TODO make sure the word exists in the dictionary...
			System.out.println(dict_);
			System.out.println("done...");
			if (dict_.getWord(s, true) == null) {
				mistakes.add(new FatalMistake(left, left + s.length() + 1, "Error: This Word is Invalid"));
			}
			
			// create token
			tk = new Token(s, pos, num, c, tense, left, left + s.length() + 1);
			left += s.length() + 1;
			
			// add to token list
			tokens_.add(tk);
		}
		
		// go through dictionary and make sure every word is present
		for (Token t : tokens_) {
			// TODO lookup word
		}
		
		// Add punctuation. Punctuation won't be in the dictionary. 
		if (punct != null) {
			tokens_.add(new Token(punct, Pos.PUNCTUATION, null, null, null, sentence_.length() - 1, sentence_.length()));
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
	public Tokenizer(String sentence, MyDictionary dict) {
		sentence_ = sentence;
		dict_ = dict;
	}

}
