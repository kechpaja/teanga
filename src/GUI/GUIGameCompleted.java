package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
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
	LevelInstance _levelInstance;
	
	public GUIGameCompleted(){//Driver d, LevelInstance l){
		//_driver = d;
		//_levelInstance = l;
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color (245,245,250,255));
		overall.setPreferredSize(new Dimension(950,170));
		overall.setVisible(true);
		
		JLabel title;
		JTextArea gameCompleted;
		String titleString = "Conratulations!";
		String gameString = "You've completed this level with a score of "+ 1 + ". You can play again or move on to the next game.";

		/*if (l.getScore() >= l.getNecessaryScore()){
			title = new JLabel("Congratulations!", SwingConstants.CENTER);//l.getScore()
			gameCompleted = new JTextArea("You've completed this level with a score of "+ 1 + ". You can play again or move on to the next game.");
		} else {
			title = new JLabel("Game Over");
			gameCompleted = new JTextArea("Your score was " + l.getScore() + ". You'll need at least a score of " + l.getNecessaryScore() + " to unlock the next level");

		} */
			
		title = new JLabel(titleString);
		gameCompleted = new JTextArea(gameString);
			
		gameCompleted.setEditable(false);
		gameCompleted.setFocusable(false);
		gameCompleted.setFont(new Font("Cambria", Font.PLAIN, 20));
		gameCompleted.setOpaque(false);
		
		title.setFont(new Font("Cambria", Font.PLAIN, 80));
		
		JButton tryAgain = new JButton("try again");
		tryAgain.addActionListener(new tryAgainActionListener());
		JButton backtoBasic = new JButton("return to home page");
		backtoBasic.addActionListener(new backtoOptions());
		
		overall.add(title, BorderLayout.NORTH);
		
		overall.add(gameCompleted, BorderLayout.CENTER);
		
		
		
		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createVerticalStrut(30));
		
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createVerticalStrut(30));
		bottomBar.add(Box.createHorizontalStrut(30));
		bottomBar.add(tryAgain);
		bottomBar.add(Box.createHorizontalStrut(5));
		bottomBar.add(backtoBasic);
		bottomBar.add(Box.createHorizontalStrut(30));
		
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);

	}
	
	private class tryAgainActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (_levelInstance.getTypeOfGame()){
				case 0:
					try {
						_driver.getPlayerStats().encode();
					} catch (IllegalBlockSizeException e1) {
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					_driver.changePage(new GUIVocabGame(_driver.getVocabGameMaker().makeLevel(_levelInstance.getLevelNum()), _driver));
					break;
				case 1:
					try {
						_driver.getPlayerStats().encode();
					} catch (IllegalBlockSizeException e1) {
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					_driver.changePage(new GUIGrammarGame(_driver.getGrammarGameMaker().makeLevel(_levelInstance.getLevelNum()), _driver));
					break;
				case 2:
					try {
						_driver.getPlayerStats().encode();
					} catch (IllegalBlockSizeException e1) {
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//_driver.changePage(new GUIBossGame());
					break;
				default:
					_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
					break;
			}
			
		}
		
	}
	
	private class backtoOptions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
		}
		
	}
	
	public static void main(String[] args){
		Driver myDriver = new Driver();
		GrammarLevel myLevel = myDriver.getGrammarGameMaker().makeLevel(1);
		GUIGameCompleted myPage = new GUIGameCompleted();//myDriver, myLevel);
		JFrame mainFrame = new JFrame("E Learning");
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(myPage);
		
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
}
