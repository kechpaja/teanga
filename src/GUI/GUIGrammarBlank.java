package GUI;

import javax.swing.JPanel;

import gfx.Rectangle;

public class GUIGrammarBlank extends Rectangle{
	
	private GUIGrammarChoice _filler;
	private int _blankNum;
	private String _correct;
	
	public GUIGrammarBlank(JPanel container, int blankNum, String correct) {
		super(container);
		
		_filler = null;
		_blankNum = blankNum;
		_correct = correct;
		
	}
	
	public void setFill(GUIGrammarChoice filler){
		_filler = filler;
	}
	
	public GUIGrammarChoice getFill(){
		return _filler;
	}
	
	public boolean isFull(){
		return !(_filler == null);
	}
	
	public int getBlankNum(){
		return _blankNum;
	}
	
	public String getCorrect(){
		return _correct;
	}

}
