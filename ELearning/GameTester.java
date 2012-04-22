package ELearning;

import java.io.*;
import java.util.*;

public class GameTester {
	Exercises e;
	HelpBox h;
	GrammarGameMaker g;
	VocabGameMaker v;
	//HelpBox h;
	
	public GameTester(){
		try{
		h = new HelpBox("/Users/taranoble/Desktop/helpfilev.txt", "/Users/taranoble/Desktop/helpfileg.txt");
		e = new Exercises("/Users/taranoble/Desktop/testfilev.txt", "/Users/taranoble/Desktop/testfileg.txt");
		g = new GrammarGameMaker(e, h);
		v = new VocabGameMaker(e, h);
		//h = new HelpBox("/Users/taranoble/Desktop/testhelp.rtf", "/Users/taranoble/Desktop/testhelp2.rtf");
		} catch (IOException e){
			System.out.println("there was an error finding the exercise files");
		}
	}
	
	public static void main(String[] args){
		BufferedReader _reader = new BufferedReader(new InputStreamReader(System.in));
		GameTester myTests = new GameTester();
		int curLevel = 0;
		VocabLevel vl = myTests.v.makeLevel(3);
		while (true){
			System.out.println("Score: "+vl.getScore());
			vl.addToWaiting();
			for (VocabPicturePair v: vl.getWaiting()){
				System.out.println(v.getPicturePath());
			}
			String input;
			try {
				input = _reader.readLine();
			} catch (IOException e){
				return;
			}
			vl.tryAnswer(input);
			
		}
		System.out.println("Vocab level finished with a score of"+vl.getScore());
		System.out.println("starting grammar level");
		GrammarLevel gl = myTests.g.makeLevel(2);
		while (!gl.isOver()){
			System.out.println("Score: "+ gl.getScore());
			String[] options = gl.getCurrent().getPossibilities();
			System.out.println(gl.getCurrent().getPicturePath());
			System.out.println(gl.getCurrent().getPartialSentence());
			for (int i = 0; i<options.length; i++){
				System.out.print(i+": "+options[i]+", ");
			}
			System.out.println();
			String input;
			try {
				input = _reader.readLine();
			} catch (IOException e) {
				return;
			}
			String[] inputsplit = input.split(" ");
			if (inputsplit.length == 1){
				System.out.println(gl.submitWhole());
			} else if (inputsplit.length == 2){
				int drag = Integer.parseInt(inputsplit[0]);
				int drop = Integer.parseInt(inputsplit[1]);
				gl.submitPart(drag, drop);
				for(int i = 0; i<gl.getCurrent().getAnswersGiven().length; i++){
					System.out.println(i + ": "+gl.getCurrent().getAnswersGiven()[i]);
				}
			}
			
		}
		System.out.println("Grammar Level Complete!");
	}

}
