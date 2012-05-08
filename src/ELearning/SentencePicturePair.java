package ELearning;

public class SentencePicturePair extends PicturePair{
	//the sentence, stored as a string where blanks are marked by ~n~, where n is the index in the answer array
	private String partialSentence;
	//the correct answers for each blank
	private String[] correctAnswers;
	//keeps track of the correct answers already given, for rendering.
	private String[] answersGiven;
	//an array of all the options given to the user to drag and drop
	private String[] possibilities;
	//number of blanks and number of correct answers. Used to determine if complete in constant time.
	
	public SentencePicturePair(String pp, String ps, String[] ca, String[] pos){
		super(pp);
		partialSentence = ps;
		for (int i=0; i<ca.length;i++)
		{
			ca[i]=ca[i].replace("_", " ");
		}
		correctAnswers = ca;
		for (int i=0; i<pos.length;i++)
		{
			pos[i]=pos[i].replace("_", " ");
		}
		possibilities = pos;
		answersGiven = new String[ca.length];
	}
	
	public String getPartialSentence(){
		return partialSentence;
	}
	
	public String[] getCorrectAnswers(){
		return correctAnswers;
	}
	
	public String[] getPossibilities(){
		return possibilities;
	}
	
	public boolean submitAnswer(int guess, int blank){
		try{
			answersGiven[blank] = possibilities[guess];
			return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}

	}
	
	public void removeAnswer(int blank){
		answersGiven[blank] = null;
	}
	
	public boolean isFinished(){
		try{
		boolean toReturn = true;
		for (int i = 0; i<answersGiven.length; i++){
			if (!answersGiven[i].equals(correctAnswers[i])){
				toReturn = false;
				break;
			}
		}
		return toReturn;
		} catch (NullPointerException e){
			return false;
		}
	}
	
	public static void main(String[] args){
		String[] one = {"is", "le"};
		String[] two = {"is", "this", "sample", "le"};
		SentencePicturePair pair1 = new SentencePicturePair("path1", "this ~0~ the samp~1~ sentence", one, two);
		pair1.submitAnswer(1, 0);
		pair1.submitAnswer(0, 0);
		pair1.submitAnswer(3, 1);
		
		String[] one2 = {};
		String[] two2 = {"is", "this", "sample", "le"};
		SentencePicturePair pair2 = new SentencePicturePair("path2", "this is the sentence", one2, two2);

	}

}
