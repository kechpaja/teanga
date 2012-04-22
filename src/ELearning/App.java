package ELearning;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import GUI.GUIGrammarGame;


/*  So, there is some type of problem.  I have found that sometimes
     when I test the game it stops creating a piece at random times
     but it has happened so rarely that I can't figure out what is
     causing it.  I am hoping that you can figure it out.  Also, I 
     have placed TODO statements in these classes where there is
     something that you need to update before running it.
*/
public class App extends javax.swing.JFrame{
	private Driver _driver;
	public App(){
		
		super("ELearning");
		this.setPreferredSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		_driver = new Driver();

		//This instantiation would actually happen in the driver (when we get
		// everything working). The two parameters would actually become
		// an array of PicturePairs.
		// TODO: You should change the path to a picture on your machine.
		//GUIVocabGame panel = new GUIVocabGame("C:/Users/Dede/finalImage.png", "wrong");
		//GUIBasicPage panel = new GUIBasicPage();
		//GUIOptionsPage panel = new GUIOptionsPage();
		JPanel panel = _driver.getCurPage();
		
		this.add(panel, BorderLayout.CENTER);
		
		this.pack();
		this.setResizable(false);
		setVisible(true);

	}

	public void changePage(){
		this.add(_driver.getCurPage());
		this.pack();
		this.repaint();
	}

	public static void main(String [] argv) {
		new App();
	}
	
}
