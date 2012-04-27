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
	
	public GUIGameCompleted(Driver d, LevelInstance l){
		_driver = d;
		_levelInstance = l;
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		Box main = Box.createVerticalBox();
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color (238,238,238,255));
		overall.setVisible(true);
		
		String titleString, gameString;
		if (l.getScore() >= l.getNecessaryScore()){
			titleString = "Congratulations!";
			gameString = "You've completed this level with a score of "+ l.getScore() + ". You can play again or move on to the next game.";
		} else {
			titleString = "Game Over";
			gameString = "Your score was " + l.getScore() + ". You'll need at least a score of " + l.getNecessaryScore() + " to unlock the next level";

		}
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createVerticalStrut(600));
		horizBox.add(Box.createHorizontalStrut(10));
		
		Box vertBox = Box.createVerticalBox();
		vertBox.add(Box.createVerticalStrut(10));
		horizBox.add(vertBox);
		
		Box titleBox = Box.createHorizontalBox();
		JLabel title = new JLabel(titleString);
		title.setFont(new Font("Cambria", Font.PLAIN, 80));
		titleBox.add(title);
		vertBox.add(titleBox);
		
		Box complBox = Box.createHorizontalBox();
		JLabel gameCompleted = new JLabel(gameString);
		gameCompleted.setFont(new Font("Cambria", Font.PLAIN, 20));
		gameCompleted.setOpaque(false);
		complBox.add(gameCompleted);
		vertBox.add(Box.createVerticalStrut(10));
		vertBox.add(complBox);
		
		JButton tryAgain = new JButton("try again");
		tryAgain.addActionListener(new tryAgainActionListener());
		JButton backtoBasic = new JButton("return to home page");
		backtoBasic.addActionListener(new backtoOptions());
		
		horizBox.add(Box.createHorizontalStrut(10));
		horizBox.add(Box.createVerticalStrut(600));
		overall.add(horizBox);
		
		
		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createVerticalStrut(30));
		topBar.add(Box.createRigidArea(new Dimension(1000,10)));
		
		Box bottomBar = Box.createHorizontalBox();
		Box botBar = Box.createVerticalBox();
		bottomBar.add(tryAgain);
		bottomBar.add(Box.createHorizontalStrut(5));
		bottomBar.add(backtoBasic);
		botBar.add(Box.createVerticalStrut(8));
		botBar.add(bottomBar);
		botBar.add(Box.createVerticalStrut(8));
		
		main.add(topBar);
		main.add(overall);
		main.add(botBar);
		this.add(main, BorderLayout.CENTER);

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
					_driver.changePage(new GUIBossGame(_driver.getBossGameMaker().makeLevel(_levelInstance.getLevelNum()), _driver));
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
}
