package ELearning;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Dimension;
import java.io.*;

import javax.swing.*;

import GUI.*;
import annie.*;

public class Driver {
	public MyDictionary dictionary;
	private JFrame mainFrame;
	public VocabGameMaker vGameMaker;
	public GrammarGameMaker gGameMaker;
	private Exercises exercises;
	public Lessons lessons;
	private HelpBox helpbox;
	public OpeningPage openingpage;
	private JPanel curPage;
	

	private String curUserName = null;
	private int gender = 0;
	private PlayerStats curPlayerStats = null;
	
	public Driver(){
		try{
			lessons = new Lessons("data/lessonfilev.txt", "data/lessonfileg.txt");
			exercises = new Exercises("data/testfilev", "data/testfileg.txt");
			helpbox = new HelpBox("data/testhelpv.txt", "data/testhelpg.txt");
			vGameMaker = new VocabGameMaker(exercises, helpbox);
			gGameMaker = new GrammarGameMaker(exercises, helpbox);
			//dictionary = new MyDictionary("data/dictionary.txt");
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
	
	public void changePage(JPanel newPage){
		System.out.println("here");
		mainFrame.getContentPane().removeAll();
		curPage = newPage;
		mainFrame.getContentPane().add(curPage, BorderLayout.CENTER);
		((JPanel)mainFrame.getContentPane()).revalidate();
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
	
	public void setGender(int g){
		gender = g;
	}
	
	public int getGender(){
		return gender;
	}
	
	public static void main(String[] args){
		Driver myDriver = new Driver();
	}
	
	public PlayerStats getPlayerStats(){
		return curPlayerStats;
	}
	
	public void setPlayerStats(PlayerStats ps){
		curPlayerStats = ps;
	}
	

}
