package GUI;

import javax.swing.JPanel;

import gfx.Rectangle;

public class GUIGrammarBlank extends Rectangle{
	
	private GUIGrammarChoice _filler;
	private int _blankNum;
	
	public GUIGrammarBlank(JPanel container, int blankNum) {
		super(container);
		
		_filler = null;
		_blankNum = blankNum;
		
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

}
