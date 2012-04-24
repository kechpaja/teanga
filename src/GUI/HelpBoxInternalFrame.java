package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;

import ELearning.HelpBox;


public class HelpBoxInternalFrame extends JFrame{
	private HelpBox _helpBox;
	
	public HelpBoxInternalFrame(HelpBox helpbox){
		super("Dictionary");
		_helpBox = helpbox;
		
		this.pack();
		this.setVisible(true);
		this.setLocation(200, 30);
		this.setPreferredSize(new Dimension(500, 500));
		this.setVisible(true);
	}
}
	
