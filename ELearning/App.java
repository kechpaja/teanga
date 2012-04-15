package ELearning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JScrollPane;


/*  So, there is some type of problem.  I have found that sometimes
     when I test the game it stops creating a piece at random times
     but it has happened so rarely that I can't figure out what is
     causing it.  I am hoping that you can figure it out.  Also, I 
     have placed TODO statements in these classes where there is
     something that you need to update before running it.
*/
public class App extends javax.swing.JFrame{
	
	private JScrollPane _scrollpane;
	private Exercises e;
	private HelpBox h;
	//private GrammarGameMaker g;
	private VocabGameMaker v;
	private int curLevel;

public App(){
		super("ELearning");
		try {
			h = new HelpBox("/Users/taranoble/Desktop/testhelp", "/Users/taranoble/Desktop/testhelp2");
			e = new Exercises("/Users/taranoble/Desktop/testfilev.txt", "/Users/taranoble/Desktop/testfileg.txt");
			//g = new GrammarGameMaker(e, h);
			v = new VocabGameMaker(e, h);
		} catch (IOException e) {
			System.out.println("Cound not find exercise files");
		}
		this.setPreferredSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		curLevel = 1;

		//This instantiation would actually happen in the driver (when we get
		// everything working). The two parameters would actually become
		// an instance of a vocab level
		// TODO: You should change the path to a picture on your machine.
		GUIVocabGame panel = new GUIVocabGame(v.makeLevel(curLevel));
		
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		setVisible(true);

	}

	public static void main(String [] argv) {
		new App();
	}
	
}

