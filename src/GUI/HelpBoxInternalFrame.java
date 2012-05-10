package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import annie.MyDictionary;

import ELearning.Driver;
import ELearning.HelpBox;


public class HelpBoxInternalFrame extends JFrame{
	private Driver _driver;
	private JButton backToLesson, genHelp, howToPlay;
	private JTextArea help;
	private JScrollPane helpScrollPane;
	private int _activityType;
	private int lessonNum;
	JPanel overall = new JPanel(new BorderLayout());
	JPanel buttons = new JPanel(new BorderLayout());
	JFrame toClose;
	JPanel _toReturnTo;
	
	public HelpBoxInternalFrame(String levelHelp, int at, int ln, Driver d, JPanel toReturn){
		super("Helpu");
		
		toClose = this;
		_driver = d;
		_activityType = at;
		lessonNum = ln;
		_toReturnTo = toReturn;
		this.setBackground(new Color(100,110,255,255));
		//overall.setSize(500, 500);

		backToLesson = new JButton(" Helpu Pli!");
		backToLesson.addActionListener(new helpActionListener());
		genHelp = new JButton("How To Use ELearning");
		genHelp.addActionListener(new genHelpActionListener());
		howToPlay = new JButton("How to play this game");
		howToPlay.addActionListener(new levelHelpActionListener());
		buttons.add(genHelp, BorderLayout.SOUTH);
		if (at > 1){
			Box midButtonSection = Box.createHorizontalBox();
			midButtonSection.add(backToLesson);
			midButtonSection.add(howToPlay);
			buttons.add(midButtonSection, BorderLayout.CENTER);
		}
		
		help = new JTextArea(levelHelp);
		help.setSize(250, 300);
		//Make overall container
		helpScrollPane = new JScrollPane(help);
		help.setEditable(false);
		help.setLineWrap(true);
		help.setVisible(true);
		help.setWrapStyleWord(true);
		help.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		helpScrollPane.setSize(250, 200);
		overall.add(helpScrollPane, BorderLayout.CENTER);
		overall.add(buttons, BorderLayout.SOUTH);

		
		this.add(overall);
		//overall.revalidate();
		//overall.repaint();
		this.pack();
		this.setLocation(3, 432);
		this.setSize(250, 250);
		this.setVisible(true);
	}
	
	private class levelHelpActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (_activityType){
			case 2:
				_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GenVocabLessonHelp.txt", _driver, 1, _toReturnTo));				
				break;
			case 3:
				_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GenGrammarHelp.txt", _driver, 2, _toReturnTo));
				break;
			case 4:
				_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GenBossLevelHelp.txt", _driver, 3, _toReturnTo));
				break;
			default:
				_driver.changePage(new GUIFullFrameHelp("data/GeneralHelp.txt", _driver, 0, _toReturnTo));
				break;
			}
			toClose.dispose();
		}
		
	}
	
	private class genHelpActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GeneralHelp.txt", _driver, 0,_toReturnTo));
			toClose.dispose();
		}
		
	}
	
	private class helpActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (_activityType){
			//helpbox is already in a vocab lesson. goes back to previous vocab lesson
			case 0:
				if (lessonNum>0){
					_driver.changePage(new GUIVocabLearn(lessonNum-1, _driver, _toReturnTo));
				} else {
					//change the general help file!
					_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GeneralHelp.txt", _driver, 0, _toReturnTo));
				}
				break;
			//helpbox is in a grammar lesson. goes back to previous grammar lesson
			case 1:
				if (lessonNum>0){
					_driver.changePage(new GUIGrammarLearn(lessonNum-1, _driver, _toReturnTo));
				} else {
					//change the general help file!
					_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GeneralHelp.txt", _driver, 0,_toReturnTo));
				}
				break;
			//helpbox is in a vocab game. goes back to relevant vocab lesson
			case 2:
				_driver.changePage(new GUIVocabLearn(lessonNum, _driver, _toReturnTo));
				break;
			//helpbox is in a grammar game. goes back to relevant grammar lesson
			case 3:
				_driver.changePage(new GUIGrammarLearn(lessonNum, _driver, _toReturnTo));
				break;
			//helpbox is in a boss level. goes back to grammar lesson. maybe change later to go back to most appropriate level
			case 4:
				_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GeneralHelp.txt", _driver, 0, _toReturnTo));
				break;
			//helpbox is not in an activity, goes back to help section of whole game
			default:
				_driver.changePage(new GUIFullFrameHelp("data/HelpFiles/GeneralHelp.txt", _driver, 0, _toReturnTo));
				break;
				
			}
			toClose.dispose();
			
		}
		
	}
	
	
	
}
	
