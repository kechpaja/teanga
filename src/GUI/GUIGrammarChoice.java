package GUI;

import javax.swing.JPanel;

import gfx.Rectangle;

public class GUIGrammarChoice extends Rectangle{

	private GUIGrammarBlank _container;
	private String _word;
	
	public GUIGrammarChoice(JPanel jpan, String word) {
		super(jpan);
		
		_container = null;
		_word = word;
		
	}
	
	public void setContianer(GUIGrammarBlank container){
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

}
