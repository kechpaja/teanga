package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import ELearning.Driver;
import annie.PlayerStats;

public class GUIOptionsPage extends GUIFoundationPage {
	
	private PlayerStats _stats;
	private Driver _driver;
	private JPanel _forHelpBox;
	
	public GUIOptionsPage(Driver driver, PlayerStats  stats){
		super(driver, true);
		try {
			stats.encode();
		} catch (IllegalBlockSizeException e) {
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			
		} catch (BadPaddingException e) {
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);			
			System.exit(0);
		} catch (IOException e) {
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		_stats = stats;
		_driver = driver;
		_forHelpBox = this;
		
		//help box
		String helpPhrase = "Welcome to ELearning! The page you're looking at contains several vocabulary and grammar lessons (the left two columns), and corresponding games to test your knowledge of them (the next two). Start in the top left, and play the games to unlock later levels.";
		setHelp(new HelpButtonListener());
		
		//back button
		setBack(new BacktoBasicActionListener(),"Reiru", true);
		
		//mainPanel
		int numacts = 0;
		GUIOptionsPanel overall = null;
		
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader("data/optionsData.csv"));
			numacts = Integer.parseInt(fileReader.readLine());
			overall = new GUIOptionsPanel(fileReader, numacts, stats, _driver, this);
			overall.setPreferredSize(new Dimension(994-20,100*numacts+70));
			
		} catch (Exception e) {
			String errorMessage = "There was an error finding some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder());
		scrollbar.setPreferredSize(new Dimension(994,595));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(scrollbar);
		setMainPanel(mainPanel);
		
		//middle button
		addMiddleButton("Pronunciation Guide", 200, new ToPronunciationListener());
		
	}
	
	private class ToPronunciationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIPronunciationGuide(_driver, _stats));			
		}
		
	}
	
	public class BacktoBasicActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIIntroPage(_driver));			
		}

	}
	
	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HelpBoxInternalFrame("Welcome to ELearning! The page you're looking at contains several vocabulary and grammar lessons (the left two columns), and corresponding games to test your knowledge of them (the next two). Start in the top left, and play the games to unlock later levels.", 
																		-1, -1, _driver, _forHelpBox);
		}

	}

}
