package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import annie.*;

import ELearning.*;

public class GUIBasicPage extends JPanel{
	Driver _driver;
	JList nameList;

	public GUIBasicPage(Driver driver){
		super(new BorderLayout());
		_driver = driver;
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
		String usernames[] = {"Kelvin", "Annie", "Tara", "Danielle"};

		//String usernames[] = (String[]) openingpage.getUsernames().toArray();
		
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,350,30,350), BorderFactory.createLineBorder(Color.black));
		nameList = new JList(usernames);
		nameList.addListSelectionListener(new mySelectionListener());
		JScrollPane scrollpane = new JScrollPane(nameList);
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
	
	private class mySelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()){
				JList list = (JList) e.getSource();
				String name = (String) list.getSelectedValue();
				_driver.setUserName(name);
				System.out.println(_driver.getUserName());
			}
			
		}
		
	}
	
	public static void main(String[] args){
		GUIBasicPage testPage = new GUIBasicPage(new Driver());
		JFrame mainFrame = new JFrame("E Learning");
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(testPage, BorderLayout.CENTER);
		
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
	
}
