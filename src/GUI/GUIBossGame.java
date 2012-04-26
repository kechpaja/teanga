package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ELearning.BossLevel;
import ELearning.Driver;

public class GUIBossGame extends JPanel{
	private Driver _driver;
	private BossLevel _bossLevel;
	private JTextArea speechBubble, parsedResponse;
	private JTextField userInput;
	private JButton back, help, dictionary;
	private JPanel mainPanel;
	private BufferedImage pic;
	
	
	public GUIBossGame(){//BossLevel b, Driver d){
		super(null);
		//_driver = d;
		//_bossLevel = b;
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(100,110,255,255));
		
		int qpanelX = 50;
		int qpanelY = 50;
		int qpanelH = 150;
		int qpanelW = 300;
		
		String backPic = "data/grocery.jpg";
		String questions = "What would you normally buy in a grocery store?";
		
		pic = null;
		try {
			pic = ImageIO.read(new File(backPic));
		} catch (IOException e) {
			System.out.println("Cannot read image (GUIBossGame)");
			System.exit(0);
		}
		
		JPanel qPanel = new JPanel();
		qPanel.setBackground(new Color(255,255,255,150));
        qPanel.setLocation(qpanelX, qpanelY+35);
        qPanel.setSize(qpanelW,qpanelH);
        this.add(qPanel);
		this.setOpaque(true);
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(10));
		
		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setBackground(new Color(0,0,0,0));
		//textArea.setLineWrap(true);
		//textArea.setWrapStyleWord(true);
		textArea.setText(questions);
		textArea.setFont(new Font("Calibri", Font.BOLD, 30));
		textArea.setSize(new Dimension(qPanel.getWidth()-20, qPanel.getHeight()));
		
		horizBox.add(textArea);
		horizBox.add(Box.createHorizontalStrut(10));
		
		qPanel.add(horizBox);
		
		
		
		
	}
	
	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		
		brush.drawImage(pic, 0, 40, 1000, 635, 0, 0, pic.getWidth(), pic.getHeight(), this);

	}
	
	public static void main(String[] args){
		GUIBossGame panel = new GUIBossGame();
		JFrame frame = new JFrame("Boss Game");
		frame.setPreferredSize(new Dimension(1000,700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}
