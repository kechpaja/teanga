package finalGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class GUIOptionsPage extends JPanel{
	
	public GUIOptionsPage(){
		
		java.awt.Dimension size = new java.awt.Dimension(1000, 1000);
		this.setPreferredSize(size);
		this.setBackground(new Color(50,50,50,255));
		
		int numacts = 10;
		JPanel overall = new JPanel(new BorderLayout());
		overall.setBackground(new Color (0,255,255,0));
		overall.setPreferredSize(new Dimension(950,100*numacts+70));
		
		JPanel titles = new JPanel(new BorderLayout());
		titles.setPreferredSize(new Dimension(1000,80));
		titles.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		Box theTitles = Box.createHorizontalBox();
		
		JLabel learning = new JLabel("Learni");
		JLabel games = new JLabel("Apliki");
		learning.setFont(new Font("Century", Font.BOLD, 30));
		games.setFont(new Font("Century", Font.BOLD, 30));
		
		learning.setBorder(BorderFactory.createEmptyBorder(20,50,0,60));
		games.setBorder(BorderFactory.createEmptyBorder(20,0,0,60));
		
		theTitles.add(Box.createRigidArea(new Dimension(70,0)));
		theTitles.add(learning);
		theTitles.add(Box.createRigidArea(new Dimension(70,0)));
		theTitles.add(games);
		theTitles.add(Box.createRigidArea(new Dimension(300,0)));
		
		titles.add(theTitles);
		
		//Make internal panel
		String grammarImagePath = "C:/Users/Dede/finalImage.png";
		String vocabImagePath = "C:/Users/Dede/finalImage2.png";
		String bossImagePath = "C:/Users/Dede/finalImage3.png";
		
		Box vocabLearningColumn = Box.createVerticalBox();
		Box grammarLearningColumn = Box.createVerticalBox();
		Box vocabGameColumn = Box.createVerticalBox();
		Box grammarGameColumn = Box.createVerticalBox();
		Box levelNameColumn = Box.createVerticalBox();
		Box bossGameColumn = Box.createVerticalBox();
		
		//add all of the columns to the box
		Box totalBox = Box.createHorizontalBox();
		
		totalBox.add(Box.createRigidArea(new Dimension(70,0)));
		totalBox.add(vocabLearningColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(grammarLearningColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(vocabGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(grammarGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(levelNameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(30,0)));
		totalBox.add(bossGameColumn);
		totalBox.add(Box.createRigidArea(new Dimension(70,0)));
		
		
		BufferedImage myPicture;
		BufferedImage myPicture2;
		BufferedImage myPicture3;
		try {
			
			myPicture = ImageIO.read(new File(grammarImagePath));
			myPicture2 = ImageIO.read(new File(vocabImagePath));
			myPicture3 = ImageIO.read(new File(bossImagePath));
			
			int type = BufferedImage.TYPE_INT_RGB;
	        BufferedImage dst = new BufferedImage(myPicture.getWidth(), myPicture.getHeight(), type);
	        Graphics2D g2 = dst.createGraphics();
	        g2.drawImage(myPicture2, 0, 0, myPicture.getWidth(), myPicture.getHeight(), this);
	        g2.dispose();
	        ImageIcon newIcon2 = new ImageIcon(dst);
	        
	        int type3 = BufferedImage.TYPE_INT_RGB;
	        BufferedImage dst3 = new BufferedImage(myPicture.getWidth(), myPicture.getHeight(), type3);
	        Graphics2D g3 = dst3.createGraphics();
	        g3.drawImage(myPicture3, 0, 0, myPicture.getWidth(), myPicture.getHeight(), this);
	        g3.dispose();
	        ImageIcon newIcon3 = new ImageIcon(dst3);
			
			for(int i = 0; i < numacts; i++){
				
				JLabel picl1 = new JLabel(new ImageIcon( myPicture ));
				picl1.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
				vocabLearningColumn.add(picl1);
				JLabel picl2 = new JLabel(newIcon2);
				picl2.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
				grammarLearningColumn.add(picl2);
				JLabel picl3 = new JLabel(new ImageIcon( myPicture ));
				picl3.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
				vocabGameColumn.add(picl3);
				JLabel picl4 = new JLabel(newIcon2);
				picl4.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
				grammarGameColumn.add(picl4);
				
				JLabel picl5 = new JLabel("The Awesome Level");
				picl5.setFont(new Font("Cambria", Font.BOLD, 25));
				picl5.setBorder(BorderFactory.createEmptyBorder(33,0,33,0));
				levelNameColumn.add(picl5);
				
				JLabel picl6 = new JLabel(newIcon3);
				picl6.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
				bossGameColumn.add(picl6);
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		overall.add(titles, BorderLayout.NORTH);
		overall.add(totalBox, BorderLayout.CENTER);
		
		JScrollPane scrollbar = new JScrollPane(overall);
		scrollbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollbar.setPreferredSize(new Dimension(985,595));
		scrollbar.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		Box fullBar = Box.createVerticalBox();

		Box topBar = Box.createHorizontalBox();
		topBar.add(Box.createRigidArea(new Dimension(0, 30)));
		Box bottomBar = Box.createHorizontalBox();
		bottomBar.add(Box.createRigidArea(new Dimension(0, 30)));
		
		fullBar.add(topBar);
		fullBar.add(scrollbar);
		fullBar.add(bottomBar);
		fullBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		add(fullBar, BorderLayout.CENTER);
	}
	
	
	
	
	

}
