package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ELearning.Driver;
import ELearning.VocabLevel;
import ELearning.VocabPicturePair;


@SuppressWarnings("serial")
public class GUIVocabGame extends JPanel{

	GUIVocabGameBoard _gameBoard;
	JTextField _textField;
	VocabLevel _vl;
	Driver driver;

	//this path and string would actually be an array of PicturePairs
	public GUIVocabGame(VocabLevel vl, Driver d){
		super(new BorderLayout());
		
		driver = d;
		java.awt.Dimension size = new java.awt.Dimension(1000, 700);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(50,50,200,255));


		//Box Layout		
		Box theBox = Box.createVerticalBox();

		//Top Toolbar (empty at this point)
		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton back = new JButton("Back");
		back.addActionListener(new backtoOptionsActionListener());
		back.setSize(new Dimension(75, 35));

		topBar.add(back);
		topBar.add(Box.createHorizontalStrut(30));
		
		_vl = vl;
		//The game board (takes care of almost everything game related)
		_gameBoard = new GUIVocabGameBoard(_vl, driver.getPlayerStats(), driver);

		//The panel that contains the text field in which the user
		// types their guesses.
		JPanel enterAnswers = new JPanel();
		enterAnswers.setPreferredSize(new Dimension(1000, 50));
		enterAnswers.setBackground(new Color(200,200,200,255));

		_textField = new JTextField(20);
		_textField.addActionListener(new TextListener());
		enterAnswers.add(_textField);		

		//The Bottom Toolbar (empty at this point)
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 30)));

		//Add everyting to the box and then this panel
		theBox.add(topBar);
		theBox.add(_gameBoard);
		theBox.add(enterAnswers);
		theBox.add(bottomBar);

		add(theBox);

	}

	//Checks a typed answers
	public void checkAnswer(String answer){
		if(_vl.tryAnswer(answer)){
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
	        	_textField.selectAll();
		}

	}
	
	private class backtoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			driver.getPlayerStats().RefreshStats(_vl.getLevelNum(), 0, _vl.getScore(), _gameBoard.getSeconds());
			driver.changePage(new GUIOptionsPage(driver, driver.getPlayerStats()));
			
		}
		
	}

}