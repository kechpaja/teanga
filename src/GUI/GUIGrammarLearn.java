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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ELearning.Driver;
import ELearning.GrammarLessonPair;

public class GUIGrammarLearn extends GUIFoundationPage{
	Driver _driver;
	int _levelNum;
	private JLabel _userName;
	private boolean _even;
	private JPanel _forHelpBox, _toReturnTo;
	
	public GUIGrammarLearn(int ln, Driver d, JPanel toReturnTo){
		super(d, true);
		
		_driver = d;
		_levelNum = ln;
		_forHelpBox = this;
		_toReturnTo = toReturnTo;
		
		//help box
		setHelp(new HelpButtonListener());
		
		//back button
		setBack(new BacktoOptionsActionListener(),"Reiru",true);
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(238,238,238,255));
		
		//Make the title
		Box vertBox = Box.createVerticalBox();
		
		Box titleBox = Box.createHorizontalBox();
		String label = "Level " + Integer.toString(_levelNum+1) + " Grammar";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		titleBox.add(Box.createHorizontalStrut(315));
		titleBox.add(title);
		titleBox.add(Box.createHorizontalStrut(315));
		vertBox.add(titleBox);
		
		
		int prefHeight = 100;
		int prefWidth = 150;
				
		vertBox.add(Box.createVerticalStrut(10));
		List<GrammarLessonPair> grammarLessonPairs= _driver.getLessons().getGLessons(_levelNum);
		for(GrammarLessonPair glp : grammarLessonPairs){
			
			//picVert
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(new File(glp.getPicturePath()));
			} catch (IOException e) {
				String errorMessage = "There was an error finding some of the files necessary \n to run ELearning. You may need to redownload the program.";
				JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			
				int type = BufferedImage.TYPE_INT_ARGB;
		        BufferedImage dst = new BufferedImage(prefWidth, prefHeight, type);
		        Graphics2D g1 = dst.createGraphics();
		        g1.drawImage(pic, 0, 0, prefWidth, prefHeight, this);
		        g1.dispose();
		        ImageIcon newIcon = new ImageIcon(dst);
		        JLabel picLabel = new JLabel(newIcon);
			
			
			//sentenceVert
			JLabel sentenceLabel = new JLabel("\"" + glp.getExampleSentence() + "\"");
			sentenceLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			
			//Explanation
			JTextArea explinArea = new JTextArea(glp.getExplanation());
			explinArea.setEditable(false);
			explinArea.setBackground(new Color(0,0,0,0));
			explinArea.setBorder(BorderFactory.createEmptyBorder());
			explinArea.setFont(new Font("Cambria", Font.PLAIN, 20));
			explinArea.setLineWrap(true);
			explinArea.setWrapStyleWord(true);
			
			JPanel picpanel = new JPanel(null);
			JPanel pan = new JPanel(new BorderLayout());
			pan.setMaximumSize(new Dimension(960,500));

			picpanel.setPreferredSize(new Dimension(950, 170));
			
			if(_even){
				picpanel.setBackground(new Color(245,245,245,255));
				pan.setBackground(new Color(245,245,245,255));
				explinArea.setBackground(new Color(245,245,245,255));
				_even = false;
			} else{
				picpanel.setBackground(new Color(231,231,231,255));
				pan.setBackground(new Color(231,231,231,255));
				explinArea.setBackground(new Color(231,231,231,255));
				_even = true;
			}
			
			picpanel.add(picLabel);
			picLabel.setSize(150,100);
			picLabel.setLocation(150, 30);
			
			picpanel.add(sentenceLabel);
			sentenceLabel.setSize(500,20);
			sentenceLabel.setLocation(400, 70);
			
			Box explinHoriz = Box.createHorizontalBox();
			Box explinBox = Box.createVerticalBox();
			explinBox.add(explinArea);
			explinBox.add(Box.createVerticalStrut(20));
			explinHoriz.add(Box.createHorizontalStrut(20));
			explinHoriz.add(explinBox);
			explinHoriz.add(Box.createHorizontalStrut(20));
			
			pan.add(picpanel, BorderLayout.NORTH);
			pan.add(explinHoriz, BorderLayout.CENTER);

			vertBox.add(pan);
		}
		
		overall.add(vertBox);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(1000,594));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(18,Integer.MAX_VALUE));
		scrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(scrollbar, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);
	}
	
	private class BacktoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (_toReturnTo == null){
				_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			} else _driver.changePage(_toReturnTo);
		}
		
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HelpBoxInternalFrame(_driver.getHelpBox().getGLessHelp(_levelNum), 
																		1, _levelNum, _driver, _forHelpBox);
		}
	}
	
}
