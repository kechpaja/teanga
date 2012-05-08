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
import GUI.GUIOptionsPage.BacktoBasicActionListener;

public class GUIVocabLearn extends GUIFoundationPage{
	Driver _driver;
	int _levelNum;
	private JLabel _userName;
	private boolean _even;
	private JPanel _forHelpBox, _toReturnTo;
	
	public GUIVocabLearn(int ln, Driver d, JPanel toReturnTo){
		super(d, true);
		
		_forHelpBox = this;
		_driver = d;
		_levelNum = ln;
		_even = true;
		_toReturnTo = toReturnTo;
		
		//help box
		setHelp(new HelpButtonListener());
		
		//back button
		setBack(new BacktoOptionsActionListener(),"Reiru", true);
		
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
		
		//make the main portion of the panel
		int prefHeight = 100;
		int prefWidth = 150;
		List<VocabLessonPair> vocabLessonPairs= _driver.getLessons().getVLessons(_levelNum);
		
		
		for(VocabLessonPair vlp : vocabLessonPairs){
			
			//picVert
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(new File(vlp.getPicturePath()));
			} catch (IOException e) {}
			
			JPanel panel = new JPanel(null);
			
			if(pic != null){
				int type = BufferedImage.TYPE_INT_ARGB;
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
			
			panel.setPreferredSize(new Dimension(970,120));
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
		scrollbar.setPreferredSize(new Dimension(994,595));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(scrollbar, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);
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
			if (_toReturnTo == null){
				_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
			} else {
				GUIVocabGame gvg = (GUIVocabGame) _toReturnTo;
				gvg.restart();
				_driver.changePage(gvg);

			}
		}
		
	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HelpBoxInternalFrame(_driver.getHelpBox().getVLessHelp(_levelNum), 
																		1, _levelNum, _driver, _forHelpBox);
		}
		
	}
}
