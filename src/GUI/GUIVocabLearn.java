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
import javax.swing.SwingConstants;

import ELearning.Driver;
import ELearning.VocabLessonPair;
import GUI.GUIOptionsPage.BacktoBasicActionListener;

public class GUIVocabLearn extends JPanel{
	Driver _driver;
	int _levelNum;
	private JLabel _userName;
	
	public GUIVocabLearn(int ln, Driver d){
		super(new BorderLayout());
		this.setBackground(new Color(50,50,100,255));
		
		_driver = d;
		_levelNum = ln;
		JPanel overall = new JPanel(new BorderLayout());
		
		//Make the title
		String label = "Level " + Integer.toString(_levelNum+1) + " Vocabulary";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		
		overall.add(title, BorderLayout.NORTH);
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(20));
		
		Box picVert = Box.createVerticalBox();
		Box wordnTranslate = Box.createVerticalBox();
		Box sentenceVert = Box.createVerticalBox();
		
		horizBox.add(picVert);
		horizBox.add(wordnTranslate);
		horizBox.add(sentenceVert);
		
		horizBox.add(Box.createHorizontalStrut(20));
		JScrollPane main = new JScrollPane(horizBox);
		main.setSize(1000,700);
		main.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		
		int prefHeight = 100;
		int prefWidth = 150;
		List<VocabLessonPair> vocabLessonPairs= _driver.getLessons().getVLessons(_levelNum);
		
		for(VocabLessonPair vlp : vocabLessonPairs){
			
			//picVert
			BufferedImage pic = null;
			try {
				System.out.println(vlp.getPicturePath());
				pic = ImageIO.read(new File(vlp.getPicturePath()));
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
	        picVert.add(Box.createVerticalStrut(5));
	        picVert.add(picLabel);
	        picVert.add(Box.createVerticalStrut(5));
			
			//wordnTranslate
	        Box wordsTVert = Box.createVerticalBox();
	        Box wordsTHoriz = Box.createHorizontalBox();
	        wordsTHoriz.add(Box.createVerticalStrut(110));
	        wordsTHoriz.add(wordsTVert);
			JLabel wordLabel = new JLabel(vlp.getVocabWord());
			wordLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			wordsTVert.add(wordLabel);
			wordsTVert.add(Box.createVerticalStrut(5));
			JLabel translateLabel = new JLabel(vlp.getVocabTranslation());
			translateLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			wordsTVert.add(translateLabel);
			wordsTVert.add(Box.createVerticalStrut(5));
			wordnTranslate.add(wordsTHoriz);
			
			//sentenceVert
			Box sentVert = Box.createVerticalBox();
			Box sentHoriz = Box.createHorizontalBox();
			JLabel sentenceLabel = new JLabel(vlp.getExampleSentence());
			sentenceLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			sentVert.add(sentenceLabel);
			sentVert.add(Box.createVerticalStrut(5));
			sentHoriz.add(Box.createVerticalStrut(110));
			sentHoriz.add(sentVert);
			sentenceVert.add(sentHoriz);
			
		}
		
				
		
		overall.add(main, BorderLayout.CENTER);
		
		_userName = new JLabel(_driver.getUserName());
		_userName.setFont(new Font("Cambria", Font.PLAIN, 20));
		_userName.setForeground(Color.white);

		Box topBar = Box.createHorizontalBox();
		topBar.add(_userName);
		topBar.add(Box.createHorizontalStrut(830));
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
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame("This page is teaching you some basic vocabulary. Don't worry if you don't understand every word in the example sentence, but feel free to click below to see the previous section", 
																		1, _levelNum, _driver);
		}
		
	}

	public static void main(String[] args){
		/*GUIVocabLearn panel = new GUIVocabLearn();
		JFrame frame = new JFrame();
		GUIVocabLearn panel = new GUIVocabLearn();
		JFrame frame = new JFrame("Vocab Learning");
		frame.setPreferredSize(new Dimension(1000,700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);*/
	}
}
