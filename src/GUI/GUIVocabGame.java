package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

import ELearning.Driver;
import ELearning.VocabLevel;
import GUI.GUIPronunciationGuide.BacktoBasicActionListener;
import encoding.EncodingShiftListener;


@SuppressWarnings("serial")
public class GUIVocabGame extends GUIFoundationPage{

	private GUIVocabGameBoard _gameBoard;
	private JTextField _textField;
	private VocabLevel _vocabLevel;
	private Driver _driver;
	private JLabel _score;
	private String _newText;
	private JPanel _forHelpBox;
	

	//this path and string would actually be an array of PicturePairs
	public GUIVocabGame(VocabLevel vl, Driver d){
		super(d, true);
		_vocabLevel = vl;
		_driver = d;
		_forHelpBox = this;
		
		//help box
		setHelp(new HelpButtonListener());
		setDictionary(new DictionaryButtonListener());
		
		//back button
		setBack(new backtoOptionsActionListener(),"Reiru",true);
		
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
		_textField.addKeyListener(new Find7Listener(_textField));
		_textField.requestFocusInWindow();
		
		KeyStroke key = KeyStroke.getKeyStroke('7');
		_textField.getInputMap().remove(key);
		_textField.getInputMap().put(key, "dosomething");
		_textField.getActionMap().put("dosomething", new changeWordListener());
	    
		Box fullBar = Box.createVerticalBox();
		fullBar.add(_gameBoard);
		fullBar.add(enterAnswers);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(fullBar, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);

	}
	
	public void focusOnTextField(){
		_textField.requestFocus();
	}

	//Checks a typed answers
	public boolean checkAnswer(String answer){
		boolean toReturn = _vocabLevel.tryAnswer(answer);
		if(toReturn){
			_gameBoard.clearPiece();
		}
		updateScore(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");
		return toReturn;

	}

	//When someone types enter, the text they typed is checked and if it
	// is correct then the bottom piece is cleared and the correct answer
	// is updated.  The text that was in the field is highlighted.
	private class TextListener implements ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent e){
			String text = _textField.getText();
			if(checkAnswer(text)){
				_textField.setBackground(new Color(50,200,60, 255));
			} else _textField.setBackground(new Color(200, 100, 100, 255));
			_textField.setText("");
		}

	}
	
	private class changeWordListener extends javax.swing.AbstractAction {
		
		public void actionPerformed(java.awt.event.ActionEvent e){
			_gameBoard._pieces[0].replacePicWithWord();
		}
	
	}

	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_vocabLevel.decrementScore(2);
			updateScore(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");
			DictionaryInternalFrame dictFrame = new DictionaryInternalFrame(_driver.getDictionary());
			dictFrame.addWindowListener(new WindowListener() {
	            public void windowClosed(WindowEvent arg0) {}
	            public void windowActivated(WindowEvent arg0) {
	                _gameBoard.pause();
	            }
	            public void windowClosing(WindowEvent arg0) {
	                _gameBoard.restart();
	            }
	            public void windowDeactivated(WindowEvent arg0) {}
	            public void windowDeiconified(WindowEvent arg0) {}
	            public void windowIconified(WindowEvent arg0) {}
	            public void windowOpened(WindowEvent arg0) {}
	        });

			
		}
		
	}
	
	public void pause(){
		_gameBoard.pause();
	}
	
	public void restart(){
		_gameBoard.restart();
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_vocabLevel.decrementScore(2);
			updateScore(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore());
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_vocabLevel.getHelp(), 2, _vocabLevel.getLevelNum(), _driver, _forHelpBox);
			helpFrame.addWindowListener(new WindowListener() {
	            public void windowClosed(WindowEvent arg0) {}
	            public void windowActivated(WindowEvent arg0) {
	                _gameBoard.pause();
	            }
	            public void windowClosing(WindowEvent arg0) {
	            	_gameBoard.restart();
	            }
	            public void windowDeactivated(WindowEvent arg0) {}
	            public void windowDeiconified(WindowEvent arg0) {}
	            public void windowIconified(WindowEvent arg0) {}
	            public void windowOpened(WindowEvent arg0) {}
	        });
		}
		
	}
	
	private class backtoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.getPlayerStats().RefreshStats(_vocabLevel.getLevelNum(), 0, _vocabLevel.getScore());
			_gameBoard.pause();
			_gameBoard = null;
			_vocabLevel = null;
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			
		}
		
	}
	
	private class Find7Listener implements KeyListener{
		private JTextComponent field_; // field on which this listener is used
		
		// constructor
				public Find7Listener(JTextComponent field) {
					field_ = field;
				}

		@Override
		public void keyPressed(KeyEvent arg0) {
			field_.setText(field_.getText().replaceAll("7",""));
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			field_.setText(field_.getText().replaceAll("7",""));
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			field_.setText(field_.getText().replaceAll("7",""));
		}
	}

}