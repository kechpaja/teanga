package ELearning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;


/*  So, there is some type of problem.  I have found that sometimes
     when I test the game it stops creating a piece at random times
     but it has happened so rarely that I can't figure out what is
     causing it.  I am hoping that you can figure it out.  Also, I 
     have placed TODO statements in these classes where there is
     something that you need to update before running it.
*/
public class App extends javax.swing.JFrame{

public App(){
		
		super("ELearning");
		this.setPreferredSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//This instantiation would actually happen in the driver (when we get
		// everything working). The two parameters would actually become
		// an array of PicturePairs.
		// TODO: You should change the path to a picture on your machine.
		//GUIVocabGame panel = new GUIVocabGame("C:/Users/Dede/finalImage.png", "wrong");
		//GUIBasicPage panel = new GUIBasicPage();
		//GUIOptionsPage panel = new GUIOptionsPage();
		//GUIGrammarGame panel = new GUIGrammarGame();
		
		//this.add(panel, BorderLayout.CENTER);
		
		String partialSentance = "The , ate the cat how funny is that , what will we do with the hat , that swallowed the cat.";
		int _maxChars = 50;
		String actSent[] = partialSentance.split(",");//Need to fix this to be ~1~
		
		int currLength = actSent[0].length();
		String string1 = actSent[0];
		String string2 = "";
		
		for(int i = 1; i<actSent.length; i++){
			
			if(currLength < _maxChars){
				string1 = string1 + "          ";
				currLength = currLength+10;
				if(currLength + actSent[i].length() < _maxChars){
					string1 = string1 + actSent[i];
					currLength = currLength + actSent[i].length();
				} else{
					String partSent[] = actSent[i].split(" ");
					int j = 0;
					while(currLength < _maxChars && j < partSent.length){
						string1 = string1 + " " + partSent[j];
						currLength = currLength + partSent[j].length()+1;
						j++;
					}
					if(j < partSent.length-1){
						string1 = string1 + " ";
					}
					while(j < partSent.length){
						string2 = string2 + " " + partSent[j];
						currLength = currLength + partSent[j].length()+1;
						j++;
					}
				}
			} else{
					string2 = string2 + "          " + actSent[i];
					currLength = currLength + 10 + actSent[i].length();
			}
		}
		
		System.out.println("String 1 : " + string1);
		System.out.println("String 2 : " + string2);
		
		
		
		
		this.pack();
		this.setResizable(false);
		setVisible(true);

	}

	public static void main(String [] argv) {
		new App();
	}
	
}
