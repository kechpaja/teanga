package GUI;

import gfx.ColorText;
import gfx.Rectangle;

import java.awt.Font;

import javax.swing.JPanel;

public class GUIGrammarChoice extends Rectangle{

	private GUIGrammarBlank _container;
	private String _word;
	private ColorText _string;
	private int _yoffset;
	
	public GUIGrammarChoice(JPanel jpan, String word) {
		super(jpan);
		
		_container = null;
		_word = word;
		_string = new ColorText(jpan, word);
		_string.setFont("Cambria", Font.BOLD, 20);
		_yoffset = (135 - _word.length()*10)/2;
		
	}
	
	public void setContainer(GUIGrammarBlank container){
		_container = container;
	}
	
	public GUIGrammarBlank getContainer(){
		return _container;
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
