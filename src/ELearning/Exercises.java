package ELearning;
import java.io.*;
import java.util.*;

public class Exercises {
	//contains the vocab exercises, sorted by level
	public List<List<VocabPicturePair>> vocabExercises;
	//contains the vocab exercises, sorted by level
	private List<List<SentencePicturePair>> grammarExercises;
	private List<List<BossQuestionPair>> bossExercises;
	private List<String> bossPictures;
	
	public Exercises(String VFile, String GFile, String BFile) throws IOException{
		vocabExercises = new ArrayList<List<VocabPicturePair>>();
		grammarExercises = new ArrayList<List<SentencePicturePair>>();
		bossExercises = new ArrayList<List<BossQuestionPair>>();
		bossPictures = new ArrayList<String>();
		generateVExercises(VFile);
		generateGExercises(GFile);
		generateBExercises(BFile);
	}
	
	
	public List<BossQuestionPair> getBLevel(int level){
		List<BossQuestionPair> toReturn = new ArrayList<BossQuestionPair>(bossExercises.get(level));
		Collections.copy(toReturn, bossExercises.get(level));
		return toReturn;
	}
	
	public String getBPic(int level){
		return bossPictures.get(level);
	}
	
	public List<SentencePicturePair> selectGRandomly(int level){
		Random generator = new Random();
		int numToChoose = GameConstants.getGNum(level);
		List<SentencePicturePair> toReturn = new LinkedList(grammarExercises.get(level));
		Collections.copy(toReturn, grammarExercises.get(level));
		Collections.shuffle(toReturn);
		return toReturn;
	}
	
	public List<VocabPicturePair> selectVRandomly(int level){
		Random generator = new Random();
		int numToChoose = GameConstants.getGNum(level);
		List<VocabPicturePair> toReturn = new LinkedList(vocabExercises.get(level));
		Collections.copy(toReturn, vocabExercises.get(level));
		Collections.shuffle(toReturn);
		return toReturn;
	}
	
	
	private void generateVExercises(String VFile) throws IOException{
		List<VocabPicturePair> cur = new ArrayList<VocabPicturePair>();
		BufferedReader vReader = new BufferedReader(new FileReader(VFile));
		String line = vReader.readLine();
		while (line != null){
			String[] split = line.split("~");
			if (split.length == 1){
				vocabExercises.add(cur);
				cur = new ArrayList<VocabPicturePair>();
			} else cur.add(new VocabPicturePair(split[0], split[1], split[2]));
			
			line = vReader.readLine();
		}
		vocabExercises.add(cur);
	}
	
	private void generateGExercises(String GFile) throws IOException, ArrayIndexOutOfBoundsException{
		List<SentencePicturePair> cur = new ArrayList<SentencePicturePair>();
		BufferedReader gReader = new BufferedReader(new FileReader(GFile));
		String line = gReader.readLine();
		while (line != null){
			String[] split = line.split(",");
			if (split.length == 1){
				grammarExercises.add(cur);
				cur = new ArrayList<SentencePicturePair>();
			} else cur.add(new SentencePicturePair(split[0], split[1], split[2].split(" "), split[3].split(" ")));
			
			line = gReader.readLine();
		}
		grammarExercises.add(cur);
	}
	
	private void generateBExercises(String BFile) throws IOException, ArrayIndexOutOfBoundsException, NumberFormatException{
		List<BossQuestionPair> cur = new ArrayList<BossQuestionPair>();
		BufferedReader bReader = new BufferedReader(new FileReader(BFile));
		String line = bReader.readLine();
		while (line != null){
			String[] split = line.split("~");
			if (split.length == 2){
				bossPictures.add(split[1]);
				bossExercises.add(cur);
				cur = new ArrayList<BossQuestionPair>();
			} else cur.add(new BossQuestionPair(split[0]));
			line = bReader.readLine();
		}
	}
	
}
