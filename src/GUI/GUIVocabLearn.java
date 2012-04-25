package GUI;

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
		
		Box horizBox = Box.createHorizontalBox();
		horizBox.add(Box.createHorizontalStrut(20));
		
		Box picVert = Box.createVerticalBox();
		Box wordnTranslate = Box.createVerticalBox();
		Box sentenceVert = Box.createVerticalBox();
		
		horizBox.add(picVert);
		horizBox.add(wordnTranslate);
		horizBox.add(sentenceVert);
		
		horizBox.add(Box.createHorizontalStrut(20));
		JScrollPane main = new JScrollPane(horizBox);
		main.setSize(1000,700);
		main.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		
		int prefHeight = 100;
		int prefWidth = 150;
		int numWords = 10;
		String picPath = "data/funpic2.gif";
		String word = "word";
		String translation = "palabra";
		String sentence = "All of the words on the page started to blend toghether (but in spanish)";
		
		for(int i = 0; i < numWords; i++){
			
			//picVert
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(new File(picPath));
			} catch (IOException e) {
				System.out.println("Cannot read image (GUIVocabLearn)");
				System.exit(0);
			}
			int type = BufferedImage.TYPE_INT_RGB;
	        BufferedImage dst = new BufferedImage(prefWidth, prefHeight, type);
	        Graphics2D g1 = dst.createGraphics();
	        g1.drawImage(pic, 0, 0, prefWidth, prefHeight, this);
	        g1.dispose();
	        ImageIcon newIcon = new ImageIcon(dst);
	        JLabel picLabel = new JLabel(newIcon);
	        picVert.add(Box.createVerticalStrut(5));
	        picVert.add(picLabel);
	        picVert.add(Box.createVerticalStrut(5));
			
			//wordnTranslate
	        Box wordsTVert = Box.createVerticalBox();
	        Box wordsTHoriz = Box.createHorizontalBox();
	        wordsTHoriz.add(Box.createVerticalStrut(110));
	        wordsTHoriz.add(wordsTVert);
			JLabel wordLabel = new JLabel(word);
			wordLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			wordsTVert.add(wordLabel);
			wordsTVert.add(Box.createVerticalStrut(5));
			JLabel translateLabel = new JLabel(translation);
			translateLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			wordsTVert.add(translateLabel);
			wordsTVert.add(Box.createVerticalStrut(5));
			wordnTranslate.add(wordsTHoriz);
			
			//sentenceVert
			Box sentVert = Box.createVerticalBox();
			Box sentHoriz = Box.createHorizontalBox();
			JLabel sentenceLabel = new JLabel(sentence);
			sentenceLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
			sentVert.add(sentenceLabel);
			sentVert.add(Box.createVerticalStrut(5));
			sentHoriz.add(Box.createVerticalStrut(110));
			sentHoriz.add(sentVert);
			sentenceVert.add(sentHoriz);
			
		}
		
				
		
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
		JFrame frame = new JFrame("Vocab Learning");
		frame.setPreferredSize(new Dimension(1000,700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
