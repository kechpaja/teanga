package ELearning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GUIVocabGameBoard extends JPanel {

	int _top;
	//String _path;
	VocabLevel _vl;
	GUIVocabGamePiece[] _pieces;
	Timer _timer, _overallTimer;


	public GUIVocabGameBoard (VocabLevel vl){

		super(new BorderLayout());

		java.awt.Dimension size = new java.awt.Dimension(1000, 590);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(255,255,255,255));

		_top = 0; //the place to put the next piece
		_vl = vl;
		_pieces = new GUIVocabGamePiece[5]; //the collection of current pieces

		//The timer that speeds up the falling
		_overallTimer = new Timer(10000, new TimerListener());
		_overallTimer.start();

		//The timer that causes the falling
		_timer = new Timer(500, new DropListener());
		_timer.start();

		//TODO: Simply for testing (you should change the path to a picture on your
		// machine.
		//_path = "C:/Users/Dede/finalImage.png";
		addPiece();

	}

	//This will have to be called everytime a new piece is made (to 
	// prepare for the creation of the next piece)
	/*public void setNewPath(String newPath){
		_path = newPath;
	}*/

	public void addPiece(){
		//If the stack of blocks is full end the game (this will 
		// be more complete in the final version.
		if(_top == 5){
			_timer.stop();
			System.out.println("Game Over");
		}
		/*else if(_path == null){
			System.out.println("Path is null!");
		}*/
		else{
			//Otherwise add a new piece (and set its bottom)
			String path = _vl.addToWaiting().getPicturePath();
			_pieces[_top] = new GUIVocabGamePiece(path, this, 395, 454-420, 454-_top*100);
			_top++;

		}
	}

	//Remove the bottom piece
	public void clearPiece(){
		_top--;

		for(int i = 0; i < 4; i++){
			_pieces[i] = _pieces[i+1];
			if(_pieces[i] != null){
				_pieces[i].setBottom(454-103*i);
			}
		}

		_pieces[4] = null;
	}

	//Paint all of the pieces when painting the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;

		for (int i = 0; i < _top; i++){
				_pieces[i].paint(brush);
		}

	}

	//The action listener for the timer (calls the move method on all of
	// the existing pieces and if there are no pieces left or if the
	// top piece has settled, adds a new piece)
	private class DropListener implements java.awt.event.ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent e){
			for (int i=0; i<_top; i++){
				_pieces[i].move();
			}

			if(_top == 0 || _pieces[_top-1].getY() >= _pieces[_top-1].getBottom()){
				addPiece();
			}
		}

	}

	//This listens to the overall timer and decreases the fall timer's 
	// delay time at a regular interval.  If the delay time cannot be
	// lowered again then the overall timer is stopped.
	private class TimerListener implements java.awt.event.ActionListener {

		public void actionPerformed(java.awt.event.ActionEvent e){
			int delay = _timer.getDelay();
			int change = delay - 30;
			if(change > 0){
				_timer.setDelay(delay - 30);
			}
			else{
				_overallTimer.stop();
			}
		}

	}

}