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
		return (answer.equals(vocabWord));
	}
	
	public String getEnglish(){
		return inEnglish;
	}
	
	public static void main(String[] args){
		VocabPicturePair test1 = new VocabPicturePair("path 1", "test", "test");
		VocabPicturePair test2 = new VocabPicturePair("path 2", "test2", "test2");
		
		System.out.println(test1.checkWord("wrong"));
		System.out.println(test1.checkWord("wrong2"));
		System.out.println(test1.checkWord("test"));
		System.out.println(test2.checkWord("test"));
		System.out.println(test2.checkWord("test2"));
		System.out.println(test1.getPicturePath());
		System.out.println(test2.getPicturePath());
	}
	
	
}
