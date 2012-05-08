package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import ELearning.Driver;

public class GUIFullFrameHelp extends JPanel{
	Driver _driver;
	boolean _even;
	//int to switch on (0 means it should go to options page, 1 should go to VocabGame, 2 should go to Grammar Game
	int _toGoTo;
	int _curLevel;
	
	public GUIFullFrameHelp(String helpFile, Driver d, int toGoTo, int levelNum){
		super(new BorderLayout());
		_driver = d;
		_toGoTo = toGoTo;
		_curLevel = levelNum;
		this.setBackground(new Color(50,50,50,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color(238,238,238,255));
		
		//Make the title
		Box vertBox = Box.createVerticalBox();
		
		Box titleBox = Box.createHorizontalBox();
		String label = "Bonvenon!";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		titleBox.add(Box.createHorizontalStrut(315));
		titleBox.add(title);
		titleBox.add(Box.createHorizontalStrut(315));
		vertBox.add(titleBox);
		
		//make the list of paragraphs from given helpfile:
		try{
			BufferedReader br = new BufferedReader(new FileReader(helpFile));
			String para = br.readLine();
			while (para != null){
				
				JTextArea explinArea = new JTextArea();
				explinArea.setText(para);
				explinArea.setEditable(false);
				explinArea.setBackground(new Color(0,0,0,0));
				explinArea.setBorder(BorderFactory.createEmptyBorder());
				explinArea.setFont(new Font("Cambria", Font.PLAIN, 20));
				explinArea.setLineWrap(true);
				explinArea.setWrapStyleWord(true);
				
				JPanel panel = new JPanel(new BorderLayout());
				panel.setBackground(new Color(238,238,238,255));
				explinArea.setBackground(new Color(238,238,238,255));

				
				panel.add(explinArea);
				
				vertBox.add(panel);
				
				para = br.readLine();
			}
		} catch (IOException e) {
			//TODO: the thing that happens when a file isn't found
			System.out.println("couldn't find general help file");
		}
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(10));
		horizBox.add(vertBox);
		horizBox.add(Box.createHorizontalStrut(10));
		
		overall.add(horizBox);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(1000,594));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(18,Integer.MAX_VALUE));
		
		//Create boundary Panels and put it all together
				Box fullBar = Box.createVerticalBox();
				
				//Top Panel
				JPanel topPanel = new JPanel(null);
				topPanel.setPreferredSize(new Dimension(950,35));
				topPanel.setBackground(new Color(50,50,50,255));
				
				Box userBox = Box.createHorizontalBox();
				JLabel _un = new JLabel(_driver.getPlayerStats().getUsername());
				_un.setFont(new Font("Cambria", Font.PLAIN, 20));
				_un.setForeground(Color.white);
				userBox.add(_un);
				userBox.setSize(200,35);
				userBox.setLocation(20, 3);
				
				Box topBar = Box.createHorizontalBox();
				JLabel _score = new JLabel("Total points: "+_driver.getPlayerStats().getPoints());
				_score.setFont(new Font("Cambria", Font.PLAIN, 20));
				_score.setForeground(Color.white);
				topBar.add(_score);
				topBar.setSize(400,35);
				topBar.setLocation(435, 3);
				
				JButton back = new JButton("Iru!");
				back.addActionListener(new OntoOptionsActionListener());
				back.setSize(new Dimension(100, 30));
				back.setLocation(875,5);
				
				
				
				topPanel.add(userBox);
				topPanel.add(topBar);
				topPanel.add(back);
				
				
				//Bottom Panel
				JPanel bottomPanel = new JPanel(null);
				bottomPanel.setPreferredSize(new Dimension(1000,35));
				bottomPanel.setBackground(new Color(50,50,50,255));
				
				fullBar.add(topPanel);
				fullBar.add(scrollbar);
				fullBar.add(bottomPanel);
				fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				
				add(fullBar, BorderLayout.CENTER);
		
	}
	
	
	private class OntoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(_toGoTo){
			case 0:
				_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
				break;
			case 1:
				GUIVocabGame gvg = new GUIVocabGame(_driver.getVocabGameMaker().makeLevel(_curLevel), _driver);
				_driver.changePage(gvg);
				gvg.focusOnTextField();				
				break;
			case 2:
				_driver.changePage(new GUIGrammarGame(_driver.getGrammarGameMaker().makeLevel(_curLevel), _driver));
				break;
			case 3:
				GUIBossGame g=new GUIBossGame(_driver.getBossGameMaker().makeLevel(_curLevel), _driver);
				_driver.changePage(g);
				g.focusOnTextField();
				break;
			default:
				_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));;
				break;
			}
			
		}
		
	}
	
}
