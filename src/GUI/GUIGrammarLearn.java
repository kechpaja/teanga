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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ELearning.Driver;
import ELearning.GrammarLessonPair;
import ELearning.VocabLessonPair;

public class GUIGrammarLearn extends JPanel{
	Driver _driver;
	int _levelNum;
	private JLabel _userName;
	
	public GUIGrammarLearn(int ln, Driver d){
		super(new BorderLayout());
		this.setBackground(new Color(50,50,50,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		
		_driver = d;
		//Make the title
		_levelNum = ln;
		String label = "Level " + Integer.toString(_levelNum+1) + " Grammar";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		
		overall.add(title, BorderLayout.NORTH);
		overall.setBackground(new Color(238,238,238,255));
		Box vertBox = Box.createVerticalBox();
		
		JScrollPane main = new JScrollPane(vertBox);
		main.setSize(1000,700);
		main.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		main.getVerticalScrollBar().setUnitIncrement(16);
		main.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		int prefHeight = 100;
		int prefWidth = 150;
				
		vertBox.add(Box.createVerticalStrut(10));
		List<GrammarLessonPair> grammarLessonPairs= _driver.getLessons().getGLessons(_levelNum);
		for(GrammarLessonPair glp : grammarLessonPairs){
			
			Box currVert = Box.createVerticalBox();
			Box currHoriz = Box.createHorizontalBox();
			
			//picVert
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(new File(glp.getPicturePath()));
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
			JLabel sentenceLabel = new JLabel("\"" + glp.getExampleSentence() + "\"");
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
			explinArea.setText(glp.getExplanation());
			explinArea.setEditable(false);
			explinArea.setBackground(new Color(225,225,225,255));
			explinArea.setBorder(BorderFactory.createEmptyBorder());
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
		
		_userName = new JLabel(_driver.getUserName());
		_userName.setFont(new Font("Cambria", Font.PLAIN, 20));
		_userName.setForeground(Color.white);

		Box topBar = Box.createHorizontalBox();
		topBar.add(_userName);
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton back = new JButton("Back");
		back.addActionListener(new BacktoOptionsActionListener());
		back.setSize(new Dimension(75, 35));
		topBar.add(back);
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton help = new JButton("Help");
		JButton dictionary = new JButton("Dictionary");
		dictionary.setSize(new Dimension(75, 35));
		help.setSize(new Dimension(75, 35));
		bottomBar.add(help);
		bottomBar.add(dictionary);
		bottomBar.add(Box.createHorizontalStrut(15));
		help.addActionListener(new HelpButtonListener());
		dictionary.addActionListener(new DictionaryButtonListener());
		
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		
	}
	
	private class BacktoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			
		}
		
	}
	
	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DictionaryInternalFrame dictFrame = new DictionaryInternalFrame(_driver.getDictionary());
		}
		
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame("This page explains some basic grammar concepts! If you're having trouble understanding them, click below to review the previous grammar level.", 
																		1, _levelNum, _driver);
		}
		
	}
	
}
