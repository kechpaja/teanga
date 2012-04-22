package GUI;

import gfx.Rectangle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GUIVocabGamePiece {
	
	int _bottom;
	double _x, _y;
	JPanel _panel;
	Rectangle _rect1;
	BufferedImage _image;
	
	public GUIVocabGamePiece(String imagePath, JPanel panel, double x, double y, int bottom){
		
		_x = x;
		_y = y;
		_bottom = bottom;
		_panel = panel;

		//The actual piece
		_rect1 = new Rectangle(panel);
		_rect1.setSize(200, 100);
		_rect1.setFillColor(new Color(50, 50, 50, 255));
		_rect1.setBorderColor(new Color(0, 0, 0, 255));
		_rect1.setLocation(_x, _y);
		
		//Load the image and reset size
		try {                
	        	_image = ImageIO.read(new File(imagePath));
	    	} catch (IOException ex) {
	        	System.out.println("image could not be loaded");
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
	
	//Gets called everytime the timer ticks
	public void move(){
		
		//If the piece is not at its lowest point
		// then move it down by 10 and repaint panel
		if(_y < _bottom){
			_y = _y+10;
			_rect1.setLocation(_x, _y);
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
		brush.drawImage(_image, null, (int) Math.round(_x+55), (int) Math.round(_y+5));
	}

}
