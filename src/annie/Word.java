package annie;
import java.util.LinkedList;


public class Word {
	public LinkedList<String> translations;
	public String partOfSpeech;
	public String exampleSentence;
	public Word(LinkedList<String> t, String p, String e)
	{
		translations=t;
		partOfSpeech=p;
		exampleSentence=e;
	}
}
