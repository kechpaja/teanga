package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class GUIGrammarLearn extends JPanel{
	
	public GUIGrammarLearn(){
		super(new BorderLayout());
		this.setBackground(new Color(50,50,100,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		
		//Make the title
		int levelnum = 1;
		String label = "Level " + Integer.toString(levelnum) + " Grammar";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		
		overall.add(title, BorderLayout.NORTH);
		Box vertBox = Box.createVerticalBox();
		
		JScrollPane main = new JScrollPane(vertBox);
		main.setSize(1000,700);
		main.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		main.getVerticalScrollBar().setUnitIncrement(16);
		main.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		int prefHeight = 100;
		int prefWidth = 150;
		int numWords = 10;
		String picPath = "data/funpic2.gif";
		String sentence = "All of the words on the page started to blend toghether (but in spanish)";
		String expln2 = "WASHINGTON—With unemployment at its highest level in decades, the U.S. Department of Labor issued a report Tuesday suggesting the crisis is primarily the result of millions of Americans just completely blowing their job interviews.";
        String expln1 = "According to the findings, seven out of 10 Americans could have landed their dream job last month if they had known where they see themselves in five years, and the number of unemployed could be reduced from 14.6 million to 5 ";
        String expln3 = "million if everyone simply greeted potential employers with firmer handshakes, maintained eye contact, and stopped fiddling with their hair and face so much.";
        String expln35 = "This economy will not recover until job candidates learn how to put their best foot forward,";
        String expln4 = "said Labor Secretary Hilda Solis, warning that even a small increase in stuttering among applicants who are asked to describe their weaknesses could cause the entire labor market to collapse. If were going to dig ourselves out of this mess, Americans need to stop wearing blue jeans to interviews, even if theyre nice blue jeans, and even if that particular office happens to have a relaxed dress code.";
		
        String explanation = expln2 + "\n\n " + expln1 + " " + expln3 + "\n\n " + expln35 + " " + expln4;
		vertBox.add(Box.createVerticalStrut(10));
		for(int i = 0; i < numWords; i++){
			
			Box currVert = Box.createVerticalBox();
			Box currHoriz = Box.createHorizontalBox();
			
			//picVert
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(new File(picPath));
			} catch (IOException e) {
				System.out.println("Cannot read image (GUIVocabLearn)");
				System.exit(0);
			}
			int type = BufferedImage.TYPE_INT_RGB;
	        BufferedImage dst = new BufferedImage(prefWidth, prefHeight, type);
	        Graphics2D g1 = dst.createGraphics();
	        g1.drawImage(pic, 0, 0, prefWidth, prefHeight, this);
	        g1.dispose();
	        ImageIcon newIcon = new ImageIcon(dst);
	        JLabel picLabel = new JLabel(newIcon);
	        
	        currHoriz.add(Box.createHorizontalStrut(10));
	        currHoriz.add(picLabel);
	        currHoriz.add(Box.createHorizontalStrut(10));
			
			//sentenceVert
			Box sentVert = Box.createVerticalBox();
			Box sentHoriz = Box.createHorizontalBox();
			JLabel sentenceLabel = new JLabel("\"" + sentence + "\"");
			sentenceLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			sentVert.add(sentenceLabel);
			sentVert.add(Box.createVerticalStrut(5));
			sentHoriz.add(Box.createVerticalStrut(110));
			sentHoriz.add(sentVert);
			
			currHoriz.add(sentHoriz);
			currHoriz.add(Box.createHorizontalStrut(10));
			
			//Explanation
			Box explinHoriz = Box.createHorizontalBox();
			JTextArea explinArea = new JTextArea();
			explinArea.setText(explanation);
			explinArea.setEditable(false);
			explinArea.setBackground(new Color(0,0,0,0));
			explinArea.setFont(new Font("Cambria", Font.PLAIN, 20));
			explinArea.setLineWrap(true);
			explinArea.setWrapStyleWord(true);
			explinHoriz.add(Box.createHorizontalStrut(10));
			explinHoriz.add(explinArea);
			explinHoriz.add(Box.createHorizontalStrut(10));
			
			currVert.add(currHoriz);
			currVert.add(Box.createVerticalStrut(2));
			currVert.add(explinHoriz);
			
			vertBox.add(currVert);
			vertBox.add(Box.createVerticalStrut(50));
		}
		
		overall.add(main, BorderLayout.CENTER);

		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));
		
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		
	}

	public static void main(String[] args){
		GUIGrammarLearn panel = new GUIGrammarLearn();
		JFrame frame = new JFrame("Grammar Learning");
		frame.setPreferredSize(new Dimension(1000,700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
