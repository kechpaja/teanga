package ELearning;
import java.io.*;
import java.util.*;

public class Exercises {
	//contains the vocab exercises, sorted by level
	private List<List<VocabPicturePair>> vocabExercises;
	//contains the vocab exercises, sorted by level
	private List<List<SentencePicturePair>> grammarExercises;
	
	public Exercises(String VFile, String GFile) throws IOException{
		vocabExercises = new ArrayList<List<VocabPicturePair>>();
		grammarExercises = new ArrayList<List<SentencePicturePair>>();
		generateVExercises(VFile);
		generateGExercises(GFile);
	}
	
	public List<SentencePicturePair> selectGRandomly(int level){
		Random generator = new Random();
		List<SentencePicturePair> toReturn = new LinkedList<SentencePicturePair>();
		int numToChoose = GameConstants.getGNum(level);
		List<SentencePicturePair> exercises = grammarExercises.get(level);
		for (int i=0; i<=numToChoose; i++){
			toReturn.add(exercises.get(generator.nextInt(exercises.size())));
		}
		return toReturn;
	}
	
	public List<VocabPicturePair> selectVRandomly(int level){
		Random generator = new Random();
		List<VocabPicturePair> toReturn = new LinkedList<VocabPicturePair>();
		List<VocabPicturePair> exercises = vocabExercises.get(level);
		for (int i=0; i<exercises.size(); i++){
			int nextI = generator.nextInt(exercises.size());
			toReturn.add(exercises.get(nextI));
		}
		return toReturn;
	}
	
	
	private void generateVExercises(String VFile) throws IOException{
		List<VocabPicturePair> cur = new ArrayList<VocabPicturePair>();
		BufferedReader vReader = new BufferedReader(new FileReader(VFile));
		String line = vReader.readLine();
		while (line != null){
			String[] split = line.split(",");
			if (split.length == 1){
				vocabExercises.add(cur);
				cur = new ArrayList<VocabPicturePair>();
			} else cur.add(new VocabPicturePair(split[0], split[1]));
			
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
			} else cur.add(new SentencePicturePair(split[0], split[1], split[2].split(" "), split[3].split(" ")));
			
			line = gReader.readLine();
		}
		grammarExercises.add(cur);
	}
	
}
