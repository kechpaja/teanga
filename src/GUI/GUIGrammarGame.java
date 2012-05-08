package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ELearning.Driver;
import ELearning.GrammarLevel;

public class GUIGrammarGame extends GUIFoundationPage{
	
	private JLabel _picLabel, _currNumLabel, _totalNumLabel, outofLabel;
	private int _maxChars, picheight, picwidth, _mywidth;
	private ArrayList<GUIGrammarBlank> _rectBlanks;
	private GUIGrammarChoicePanel _choicePanel;
	private GrammarLevel _grammarLevel;
	private Driver _driver;
	private JPanel _panel;
	private Box _horizontalChoice,topHoriz;
	private JLabel _userName, _score;
	private Box picHoriz;

	public GUIGrammarGame(GrammarLevel gl, Driver d){
		super(d, true);

		_panel = this;
		_driver = d;
		_grammarLevel = gl;
		
		//help box
		setHelp(new HelpButtonListener());
		setDictionary(new DictionaryButtonListener());
		
		//back button
		setBack(new backtoOptionsActionListener(),"Reiru",true);
		
		//Constants
		picheight = 200;
		picwidth = 300;
		_maxChars = 60;
		
		//get width
		String word = "                   ";
		JLabel label = new JLabel(word);
		FontMetrics metrics = label.getFontMetrics(new Font("Cambria",Font.PLAIN,30));
		_mywidth = SwingUtilities.computeStringWidth(metrics, word);
		
		
		String picPath = gl.getCurrent().getPicturePath();
		
		_picLabel = null;
		
		try {
			BufferedImage pic = ImageIO.read(new File(picPath));
			int type = BufferedImage.TYPE_INT_RGB;
	        BufferedImage dst = new BufferedImage(picwidth, picheight, type);
	        Graphics2D g1 = dst.createGraphics();
	        g1.drawImage(pic, 0, 0, picwidth, picheight, this);
	        g1.dispose();
	        ImageIcon newIcon = new ImageIcon(dst);
	        _picLabel = new JLabel(newIcon);
		} catch (IOException e) {
			_picLabel = null;
		}
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(238,238,238,255));
		Box mainVertical = Box.createVerticalBox();
		overall.add(mainVertical, BorderLayout.CENTER);
		
		topHoriz = Box.createHorizontalBox();
		picHoriz = Box.createHorizontalBox();
		_horizontalChoice = Box.createHorizontalBox();
		Box submitHoriz = Box.createHorizontalBox();
		
		mainVertical.add(Box.createVerticalStrut(20));
		mainVertical.add(topHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(picHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(_horizontalChoice);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(submitHoriz);
		mainVertical.add(Box.createVerticalStrut(20));
		
		topHoriz.add(Box.createHorizontalStrut(750));
		_currNumLabel = new JLabel(Integer.toString(_grammarLevel.getCurrentNum()));
		_totalNumLabel = new JLabel(Integer.toString(_grammarLevel.getTotalNum()));
		outofLabel = new JLabel(" de ");
		_currNumLabel.setFont(new Font("Century", Font.BOLD, 25));
		_totalNumLabel.setFont(new Font("Century", Font.BOLD, 25));
		outofLabel.setFont(new Font("Century", Font.BOLD, 25));
		topHoriz.add(_currNumLabel);
		topHoriz.add(outofLabel);
		topHoriz.add(_totalNumLabel);
		topHoriz.add(Box.createHorizontalStrut(30));
		
		if(_picLabel != null){
			picHoriz.add(_picLabel);
		}
		_choicePanel = makeSentanceBox(gl.getCurrent().getPartialSentence());
		_horizontalChoice.add(_choicePanel);
		
		JButton submit = new JButton("Submetiĝu");
		submit.addActionListener(new SubmitListener());
		submitHoriz.add(submit);
		submit.setSize(120,35);
		JButton skip = new JButton("Saltu");
		skip.setSize(120,35);
		skip.addActionListener(new SkipListener());
		submitHoriz.add(skip);

		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(overall, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);
		
	}
	
	public GUIGrammarChoicePanel makeSentanceBox(String partialSentance){//update to include JTextFields for drag and drop (as opposed to blanks)
		
		int numChars = partialSentance.length();
		Box vertBox = Box.createVerticalBox();
		String actSent[] = partialSentance.split("~");//Need to fix this to be ~1~
		LinkedList<String> words = new LinkedList<String>();
		
		//parse the sentence into words with null's representing blanks
		for(int i = 0; i < actSent.length; i++){
			if(i%2 == 1){
				words.push(null);//if it is a blank add a null
			} else{
				String smallWs[] = actSent[i].split(" ");
				
				//add strings 1 word at a time
				for(int j=0; j < smallWs.length; j++){
					words.push(smallWs[j]);
				}
				
			}
		}
		
		ArrayList<JLabel> spaces = new ArrayList<JLabel>();
		
		if(numChars > _maxChars*2){//too long
			//go to next exercise
		} else if(numChars > _maxChars){//will need 2 lines
			LinkedList<JLabel> line1 = new LinkedList<JLabel>();
			LinkedList<JLabel> line2 = new LinkedList<JLabel>();
			int currNum = 0;
			String currString = "";
			
			//fill in line1
			while(currNum < _maxChars && !words.isEmpty()){
				String last;
				if((last = words.removeLast()) == null){//when you get to a blank
					JLabel phrase = new JLabel(currString);//put the beginning phrase in line
					currString = "";
					line1.push(phrase);
					JLabel blank = new JLabel("                   ");//put a blank in the line
					blank.setFont(new Font("Cambria", Font.PLAIN, 30));
					line1.push(blank);
					spaces.add(blank);//add blank label to list of blanks
					currNum += 10;
				} else{//otherwise, add word to line 1
					currString += last + " ";
					currNum += last.length();
				}
			}
			
			if(!currString.equals("")){
				line2.push(new JLabel(currString));
				currString = "";
			}
			
			//when you have finished line 1
			while(!words.isEmpty()){
				String last;
				if((last = words.removeLast()) == null){
					JLabel phrase = new JLabel(currString);//put the beginning phrase in line
					currString = "";
					line2.push(phrase);
					JLabel blank = new JLabel("                   ");//put a blank in the line
					blank.setFont(new Font("Cambria", Font.PLAIN, 30));
					line2.push(blank);
					spaces.add(blank);//add blank label to list of blanks
				} else{
					currString += last + " ";
				}
			}
			
			//make the last section into a label in line2
			if(!currString.equals("")){
				JLabel phrase = new JLabel(currString);
				line2.push(phrase);
			}
			
			Box line1Box = Box.createHorizontalBox();
			Box line2Box = Box.createHorizontalBox();
			
			line1Box.add(Box.createHorizontalStrut(20));
			while(!line1.isEmpty()){
				JLabel currLabel = line1.removeLast();
				currLabel.setFont(new Font("Century", Font.BOLD, 25));
				line1Box.add(currLabel);
			}
			line1Box.add(Box.createHorizontalStrut(20));
			line2Box.add(Box.createHorizontalStrut(20));
			while(!line2.isEmpty()){
				JLabel currLabel = line2.removeLast();
				currLabel.setFont(new Font("Century", Font.BOLD, 25));
				line2Box.add(currLabel);
			}
			line2Box.add(Box.createHorizontalStrut(20));
			
			vertBox.add(Box.createVerticalStrut(10));
			vertBox.add(line1Box);
			vertBox.add(Box.createVerticalStrut(10));
			vertBox.add(line2Box);
			vertBox.add(Box.createVerticalStrut(10));
			
		} else{//Only 1 line
			LinkedList<JLabel> line1 = new LinkedList<JLabel>();
			String currString = "";
			
			while(!words.isEmpty()){
				String last;
				if((last = words.removeLast()) == null){
					JLabel phrase = new JLabel(currString);//put the beginning phrase in line
					currString = "";
					line1.push(phrase);
					JLabel blank = new JLabel("                   ");//put a blank in the line
					blank.setFont(new Font("Cambria", Font.PLAIN, 30));
					line1.push(blank);
					spaces.add(blank);//add blank label to list of blanks
				} else{
					currString += last + " ";
				}
			}
			
			//make the last section into a label in line2
			if(!currString.equals("")){
				JLabel phrase = new JLabel(currString);
				line1.push(phrase);
			}
			
			Box line1Box = Box.createHorizontalBox();
			
			line1Box.add(Box.createHorizontalStrut(20));
			while(!line1.isEmpty()){
				JLabel currLabel = line1.removeLast();
				currLabel.setFont(new Font("Century", Font.BOLD, 25));
				line1Box.add(currLabel);
			}
			line1Box.add(Box.createHorizontalStrut(20));
			
			vertBox.add(Box.createVerticalStrut(20));
			vertBox.add(line1Box);
			vertBox.add(Box.createVerticalStrut(20));
		}
		
			
		GUIGrammarChoicePanel panel = new GUIGrammarChoicePanel(this, vertBox, spaces, _grammarLevel, _mywidth);
		return panel;
	}
	
	private class SkipListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_grammarLevel.skipCurrent();
			if (_grammarLevel.isOver()){
				_driver.getPlayerStats().RefreshStats(_grammarLevel.getLevelNum(), 1, _grammarLevel.getScore());
				_driver.changePage(new GUIGameCompleted(_driver, _grammarLevel));
			} else {
				//update choicePanel
				_horizontalChoice.removeAll();
				_choicePanel = makeSentanceBox(_grammarLevel.getCurrent().getPartialSentence());
				_horizontalChoice.add(_choicePanel);
				//and update the current number
				topHoriz.removeAll();
				topHoriz.add(Box.createHorizontalStrut(750));
				_currNumLabel = new JLabel(Integer.toString(_grammarLevel.getCurrentNum()));
				_totalNumLabel = new JLabel(Integer.toString(_grammarLevel.getTotalNum()));
				outofLabel = new JLabel(" de ");
				_currNumLabel.setFont(new Font("Century", Font.BOLD, 25));
				_totalNumLabel.setFont(new Font("Century", Font.BOLD, 25));
				outofLabel.setFont(new Font("Century", Font.BOLD, 25));
				topHoriz.add(_currNumLabel);
				topHoriz.add(outofLabel);
				topHoriz.add(_totalNumLabel);
				topHoriz.add(Box.createHorizontalStrut(30));
				String picPath = _grammarLevel.getCurrent().getPicturePath();
				
				_picLabel = null;
				
				try {
					BufferedImage pic = ImageIO.read(new File(picPath));
					int type = BufferedImage.TYPE_INT_RGB;
			        BufferedImage dst = new BufferedImage(picwidth, picheight, type);
			        Graphics2D g1 = dst.createGraphics();
			        g1.drawImage(pic, 0, 0, picwidth, picheight, _panel);
			        g1.dispose();
			        ImageIcon newIcon = new ImageIcon(dst);
			        _picLabel = new JLabel(newIcon);
	
				} catch (IOException e2) {
				}
				
				picHoriz.removeAll();
				if(_picLabel != null){
					picHoriz.add(_picLabel);
				}
				_panel.revalidate();
				
			}
			updateScore(_grammarLevel.getScore() + "/" + _grammarLevel.getNecessaryScore());
			
		}
		
	}
	
	private class SubmitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			_rectBlanks = _choicePanel.getBlanks();
			if (_grammarLevel.submitWhole()){
				//check if this was the last unit
				if (_grammarLevel.isOver()){
					_driver.getPlayerStats().RefreshStats(_grammarLevel.getLevelNum(), 1, _grammarLevel.getScore());
					_driver.changePage(new GUIGameCompleted(_driver, _grammarLevel));
				} else {
					//update choicePanel
					_horizontalChoice.removeAll();
					_choicePanel = makeSentanceBox(_grammarLevel.getCurrent().getPartialSentence());
					_horizontalChoice.add(_choicePanel);
					//and update the current number
					topHoriz.removeAll();
					topHoriz.add(Box.createHorizontalStrut(750));
					_currNumLabel = new JLabel(Integer.toString(_grammarLevel.getCurrentNum()));
					_totalNumLabel = new JLabel(Integer.toString(_grammarLevel.getTotalNum()));
					outofLabel = new JLabel(" de ");
					_currNumLabel.setFont(new Font("Century", Font.BOLD, 25));
					_totalNumLabel.setFont(new Font("Century", Font.BOLD, 25));
					outofLabel.setFont(new Font("Century", Font.BOLD, 25));
					topHoriz.add(_currNumLabel);
					topHoriz.add(outofLabel);
					topHoriz.add(_totalNumLabel);
					topHoriz.add(Box.createHorizontalStrut(30));
					
					String picPath = _grammarLevel.getCurrent().getPicturePath();
					
					_picLabel = null;
					
					try {
						BufferedImage pic = ImageIO.read(new File(picPath));
						int type = BufferedImage.TYPE_INT_RGB;
				        BufferedImage dst = new BufferedImage(picwidth, picheight, type);
				        Graphics2D g1 = dst.createGraphics();
				        g1.drawImage(pic, 0, 0, picwidth, picheight, _panel);
				        g1.dispose();
				        ImageIcon newIcon = new ImageIcon(dst);
				        _picLabel = new JLabel(newIcon);
		
					} catch (IOException e2) {
					}
					
					picHoriz.removeAll();
					if(_picLabel != null){
						picHoriz.add(_picLabel);
					}
					_panel.revalidate();
					
					
				}
			} else {
				GUIGrammarGame reloaded = new GUIGrammarGame(_grammarLevel, _driver);
				reloaded.getChoicePanel().notCorrect();
				_driver.changePage(reloaded);
			}
			updateScore(_grammarLevel.getScore() + "/" + _grammarLevel.getNecessaryScore());
		}
	}
	
	public GUIGrammarChoicePanel getChoicePanel(){
		return _choicePanel;
	}
	
	public class backtoOptionsActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.getPlayerStats().RefreshStats(_grammarLevel.getLevelNum(), 1, _grammarLevel.getScore());
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			
		}
		
	}
	
	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_grammarLevel.decrementScore(2);
			updateScore(_grammarLevel.getScore() + "/" + _grammarLevel.getNecessaryScore());
			DictionaryInternalFrame dictFrame = new DictionaryInternalFrame(_driver.getDictionary());

			
		}
		
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_grammarLevel.decrementScore(3);
			updateScore(_grammarLevel.getScore() + "/" + _grammarLevel.getNecessaryScore());
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_grammarLevel.getHelp(), 3, _grammarLevel.getLevelNum(), _driver, _panel);
		}
		
	}

}
