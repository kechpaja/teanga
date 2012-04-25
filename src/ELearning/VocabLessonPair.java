package ELearning;

public class VocabLessonPair {
	private String vocabWord;
	private String vocabTranslation;
	private String picturePath;
	private String exampleSentence;
	
	
	public VocabLessonPair(String pp, String vw, String vt, String es){
		vocabWord = vw;
		vocabTranslation = vt;
		picturePath = pp;
		exampleSentence = es;
	}
	
	public String getExampleSentence() {
		return exampleSentence;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public String getVocabTranslation() {
		return vocabTranslation;
	}

	public String getVocabWord() {
		return vocabWord;
	}


}
