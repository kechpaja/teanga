package ELearning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import parsing.Parser;
import GUI.GUIIntroPage;
import annie.MyDictionary;
import annie.OpeningPage;
import annie.PlayerStats;

public class Driver {
	private JFrame mainFrame;
	private MyDictionary dictionary;
	private VocabGameMaker vGameMaker;
	private GrammarGameMaker gGameMaker;
	private BossGameMaker bGameMaker;
	private Exercises exercises;
	private Lessons lessons;
	private HelpBox helpbox;
	public OpeningPage openingpage;
	private JPanel curPage;
	private Parser parser;
	

	private String curUserName = null;
	private int gender = 0;
	private PlayerStats curPlayerStats = null;
	
	public Driver(){
		try{
			parser = new Parser("data/parserules.txt", "data/dictionary.txt");
			lessons = new Lessons("data/lessonfilev.txt", "data/lessonfileg.txt");
			exercises = new Exercises("data/lessonfilev.txt", "data/testfileg.txt", "data/testfileb.txt");
			helpbox = new HelpBox("data/HelpFiles/VocabGameHelp.txt", "data/HelpFiles/GrammarGameHelp.txt", "data/HelpFiles/BossGameHelp.txt", "data/HelpFiles/VocabLessonHelp.txt", "data/HelpFiles/GrammarLessonHelp.txt");
			vGameMaker = new VocabGameMaker(exercises, helpbox);
			gGameMaker = new GrammarGameMaker(exercises, helpbox);
			bGameMaker = new BossGameMaker(exercises, helpbox, parser);
			dictionary = new MyDictionary("data/dictionary.txt");
			openingpage = new OpeningPage();
			curPage = new GUIIntroPage(this);
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
		mainFrame.getContentPane().removeAll();
		curPage = newPage;
		mainFrame.getContentPane().add(curPage, BorderLayout.CENTER);
		((JPanel)mainFrame.getContentPane()).revalidate();
		mainFrame.repaint();
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
	
	public MyDictionary getDictionary(){
		return dictionary;
	}
	
	public VocabGameMaker getVocabGameMaker(){
		return vGameMaker;
	}
	
	public GrammarGameMaker getGrammarGameMaker(){
		return gGameMaker;
	}
	
	public BossGameMaker getBossGameMaker(){
		return bGameMaker;
	}
	
	public static void main(String[] args){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		}
		Driver myDriver = new Driver();
	}
	
	public PlayerStats getPlayerStats(){
		return curPlayerStats;
	}
	
	public void setPlayerStats(PlayerStats ps){
		curPlayerStats = ps;
	}
	
	public Lessons getLessons(){
		return lessons;
	}
	
	public HelpBox getHelpBox(){
		return helpbox;
	}
	

}
