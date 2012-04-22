package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class GUIBasicPage extends JPanel{

	public GUIBasicPage(){
		super(new BorderLayout());
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 600);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setBackground(new Color(100,110,255,255));
		
		//Make overall container
		JPanel overall = new JPanel(new BorderLayout());
		
		//Make the title
		JLabel title = new JLabel("ELearning", SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("AR DELANEY", Font.PLAIN, 80));
		title.setBorder(BorderFactory.createEmptyBorder(50,0,20,0));
		
		//Make the username list
		JPanel listpane = new JPanel(new BorderLayout());
		String usernames[] = {"Freddy", "Bob", "James", "John", "Arthur", "Sam", "Jeffery", "Mary", "Margret", "Suzzy", "Samantha", "Brittney", "Deborah", "Jeana", "Sandra", "Julie", "Frankie"};
		
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,350,30,350), BorderFactory.createLineBorder(Color.black));
		JList list = new JList(usernames);
		JScrollPane scrollpane = new JScrollPane(list);
		scrollpane.setBorder(compound);
		
		JPanel buttonpane = new JPanel();
		buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.Y_AXIS));
		
		JButton submitB = new JButton("Submit");
		JButton addUserB = new JButton("Add User");
		
		Box verticalBox = Box.createVerticalBox();
	    verticalBox.add(Box.createRigidArea(new Dimension(0, 10)));
	    verticalBox.add(submitB);
	    verticalBox.add(Box.createRigidArea(new Dimension(0, 10)));
	    verticalBox.add(addUserB);
	    verticalBox.add(Box.createVerticalStrut(110));
	    submitB.setAlignmentX(CENTER_ALIGNMENT);
	    addUserB.setAlignmentX(CENTER_ALIGNMENT);
		
		listpane.add(scrollpane, BorderLayout.CENTER);
		listpane.add(verticalBox, BorderLayout.SOUTH);
		
		//add everything to the panel
		overall.add(title, BorderLayout.NORTH);
		overall.add(listpane, BorderLayout.CENTER);
		
		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 40)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 40)));
		
		add(topBar, BorderLayout.NORTH);
		add(overall, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
	}
	
}
