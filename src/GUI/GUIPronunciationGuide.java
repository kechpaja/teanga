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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ELearning.Driver;
import annie.PlayerStats;

@SuppressWarnings("serial")
public class GUIPronunciationGuide extends JPanel{
	
	private Driver _driver;
	private PlayerStats _stats;
	private JPanel _forHelpBox;

	public GUIPronunciationGuide(Driver d, PlayerStats stats){
		super(new BorderLayout());
		Box fullBar = Box.createVerticalBox();
		_driver = d;
		_stats = stats;
		_forHelpBox = this;
		
		BufferedImage guidePic = null;
		try {
			guidePic = ImageIO.read(new File("data/pronunciationGuide.png"));
		} catch (IOException e){
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		int guideBItype = BufferedImage.TYPE_INT_ARGB;
        BufferedImage guideBI = new BufferedImage(924, 1046, guideBItype);
        Graphics2D guideBrush = guideBI.createGraphics();
        guideBrush.drawImage(guidePic, 0, 0, 924, 1046, this);
        guideBrush.dispose();
        ImageIcon guideIcon = new ImageIcon(guideBI);
        
        JLabel guideLabel = new JLabel(guideIcon);
        
        Box vertBox = Box.createVerticalBox();
        vertBox.add(Box.createVerticalStrut(5));
        
        Box titleBox = Box.createHorizontalBox();
		String label = "Bonvenon!";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		titleBox.add(Box.createHorizontalStrut(310));
		titleBox.add(title);
		titleBox.add(Box.createHorizontalStrut(310));
		vertBox.add(titleBox);
		
		Box labelBox = Box.createHorizontalBox();
		labelBox.add(Box.createHorizontalStrut(4));
		labelBox.add(guideLabel);
		labelBox.add(Box.createHorizontalStrut(4));
		
		vertBox.add(labelBox);
		vertBox.add(Box.createVerticalStrut(5));
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.add(vertBox, BorderLayout.CENTER);
		overall.setMaximumSize(new Dimension(950,1000));
		overall.setBackground(Color.white);
        
        JScrollPane scrollbar = new JScrollPane(overall);
        scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(1000,594));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(18,Integer.MAX_VALUE));
		scrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Top Panel
		JPanel topPanel = new JPanel(null);
		topPanel.setPreferredSize(new Dimension(1000,35));
		topPanel.setBackground(new Color(50,50,50,255));
		
		Box userBox = Box.createHorizontalBox();
		JLabel _un = new JLabel(_driver.getPlayerStats().getUsername());
		_un.setFont(new Font("Cambria", Font.PLAIN, 20));
		_un.setForeground(Color.white);
		userBox.add(_un);
		userBox.setSize(200,35);
		userBox.setLocation(20, -2);
		
		Box topBar = Box.createHorizontalBox();
		JLabel _score = new JLabel("Punktoj tutaj: "+_driver.getPlayerStats().getPoints());
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		topBar.add(_score);
		topBar.setSize(400,35);
		topBar.setLocation(435, -2);
		
		BufferedImage backpic = null;
		try {
			backpic = ImageIO.read(new File("data/OtherPictures/backarrow.png"));
		} catch (IOException e){
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		int type3 = BufferedImage.TYPE_INT_ARGB;
        BufferedImage dst3 = new BufferedImage(20, 20, type3);
        Graphics2D g3 = dst3.createGraphics();
        g3.drawImage(backpic, 0, 0, 20, 20, this);
        g3.dispose();
        ImageIcon newIcon3 = new ImageIcon(dst3);
		
		JButton back = new JButton("Reiru",newIcon3);
		back.addActionListener(new BacktoBasicActionListener());
		back.setSize(new Dimension(100, 30));
		back.setLocation(875,0);
		
		topPanel.add(userBox);
		topPanel.add(topBar);
		topPanel.add(back);
		
		
		//Bottom Panel
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setPreferredSize(new Dimension(1000,45));
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
	
	public class BacktoBasicActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIOptionsPage(_driver, _stats));			
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
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame("Welcome to ELearning! The page you're looking at contains several vocabulary and grammar lessons (the left two columns), and corresponding games to test your knowledge of them (the next two). Start in the top left, and play the games to unlock later levels.", 
																		-1, -1, _driver, _forHelpBox);
		}
		
	}
}
