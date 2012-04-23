package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ELearning.Driver;

public class GUIBasicPage extends JPanel{
	Driver _driver;
	JList nameList;
	JPasswordField passField;
	JButton submitB;
	Box _passOrUser;
	JPanel _passPanel, _newUserPanel;
	JTextField newNameField;
	JPasswordField newPassField;
	Box verticalBox;
	String[] genderChoices = {"Male", "Female", "Other"};
	JComboBox genderChoice;
	boolean isSelectedUser;

	public GUIBasicPage(Driver driver){
		super(new BorderLayout());
		_driver = driver;
		isSelectedUser = true;
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

		String[] usernames = _driver.openingpage.getUsernames().toArray(new String[0]);
		
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,350,30,350), BorderFactory.createLineBorder(Color.black));
		nameList = new JList(usernames);
		nameList.addListSelectionListener(new mySelectionListener());
		JScrollPane scrollpane = new JScrollPane(nameList);
		scrollpane.setBorder(compound);
		
		JPanel buttonpane = new JPanel();
		buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.Y_AXIS));
		
		submitB = new JButton("Submit");
		submitB.addActionListener(new SubmitActionListener());
		JButton addUserB = new JButton("Add User");
		addUserB.addActionListener(new AddUserActionListener());
		passField = new JPasswordField(20);
		JLabel passLabel = new JLabel("Password: ");
		_passPanel = new JPanel(new BorderLayout());
		_newUserPanel = new JPanel(new BorderLayout());
		Box passBox = Box.createHorizontalBox();
		passBox.add(Box.createHorizontalStrut(350));		
		passBox.add(passLabel);
		passBox.add(Box.createHorizontalStrut(10));
		passBox.add(passField);
		passBox.add(Box.createHorizontalStrut(350));
		_passPanel.add(passBox, BorderLayout.CENTER);
		
		Box newUserBox = Box.createHorizontalBox();
		JLabel newName = new JLabel("New Username: ");
		JLabel newPass = new JLabel("New Password: ");
		newNameField = new JTextField();
		newPassField = new JPasswordField();
		genderChoice = new JComboBox(genderChoices);
		newUserBox.add(Box.createHorizontalStrut(200));
		newUserBox.add(newName);
		newUserBox.add(newNameField);
		newUserBox.add(Box.createHorizontalStrut(20));
		newUserBox.add(newPass);
		newUserBox.add(newPassField);
		newUserBox.add(genderChoice);
		newUserBox.add(Box.createHorizontalStrut(200));
		_newUserPanel.add(newUserBox, BorderLayout.CENTER);
		
		_passOrUser = Box.createHorizontalBox();
		_passOrUser.add(_passPanel);
		
		verticalBox = Box.createVerticalBox();
	    verticalBox.add(Box.createRigidArea(new Dimension(0, 10)));
	    if (!_driver.openingpage.getUsernames().isEmpty()){
		    verticalBox.add(_passOrUser);
		    verticalBox.add(Box.createVerticalStrut(10));
	    	verticalBox.add(submitB);
	    	verticalBox.add(Box.createRigidArea(new Dimension(0, 10)));
	    }
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
	
	public void passBoxNewUser(){
		_passOrUser.removeAll();
		_passOrUser.add(_newUserPanel);
		isSelectedUser = false;
		this.revalidate();
	}
	
	public void passBoxPass(){
		_passOrUser.removeAll();
		_passOrUser.add(_passPanel);
		this.revalidate();
	}
	
	private class mySelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()){
				JList list = (JList) e.getSource();
				String name = (String) list.getSelectedValue();
				_driver.setUserName(name);
			}
			
		}
		
	}
		
	private class AddUserActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			passBoxNewUser();
			verticalBox.removeAll();
		    verticalBox.add(Box.createRigidArea(new Dimension(0, 10)));
		    verticalBox.add(_passOrUser);
		    verticalBox.add(Box.createVerticalStrut(10));
			verticalBox.add(submitB);
	    	verticalBox.add(Box.createVerticalStrut(75));
			verticalBox.revalidate();
		}
		
	}
	
	private class SubmitActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (isSelectedUser){
				if (_driver.getUserName() == null){
				String infoMessage = "Please select an existing user";
				JOptionPane.showMessageDialog(new JFrame(), infoMessage, "No User Selected", JOptionPane.INFORMATION_MESSAGE);
				return;
				} else {
				try {
					if (_driver.openingpage.correctPassword(_driver.getUserName(), new String(passField.getPassword()))){
						_driver.setPlayerStats(_driver.openingpage.bootGame(_driver.getUserName()));
						_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));

					} else {
						String infoMessage = "Incorrect Password";
						JOptionPane.showMessageDialog(new JFrame(), infoMessage, "", JOptionPane.INFORMATION_MESSAGE);
						//try to figure out how to zero the passfield
						return;
					}
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (BadPaddingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} 
				
		} else {
			int gender = genderChoice.getSelectedIndex();
			String newName = newNameField.getText();
			try {
				if (_driver.openingpage.usernameAvailable(newName)){
					_driver.setUserName(newName);
					_driver.openingpage.newUser(newName, new String(newPassField.getPassword()), gender);
					_driver.setPlayerStats(_driver.openingpage.newGame(newName, gender));
					_driver.changePage(new GUIOptionsPage(_driver, _driver.getPlayerStats()));
				} else {
					String infoMessage = "The user name " + newName + " is already taken. Please try another.";
					JOptionPane.showMessageDialog(new JFrame(), infoMessage, "", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
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
