package GUI;

import gfx.ColorText;
import gfx.Rectangle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUIVocabGamePiece {
	
	private int _addTo;
	private int _bottom;
	private double _x, _y;
	private int _width;
	private JPanel _panel;
	private Rectangle _rect1;
	private BufferedImage _image;
	private ColorText _word;
	private boolean _paintPic;
	
	public GUIVocabGamePiece(String imagePath, JPanel panel, double x, double y, int bottom, String word){
		
		if(word.contains(", "))
		{
			word=word.substring(0, word.indexOf(","));
		}
		_x = x;
		_y = y;
		_bottom = bottom;
		_panel = panel;
		_paintPic = true;

		//The actual piece
		_rect1 = new Rectangle(panel);
		_rect1.setSize(200, 100);
		_rect1.setFillColor(new Color(50, 50, 50, 255));
		_rect1.setBorderColor(new Color(0, 0, 0, 255));
		_rect1.setLocation(_x, _y);
		_addTo=100-8*word.length();
		
		_word = new ColorText(_panel, word);
		_word.setFont("Cambria", Font.PLAIN, 40);
		_word.setColor(new Color(220,220,255,255));
		JLabel label = new JLabel(word);
		FontMetrics metrics = label.getFontMetrics(new Font("Cambria",Font.PLAIN,40));
		_width = SwingUtilities.computeStringWidth(metrics, word.trim());
		
		_word.setLocation((int)Math.floor(_x)+(200-_width)/2, (int)Math.floor(_y)+60);
		_word.setVisible(false);
		
		//Load the image and reset size
		try {                
	        	_image = ImageIO.read(new File(imagePath));
	    	} catch (IOException ex) {
	    		replacePicWithWord();
	        	return;
	    	}
	   
		int type = _image.getType();
		BufferedImage resizedImage = new BufferedImage(90, 90, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(_image, 0, 0, 90, 90, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		   
		_image = resizedImage;

	}
	
	public void replacePicWithWord(){
		_word.setVisible(true);
		_paintPic = false;
	}
	
	//Gets called everytime the timer ticks
	public void move(){
		
		//If the piece is not at its lowest point
		// then move it down by 10 and repaint panel
		if(_y < _bottom){
			_y = _y+10;
			_rect1.setLocation(_x, _y);
			_word.setLocation((int)Math.floor(_x)+(200-_width)/2, (int)Math.floor(_y)+60);
			_panel.repaint();
		}
				
	}
	
	//Useful accessor methods
	public double getX(){
		return _x;
	}
	
	public double getY(){
		return _y;
	}
	
	public double getBottom(){
		return _bottom;
	}
	
	//When the bottom piece is cleared then the
	// bottom of all the rest of the pieces is reset.
	public void setBottom(int bottom){
		_bottom = bottom;
	}
	
	//When painting the piece you must paint the background
	// rectangle and the actual image.
	public void paint(Graphics2D brush){
		_rect1.paint(brush);
		if(_paintPic){
			brush.drawImage(_image, null, (int) Math.round(_x+55), (int) Math.round(_y+5));
		} else{
			_word.paint(brush);
		}
	}
	


}
