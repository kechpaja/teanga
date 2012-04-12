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
			Type pos;
			
			// check the endings
			if (s.equals("la")) {
				pos = Type.ART;
			} else if (s.equals("en") || s.equals("al") || s.equals("de")) {
				pos = Type.PREP; //TODO we need a list of prepositions to check against
			}
			
			else if (s.matches(".*e$")) {
				pos = Type.ADV;
			}
			
			else if (s.matches(".*o$")) {
				pos = Type.NOMSG;
			} else if (s.matches(".*on$")) {
				pos = Type.ACCSG;
			} else if (s.matches(".*oj$")) {
				pos = Type.NOMPL;
			} else if (s.matches(".*ojn$")) {
				pos = Type.ACCPL;
			}
			
			else if (s.matches(".*a$")) {
				pos = Type.ADJNSG;
			} else if (s.matches(".*an$")) {
				pos = Type.ADJASG;
			} else if (s.matches(".*aj$")) {
				pos = Type.ADJNPL;
			} else if (s.matches(".*ajn$")) {
				pos = Type.ADJAPL;
			}
			
			else if (s.matches(".*as$")) {
				pos = Type.PRES;
			} else if (s.matches(".*is$")) {
				pos = Type.PAST;
			} else if (s.matches(".*os$")) {
				pos = Type.FUT;
			} else if (s.matches(".*us$")) {
				pos = Type.COND;
			} else if (s.matches(".*u$")) {
				pos = Type.IMP;
			} else if (s.matches(".*i$")) {
				pos = Type.INF;
			}
			
			else {
				pos = Type.BAD;
			}
			
			// create token
			tk = new Token(s, pos);
			
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
