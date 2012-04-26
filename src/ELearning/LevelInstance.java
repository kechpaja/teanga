package ELearning;

public abstract class LevelInstance {
	protected int score;
	protected boolean isOver;
	protected int necessaryScore;
	
	public LevelInstance(int necessary){
		score = 0;
		necessaryScore = necessary;
		isOver = false;
	}
	
	public boolean isOver(){
		return isOver;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getNecessaryScore(){
		return necessaryScore;
	}
	
	public void decrementScore(int amt){
		score = score - amt;
	}
	
	
	public String getHelp(int level, boolean isVocab){
		return "TODO";
	}
}
