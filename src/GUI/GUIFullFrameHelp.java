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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingConstants;

import ELearning.Driver;

public class GUIFullFrameHelp extends GUIFoundationPage{
	Driver _driver;
	boolean _even;
	//int to switch on (0 means it should go to options page, 1 should go to VocabGame, 2 should go to Grammar Game
	int _toGoTo;
	JPanel _toReturnTo;
	
	public GUIFullFrameHelp(String helpFile, Driver d, int toGoTo, JPanel toReturnTo){
		super(d, true);
		_driver = d;
		_toGoTo = toGoTo;
		_toReturnTo = toReturnTo;
		//if the JPanel given is a vocabgame, pause it so that the user can return to it in the same place.
		if (_toGoTo == 1){
			GUIVocabGame gvg = (GUIVocabGame) toReturnTo;
			gvg.pause();
		}

		hideBottom();
		
		//back button
		setBack(new OntoOptionsActionListener(),"Iru!", false);
		
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
				panel.setMaximumSize(new Dimension(950,200));
				explinArea.setBackground(new Color(238,238,238,255));

				
				panel.add(explinArea);
				
				vertBox.add(panel);
				
				para = br.readLine();
			}
			vertBox.add(Box.createVerticalGlue());
		} catch (IOException e) {
			//TODO: the thing that happens when a file isn't found
		}
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(10));
		horizBox.add(vertBox);
		horizBox.add(Box.createHorizontalStrut(10));
		
		overall.add(horizBox);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.getVerticalScrollBar().setUnitIncrement(16);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(994,595));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(scrollbar, BorderLayout.CENTER);
		
		setMainPanel(mainPanel);
		
	}
	
	
	private class OntoOptionsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (_toGoTo == 1){
				GUIVocabGame gvg = (GUIVocabGame) _toReturnTo;
				_driver.changePage(gvg);
				gvg.focusOnTextField();	
				gvg.restart();
			} else _driver.changePage(_toReturnTo);;	
		}
		
	}
	
}
