package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import parsing.Mistake;
import parsing.Response;
import ELearning.BossLevel;
import ELearning.Driver;
import encoding.EncodingShiftListener;

public class GUIBossGame extends JPanel{
	private Driver _driver;
	private BossLevel _bossLevel;
	private JTextField userInput;
	private JButton back, help, dictionary, next, prev, uiButton;
	private JPanel mainPanel, rPanel, panel, aPanel, qPanel;
	private JTextArea textArea;
	private BufferedImage pic;
	private int _current;
	private Response response;
	private boolean switched;
	private JLabel _score, _currNumLabel;
	private Box topHoriz;


	public GUIBossGame(BossLevel b, Driver d){
		super(null);
		_driver = d;
		_bossLevel = b;
		b.addVocabWords(d);
		_current = 0;
		rPanel = null;
		panel = this;
		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(50,50,50,255));

		//constants specified for each level (make sure the panel looks good for the length of the questions being asked)
		int qpanelX = 50;
		int qpanelY = 50;
		int apanelX = 650;
		int apanelY = 200;

		String backPic = b.getPicturePath();
		String questions = b.getCurrentQuestion();
		switched = false;

		pic = null;
		try {
			pic = ImageIO.read(new File(backPic));
		} catch (IOException e) {
			String errorMessage = "There was an error finding some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		String mystring = " "+Integer.toString(_bossLevel.getCurrentNum()) + " de "+Integer.toString(_bossLevel.getTotalNum());
		_currNumLabel = new JLabel(mystring);
		_currNumLabel.setFont(new Font("Cambria", Font.BOLD, 25));
		_currNumLabel.setLocation(850,40);
		_currNumLabel.setSize(120,50);
		if(_bossLevel.getLevelNum()==2)
			_currNumLabel.setForeground(Color.WHITE);
		_currNumLabel.setBackground(new Color(255,255,255,180));
		//_currNumLabel.setOpaque(true);
		this.add(_currNumLabel);

		JPanel qPanel = new JPanel();
		qPanel.setLayout(new BorderLayout());
		qPanel.setBackground(new Color(255,255,255,0));
        qPanel.setLocation(qpanelX, qpanelY+35);
        qPanel.setSize(300,400);
     
        this.add(qPanel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setBackground(new Color(255,255,255,180));
		textArea.setBorder(BorderFactory.createEmptyBorder(7,7,7,0));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(questions);
		textArea.setFont(new Font("Calibri", Font.BOLD, 30));
		textArea.setSize(100,100);

		JTextArea textArea2 = new JTextArea();
		textArea2.setEditable(false);
		textArea2.setBackground(new Color(0,0,0,0));
		textArea2.setBorder(BorderFactory.createEmptyBorder(0,7,7,7));
		textArea2.setText("              ");

		qPanel.add(textArea, BorderLayout.NORTH);
		qPanel.add(textArea2, BorderLayout.SOUTH);

		userInput = new JTextField(18);
		userInput.setBorder(BorderFactory.createLineBorder(Color.black));
		userInput.addKeyListener(new EncodingShiftListener(userInput));
		userInput.addActionListener(new enterSubmitListener());

		JPanel aPanel = new JPanel();
		aPanel.setBackground(new Color(255,255,255,0));
        aPanel.setLocation(apanelX, apanelY+35);
        aPanel.setSize(300,40);
        this.add(aPanel);

		uiButton = new JButton("Submetiĝu");
		uiButton.addActionListener(new MySubmitListener());

		Box answHoriz = Box.createHorizontalBox();
		answHoriz.add(userInput);
		answHoriz.add(uiButton);
		aPanel.add(answHoriz);

		//Update Boundary Panels and put everything together

		//Top Panel
		JPanel topPanel = new JPanel(null);
		topPanel.setSize(new Dimension(994,40));
		topPanel.setBackground(new Color(50,50,50,255));
		topPanel.setLocation(0,0);

		JLabel _un = new JLabel(_driver.getPlayerStats().getUsername());
		_un.setFont(new Font("Cambria", Font.PLAIN, 20));
		_un.setForeground(Color.white);
		_un.setSize(200,35);
		_un.setLocation(20, 2);
		
		String scoreWord = _bossLevel.getScore() + "/" + _bossLevel.getNecessaryScore();
		_score = new JLabel(scoreWord);
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		FontMetrics metrics = _score.getFontMetrics(new Font("Cambria", Font.PLAIN, 20));
		int width = SwingUtilities.computeStringWidth(metrics, scoreWord);
		_score.setLocation((994-width)/2, 5-3);
		_score.setSize(new Dimension(width*2,35));

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

		JButton back = new JButton("Reiru",newIcon3);
		back.addActionListener(new backtoOptionsActionListener());		
		back.setSize(new Dimension(125, 30));
		back.setLocation(994-125-20,5);

		topPanel.add(_un);
		topPanel.add(_score);
		topPanel.add(back);


		//Bottom Panel
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setSize(new Dimension(994,40));
		bottomPanel.setBackground(new Color(20,20,20,255));
		bottomPanel.setLocation(0,635);

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
		help.setLocation(20, 5);

		JButton nextQ = new JButton("Next Question");
		nextQ.addActionListener(new NextQListener());
		nextQ.setSize(new Dimension(120,30));
		int y = 5;
		int x = (994-120)/2;
		nextQ.setLocation(x, y);

		JButton dictionary = new JButton("Vortaro",newIcon);
		dictionary.setSize(new Dimension(125, 30));
		dictionary.addActionListener(new DictionaryButtonListener());
		dictionary.setLocation(994-125-20, 5);

		bottomPanel.add(help);
		bottomPanel.add(nextQ);
		bottomPanel.add(dictionary);

		add(topPanel);
		add(bottomPanel);

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

		if(mistakeNum == 0 && response.getMistakes().size()<1){

			JLabel congrats = new JLabel("Congrats! You have no errors!");
			congrats.setFont(new Font("Cambria", Font.PLAIN, 20));
			Box conBox = Box.createHorizontalBox();
			conBox.add(congrats);
			resultVert.add(conBox);

			nrPanel.setVisible(true);
			return nrPanel;
		}

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
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.white));
		messageLabel.setText(message);
		messageLabel.setSize(new Dimension(400,80));
		messageLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
		messageLabel.setLineWrap(true);
		messageLabel.setWrapStyleWord(true);
		messageLabel.setEditable(false);
		messageLabel.setBackground(new Color(255,255,255,255));
		messageBox.add(messageLabel);
		messageBox.add(Box.createHorizontalStrut(70));

		Box moveBox = Box.createHorizontalBox();
		try{
			BufferedImage arrowOne;
			BufferedImage arrowTwo; 
			arrowOne = ImageIO.read(new File("data/OtherPictures/leftarrow.png"));
			arrowTwo = ImageIO.read(new File("data/OtherPictures/rightarrow.png"));

			BufferedImage dst = new BufferedImage(50, 20, BufferedImage.TYPE_INT_ARGB);

	        Graphics2D g1 = dst.createGraphics();
	        g1.drawImage(arrowOne, 0, 0, 50, 20, this);

	        g1.dispose();
	        ImageIcon newIcon = new ImageIcon(dst);

	        BufferedImage dst2 = new BufferedImage(50, 20, BufferedImage.TYPE_INT_ARGB);

	        Graphics g2 = dst2.createGraphics();
	        g2.drawImage(arrowTwo, 0, 0, 50, 20, this);

	        g2.dispose();
	        ImageIcon newIcon2 = new ImageIcon(dst2);

	        prev = new JButton(newIcon);
	        next = new JButton(newIcon2);
		} catch (IOException e){
			next = new JButton("Sekva");
			prev = new JButton("AntaÅ­a");
		}
		next.addActionListener(new MyMoveListener(1));
		prev.addActionListener(new MyMoveListener(-1));
		moveBox.add(Box.createHorizontalStrut(25));

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

	public void focusOnTextField(){
		userInput.requestFocus();
	}

	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;

		brush.drawImage(pic, 0, 40, 1000, 635, 0, 0, pic.getWidth(), pic.getHeight(), this);

	}

	public void submit() {	
		if(!switched){
			switched = true;
			response = _bossLevel.tryAnswer(userInput.getText());
			_score.setText(_bossLevel.getScore() + "/" + _bossLevel.getNecessaryScore());
			JPanel nrPanel = makeRPanel(response, 0);
			rPanel = nrPanel;
			panel.add(rPanel);
			panel.revalidate();
		}
		uiButton.setEnabled(false);
	}

	private class MySubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			submit();
		}

	}

	private class enterSubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			submit();
		}

	}

	private class NextQListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!switched){
				_bossLevel.tryAnswer(" ");
			}
			switched = false;
			_current = 0;
			userInput.setText("");
			if (_bossLevel.isOver()){
				_driver.changePage(new GUIGameCompleted(_driver, _bossLevel));
			}

			textArea.setText(_bossLevel.getCurrentQuestion());
			if(rPanel != null){
				rPanel.setVisible(false);
			}
			uiButton.setEnabled(true);
			_currNumLabel.setText(" "+_bossLevel.getCurrentNum() + " de "+Integer.toString(_bossLevel.getTotalNum()));
			_score.setText(_bossLevel.getScore() + "/" + _bossLevel.getNecessaryScore());
			panel.revalidate();
			panel.repaint();
			userInput.requestFocusInWindow();
		}		
	}

	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_bossLevel.decrementScore(2);
			_score.setText(_bossLevel.getScore() + "/" + _bossLevel.getNecessaryScore());
			DictionaryInternalFrame dictFrame = new DictionaryInternalFrame(_driver.getDictionary());
		}

	}

	private class HelpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_bossLevel.decrementScore(3);
			_score.setText(_bossLevel.getScore() + "/" + _bossLevel.getNecessaryScore()+"       ");
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame(_bossLevel.getHelp(), 3, _bossLevel.getLevelNum(), _driver, panel);
		}

	}

	private class backtoOptionsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.getPlayerStats().RefreshStats(_bossLevel.getLevelNum(), 2, _bossLevel.getScore());
			_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));			
		}

	}

	private class MyMoveListener implements ActionListener{

		private int _direction;
		public MyMoveListener(int direction){
			_direction = direction;
			prev.setEnabled(false);
			if(_current >= response.getMistakes().size()-1){
				next.setEnabled(false);
			} 

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

			} else{
				//move forwards in list of mistakes
				_current++;
				JPanel nrPanel = makeRPanel(response,_current);
				panel.remove(rPanel);
				rPanel = nrPanel;
				panel.add(rPanel);
				panel.repaint();
			}	


			if(_current == 0){
				prev.setEnabled(false);
			} else{
				prev.setEnabled(true);
			}
		}


	}

}
