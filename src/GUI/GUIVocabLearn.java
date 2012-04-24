package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GUIVocabLearn extends JPanel{
	
	public GUIVocabLearn(){
		super(new BorderLayout());
		this.setBackground(new Color(50,50,100,255));
		
		JPanel overall = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(overall);
		
		//Make the title
		int levelnum = 1;
		String label = "Level " + Integer.toString(levelnum) + " Vocab";
		JLabel title = new JLabel(label, SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		title.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));
		
		overall.add(title, BorderLayout.NORTH);
		
		JPanel main = new JPanel(new BorderLayout());
		main.setPreferredSize(new Dimension(1000,3000));
		main.setBackground(new Color(0,0,0,255));
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(20));
		
		Box picVert = Box.createVerticalBox();
		Box wordnTranslate = Box.createVerticalBox();
		Box sentance = Box.createVerticalBox();
		
		horizBox.add(Box.createHorizontalStrut(20));
		
		overall.add(main, BorderLayout.CENTER);

		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));
		
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		
	}

	public static void main(String[] args){
		GUIVocabLearn panel = new GUIVocabLearn();
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000,700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
