package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
	private JLabel _userName;

	//this path and string would actually be an array of PicturePairs
	public GUIVocabGame(VocabLevel vl, Driver d){
		super(new BorderLayout());
		
		_driver = d;
		java.awt.Dimension size = new java.awt.Dimension(1000, 700);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(50,50,200,255));


		//Box Layout		
		Box theBox = Box.createVerticalBox();
		
		_userName = new JLabel(_driver.getUserName());
		_userName.setFont(new Font("Cambria", Font.PLAIN, 20));
		_userName.setForeground(Color.white);

		//Top Toolbar (empty at this point)
		Box topBar = Box.createHorizontalBox();
		topBar.add(_userName);
		topBar.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton back = new JButton("Back");
		back.addActionListener(new backtoOptionsActionListener());
		back.setSize(new Dimension(75, 35));

		topBar.add(back);
		topBar.add(Box.createHorizontalStrut(30));
		
		_vocabLevel = vl;
		//The game board (takes care of almost everything game related)
		_gameBoard = new GUIVocabGameBoard(_vocabLevel, _driver.getPlayerStats(), _driver);

		//The panel that contains the text field in which the user
		// types their guesses.
		JPanel enterAnswers = new JPanel();
		enterAnswers.setPreferredSize(new Dimension(1000, 50));
		enterAnswers.setBackground(new Color(200,200,200,255));

		_textField = new JTextField(20);
		_textField.addActionListener(new TextListener());
		_textField.addKeyListener(new EncodingShiftListener(_textField));
		enterAnswers.add(_textField);		

		//The Bottom Toolbar (empty at this point)
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton help = new JButton("Help");
		JButton dictionary = new JButton("Dictionary");
		dictionary.setSize(new Dimension(75, 35));
		help.setSize(new Dimension(75, 35));
		bottomBar.add(help);
		bottomBar.add(dictionary);
		bottomBar.add(Box.createHorizontalStrut(15));
		help.addActionListener(new HelpButtonListener());
		dictionary.addActionListener(new DictionaryButtonListener());

		//Add everyting to the box and then this panel
		theBox.add(topBar);
		theBox.add(_gameBoard);
		theBox.add(enterAnswers);
		theBox.add(bottomBar);

		add(theBox);

	}
	

	//Checks a typed answers
	public void checkAnswer(String answer){
		if(_vocabLevel.tryAnswer(answer)){
			_gameBoard.clearPiece();

			//Set _correctAnswer to the next correct answer
		}
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