package encoding;

import java.awt.event.*;
import javax.swing.text.*;

public class EncodingShiftListener implements KeyListener {
	
	// NOTE: you should be able to just add this to any text component, and it will do the rest.
	// Remember that the constructor requires a reference to the component it is added to...

	private JTextComponent field_; // field on which this listener is used
	
	// constructor
	public EncodingShiftListener(JTextComponent field) {
		field_ = field;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		field_.setText(EncodingShifter.shift(field_.getText() + arg0.getKeyChar()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
