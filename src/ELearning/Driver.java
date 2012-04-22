package ELearning;

import java.io.*;
import annie.*;

public class Driver {
	VocabGameMaker vGameMaker;
	GrammarGameMaker gGameMaker;
	Exercises exercises;
	HelpBox helpbox;
	OpeningPage openingpage;
	
	public Driver(){
		try{
			exercises = new Exercises("Insert vfile here", "Insert gfile here");
			helpbox = new HelpBox("insert v file here", "insert gfile here");
			vGameMaker = new VocabGameMaker(exercises, helpbox);
			gGameMaker = new GrammarGameMaker(exercises, helpbox);
			openingpage = new OpeningPage();
		} catch (IOException e){
			System.out.println("Figure out what to do in this situation");
		}
	}
	

}
