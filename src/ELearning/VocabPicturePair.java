package ELearning;

public class VocabPicturePair extends PicturePair{
	private String vocabWord;
	private String inEnglish;
	
	public VocabPicturePair(String pp, String vw, String ie){
		super(pp);
		vocabWord = vw;
		inEnglish = ie;
	}
	
	public boolean checkWord(String answer){
		answer=answer.toLowerCase();
		answer=annie.MyDictionary.deinflect(answer);
		return (answer.equals(vocabWord));
	}
	
	public String getEnglish(){
		return inEnglish;
	}
	
	
}
