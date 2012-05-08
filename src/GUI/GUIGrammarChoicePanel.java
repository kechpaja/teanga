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

import ELearning.GrammarLevel;

@SuppressWarnings("serial")
public class GUIGrammarChoicePanel extends JPanel {
	private Box _layout;
	private ArrayList<JLabel> _blanks;
	private ArrayList<GUIGrammarBlank> _rectBlanks;
	private ArrayList<GUIGrammarChoice> _rectChoices;
	private JPanel _panel, _container;
	private String[] _choices;
	private GrammarLevel _grammarLevel;
	private int _width;
	
	public GUIGrammarChoicePanel(JPanel container, Box layout, ArrayList<JLabel> blankPlace, GrammarLevel gl, int width){
		super(new BorderLayout());
		
		
		_grammarLevel = gl;
		_layout = layout;
		_blanks = blankPlace;
		_choices = gl.getCurrent().getPossibilities();
		_panel = this;
		_container = container;
		_rectBlanks = new ArrayList<GUIGrammarBlank>();
		_rectChoices = new ArrayList<GUIGrammarChoice>();
		_width = width;
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
			GUIGrammarBlank currRect = new GUIGrammarBlank(this, k, gl.getCurrent().getCorrectAnswers()[k]);
			currRect.setSize(_width,30);
			currRect.setLocation(0, 0);
			currRect.setColor(new Color(150,150,150,255));
			currRect.setBorderColor(new Color(0,0,100,255));
			currRect.setVisible(false);
			_rectBlanks.add(currRect);
		}
		
		for(int j=0; j< _choices.length; j++){
			GUIGrammarChoice currRect = new GUIGrammarChoice(this, _choices[j], j);
			currRect.setSize(_width-5,26);
			currRect.setLocation((1000-(_choices.length*135 + (_choices.length-1)*20))/2+j*155,207);
			currRect.setColor(new Color(60,60,60,255));
			_rectChoices.add(currRect);
			
		}
		
		MovingAdapter ma = new MovingAdapter();
		this.addMouseMotionListener(ma);
		this.addMouseListener(ma);
	}
	
	public void setChoices(int _width){
		for(int j=0; j< _choices.length; j++){
			_rectChoices.get(j).setSize(_width-5,26);
			_rectChoices.get(j).setLocation((1000-(_choices.length*135 + (_choices.length-1)*20))/2+j*155,207);			
		}
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
	
	public void notCorrect(){	
		for (GUIGrammarBlank blank : _rectBlanks){
			blank.setColor(new Color(100, 0, 0, 255));
			blank.setBorderColor(new Color(50,0,100,255));
		}
		this.repaint();
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
			_rectChoices.get(j).getText().paint(brush);
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
			int compWidth = 0;
			try{
				panelX = _panel.getLocationOnScreen().x;
				panelY = _panel.getLocationOnScreen().y;
				compX = e.getComponent().getLocationOnScreen().x;
				compY = e.getComponent().getLocationOnScreen().y;
				compWidth = e.getComponent().getWidth()-4;
			} catch(IllegalComponentStateException exception){
				move = false;
			}
			
			while(move == false || (compX-panelX) == 0 || (compY-panelY) == 0 || e.getComponent().isVisible() == false){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
				}
				try {
					panelX = _panel.getLocationOnScreen().x;
					panelY = _panel.getLocationOnScreen().y;
					compX = e.getComponent().getLocationOnScreen().x;
					compY = e.getComponent().getLocationOnScreen().y;
					compWidth = e.getComponent().getWidth()-4;
					move = true;
				} catch(IllegalComponentStateException exception){
					move = false;
				}
			}
			
			_rectBlanks.get(_num).setLocation((compX-panelX)+1, (compY-panelY));
			_rectBlanks.get(_num).setSize(compWidth, _rectBlanks.get(_num).getHeight());
			_rectBlanks.get(_num).setVisible(true);
			setChoices(compWidth);
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
		private GUIGrammarChoice currRect;
		
		public MovingAdapter(){
			x = 0;
			y = 0;
			currRect = null;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			currRect = null;
			for(int k = 0; k < _rectChoices.size(); k++){
				if(_rectChoices.get(k).contains(e.getPoint())){
					currRect = _rectChoices.get(k);
					x = e.getX();
					y = e.getY();
					break;
				}
			}
			
		}

		public void mouseDragged(MouseEvent e) {
			
			if (currRect != null && e.getX() > 0 && e.getX() < _panel.getWidth() && e.getY() > 0 && e.getY() < _panel.getHeight()){
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
				//remove it from its container
				if(currRect.isContained()){
					currRect.getContainer().setFill(null);
					_grammarLevel.getCurrent().removeAnswer(currRect.getContainer().getBlankNum());
					currRect.setContainer(null);
				}
				//add it to its new container
				for(int i = 0; i< _rectBlanks.size(); i++){
					if(checkOverlap(_rectBlanks.get(i), x, y)){
						if(_rectBlanks.get(i).isFull()){
							currRect.setLocation(_rectBlanks.get(i).getX()+3, currRect.getY()+40);
							_container.repaint();
						} else{
							currRect.setLocation(_rectBlanks.get(i).getX()+2, _rectBlanks.get(i).getY()+2);
							currRect.setContainer(_rectBlanks.get(i));
							_rectBlanks.get(i).setFill(currRect);
							_grammarLevel.getCurrent().submitAnswer(currRect.getIndex(), i);
							_container.repaint();
						}
						break;
					}
				}
			} catch(NullPointerException exception){
			}
			

		}
		
	}
	
	
}
