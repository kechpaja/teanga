package GUI;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import parsing.Mistake;
import parsing.Response;
import ELearning.BossLevel;
import ELearning.Driver;
import encoding.EncodingShiftListener;

public class GUIBossGame extends JPanel{
	private Driver _driver;
	private BossLevel _bossLevel;
	private JTextField userInput;
	private JButton back, help, dictionary, next, prev;
	private JPanel mainPanel, rPanel, panel, aPanel, qPanel;
	private BufferedImage pic;
	private int _current;
	private Response response;
	
	
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
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(0,0,0,255));//Make last value 0
		topPanel.setSize(new Dimension(1000,30));
		topPanel.setLocation(0,0);
		
		Box topBox = Box.createHorizontalBox();
		topPanel.add(topBox, BorderLayout.CENTER);
		this.add(topPanel);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(new Color(0,0,0,255));//Make last value 0
		bottomPanel.setSize(new Dimension(1000,30));
		bottomPanel.setLocation(0,660);//EDIT THIS TO SET BOTTOM BOX LOCATION TO THE BOTTOM OF THE SCREEN
		
		Box bottomBox = Box.createHorizontalBox();
		bottomPanel.add(bottomBox);
		this.add(bottomPanel);
		
	}
	
	public JPanel makeRPanel(Response response, int mistakeNum){
		JPanel nrPanel = new JPanel();
		nrPanel.setBackground(new Color(255,255,255,180));
        nrPanel.setLocation(190,405);
        nrPanel.setSize(600,230);
        panel.add(nrPanel);
        nrPanel.setVisible(false);
		
		Box resultHoriz = Box.createHorizontalBox();
		Box resultVert = Box.createVerticalBox();
		resultHoriz.add(Box.createHorizontalStrut(5));
		resultHoriz.add(resultVert);
		resultHoriz.add(Box.createHorizontalStrut(5));
		
		nrPanel.add(resultHoriz);
		 
		Mistake currMistake = response.getMistakes().get(mistakeNum);
		int startIndex = currMistake.getStartIndex();
		int endIndex = currMistake.getEndIndex();
		String sentence = response.getSentence();
		String message = currMistake.getMessage();
		
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
		
		nrPanel.setVisible(true);
		
		return nrPanel;
	}
	
	public Box parseResponse(String sentence, int startIndex, int endIndex){
		String part1 = null;
		String error = null;
		String part2 = null;
		Box horizBox = Box.createHorizontalBox();
		Font myFont = new Font("Cambria", Font.PLAIN, 20);

		System.out.println("Length: " + sentence.length());
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
		} else if(endIndex >= sentence.length()-1){
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
	
	private class MySubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			response = _bossLevel.tryAnswer(userInput.getText());
			JPanel nrPanel = makeRPanel(response, 0);
			rPanel = nrPanel;
			panel.add(nrPanel);
			panel.revalidate();
			
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
				_current--;
				JPanel nrPanel = makeRPanel(response,_current);
				panel.remove(rPanel);
				rPanel = nrPanel;
				panel.add(rPanel);
				panel.repaint();
				System.out.println("Move backwards");

			} else{
				//move forwards in list of mistakes
				System.out.println("Move forwards");
				_current++;
				JPanel nrPanel = makeRPanel(response,_current);
				panel.remove(rPanel);
				rPanel = nrPanel;
				panel.add(rPanel);
				panel.repaint();
			}
			System.out.println(_current);
			
			if(_current == 0){
				prev.setEnabled(false);
			} else{
				prev.setEnabled(true);
			}
			
			if(_current == response.getMistakes().size()-1){
				next.setEnabled(false);
			} else{
				next.setEnabled(true);
			}
			
		}
		
		
	}
	
}
