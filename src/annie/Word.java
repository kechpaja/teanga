package annie;
import java.util.LinkedList;


public class Word {
	private LinkedList<String> translations;
	private  String partOfSpeech;
	private  String exampleSentence;
	public Word(LinkedList<String> t, String p, String e)
	{
		translations=t;
		partOfSpeech=p;
		exampleSentence=e;
	}
	public LinkedList<String> getTranslations()
	{
		return translations;
	}
	public String getPOS()
	{
		return partOfSpeech;
	}
	public String getEx()
	{
		return exampleSentence;
	}
	
	// toString()
	public String toString() {
		return partOfSpeech + "\n" + translations + "\n" + exampleSentence;
	}
}
