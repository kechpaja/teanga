package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGrammarChoicePanel extends JPanel {
	private Box _layout;
	private ArrayList<JLabel> _blanks;
	
	public GUIGrammarChoicePanel(Box layout, ArrayList<JLabel> blankLabels){
		super(new BorderLayout());
		
		_layout = layout;
		_blanks = blankLabels;
		this.add(_layout, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(900, 200));
		this.setBackground(new Color(0,0,0,0));
	}
	
	public ArrayList<JLabel> getBlanks(){
		return _blanks;
	}
}
