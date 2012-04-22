package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGrammarGame extends JPanel{
	
	private JLabel _picLabel, _currNumLabel, _totalNumLabel;
	private int _maxChars;

	public GUIGrammarGame(){
		super(new BorderLayout());

		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(100,110,255,255));
		
		//Constants
		int picheight = 300;
		int picwidth = 400;
		_maxChars = 50;
		
		String picPath = "data/funpic2.gif";
		String partial = "The ~0~ ate the bird";
		String correct[] = {"cat"};
		String possibilities[] = {"cat", "chair", "bee", "wasp", "frog"};
		
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
			System.out.println("Could not read the image");
			System.exit(0);
		}
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(0,0,0,255));
		Box mainVertical = Box.createVerticalBox();
		overall.add(mainVertical, BorderLayout.CENTER);
		
		Box topHoriz = Box.createHorizontalBox();
		Box picHoriz = Box.createHorizontalBox();
		Box phraseHoriz = Box.createHorizontalBox();
		Box choiceHoriz = Box.createHorizontalBox();
		Box submitHoriz = Box.createHorizontalBox();
		
		mainVertical.add(Box.createVerticalStrut(20));
		mainVertical.add(topHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(picHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(phraseHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(choiceHoriz);
		mainVertical.add(Box.createVerticalStrut(10));
		mainVertical.add(submitHoriz);
		mainVertical.add(Box.createVerticalStrut(20));
		
		topHoriz.add(Box.createHorizontalStrut(750));
		_currNumLabel = new JLabel("1");
		_totalNumLabel = new JLabel("10");
		JLabel outofLabel = new JLabel(" of ");
		_currNumLabel.setFont(new Font("Century", Font.BOLD, 25));
		_totalNumLabel.setFont(new Font("Century", Font.BOLD, 25));
		outofLabel.setFont(new Font("Century", Font.BOLD, 25));
		topHoriz.add(_currNumLabel);
		topHoriz.add(outofLabel);
		topHoriz.add(_totalNumLabel);
		topHoriz.add(Box.createHorizontalStrut(30));
		
		picHoriz.add(_picLabel);
		GUIGrammarChoicePanel choicePanel = makeSentanceBox("The ~0~ ate the cat how funny is that ~1~ what will we do with the hat ~2~ that swallowed the cat.");
		phraseHoriz.add(choicePanel);
		
		ArrayList<JLabel> blankLabels = choicePanel.getBlanks();
		
		//create a rectangle for each of the
		
		//choice Horiz
		
		JButton submit = new JButton("Submit Answer");
		submit.addActionListener(new SubmitListener());
		submitHoriz.add(submit);
		
		Box topBar = Box.createHorizontalBox();
		topBar.setBackground(new Color(0,0,0,255));
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));

		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
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
			System.out.println("Error, grammar game sentance too long");
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
					JLabel blank = new JLabel("          ");//put a blank in the line
					line1.push(blank);
					spaces.add(blank);//add blank label to list of blanks
					currNum += 10;
				} else{//otherwise, add word to line 1
					currString += last;
					currNum += last.length();
				}
			}
			
			System.out.println("made it past cho");
			
			//when you have finished line 1
			while(!words.isEmpty()){
				String last;
				if((last = words.removeLast()) == null){
					JLabel phrase = new JLabel(currString);//put the beginning phrase in line
					currString = "";
					line2.push(phrase);
					JLabel blank = new JLabel("          ");//put a blank in the line
					line2.push(blank);
					spaces.add(blank);//add blank label to list of blanks
				} else{
					currString += last;;
				}
			}
			
			//make the last section into a label in line2
			if(!currString.equals("")){
				JLabel phrase = new JLabel(currString);
				line2.add(phrase);
			}
			
			Box line1Box = Box.createHorizontalBox();
			Box line2Box = Box.createHorizontalBox();
			
			line1Box.add(Box.createHorizontalStrut(20));
			while(!line1.isEmpty()){
				line1Box.add(line1.getLast());
			}
			line1Box.add(Box.createHorizontalStrut(20));
			
			line2Box.add(Box.createHorizontalStrut(20));
			while(!line2.isEmpty()){
				line2Box.add(line2.getLast());
			}
			line2Box.add(Box.createHorizontalStrut(20));
			
			vertBox.add(Box.createVerticalStrut(10));
			vertBox.add(line1Box);
			vertBox.add(Box.createVerticalStrut(10));
			vertBox.add(line2Box);
			vertBox.add(Box.createVerticalStrut(10));
			
			//TODO:make the actual box
		} else{//Only 1 line
			LinkedList<JLabel> line1 = new LinkedList<JLabel>();
			String currString = "";
			
			//Just have 1 line to add to
			while(!words.isEmpty()){
				String last;
				if((last = words.removeLast()) == null){
					JLabel phrase = new JLabel(currString);//put the beginning phrase in line
					currString = "";
					line1.add(phrase);
					JLabel blank = new JLabel("          ");//put a blank in the line
					line1.add(blank);
					spaces.add(blank);//add blank label to list of blanks
				} else{
					currString += last;;
				}
			}
			
			Box line1Box = Box.createHorizontalBox();
			
			line1Box.add(Box.createHorizontalStrut(20));
			while(!line1.isEmpty()){
				line1Box.add(line1.getLast());
			}
			line1Box.add(Box.createHorizontalStrut(20));
			
			vertBox.add(Box.createVerticalStrut(20));
			vertBox.add(line1Box);
			vertBox.add(Box.createVerticalStrut(20));
		}
			
		GUIGrammarChoicePanel panel = new GUIGrammarChoicePanel(vertBox, spaces);
		return panel;
	}
	
	public Box makePossBox(){
		
		
		return null;
	}
	
	public class SubmitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//check answers
		}
	}

}
