package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import encoding.EncodingShiftListener;

import ELearning.Driver;
import ELearning.VocabLevel;
import ELearning.VocabPicturePair;


@SuppressWarnings("serial")
public class GUIVocabGame extends JPanel{

	GUIVocabGameBoard _gameBoard;
	JTextField _textField;
	VocabLevel _vocabLevel;
	Driver _driver;
	private JLabel _score;

	//this path and string would actually be an array of PicturePairs
	public GUIVocabGame(VocabLevel vl, Driver d){
		super(new BorderLayout());
		_vocabLevel = vl;
		_driver = d;
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 700);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		//The game board (takes care of almost everything game related)
		_gameBoard = new GUIVocabGameBoard(_vocabLevel, _driver.getPlayerStats(), _driver);

		//The panel that contains the text field in which the user
		// types their guesses.
		JPanel enterAnswers = new JPanel();
		enterAnswers.setPreferredSize(new Dimension(1000, 50));
		enterAnswers.setBackground(new Color(238,238,238,255));

		_textField = new JTextField(20);
		_textField.addActionListener(new TextListener());
		_textField.addKeyListener(new EncodingShiftListener(_textField));
		enterAnswers.add(_textField);	
		
		
		//Creating the Boundaries and Putting it all together
		Box fullBar = Box.createVerticalBox();
		
		//Top Panel
		JPanel topPanel = new JPanel(null);
		topPanel.setPreferredSize(new Dimension(950,40));
		topPanel.setBackground(new Color(50,50,50,255));
		
		Box userBox = Box.createHorizontalBox();
		JLabel _un = new JLabel(_driver.getPlayerStats().getUsername());
		_un.setFont(new Font("Cambria", Font.PLAIN, 20));
		_un.setForeground(Color.white);
		userBox.add(_un);
		userBox.setSize(200,35);
		userBox.setLocation(20, 0);
		
		Box topBar = Box.createHorizontalBox();
		_score = new JLabel(vl.getScore() + "/" + vl.getNecessaryScore());
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		topBar.add(_score);
		topBar.setSize(400,40);
		topBar.setLocation(475, -2);
		
		JButton back = new JButton("Back");
		back.addActionListener(new backtoOptionsActionListener());
		back.setSize(new Dimension(100, 30));
		back.setLocation(875,3);
		
		topPanel.add(userBox);
		topPanel.add(topBar);
		topPanel.add(back);
		
		
		//Bottom Panel
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setPreferredSize(new Dimension(1000,35));
		bottomPanel.setBackground(new Color(50,50,50,255));
		
		JButton help = new JButton("Help");
		help.setSize(new Dimension(100, 30));
		help.addActionListener(new HelpButtonListener());
		help.setLocation(19, 3);
		
		
		JButton dictionary = new JButton("Dictionary");
		dictionary.setSize(new Dimension(100, 30));
		dictionary.addActionListener(new DictionaryButtonListener());
		dictionary.setLocation(875, 3);
		
		bottomPanel.add(help);
		bottomPanel.add(dictionary);
		
		fullBar.add(topPanel);
		fullBar.add(_gameBoard);
		fullBar.add(enterAnswers);
		fullBar.add(bottomPanel);
		fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		add(fullBar, BorderLayout.CENTER);

	}
	

	//Checks a typed answers
	public void checkAnswer(String answer){
		if(_vocabLevel.tryAnswer(answer)){
			_gameBoard.clearPiece();
		}
		_score.setText(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");

	}

	//When someone types enter, the text they typed is checked and if it
	// is correct then the bottom piece is cleared and the correct answer
	// is updated.  The text that was in the field is highlighted.
	private class TextListener implements ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent e){
			String text = _textField.getText();
			checkAnswer(text);
			_textField.setText("");
		}

	}

	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_vocabLevel.decrementScore(2);
			_score.setText(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");
			DictionaryInternalFrame dictFrame = new DictionaryInternalFrame(_driver.getDictionary());
			dictFrame.addWindowListener(new WindowListener() {
	            public void windowClosed(WindowEvent arg0) {}
	            public void windowActivated(WindowEvent arg0) {
	                _gameBoard.pause();
	            }
	            public void windowClosing(WindowEvent arg0) {}
	            public void windowDeactivated(WindowEvent arg0) {
	            	System.out.println("here3");
	                _gameBoard.restart();
	            }
	            public void windowDeiconified(WindowEvent arg0) {}
	            public void windowIconified(WindowEvent arg0) {}
	            public void windowOpened(WindowEvent arg0) {}
	        });

			
		}
		
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_vocabLevel.getHelp(), 0, _vocabLevel.getLevelNum(), _driver);
			helpFrame.addWindowListener(new WindowListener() {
	            public void windowClosed(WindowEvent arg0) {}
	            public void windowActivated(WindowEvent arg0) {
	                _gameBoard.pause();
	            }
	            public void windowClosing(WindowEvent arg0) {}
	            public void windowDeactivated(WindowEvent arg0) {
	            	System.out.println("here3");
	                _gameBoard.restart();
	            }
	            public void windowDeiconified(WindowEvent arg0) {}
	            public void windowIconified(WindowEvent arg0) {}
	            public void windowOpened(WindowEvent arg0) {}
	        });
		}
		
	}
	
	private class backtoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.getPlayerStats().RefreshStats(_vocabLevel.getLevelNum(), 0, _vocabLevel.getScore(), _gameBoard.getSeconds());
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			
		}
		
	}

}