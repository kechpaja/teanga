package ELearning;

import java.util.List;

import parsing.Parser;

import parsing.Response;

public class BossLevel extends LevelInstance{
	private String picturePath;
	private int levelnum;
	private List<BossQuestionPair> questions;
	private BossQuestionPair curQuestion;
	private String help;
	private Parser parser;
	private List<String> vocabWords;
	
	
	public int getLevelNum(){
		return levelnum;
	}
	
	public String getHelp(){
		return help;
	}
	
	public String getPicturePath(){
		return picturePath;
	}
	
	public String getCurrentQuestion(){
		return curQuestion.getQuestion();
	}
	
	public BossLevel(List<BossQuestionPair> bqp, String h, int ln, String pp, Parser p){
		super(100, 2, 2);
		questions = bqp;
		curQuestion = questions.remove(0);
		help = h;
		levelnum = ln;
		picturePath = pp;
		parser = p;
	}
		
	public Response tryAnswer(String input){
		Response response = parser.parse(input);
		if (this.isOver()){
			return response;
		}
		if (!response.containsFatal()){
			this.score += (curQuestion.getPoints() - response.getMistakes().size());
			System.out.println(curQuestion.getQuestion());
			curQuestion = questions.remove(0);
			if (questions.isEmpty()) {
				this.isOver = true;
			}
		} else score -= 3;
		return response;
	}

}
