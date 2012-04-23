package GUI;

import gfx.Rectangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGrammarChoicePanel extends JPanel {
	private Box _layout;
	private ArrayList<JLabel> _blanks;
	private ArrayList<Rectangle> _rectBlanks;
	private JPanel _panel;
	
	public GUIGrammarChoicePanel(Box layout, ArrayList<JLabel> blankPlace){
		super(new BorderLayout());
		
		_layout = layout;
		_blanks = blankPlace;
		_panel = this;
		_rectBlanks = new ArrayList<Rectangle>();
		this.add(_layout, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(1000, 200));
		this.setBackground(new Color(0,0,0,0));
		
		for(int k=0; k< _blanks.size(); k++){
			
			JLabel currLabel = _blanks.get(k);
			currLabel.addComponentListener(new myComponentListener(k));
			Rectangle currRect = new Rectangle(this);
			currRect.setSize(140,30);
			currRect.setLocation(0, 0);
			currRect.setColor(new Color(0,0,0,255));
			currRect.setVisible(false);
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
		System.out.println("Painting here!");
		for (int i = 0; i < _rectBlanks.size(); i++){
			_rectBlanks.get(i).paint(brush);
		}

	}
	
	private class myComponentListener implements ComponentListener{
		private int _num;
		private myComponentListener(int num){
			_num = num;
		}
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {

			_panel.revalidate();
			boolean move = true;
			int panelX = 0;
			int panelY = 0;
			int compX = 0;
			int compY = 0;
			try{
				panelX = _panel.getLocationOnScreen().x;
				panelY = _panel.getLocationOnScreen().y;
				compX = e.getComponent().getLocationOnScreen().x;
				compY = e.getComponent().getLocationOnScreen().y;
			} catch(IllegalComponentStateException exception){
				move = false;
			}
			
			while(move == false || (compX-panelX) == 0 || (compY-panelY) == 0 || e.getComponent().isVisible() == false){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					panelX = _panel.getLocationOnScreen().x;
					panelY = _panel.getLocationOnScreen().y;
					compX = e.getComponent().getLocationOnScreen().x;
					compY = e.getComponent().getLocationOnScreen().y;
					move = true;
				} catch(IllegalComponentStateException exception){
					move = false;
				}
			}
			
			_rectBlanks.get(_num).setLocation((compX-panelX)+1, (compY-panelY));
			_rectBlanks.get(_num).setVisible(true);
			_panel.revalidate();
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
	
}
