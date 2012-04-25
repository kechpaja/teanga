package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import annie.MyDictionary;

import ELearning.Driver;
import ELearning.HelpBox;


public class HelpBoxInternalFrame extends JFrame{
	private Driver _driver;
	private JButton backToLesson;
	private JTextArea help;
	private JScrollPane helpScrollPane;
	private boolean isVocab;
	private int lessonNum;
	JPanel overall = new JPanel(new BorderLayout());
	
	public HelpBoxInternalFrame(String levelHelp, boolean isV, int ln, Driver d){
		super("Help");
		
		_driver = d;
		isVocab = isV;
		lessonNum = ln;
		this.setBackground(new Color(100,110,255,255));
		//overall.setSize(500, 500);

		backToLesson = new JButton("Search");
		help = new JTextArea(levelHelp);
		help.setSize(250, 300);
		//Make overall container
		helpScrollPane = new JScrollPane();
		help.setEditable(false);
		help.setLineWrap(true);
		help.setWrapStyleWord(true);
		helpScrollPane.setSize(250, 200);
		helpScrollPane.add(help);
		overall.add(helpScrollPane, BorderLayout.CENTER);
		overall.add(backToLesson, BorderLayout.SOUTH);
		overall.revalidate();
		
		
		this.add(overall);
		this.pack();
		this.setVisible(true);
		this.setLocation(30, 30);
		this.setSize(250, 250);
		this.setVisible(true);
	}
	
	private class backToLessonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isVocab){
				_driver.changePage(new GUIVocabLearn(lessonNum, _driver));
			} // TODO: when there is a grammar lesson page else _driver.changePage(new GUIGrammarLearn(_driver))
			
		}
		
	}
	
}
	
