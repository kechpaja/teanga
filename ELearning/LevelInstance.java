package ELearning;

public abstract class LevelInstance {
	protected int score;
	
	public LevelInstance(){
		score = 0;
	}
	
	public int getScore(){
		return score;
	}
	
	
	public String getHelp(int level, boolean isVocab){
		return "No help available for this level";
	}
}
