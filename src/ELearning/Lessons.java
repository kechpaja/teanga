package ELearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lessons {
	//contains the vocab exercises, sorted by level
	public List<List<VocabLessonPair>> vocabLessons;
	//contains the grammar exercises, sorted by level
	private List<List<GrammarLessonPair>> grammarLessons;

	
	public Lessons(String VFile, String GFile) throws IOException{
		vocabLessons = new ArrayList<List<VocabLessonPair>>();
		grammarLessons = new ArrayList<List<GrammarLessonPair>>();

		generateVLessons(VFile);
		generateGLessons(GFile);

	}
	
	private void generateVLessons(String VFile) throws IOException{
		List<VocabLessonPair> cur = new ArrayList<VocabLessonPair>();
		BufferedReader vReader = new BufferedReader(new FileReader(VFile));
		String line = vReader.readLine();
		while (line != null){
			String[] split = line.split("~");
			if (split.length == 1){
				vocabLessons.add(cur);
				cur = new ArrayList<VocabLessonPair>();
			} else cur.add(new VocabLessonPair(split[0], split[1], split[2], split[3]));
			
			line = vReader.readLine();
		}
		vocabLessons.add(cur);
	}
	
	private void generateGLessons(String GFile) throws IOException{
		List<GrammarLessonPair> cur = new ArrayList<GrammarLessonPair>();
		BufferedReader gReader = new BufferedReader(new FileReader(GFile));
		String line = gReader.readLine();
		while (line != null){
			String[] split = line.split("~");
			if (split.length == 1){
				grammarLessons.add(cur);
				cur = new ArrayList<GrammarLessonPair>();
			} else cur.add(new GrammarLessonPair(split[0], split[1], split[2]));

			line = gReader.readLine();
		}
		grammarLessons.add(cur);		
	}
	
	
	public List<VocabLessonPair> getVLessons(int level){
		return vocabLessons.get(level);
	}
	
	public List<GrammarLessonPair> getGLessons(int level){
		return grammarLessons.get(level);
	}
	
}
