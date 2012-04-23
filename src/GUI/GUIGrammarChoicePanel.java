package GUI;

import gfx.Rectangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGrammarChoicePanel extends JPanel {
	private Box _layout;
	private ArrayList<Integer> _blanks1, _blanks2;
	private ArrayList<Rectangle> _rectBlanks;
	
	public GUIGrammarChoicePanel(Box layout, ArrayList<Integer> blankLine1, ArrayList<Integer> blankLine2){
		super(new BorderLayout());
		
		_layout = layout;
		_blanks1 = blankLine1;
		_blanks2 = blankLine2;
		_rectBlanks = new ArrayList<Rectangle>();
		this.add(_layout, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(900, 200));
		this.setBackground(new Color(0,0,0,0));
		
		for(int k=0; k< blankLine1.size(); k++){
			Integer currNum = blankLine1.get(k);
			Rectangle currRect = new Rectangle(this);
			currRect.setSize(150,40);
			currRect.setLocation(currNum*100, currNum*100);
			currRect.setColor(new Color(0,0,0,255));
			currRect.setVisible(true);
			_rectBlanks.add(currRect);
		}
	}
	
	public ArrayList<Rectangle> getBlanks(){
		return _rectBlanks;
	}
	
	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;

		for (int i = 0; i < _rectBlanks.size(); i++){
				_rectBlanks.get(i).paint(brush);
		}

	}
	
}
