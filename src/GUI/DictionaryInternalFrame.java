package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import annie.MyDictionary;
import annie.Word;

import javax.swing.*;

import encoding.EncodingShiftListener;

@SuppressWarnings("serial")
public class DictionaryInternalFrame extends JFrame{
	private MyDictionary _dictionary;
	private JTextField input;
	private JButton EsperantoToEnglish, EnglishToEsperanto, search;
	private JTextArea result;
	private Box searchBar;
	private Boolean isFromEsperanto;
	private JScrollPane resultScrollPane;
	JPanel overall = new JPanel(new BorderLayout());
	
	public DictionaryInternalFrame(MyDictionary dictionary){
		super("Vortaro");
		_dictionary = dictionary;
		
		this.setBackground(new Color(100,110,255,255));
		//overall.setSize(500, 500);

		//top and bottom bar
		isFromEsperanto = true;
		input = new JTextField();
		input.addKeyListener(new EncodingShiftListener(input));
		input.addActionListener(new submitSearchListener());
		search = new JButton("Serĉu");
		search.addActionListener(new SearchActionListener());
		searchBar = new Box(BoxLayout.X_AXIS);
		searchBar.add(input);
		EsperantoToEnglish = new JButton("Eo -> Eng");
		EsperantoToEnglish.addActionListener(new Eo2EngActionListener());
		EnglishToEsperanto = new JButton("Eng -> Eo");
		EnglishToEsperanto.addActionListener(new Eng2EoActionListener());
		searchBar.add(EsperantoToEnglish);
		
		
		result = new JTextArea(" ", 10, 10);
		result.setEditable(false);
		result.setLineWrap(true);
		result.setWrapStyleWord(true);
		result.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		resultScrollPane = new JScrollPane(result);
		resultScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		overall.add(searchBar, BorderLayout.NORTH);
		overall.add(resultScrollPane, BorderLayout.CENTER);
		overall.add(search, BorderLayout.SOUTH);
		overall.revalidate();
		
		this.add(overall);
		this.pack();
		this.setLocation(748, 432);
		this.setVisible(true);
		this.setSize(250, 250);
		this.setVisible(true);
	}
	
	private void search(){
		Word w = _dictionary.getWord(input.getText(), isFromEsperanto);
		if(w!=null)
		{
			String translations="";
			for(String t: w.getTranslations())
			{
				translations=t+"\n";
			}
			//translations=translations.substring(0, translations.length()-2);
			result.setText("Translates to: \n"+ translations+"Part of speech: \n"+w.getPOS()
					+"\nExample sentence: \n"+w.getEx());
			result.setCaretPosition(0);
		}
		else
		{
			String eorEo="English to Esperanto";
			String other="Esperanto to English";
			if(isFromEsperanto)
			{
				eorEo="Esperanto to English";
				other="English to Esperanto";
			}
			result.setText("The dictionary does not contain the word '"+ input.getText()+"'.\n"
					+"You are translating from "+eorEo+". If you meant to translate from "+
					other+", click the toggle transation button button, above.");
			result.setCaretPosition(0);
		}
	}
	
	private class SearchActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			search();		
		}
	}
	
	private class submitSearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			search();		
		}
		
		
	}

	
	private class Eo2EngActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			searchBar.remove(EsperantoToEnglish);
			searchBar.add(EnglishToEsperanto);
			overall.revalidate();
			isFromEsperanto = false;
		}
		
	}
	
	private class Eng2EoActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			searchBar.remove(EnglishToEsperanto);
			searchBar.add(EsperantoToEnglish);
			overall.revalidate();
			isFromEsperanto = true;
		}
		
	}

	 
	
	public static void main(String[] args){
		DictionaryInternalFrame dif = new DictionaryInternalFrame(new MyDictionary("data/dictionary.txt"));
	}

}
