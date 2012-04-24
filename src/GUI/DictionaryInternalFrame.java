package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import annie.MyDictionary;
import annie.Word;

import javax.swing.*;

@SuppressWarnings("serial")
public class DictionaryInternalFrame extends JFrame{
	private MyDictionary _dictionary;
	private JTextField input;
	private JButton EsperantoToEnglish, EnglishToEsperanto, search;
	private JTextArea result;
	private Box searchBar;
	private Boolean isFromEsperanto;
	
	public DictionaryInternalFrame(MyDictionary dictionary){
		super("Dictionary");
		_dictionary = dictionary;
		
		this.setBackground(new Color(100,110,255,255));

		isFromEsperanto = true;
		input = new JTextField();
		search = new JButton("Search");
		search.addActionListener(new SearchActionListener());
		searchBar = new Box(BoxLayout.X_AXIS);
		searchBar.add(input);
		EsperantoToEnglish = new JButton("Eo -> Eng");
		EnglishToEsperanto = new JButton("Eng -> Eo");
		searchBar.add(EsperantoToEnglish);
		result = new JTextArea();
		result.setSize(500, 500);
		//Make overall container
		JPanel overall = new JPanel(new BorderLayout());
		JScrollPane resultScrollPane = new JScrollPane();
		result.setEditable(false);
		resultScrollPane.add(result);
		overall.add(searchBar, BorderLayout.NORTH);
		overall.add(resultScrollPane, BorderLayout.CENTER);
		overall.add(search, BorderLayout.SOUTH);
		
		
		this.add(overall);
		this.pack();
		this.setVisible(true);
		this.setLocation(30, 30);
		this.setPreferredSize(new Dimension(500, 500));
		this.setVisible(true);
	}
	
	private class SearchActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Word w = _dictionary.getWord(input.getText(), true);
			result.setText(w.exampleSentence);
			
			
		}
		
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
