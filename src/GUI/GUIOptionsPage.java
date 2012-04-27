package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import ELearning.Driver;
import annie.PlayerStats;

@SuppressWarnings("serial")
public class GUIOptionsPage extends JPanel{
	Driver _driver;
	JLabel _userName;
	private JPanel topPanel;
	
	public GUIOptionsPage(Driver d, PlayerStats stats){
		try {
			stats.encode();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_driver = d;
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		//Read In File
		FileInputStream file;
		int numacts = 0;
		JButton buttons[][] = null;
		JLabel levelNames[] = null;
		
		try {
			file = new FileInputStream("data/optionsData.csv");
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(new DataInputStream(file)));
			numacts = Integer.parseInt(fileReader.readLine());
			buttons = new JButton[numacts][5];
			levelNames = new JLabel[numacts];
			
			String line;

			for(int i = 0; i<numacts; i++){
				
				if((line = fileReader.readLine()) == null){
					System.out.println("Options file does not have correct number of lines");
					System.exit(0);
				}
					
				String picturePaths[] = new String[6];
				picturePaths = line.split(",");
				
				BufferedImage pictures[] = new BufferedImage[5];
				pictures[0] = ImageIO.read(new File(picturePaths[0]));
				pictures[1] = ImageIO.read(new File(picturePaths[1]));
				pictures[2] = ImageIO.read(new File(picturePaths[2]));
				pictures[3] = ImageIO.read(new File(picturePaths[3]));
				pictures[4] = ImageIO.read(new File(picturePaths[5]));
				
				int picsize = 87;
				
				for(int j=0; j<5; j++){
					int type = BufferedImage.TYPE_INT_RGB;
			        BufferedImage dst = new BufferedImage(picsize, picsize, type);
			        Graphics2D g1 = dst.createGraphics();
			        g1.drawImage(pictures[j], 0, 0, picsize, picsize, this);
			        g1.dispose();
			        ImageIcon newIcon = new ImageIcon(dst);
			        buttons[i][j] = new JButton(newIcon);
			        buttons[i][j].setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
			        buttons[i][j].setBackground(new Color(238,238,238,255));
			        buttons[i][j].setEnabled(stats.isUnlocked(i, j));
				}
				buttons[0][4].setEnabled(true);//--------------------------------------------------
				
				levelNames[i] = new JLabel(picturePaths[4]);
				levelNames[i].setBackground(new Color(238,238,238,255));
				levelNames[i].setFont(new Font("Cambria", Font.BOLD, 25));
				levelNames[i].setSize(new Dimension(40,15));
				levelNames[i].setBorder(BorderFactory.createEmptyBorder(33,0,33,0));
				
			}
			
		} catch (FileNotFoundException e1) {
			System.out.println("Data file could not be opened.");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Number of lines could not be read.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Could not read file.");
			System.exit(0);
		}
		
		//Create The Layout
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color (238,238,238,255));
		overall.setPreferredSize(new Dimension(950,100*numacts+70));
		
		JPanel titles = new JPanel(new BorderLayout());
		titles.setPreferredSize(new Dimension(1000,80));
		titles.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		titles.setBackground(new Color(238,238,238,255));
		
		Box theTitles = Box.createHorizontalBox();
		
		_userName = new JLabel(_driver.getUserName());
		JLabel learning = new JLabel("Learni");
		JLabel games = new JLabel("Apliki");
		_userName.setFont(new Font("Cambria", Font.PLAIN, 20));
		_userName.setForeground(Color.white);
		learning.setFont(new Font("Century", Font.BOLD, 30));
		games.setFont(new Font("Century", Font.BOLD, 30));
		
		learning.setBorder(BorderFactory.createEmptyBorder(20,50,0,60));
		games.setBorder(BorderFactory.createEmptyBorder(20,0,0,60));
		
		theTitles.add(Box.createRigidArea(new Dimension(80,0)));
		theTitles.add(learning);
		theTitles.add(Box.createRigidArea(new Dimension(75,0)));
		theTitles.add(games);
		theTitles.add(Box.createRigidArea(new Dimension(300,0)));
		
		titles.add(theTitles);

		Box vocabLearningColumn = Box.createVerticalBox();
		Box grammarLearningColumn = Box.createVerticalBox();
		Box vocabGameColumn = Box.createVerticalBox();
		Box grammarGameColumn = Box.createVerticalBox();
		Box levelNameColumn = Box.createVerticalBox();
		Box bossGameColumn = Box.createVerticalBox();
		
		//add all of the columns to the box
		Box totalBox = Box.createHorizontalBox();
		
		totalBox.add(Box.createRigidArea(new Dimension(70,0)));
		totalBox.add(vocabLearningColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(grammarLearningColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(vocabGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(grammarGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(levelNameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(bossGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(70,0)));
		
			for(int i = 0; i < numacts; i++){
				
				vocabLearningColumn.add(buttons[i][0]);
				buttons[i][0].addActionListener(new MakePageListener(i,1));
				grammarLearningColumn.add(buttons[i][1]);
				buttons[i][1].addActionListener(new MakePageListener(i,2));
				vocabGameColumn.add(buttons[i][2]);
				buttons[i][2].addActionListener(new MakePageListener(i,3));
				grammarGameColumn.add(buttons[i][3]);
				buttons[i][3].addActionListener(new MakePageListener(i,4));
				levelNameColumn.add(levelNames[i]);
				bossGameColumn.add(buttons[i][4]);
				buttons[i][4].addActionListener(new MakePageListener(i,5));
			
			}
			
		overall.add(titles, BorderLayout.NORTH);
		overall.add(totalBox, BorderLayout.CENTER);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(1000,598));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		Box fullBar = Box.createVerticalBox();

		/*
		
		
		*/
		
		//Box topCushion = Box.createVerticalBox();
		//topCushion.add(Box.createVerticalStrut(8));
		
		//Box bottomCushion = Box.createVerticalBox();
		//bottomCushion.add(Box.createVerticalStrut(8));
		
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createHorizontalStrut(830));
		JButton help = new JButton("Help");
		JButton dictionary = new JButton("Dictionary");
		dictionary.setSize(new Dimension(75, 35));
		help.setSize(new Dimension(75, 35));
		bottomBar.add(help);
		bottomBar.add(dictionary);
		bottomBar.add(Box.createHorizontalStrut(15));
		help.addActionListener(new HelpButtonListener());
		dictionary.addActionListener(new DictionaryButtonListener());
		
		topPanel = new JPanel(null);
		topPanel.setPreferredSize(new Dimension(1000,35));
		topPanel.setBackground(new Color(50,50,50,255));
		topPanel.addComponentListener(new componentlistener());
		topPanel.add(_userName, BorderLayout.WEST);
		
		JButton back = new JButton("Back");
		back.addActionListener(new BacktoBasicActionListener());
		back.setSize(new Dimension(75, 35));
		
		Box topBar = Box.createHorizontalBox();
		JLabel _score = new JLabel("Total points: "+_driver.getPlayerStats().getPoints());
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		topBar.add(_score);
		
		topPanel.add(back, BorderLayout.EAST);
		topPanel.add(topBar, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1000,35));
		bottomPanel.setBackground(new Color(50,50,50,255));
		bottomPanel.addComponentListener(new componentlistener());
		
		fullBar.add(topPanel);
		fullBar.add(scrollbar);
		fullBar.add(bottomPanel);
		fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		add(fullBar, BorderLayout.CENTER);
		System.out.println("Panel height: " + topPanel.getHeight());
	}
	
	public class BacktoBasicActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIBasicPage(_driver));			
		}
		
	}
	
	public class componentlistener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			System.out.println("Panel height : "  + e.getComponent().getHeight());
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
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
			HelpBoxInternalFrame helpFrame = new HelpBoxInternalFrame("Welcome to ELearning! The page you're looking at contains several vocabulary and grammar lessons (the right two columns), and corresponding games to test your knowledge of them. Start in the top left, and play the games to unlock later levels.", 
																		-1, -1, _driver);
		}
		
	}
	
	//Creates a Vocab Game
	private class MakePageListener implements ActionListener {
		private int _levelNum;
		private int _type;
		
		public MakePageListener(int level, int type){
			_levelNum = level;
			_type = type;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//create a game or learning page of level _levelNum
			switch(_type){
			case 1:
				//create a vocab learning activity
				_driver.changePage(new GUIVocabLearn(_levelNum, _driver));
				break;
			case 2:
				//create a grammar learning activity
				_driver.changePage(new GUIGrammarLearn(_levelNum, _driver));

				break;
			case 3:
				//create a vocab game
				_driver.changePage(new GUIVocabGame(_driver.getVocabGameMaker().makeLevel(_levelNum), _driver));
				break;
			case 4:
				//create a grammar game
				_driver.changePage(new GUIGrammarGame(_driver.getGrammarGameMaker().makeLevel(_levelNum), _driver));
				break;
			case 5:
				//create a boss game
				_driver.changePage(new GUIBossGame(_driver.getBossGameMaker().makeLevel(_levelNum), _driver));
				break;
			}
		}
	}
}
