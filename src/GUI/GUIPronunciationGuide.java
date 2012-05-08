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
public class GUIPronunciationGuide extends GUIFoundationPage{
	
	private Driver _driver;
	private PlayerStats _stats;
	private JPanel _forHelpBox;

	public GUIPronunciationGuide(Driver d, PlayerStats stats){
		super(d, true);

		_driver = d;
		_stats = stats;
		_forHelpBox = this;
		
		//help box
		setHelp(new HelpButtonListener());
		
		//back button
		setBack(new BacktoBasicActionListener(),"Reiru",true);
		
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
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(scrollbar, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);
	}
	
	public class BacktoBasicActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIOptionsPage(_driver, _stats));			
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
