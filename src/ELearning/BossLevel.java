package ELearning;

import java.util.ArrayList;
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
	private List<String> vocabWords=new ArrayList<String>();
	private int currentNum;
	private int totalNum=15;
	
	
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
		currentNum = 1;
	}
	public void addVocabWords(Driver _driver)
	{
		List<VocabLessonPair> vocabLessonPairs= _driver.getLessons().getVLessons(levelnum);
		for(VocabLessonPair v: vocabLessonPairs)
		{
			vocabWords.add(v.getVocabWord());
		}
	}
		
	public Response tryAnswer(String input){
		Response response = parser.parse(input);
		if (this.isOver()){
			System.out.println("isOver");
			return response;
		}
		if (!response.containsFatal()){
			this.score += (5 - 2*response.getMistakes().size() + pointsToAdd(input));
			curQuestion = questions.remove(0);
			currentNum ++;
			if (questions.isEmpty()) {
				this.isOver = true;
			}
		} else score -= 3;
		return response;
		
	}
	
	public int pointsToAdd(String r)
	{
		int ret=0;
		r=r.toLowerCase();
		//System.out.println(r);
		String[] rArray=r.split(" ");
		for(String s: rArray)
		{
			s=s.replaceAll("([a-z]+)[?:!.,;]*", "$1");;
			//System.out.println(annie.MyDictionary.deinflect(s));
			if(vocabWords.contains(annie.MyDictionary.deinflect(s)))
			{
				ret=ret+2;
				vocabWords.remove(annie.MyDictionary.deinflect(s));
				//System.out.println(vocabWords.size());
			}
		}
		return ret;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public int getTotalNum() {
		return totalNum;
	}


}
