package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import parsing.Response;

import encoding.EncodingShiftListener;

import ELearning.BossLevel;
import ELearning.Driver;

public class GUIBossGame extends JPanel{
	private Driver _driver;
	private BossLevel _bossLevel;
	private JTextArea speechBubble, parsedResponse;
	private JTextField userInput;
	private JButton back, help, dictionary, next, prev;
	private JPanel mainPanel, rPanel, panel, aPanel, qPanel;
	private BufferedImage pic;
	private int _current;
	
	
	public GUIBossGame(BossLevel b, Driver d){
		super(null);
		_driver = d;
		_bossLevel = b;
		_current = 0;
		panel = this;
		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(100,110,255,255));
		
		//constants specified for each level (make sure the panel looks good for the length of the questions being asked)
		int qpanelX = 50;
		int qpanelY = 50;
		int qpanelH = 120;
		int qpanelW = 240;
		int apanelX = 650;
		int apanelY = 200;
		
		String backPic = b.getPicturePath();
		String questions = b.getCurrentQuestion();
		
		pic = null;
		try {
			pic = ImageIO.read(new File(backPic));
		} catch (IOException e) {
			System.out.println("Cannot read image (GUIBossGame)");
			System.exit(0);
		}
		
		JPanel qPanel = new JPanel();
		qPanel.setBackground(new Color(255,255,255,180));
        qPanel.setLocation(qpanelX, qpanelY+35);
        qPanel.setSize(qpanelW,qpanelH);
        this.add(qPanel);
		this.setOpaque(true);
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(10));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setBackground(new Color(0,0,0,0));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(questions);
		textArea.setFont(new Font("Calibri", Font.BOLD, 30));
		textArea.setSize(new Dimension(qPanel.getWidth()-20, qPanel.getHeight()));
		
		horizBox.add(textArea);
		horizBox.add(Box.createHorizontalStrut(10));
		
		qPanel.add(horizBox);
		
		userInput = new JTextField(18);
		userInput.setBorder(BorderFactory.createLineBorder(Color.black));
		userInput.addKeyListener(new EncodingShiftListener(userInput));
		
		JPanel aPanel = new JPanel();
		aPanel.setBackground(new Color(255,255,255,0));
        aPanel.setLocation(apanelX, apanelY+35);
        aPanel.setSize(300,40);
        this.add(aPanel);
		
		JButton uiButton = new JButton("Submit");
		uiButton.addActionListener(new MySubmitListener());
		
		Box answHoriz = Box.createHorizontalBox();
		answHoriz.add(userInput);
		answHoriz.add(uiButton);
		aPanel.add(answHoriz);
		
		rPanel = new JPanel();
		rPanel.setBackground(new Color(255,255,255,180));
        rPanel.setLocation(190,405);
        rPanel.setSize(600,230);
        this.add(rPanel);
        rPanel.setVisible(false);
		
		Box resultHoriz = Box.createHorizontalBox();
		Box resultVert = Box.createVerticalBox();
		resultHoriz.add(Box.createHorizontalStrut(5));
		resultHoriz.add(resultVert);
		resultHoriz.add(Box.createHorizontalStrut(5));
		
		rPanel.add(resultHoriz);
		
		int startIndex = 11;
		int endIndex = 21;
		String sentence = "This is my incredible sentance in which part of it is wrong!";
		String message = "Whatever word is highlighted is very very incorrect. I am making this message super long just in case ...";
		
		Box titleHoriz = Box.createHorizontalBox();
		JLabel errorLabel = new JLabel("Errors: ");
		errorLabel.setFont(new Font("Cambria", Font.PLAIN, 25));
		errorLabel.setForeground(new Color(255,0,0,255));
		titleHoriz.add(Box.createHorizontalStrut(10));
		titleHoriz.add(errorLabel);
		titleHoriz.add(Box.createHorizontalStrut(500));
		resultVert.add(titleHoriz);
		
		Box newSent = parseResponse(sentence, startIndex, endIndex);
		
		Box explinBox = Box.createHorizontalBox();
		JLabel explination = new JLabel("Explanation: ");
		explination.setFont(new Font("Cambria", Font.BOLD, 20));
		//explinBox.add(Box.createHorizontalStrut(20));
		explinBox.add(explination);
		explinBox.add(Box.createHorizontalStrut(360));
		
		Box messageBox = Box.createHorizontalBox();
		messageBox.add(Box.createHorizontalStrut(70));
		messageBox.add(Box.createVerticalStrut(80));
		JTextArea messageLabel = new JTextArea();
		messageLabel.setText(message);
		messageLabel.setSize(new Dimension(400,80));
		messageLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
		messageLabel.setLineWrap(true);
		messageLabel.setWrapStyleWord(true);
		messageLabel.setEditable(false);
		messageLabel.setBackground(new Color(0,0,0,0));
		messageBox.add(messageLabel);
		messageBox.add(Box.createHorizontalStrut(70));
		
		Box moveBox = Box.createHorizontalBox();
		next = new JButton("Next");
		prev = new JButton("Prev");
		next.addActionListener(new MyMoveListener(1));
		prev.addActionListener(new MyMoveListener(-1));
		moveBox.add(prev);
		moveBox.add(next);
		
		resultVert.add(Box.createVerticalStrut(5));
		resultVert.add(newSent);
		resultVert.add(Box.createVerticalStrut(15));
		resultVert.add(explinBox);
		resultVert.add(Box.createVerticalStrut(5));
		resultVert.add(messageBox);
		resultVert.add(Box.createVerticalStrut(5));
		resultVert.add(moveBox);
		resultVert.add(Box.createVerticalStrut(5));
		
	}
	
	public void showErrors(){
		//panel.removeAll();
		//panel.add(aPanel);
		//panel.add(qPanel);
		rPanel.setVisible(true);
		//panel.repaint();
		this.revalidate();
	}
	
	public Box parseResponse(String sentence, int startIndex, int endIndex){
		String part1 = null;
		String error = null;
		String part2 = null;
		Box horizBox = Box.createHorizontalBox();
		Font myFont = new Font("Cambria", Font.PLAIN, 20);
		if(startIndex == 0){
			error = sentence.substring(0,endIndex);
			JLabel errorLabel = new JLabel(error);
			errorLabel.setFont(myFont);
			errorLabel.setForeground(Color.red);
			part2 = sentence.substring(endIndex);
			JLabel part2Label = new JLabel(part2);
			part2Label.setFont(myFont);
			horizBox.add(Box.createHorizontalStrut(5));
			horizBox.add(errorLabel);
			horizBox.add(part2Label);
			horizBox.add(Box.createHorizontalStrut(5));
		} else if(endIndex == sentence.length()){
			part1 = sentence.substring(0,startIndex);
			JLabel part1Label = new JLabel(part1);
			part1Label.setFont(myFont);
			error = sentence.substring(startIndex);
			JLabel errorLabel = new JLabel(error);
			errorLabel.setFont(myFont);
			errorLabel.setForeground(Color.red);
			horizBox.add(Box.createHorizontalStrut(5));
			horizBox.add(part1Label);
			horizBox.add(errorLabel);
			horizBox.add(Box.createHorizontalStrut(5));
		} else {
			part1 = sentence.substring(0, startIndex);
			JLabel part1Label = new JLabel(part1);
			part1Label.setFont(myFont);
			error = sentence.substring(startIndex, endIndex);
			JLabel errorLabel = new JLabel(error);
			errorLabel.setFont(myFont);
			errorLabel.setForeground(Color.red);
			part2 = sentence.substring(endIndex);
			JLabel part2Label = new JLabel(part2);
			part2Label.setFont(myFont);
			horizBox.add(Box.createHorizontalStrut(5));
			horizBox.add(part1Label);
			horizBox.add(errorLabel);
			horizBox.add(part2Label);
			horizBox.add(Box.createHorizontalStrut(5));	
		}
		
		return horizBox;
	}
	
	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		
		brush.drawImage(pic, 0, 40, 1000, 635, 0, 0, pic.getWidth(), pic.getHeight(), this);

	}
	
	public static void main(String[] args){
		//GUIBossGame panel = new GUIBossGame();
		JFrame frame = new JFrame("Boss Game");
		frame.setPreferredSize(new Dimension(1000,700));
		//frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private class MySubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Response response = _bossLevel.tryAnswer(userInput.getText());
			showErrors();
		}
		
	}
	
	private class MyMoveListener implements ActionListener{

		private int _direction;
		public MyMoveListener(int direction){
			_direction = direction;
			prev.setEnabled(false);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(_direction == -1){
				//move backwards in list of mistakes
				System.out.println("Move backwards");
				_current--;
			} else{
				//move forwards in list of mistakes
				System.out.println("Move forwards");
				_current++;
			}
			System.out.println(_current);
			
			if(_current == 0){
				prev.setEnabled(false);
			} else{
				prev.setEnabled(true);
			}
			
			//same for end of list and "next"
			
		}
		
		
	}
	
}
