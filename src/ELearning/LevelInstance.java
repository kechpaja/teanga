package ELearning;

public abstract class LevelInstance {
	private int levelNum;
	protected int score;
	protected boolean isOver;
	protected int necessaryScore;
	protected int typeOfGame;
	
	public LevelInstance(int necessary, int type, int ln){
		score = 0;
		necessaryScore = necessary;
		isOver = false;
		typeOfGame = type;
		levelNum = ln;
	}
	
	public int getLevelNum(){
		return levelNum;
	}
	
	public int getTypeOfGame(){
		return typeOfGame;
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
