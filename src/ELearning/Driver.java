package ELearning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Dimension;
import java.io.*;

import javax.swing.*;

import GUI.*;
import annie.*;

public class Driver {
	private JFrame mainFrame;
	private VocabGameMaker vGameMaker;
	private GrammarGameMaker gGameMaker;
	private Exercises exercises;
	private HelpBox helpbox;
	private OpeningPage openingpage;
	private JPanel curPage;
	

	private String curUserName = null;
	
	public Driver(){
		try{
			exercises = new Exercises("Insert vfile here", "Insert gfile here");
			helpbox = new HelpBox("insert v file here", "insert gfile here");
			vGameMaker = new VocabGameMaker(exercises, helpbox);
			gGameMaker = new GrammarGameMaker(exercises, helpbox);
			openingpage = new OpeningPage();
			curPage = new GUIBasicPage(this);
		} catch (IOException e){
			String errorMessage = "There was an error finding some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		mainFrame = new JFrame("E Learning");
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(curPage, BorderLayout.CENTER);
		
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
	}
	
	public String getUserName(){
		return curUserName;
	}
	
	public void setUserName(String un){
		curUserName = un;
	}
	
	public JPanel getCurPage(){
		return curPage;
	}
	
	public static void main(String[] args){
		Driver myDriver = new Driver();
	}
	

}
