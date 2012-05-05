package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ELearning.Driver;
import annie.PlayerStats;

public class GUIOptionsPanel extends JPanel{
	
	private JButton buttons[][];
	private JLabel levelNames[];
	private Driver _driver;
	
	public GUIOptionsPanel(BufferedReader  fileReader, int numacts, PlayerStats stats, Driver driver) throws IOException{
		super(new BorderLayout());
		
		this.setBackground(new Color (238,238,238,255));
		this.setPreferredSize(new Dimension(950,100*numacts+70));
		
		buttons = new JButton[numacts][5];
		levelNames = new JLabel[numacts];
		_driver = driver;
		
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
				int type = BufferedImage.TYPE_INT_ARGB;
		        BufferedImage dst = new BufferedImage(picsize, picsize, type);
		        Graphics2D g1 = dst.createGraphics();
		        g1.drawImage(pictures[j], 0, 0, picsize, picsize, this);
		        g1.dispose();
		        ImageIcon newIcon = new ImageIcon(dst);
		        buttons[i][j] = new JButton(newIcon);
		        buttons[i][j].setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		        buttons[i][j].setBackground(new Color(238,238,238,0));
		        buttons[i][j].setEnabled(stats.isUnlocked(i, j));
			}
			buttons[0][4].setEnabled(true);//--------------------------------------------------
			
			levelNames[i] = new JLabel(picturePaths[4]);
			levelNames[i].setBackground(new Color(238,238,238,255));
			levelNames[i].setFont(new Font("Cambria", Font.BOLD, 25));
			levelNames[i].setSize(new Dimension(40,15));
			levelNames[i].setBorder(BorderFactory.createEmptyBorder(33,0,33,0));
			
		}
		
		JPanel titles = new JPanel(new BorderLayout());
		titles.setPreferredSize(new Dimension(1000,80));
		titles.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		titles.setBackground(new Color(238,238,238,255));
		
		Box theTitles = Box.createHorizontalBox();
		JLabel learning = new JLabel("Learni");
		JLabel games = new JLabel("Apliki");
		learning.setFont(new Font("Century", Font.BOLD, 30));
		games.setFont(new Font("Century", Font.BOLD, 30));
		
		learning.setBorder(BorderFactory.createEmptyBorder(20,50,0,60));
		games.setBorder(BorderFactory.createEmptyBorder(20,0,0,60));
		
		theTitles.add(Box.createRigidArea(new Dimension(69,0)));
		theTitles.add(learning);
		theTitles.add(Box.createRigidArea(new Dimension(111,0)));
		theTitles.add(games);
		
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
		totalBox.add(Box.createRigidArea(new Dimension(28,0)));
		totalBox.add(grammarLearningColumn);
		totalBox.add(Box.createRigidArea(new Dimension(70,0)));
		totalBox.add(vocabGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(28,0)));
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
			
			this.add(titles, BorderLayout.NORTH);
			this.add(totalBox, BorderLayout.CENTER);
		
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
				if ((_levelNum == 0) && (_driver.getPlayerStats().getSingleGame(0, 0).bestScore == 0)){
					_driver.changePage(new GUIFullFrameHelp("data/GenVocabLessonHelp.txt", _driver, 1, 0));
				} else _driver.changePage(new GUIVocabGame(_driver.getVocabGameMaker().makeLevel(_levelNum), _driver));
				break;
			case 4:
				if ((_levelNum == 0) && (_driver.getPlayerStats().getSingleGame(0, 1).bestScore == 0)){
					_driver.changePage(new GUIFullFrameHelp("data/GenGrammarHelp.txt", _driver, 2, 0));
				} else _driver.changePage(new GUIGrammarGame(_driver.getGrammarGameMaker().makeLevel(_levelNum), _driver));
				break;
			case 5:
				//create a boss game
				if ((_levelNum == 0) && (_driver.getPlayerStats().getSingleGame(0, 2).bestScore == 0)){
					_driver.changePage(new GUIFullFrameHelp("data/GenBossLevelHelp.txt", _driver, 3, 0));
				} else _driver.changePage(new GUIBossGame(_driver.getBossGameMaker().makeLevel(_levelNum), _driver));
				break;
			}
		}
	}

}
