package gfx;
import java.awt.*;
import javax.swing.*;

/**
This class allows you to paint nice looking fonts to specific locations on your JPanel.  
*/

public class ColorText {
	private String _text;
	private Font _font;
	private int _posX, _posY;
	private Color _color;
	private Dimension _size;
	private boolean _visible;
	
	/**
		Creates a new instance of ColorText
		Inputs:  
			p -- reference to the Panel where you want to draw the text
			text -- the text you wish to appear (for example, 'CS15 is awesome!')
			color -- the java.awt.Color that you wish your text to have
			posX -- the x position of where the upper left corner of the text should be placed
			posY -- the y position of where the upper left corner of the text should be placed
	*/

	public ColorText (JPanel p, String text) {
		_text = text;
		_font = new Font("SansSerif", Font.PLAIN, 14);
		_color = new Color(0,0,0,255);
		_posX = 100;
		_posY = 100;
		FontMetrics fm = p.getFontMetrics(_font);
		_size = new Dimension(fm.stringWidth(_text), fm.getHeight());
		_visible = true;
	}

	/**
		Make sure you call this method on an instance of your ColorText in the paintComponent of your DrawingPanel to make your text appear!
	*/

	public void setFont(String text, int style, int size){
		_font = new Font(text, style, size);
	}

	public void setLocation(int x, int y){
		_posX = x;
		_posY = y;
	}
	
	public String getText(){
		return _text;
	}
	
	public void setVisible(boolean flag){
		_visible = flag;
	}
	
	public void setColor(Color newColor){
		_color = newColor;
	}

	public void paint(Graphics2D brush) {
		
		if(_visible==true){
		brush.setColor(_color);
		brush.setFont(_font);
		brush.drawString(_text, _posX, _posY);
		// rotation can be done same as shape
		}
	}
	
	public int getCenterX() {
		return (_size.width/2) + _posX;
	}
	
	public int getCenterY() {
		return (_size.height/2) + _posY;
	}
}
