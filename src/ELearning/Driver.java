package ELearning;

import java.io.*;

import javax.swing.*;

import GUI.*;
import annie.*;

public class Driver {
	private App app;
	public VocabGameMaker vGameMaker;
	public GrammarGameMaker gGameMaker;
	public Exercises exercises;
	public HelpBox helpbox;
	public OpeningPage openingpage;
	public JPanel curPage;
	
	public Driver(App a){
		try{
			exercises = new Exercises("Insert vfile here", "Insert gfile here");
			helpbox = new HelpBox("insert v file here", "insert gfile here");
			vGameMaker = new VocabGameMaker(exercises, helpbox);
			gGameMaker = new GrammarGameMaker(exercises, helpbox);
			openingpage = new OpeningPage();
		} catch (IOException e){
			System.out.println("Figure out what to do in this situation");
		}
		curPage = new GUIBasicPage(openingpage);
		app = a;
	}
	
	public JPanel getCurPage(){
		return curPage;
	}
	

}
