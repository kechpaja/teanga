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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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
		
		//phrase Horiz
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
		
		makeSentanceBox("The ~0~ ate the cat how funny is that ~1~ what will we do with the hat ~2~ that swallowed the cat.");
	}
	
	public Box makeSentanceBox(String partialSentance){//update to include JTextFields for drag and drop (as opposed to blanks)
		
		int numChars = partialSentance.length();
		Box vertBox = Box.createVerticalBox();
		String actSent[] = partialSentance.split(",");//Need to fix this to be ~1~
		
		if(numChars > _maxChars*2){
			System.out.println("Error, grammar game sentance too long");
		} else if(numChars > _maxChars){
			Box line1 = Box.createHorizontalBox();
			Box line2 = Box.createHorizontalBox();
			
			int currLength = actSent[0].length();
			String string1 = actSent[0];
			String string2 = "";
			
			for(int i = 1; i<actSent.length; i++){
				
				if(currLength < _maxChars){
					string1 = string1 + "          ";
					currLength = currLength+10;
					if(currLength + actSent[i].length() < _maxChars){
						string1 = string1 + actSent[i];
						currLength = currLength + actSent[i].length();
					} else{
						String partSent[] = actSent[i].split(" ");
						int j = 0;
						while(currLength < _maxChars && j < partSent.length){
							string1 = string1 + " " + partSent[j];
							currLength = currLength + partSent[j].length()+1;
							j++;
						}
						if(j < partSent.length-1){
							string1 = string1 + " ";
						}
						while(j < partSent.length){
							string2 = string2 + " " + partSent[j];
							currLength = currLength + partSent[j].length()+1;
							j++;
						}
					}
				} else{
						string2 = string2 + "          " + actSent[i];
						currLength = currLength + 10 + actSent[i].length();
				}
			}
			JLabel string1Label = new JLabel(string1);
			line1.add(string1Label);
			JLabel string2Label = new JLabel(string2);
			line2.add(string2Label);
			
			vertBox.add(Box.createVerticalStrut(20));
			vertBox.add(line1);
			vertBox.add(Box.createVerticalStrut(10));
			vertBox.add(line2);
			vertBox.add(Box.createVerticalStrut(20));
			
		} else{
			Box line = Box.createHorizontalBox();
			String string1 = actSent[0];
			for(int i = 1; i< actSent.length; i++){
				string1 = string1 + "          " + actSent[i];
			}
			JLabel label = new JLabel(string1);
			line.add(label);
			vertBox.add(Box.createVerticalStrut(30));
			vertBox.add(line);
			vertBox.add(Box.createVerticalStrut(30));
			
		}
		
		return vertBox;
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
