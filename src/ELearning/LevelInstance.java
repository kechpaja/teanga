package ELearning;

public abstract class LevelInstance {
	protected int score;
	protected boolean isOver;
	
	public LevelInstance(){
		score = 0;
		isOver = false;
	}
	
	public boolean isOver(){
		return isOver;
	}
	
	public int getScore(){
		return score;
	}
	
	public void decrementScore(int amt){
		score = score - amt;
	}
	
	
	public String getHelp(int level, boolean isVocab){
		return "TODO";
	}
}
