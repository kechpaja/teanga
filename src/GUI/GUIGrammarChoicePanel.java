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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIGrammarChoicePanel extends JPanel {
	private Box _layout;
	private ArrayList<JLabel> _blanks;
	private ArrayList<GUIGrammarBlank> _rectBlanks;
	private ArrayList<GUIGrammarChoice> _rectChoices;
	private JPanel _panel, _container;
	private String[] _choices, _answers;
	
	public GUIGrammarChoicePanel(JPanel container, Box layout, ArrayList<JLabel> blankPlace, String[] possible){
		super(new BorderLayout());
		
		_layout = layout;
		_blanks = blankPlace;
		_choices = possible;
		_panel = this;
		_container = container;
		_rectBlanks = new ArrayList<GUIGrammarBlank>();
		_rectChoices = new ArrayList<GUIGrammarChoice>();
		_answers = new String[_rectBlanks.size()];
		this.add(_layout, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(1000, 200));
		this.setBackground(new Color(0,0,0,0));
		
		
		Box horiz = Box.createHorizontalBox();
		horiz.add(Box.createRigidArea(new Dimension(100,89)));//means that this panel space starts at
		horiz.add(Box.createRigidArea(new Dimension(100,89)));//177
		_layout.add(horiz);
		
		for(int k=0; k< _blanks.size(); k++){
			
			JLabel currLabel = _blanks.get(k);
			currLabel.addComponentListener(new MyComponentListener(k));
			GUIGrammarBlank currRect = new GUIGrammarBlank(this, k);
			currRect.setSize(140,30);
			currRect.setLocation(0, 0);
			currRect.setColor(new Color(150,150,150,255));
			currRect.setBorderColor(new Color(100,100,100,255));
			currRect.setVisible(false);
			_rectBlanks.add(currRect);
		}
		
		for(int j=0; j< _choices.length; j++){
			GUIGrammarChoice currRect = new GUIGrammarChoice(this, _choices[j]);
			currRect.setSize(135,26);
			currRect.setLocation((1000-(_choices.length*135 + (_choices.length-1)*20))/2+j*155,207);
			currRect.setColor(new Color(0,0,200,255));
			_rectChoices.add(currRect);
			
		}
		
		MovingAdapter ma = new MovingAdapter();
		this.addMouseMotionListener(ma);
		this.addMouseListener(ma);
	}
	
	public ArrayList<GUIGrammarBlank> getBlanks(){
		return _rectBlanks;
	}
	
	public boolean checkOverlap(Rectangle a, double x, double y){
		
		double blankXmin = a.getX();
		double blankXmax = a.getX() + a.getWidth();
		double blankYmin = a.getY();
		double blankYmax = a.getY() + a.getHeight();
		
		double choiceXmin = x;
		double choiceXmax = x+135;
		double choiceYmin = y;
		double choiceYmax = x+25;
		
		boolean isTrue = true;
		
		if(!((choiceXmin > blankXmin && choiceXmin < blankXmax) || (choiceXmax > blankXmin && choiceXmax < blankXmax))){
			isTrue = false;
		}
		
		if(!((choiceYmin > blankYmin && choiceYmin < blankYmax) || (choiceYmax > blankYmin && choiceYmax < blankYmax))){
			isTrue = false;
		}
		
		return isTrue;
		
	}
	
	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		for (int i = 0; i < _rectBlanks.size(); i++){
			_rectBlanks.get(i).paint(brush);
		}
		for (int j = 0; j < _rectChoices.size(); j++){
			_rectChoices.get(j).paint(brush);
		}

	}
	
	private class MyComponentListener implements ComponentListener{
		private int _num;
		private MyComponentListener(int num){
			_num = num;
		}
		
		@Override
		public void componentResized(ComponentEvent e) {}

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
			_container.repaint();
			
		}

		@Override
		public void componentShown(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent e) {}
	
	}
	
	private class MovingAdapter extends MouseAdapter{

		private int x;
		private int y;
		private int currRectNum;
		private Rectangle currRect;
		
		public MovingAdapter(){
			x = 0;
			y = 0;
			currRectNum = -1;
			currRect = null;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			currRect = null;
			for(int k = 0; k < _rectChoices.size(); k++){
				if(_rectChoices.get(k).contains(e.getPoint())){
					currRect = _rectChoices.get(k);
					currRectNum = k;
					x = e.getX();
					y = e.getY();
					break;
				}
			}
			
		}

		public void mouseDragged(MouseEvent e) {
			
			if (currRect != null){
				int dx = e.getX() - x;
				int dy = e.getY() - y;
				
				currRect.setLocation(currRect.getX()+dx, currRect.getY()+dy);
				
				x += dx;
				y += dy;
				_container.repaint();
			}
		}
		
		public void mouseReleased(MouseEvent e){
			try{
				for(int i = 0; i< _rectBlanks.size(); i++){
					if(checkOverlap(_rectBlanks.get(i), x, y)){
						currRect.setLocation(_rectBlanks.get(i).getX()+2, _rectBlanks.get(i).getY()+2);
						_container.repaint();
						_answers[i] = _choices[currRectNum];
						break;
					}
				}
			} catch(NullPointerException exception){
				System.out.println("Exception");
			}
		}
		
	}
	
}
