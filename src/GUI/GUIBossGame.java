package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ELearning.BossLevel;
import ELearning.Driver;

public class GUIBossGame extends JPanel{
	private Driver _driver;
	private BossLevel _bossLevel;
	private JTextArea speechBubble, parsedResponse;
	private JTextField userInput;
	private JButton back, help, dictionary;
	private JPanel backgroundPanel;
	private ImageIcon backgroundImg;
	
	
	public GUIBossGame(BossLevel b, Driver d){
		_driver = d;
		_bossLevel = b;
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(100,110,255,255));
		
		backgroundImg = new ImageIcon(_bossLevel.getPicturePath());
		backgroundPanel = new JPanel(){
		     Image image = backgroundImg.getImage();
		     {setOpaque(false);}
		     public void paintComponent (Graphics g) {
		       g.drawImage(image, 0, 0, this);
		       super.paintComponent(g);
		     }
		   };
		backgroundPanel.setMinimumSize(new Dimension(600, 600));
		this.add(backgroundPanel);
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(200,200,200,255));
		
	}
}
