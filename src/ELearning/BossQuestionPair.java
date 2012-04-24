package ELearning;

public class BossQuestionPair {
	private String question;
	private int points;
	
	public BossQuestionPair(String q, int p){
		question = q;
		points = p;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public int getPoints(){
		return points;
	}
	
}
