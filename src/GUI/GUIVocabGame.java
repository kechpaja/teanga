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
import encoding.EncodingShiftListener;


@SuppressWarnings("serial")
public class GUIVocabGame extends JPanel{

	private GUIVocabGameBoard _gameBoard;
	private JTextField _textField;
	private VocabLevel _vocabLevel;
	private Driver _driver;
	private JLabel _score;
	private String _newText;
	

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
		_textField.addKeyListener(new Find7Listener(_textField));
		_textField.requestFocusInWindow();
		
		KeyStroke key = KeyStroke.getKeyStroke('7');
				//, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		_textField.getInputMap().remove(key);
		_textField.getInputMap().put(key, "dosomething");
		_textField.getActionMap().put("dosomething", new changeWordListener());
	    
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
		
		BufferedImage backpic = null;
		try {
			backpic = ImageIO.read(new File("data/OtherPictures/backarrow.png"));
		} catch (IOException e){
			
		}
		int type3 = BufferedImage.TYPE_INT_ARGB;
        BufferedImage dst3 = new BufferedImage(20, 20, type3);
        Graphics2D g3 = dst3.createGraphics();
        g3.drawImage(backpic, 0, 0, 20, 20, this);
        g3.dispose();
        ImageIcon newIcon3 = new ImageIcon(dst3);
		
		JButton back = new JButton("Reiru",newIcon3);
		back.addActionListener(new backtoOptionsActionListener());
		back.setSize(new Dimension(100, 30));
		back.setLocation(875,0);
		
		topPanel.add(userBox);
		topPanel.add(topBar);
		topPanel.add(back);
		
		
		//Bottom Panel
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setPreferredSize(new Dimension(1000,35));
		bottomPanel.setBackground(new Color(50,50,50,255));
		
		BufferedImage dictpic = null;
		try {
			dictpic = ImageIO.read(new File("data/OtherPictures/realdictionary.png"));
		} catch (IOException e){
			
		}
		int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage dst = new BufferedImage(27, 27, type);
        Graphics2D g1 = dst.createGraphics();
        g1.drawImage(dictpic, 0, 0, 27, 27, this);
        g1.dispose();
        ImageIcon newIcon = new ImageIcon(dst);
		
        BufferedImage helppic = null;
		try {
			helppic = ImageIO.read(new File("data/OtherPictures/QuestionMark.png"));
		} catch (IOException e){
			
		}
		int type2 = BufferedImage.TYPE_INT_ARGB;
        BufferedImage dst2 = new BufferedImage(23, 23, type2);
        Graphics2D g2 = dst2.createGraphics();
        g2.drawImage(helppic, 0, 0, 23, 23, this);
        g2.dispose();
        ImageIcon newIcon2 = new ImageIcon(dst2);
        
		JButton help = new JButton("Helpu",newIcon2);
		help.setSize(new Dimension(125, 30));
		help.addActionListener(new HelpButtonListener());
		help.setLocation(19, 5);
		
		
		JButton dictionary = new JButton("Vortaro",newIcon);
		dictionary.setSize(new Dimension(125, 30));
		dictionary.addActionListener(new DictionaryButtonListener());
		dictionary.setLocation(850, 5);
		
		bottomPanel.add(help);
		bottomPanel.add(dictionary);
		
		fullBar.add(topPanel);
		fullBar.add(_gameBoard);
		fullBar.add(enterAnswers);
		fullBar.add(bottomPanel);
		fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		add(fullBar, BorderLayout.CENTER);

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
		_score.setText(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");
		return toReturn;

	}

	//When someone types enter, the text they typed is checked and if it
	// is correct then the bottom piece is cleared and the correct answer
	// is updated.  The text that was in the field is highlighted.
	private class TextListener implements ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent e){
			String text = _textField.getText();
			if(checkAnswer(text)){
				_textField.setBackground(new Color(100, 200, 100, 255));
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
			_score.setText(_vocabLevel.getScore() + "/" + _vocabLevel.getNecessaryScore()+"       ");
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
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_vocabLevel.decrementScore(2);
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_vocabLevel.getHelp(), 0, _vocabLevel.getLevelNum(), _driver);
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