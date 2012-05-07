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
import javax.swing.JViewport;
import javax.swing.SwingConstants;

import ELearning.Driver;
import ELearning.VocabLessonPair;
import GUI.GUIGrammarGame.backtoOptionsActionListener;
import GUI.GUIOptionsPage.BacktoBasicActionListener;

public class GUIVocabLearn extends JPanel{
	Driver _driver;
	int _levelNum;
	private JLabel _userName;
	private boolean _even;
	
	public GUIVocabLearn(int ln, Driver d){
		super(new BorderLayout());
		this.setBackground(new Color(50,50,50,255));
		
		_driver = d;
		_levelNum = ln;
		_even = true;
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(238,238,238,255));
		
		Box vertBox = Box.createVerticalBox();
		//Make the title
		Box titleBox = Box.createHorizontalBox();
		String label = "Level " + Integer.toString(_levelNum+1) + " Vocabulary";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		title.setBackground(new Color(238,238,238,255));
		titleBox.add(title);
		
		vertBox.add(titleBox);
		
		
		int prefHeight = 100;
		int prefWidth = 150;
		List<VocabLessonPair> vocabLessonPairs= _driver.getLessons().getVLessons(_levelNum);
		
		
		for(VocabLessonPair vlp : vocabLessonPairs){
			
			//picVert
			BufferedImage pic = null;
			try {
				System.out.println(vlp.getPicturePath());
				pic = ImageIO.read(new File(vlp.getPicturePath()));
			} catch (IOException e) {}
			
			JPanel panel = new JPanel(null);
			
			if(pic != null){
				int type = BufferedImage.TYPE_INT_RGB;
		        BufferedImage dst = new BufferedImage(prefWidth, prefHeight, type);
		        Graphics2D g1 = dst.createGraphics();
		        g1.drawImage(pic, 0, 0, prefWidth, prefHeight, this);
		        g1.dispose();
		        ImageIcon newIcon = new ImageIcon(dst);
		        JLabel picLabel = new JLabel(newIcon);
		        
				panel.add(picLabel);
				picLabel.setLocation(80, 10);
				picLabel.setSize(150,100);
			}
			
			//wordnTranslate
			JLabel wordLabel = new JLabel(vlp.getVocabWord());
			wordLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			
			JLabel translateLabel = new JLabel(vlp.getVocabTranslation());
			translateLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			
			//sentenceVert
			JLabel sentenceLabel = new JLabel(vlp.getExampleSentence());
			sentenceLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			
			panel.setPreferredSize(new Dimension(950,120));
			if(_even){
				panel.setBackground(new Color(245,245,245,255));
				_even = false;
			} else{
				panel.setBackground(new Color(231,231,231,255));
				_even = true;
			}
			
			panel.add(wordLabel);
			wordLabel.setLocation(300, 35);
			wordLabel.setSize(140,25);
			
			panel.add(translateLabel);
			translateLabel.setLocation(300, 65);
			translateLabel.setSize(140,25);
			
			panel.add(sentenceLabel);
			sentenceLabel.setLocation(500, 50);
			sentenceLabel.setSize(400,25);
			
			vertBox.add(panel);
		}
		
		overall.add(vertBox);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(1000,594));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(18,Integer.MAX_VALUE));
		
		//Create boundary Panels and put it all together
		Box fullBar = Box.createVerticalBox();
		
		//Top Panel
		JPanel topPanel = new JPanel(null);
		topPanel.setPreferredSize(new Dimension(950,35));
		topPanel.setBackground(new Color(50,50,50,255));
		
		Box userBox = Box.createHorizontalBox();
		JLabel _un = new JLabel(_driver.getPlayerStats().getUsername());
		_un.setFont(new Font("Cambria", Font.PLAIN, 20));
		_un.setForeground(Color.white);
		userBox.add(_un);
		userBox.setSize(200,35);
		userBox.setLocation(20, 3);
		
		Box topBar = Box.createHorizontalBox();
		JLabel _score = new JLabel("Total points: "+_driver.getPlayerStats().getPoints());
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		topBar.add(_score);
		topBar.setSize(400,35);
		topBar.setLocation(435, 3);
		
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
		
		JButton back = new JButton("Redonu",newIcon3);
		back.addActionListener(new BacktoOptionsActionListener());
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
		fullBar.add(scrollbar);
		fullBar.add(bottomPanel);
		fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		add(fullBar, BorderLayout.CENTER);
		
	}
	
	public JPanel makePanel(JLabel picture, JLabel word, JLabel translate, JLabel sentence){
		JPanel panel = new JPanel(null);
		panel.setPreferredSize(new Dimension(1000,110));
		if(_even){
			panel.setBackground(new Color(245,245,245,255));
			_even = false;
		} else{
			panel.setBackground(new Color(231,231,231,255));
			_even = true;
		}
		
		picture.setLocation(30, 5);
		picture.setVisible(true);
		word.setLocation(200, 40);
		word.setVisible(true);
		translate.setLocation(200, 58);
		translate.setVisible(true);
		sentence.setLocation(400, 50);
		sentence.setVisible(true);
		
		panel.add(picture);
		panel.add(word);
		panel.add(translate);
		panel.add(sentence);
		panel.repaint();
		
		return panel;
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
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_driver.getHelpBox().getVLessHelp(_levelNum), 
																		1, _levelNum, _driver);
		}
		
	}
}
