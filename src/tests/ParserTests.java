package tests;

import parsing.*;
import rules.*;
import junit.framework.TestCase;
import java.util.*;
import org.junit.Test;

public class ParserTests extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Tests the tokenizer, printing out the tokens produced and the expected list. 
	 */
	
	// TODO there will be assert() statements here eventually. However, for now it's easier
	// to just have the program print out the output. 
	/*
	@Test
	public void testTokenizer() {
		Tokenizer tkn1 = new Tokenizer("kato");
		Tokenizer tkn2 = new Tokenizer("la kato");
		Tokenizer tkn3 = new Tokenizer("kato estas en la domo");
		Tokenizer tkn4 = new Tokenizer("la bela kato manÄ?as la muson");
		Tokenizer tkn5 = new Tokenizer("La musoj manÄ?is rapide");
		Tokenizer tkn6 = new Tokenizer("LA KATOJ MANÄœOS LA BELAJN MUSOJN");
		Tokenizer tkn7 = new Tokenizer("Mia domo estus en la Ä?ardeno");
		Tokenizer tkn8 = new Tokenizer("Vidu la kato");
		Tokenizer tkn9 = new Tokenizer("kato");
		Tokenizer tkn10 = new Tokenizer("kato");
		
		List<Tokenizer> tklist = new LinkedList<Tokenizer>();
		
		tklist.add(tkn1);
		tklist.add(tkn2);
		tklist.add(tkn3);
		tklist.add(tkn4);
		tklist.add(tkn5);
		tklist.add(tkn6);
		tklist.add(tkn7);
		tklist.add(tkn8);
		tklist.add(tkn9);
		tklist.add(tkn10);
		
		List<Mistake> ls = new LinkedList<Mistake>();
		
		for (Tokenizer tkn : tklist) {
			tkn.init(ls); // initialize tokenizer
			
			System.out.println(tkn.getSentence());
			
			while (tkn.hasNext()) { // print out tokens
				Token t = tkn.getNextToken();
				System.out.println(t + ": " + t.getName());
			}
			
			System.out.println("---");
		}
	}
	*/
	
	/**
	 * Test the method parseTokenStream, which parses the token stream (no way!)
	 * As with all methods here, assumes that the Tokenizer class is functioning properly. 
	 */
	
/*	@Test
	public void testParseTokenStream() {
		List<SemanticRule> sem = new LinkedList<SemanticRule>();
		List<SyntacticRule> syn = new LinkedList<SyntacticRule>();
		
		// TODO load up the lists of rules
		syn.add(new SyntacticRule(Type.DPN, Type.VP, Type.S));
		syn.add(new SyntacticRule(Type.NPNSG, Type.VP, Type.S));
		syn.add(new SyntacticRule(Type.NPNPL, Type.VP, Type.S));
		
		syn.add(new SyntacticRule(Type.ART, Type.NPNSG, Type.DPN));
		syn.add(new SyntacticRule(Type.ART, Type.NPNPL, Type.DPN));
		syn.add(new SyntacticRule(Type.ART, Type.NOMSG, Type.DPN));
		syn.add(new SyntacticRule(Type.ART, Type.NOMPL, Type.DPN));
		
		syn.add(new SyntacticRule(Type.ADJNSG, Type.NPNSG, Type.NPNSG));
		syn.add(new SyntacticRule(Type.ADJNSG, Type.NOMSG, Type.NPNSG));
		syn.add(new SyntacticRule(Type.ADJNPL, Type.NPNPL, Type.NPNPL));
		syn.add(new SyntacticRule(Type.ADJNPL, Type.NOMPL, Type.NPNPL));
		
		syn.add(new SyntacticRule(Type.PRES, Type.DPA, Type.VP));
		syn.add(new SyntacticRule(Type.PAST, Type.DPA, Type.VP));
		syn.add(new SyntacticRule(Type.FUT, Type.DPA, Type.VP));
		syn.add(new SyntacticRule(Type.COND, Type.DPA, Type.VP));
		
		syn.add(new SyntacticRule(Type.PRES, Type.NPASG, Type.VP));
		syn.add(new SyntacticRule(Type.PAST, Type.NPASG, Type.VP));
		syn.add(new SyntacticRule(Type.FUT, Type.NPASG, Type.VP));
		syn.add(new SyntacticRule(Type.COND, Type.NPASG, Type.VP));
		
		syn.add(new SyntacticRule(Type.PRES, Type.NPAPL, Type.VP));
		syn.add(new SyntacticRule(Type.PAST, Type.NPAPL, Type.VP));
		syn.add(new SyntacticRule(Type.FUT, Type.NPAPL, Type.VP));
		syn.add(new SyntacticRule(Type.COND, Type.NPAPL, Type.VP));
		
		syn.add(new SyntacticRule(Type.ART, Type.NPASG, Type.DPA));
		syn.add(new SyntacticRule(Type.ART, Type.NPAPL, Type.DPA));
		syn.add(new SyntacticRule(Type.ART, Type.ACCSG, Type.DPA));
		syn.add(new SyntacticRule(Type.ART, Type.ACCPL, Type.DPA));
		
		syn.add(new SyntacticRule(Type.ADJASG, Type.NPASG, Type.NPASG));
		syn.add(new SyntacticRule(Type.ADJASG, Type.ACCSG, Type.NPASG));
		syn.add(new SyntacticRule(Type.ADJAPL, Type.NPAPL, Type.NPAPL));
		syn.add(new SyntacticRule(Type.ADJAPL, Type.ACCPL, Type.NPAPL));
		
		
		Parser p = new Parser(syn, sem);
		
		p.parse("la bela kato manÄ?as la muson");
		p.parse("belaj musoj manÄ?as la katojn");
		
		System.out.println("-----");
		
		// TODO write a bunch of cases. Shouldn't be too bad; just take sentences and parse them. 
	} */

	
	//TODO test tokenizer; have it print out a list of tokens. 
}
