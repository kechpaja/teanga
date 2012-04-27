package GUI;

import gfx.ColorText;
import gfx.Rectangle;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class GUIGrammarChoice extends Rectangle{

	private GUIGrammarBlank _container;
	private String _word;
	private ColorText _string;
	private int _yoffset;
	private int _index;
	
	public GUIGrammarChoice(JPanel jpan, String word, int index) {
		super(jpan);
		
		_index = index;
		_container = null;
		_word = word;
		_string = new ColorText(jpan, word);
		_string.setFont("Cambria", Font.PLAIN, 20);
		_string.setColor(new Color(220,220,255,255));
		_yoffset = (135 - _word.length()*10)/2;
		
	}
	
	public void setContainer(GUIGrammarBlank container){
		_container = container;
	}
	
	public GUIGrammarBlank getContainer(){
		return _container;
	}
	
	public int getIndex(){
		return _index;
	}
	
	public boolean isContained(){
		return !(_container == null);
	}
	
	public String getWord(){
		return _word;
	}
	
	public void setLocation(double x, double y){
		if(_string != null){
			_string.setLocation((int)Math.floor(x)+_yoffset, (int)Math.floor(y)+20);
		}
		super.setLocation(x,y);
		
	}
	
	public ColorText getText(){
		return _string;
	}

}
