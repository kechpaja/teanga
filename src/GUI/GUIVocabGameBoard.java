package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import annie.PlayerStats;

import ELearning.Driver;
import ELearning.VocabLevel;
import ELearning.VocabPicturePair;


@SuppressWarnings("serial")
public class GUIVocabGameBoard extends JPanel {

	int _top;
	//String _path;
	VocabLevel _vl;
	PlayerStats _playerStats;
	GUIVocabGamePiece[] _pieces;
	Timer _timer, _overallTimer, _countUp;
	Driver _driver;
	int seconds;
	


	public GUIVocabGameBoard (VocabLevel vl, PlayerStats ps, Driver d){

		super(new BorderLayout());

		java.awt.Dimension size = new java.awt.Dimension(1000, 575);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(238,238,238,255));

		_top = 0; //the place to put the next piece
		_vl = vl;
		_driver = d;
		_playerStats = ps;
		_pieces = new GUIVocabGamePiece[5]; //the collection of current pieces

		//The timer that speeds up the falling
		_overallTimer = new Timer(10000, new TimerListener());
		_overallTimer.start();

		//The timer that causes the falling
		_timer = new Timer(500, new DropListener());
		_timer.start();
		
		//The timer that keeps track of how long they have been playing
		_countUp = new Timer(1000, new CountUpListener());
		seconds = 0;
		_countUp.start();


		addPiece();

	}
	public int getSeconds(){
		return seconds;
	}


	public void addPiece(){
		//If the stack of blocks is full end the game (this will 
		// be more complete in the final version.
		if(_top == 5){
			_timer.stop();
			_playerStats.RefreshStats(_vl.getLevelNum(), 0, _vl.getScore());
			_driver.changePage(new GUIGameCompleted(_driver, _vl));
		}
		else{
			//Otherwise add a new piece (and set its bottom)
			VocabPicturePair newPair = _vl.addToWaiting();
			String path = newPair.getPicturePath();
			_pieces[_top] = new GUIVocabGamePiece(path, this, 395, 454-420, 454-_top*100, newPair.getEnglish());
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
	
	private class CountUpListener implements java.awt.event.ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			seconds++;
		}
		
	}
	
	public void pause(){
		_timer.stop();
		_countUp.stop();
		_overallTimer.stop();
	}
	
	public void restart(){
		_timer.start();
		_countUp.start();
		_overallTimer.start();
	}

}