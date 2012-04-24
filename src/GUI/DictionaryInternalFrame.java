package GUI;

import java.awt.Dimension;
import java.io.IOException;

import annie.MyDictionary;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DictionaryInternalFrame extends JFrame{
	private MyDictionary _dictionary;
	
	public DictionaryInternalFrame(MyDictionary dictionary){
		super("Dictionary");
		_dictionary = dictionary;
		
		this.pack();
		this.setVisible(true);
		this.setLocation(30, 30);
		this.setPreferredSize(new Dimension(500, 500));
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		try {
			DictionaryInternalFrame dif = new DictionaryInternalFrame(new MyDictionary("data/dictionary.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
