package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import ELearning.Driver;
import ELearning.GrammarLevel;
import ELearning.LevelInstance;

@SuppressWarnings("serial")
public class GUIGameCompleted extends JPanel{
	Driver _driver;
	
	public GUIGameCompleted(Driver d, LevelInstance l){
		_driver = d;
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color (0,255,255,0));
		overall.setPreferredSize(new Dimension(950,170));
		
		JLabel title;
		JTextArea gameCompleted;
		if (l.getScore() >= l.getNecessaryScore()){
			title = new JLabel("Congratulations!", SwingConstants.CENTER);
			gameCompleted = new JTextArea("You've completed this level with a score of "+ l.getScore() + ". You can play again or move on to the next game.");
		} else {
			title = new JLabel("Game Over");
			gameCompleted = new JTextArea("Your score was " + l.getScore() + ". You'll need at least a score of " + l.getNecessaryScore() + " to unlock the next level");

		}
		gameCompleted.setEditable(false);
		gameCompleted.setFocusable(false);
		gameCompleted.setFont(new Font("Cambria", Font.PLAIN, 20));
		gameCompleted.setOpaque(false);
		gameCompleted.setAlignmentX(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Cambria", Font.PLAIN, 80));
		title.setBorder(BorderFactory.createEmptyBorder(20,250,20,0));
		
		overall.add(title, BorderLayout.NORTH);
		
		
		overall.add(gameCompleted);
		
		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);

	}
	
	public static void main(String[] args){
		Driver myDriver = new Driver();
		GrammarLevel myLevel = myDriver.getGrammarGameMaker().makeLevel(1);
		GUIGameCompleted myPage = new GUIGameCompleted(myDriver, myLevel);
		JFrame mainFrame = new JFrame("E Learning");
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(myPage, BorderLayout.CENTER);
		
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
}
