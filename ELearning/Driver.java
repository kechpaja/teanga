package ELearning;


public class Driver {
	private Exercises exercises;
	private HelpBox helpbox;
	private GrammarGameMaker ggMaker;
	private VocabGameMaker vgMaker;
	
	
	public Driver(){
		try{
			helpbox = new HelpBox("/Users/taranoble/Desktop/helpfilev.txt", "/Users/taranoble/Desktop/helpfileg.txt");
			exercises = new Exercises("/Users/taranoble/Desktop/testfilev.txt", "/Users/taranoble/Desktop/testfileg.txt");
			ggMaker = new GrammarGameMaker(exercises, helpbox);
			vgMaker = new VocabGameMaker(exercises, helpbox);
		} catch (IOException e){
			System.out.println("there was an error finding the exercise files");
		}
	}
	
	public runGame(){
		JPanel curPage = new GUIBasicPage(this);
	}
	
	
	public static void main(String[] args){
		Driver driver = new Driver();	
		driver.runGame();
	}
	
	
}