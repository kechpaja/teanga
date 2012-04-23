package ELearning;

public class GrammarLessonPair {
	private String picturePath;
	private String explanation;
	private String exampleSentence;
	
	
	public GrammarLessonPair(String pp, String e, String es){
		picturePath = pp;
		explanation = e;
		exampleSentence = es;
	}
	
	public String getExampleSentence() {
		return exampleSentence;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public String getExplanation(){
		return explanation;
	}


}
