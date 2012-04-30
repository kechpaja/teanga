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
	//contains the helpbox contents for each vocab lesson
	private List<String> vocabHelp;
	//contains the helpbox contents for each grammar lesson
	private List<String> grammarHelp;
	
	public Lessons(String VFile, String GFile, String VHFile, String GHFile) throws IOException{
		vocabLessons = new ArrayList<List<VocabLessonPair>>();
		grammarLessons = new ArrayList<List<GrammarLessonPair>>();
		vocabHelp = new ArrayList<String>();
		grammarHelp = new ArrayList<String>();
		generateVLessons(VFile);
		generateGLessons(GFile);
		generateVHelp(VHFile);
		generateGHelp(GHFile);
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
			if(split.length == 2){
				System.out.println(line);
			}
			if (split.length == 1){
				grammarLessons.add(cur);
				cur = new ArrayList<GrammarLessonPair>();
			} else cur.add(new GrammarLessonPair(split[0], split[1], split[2]));

			line = gReader.readLine();
		}
		grammarLessons.add(cur);		
	}
	
	private void generateVHelp(String VHFile) throws IOException{
		BufferedReader vhReader = new BufferedReader(new FileReader(VHFile));
		String hLine = vhReader.readLine();
		while (hLine != null){
			vocabHelp.add(hLine);
			hLine = vhReader.readLine();
		}
	}
	
	private void generateGHelp(String GHFile) throws IOException{
		BufferedReader ghReader = new BufferedReader(new FileReader(GHFile));
		String hLine = ghReader.readLine();
		while (hLine != null){
			grammarHelp.add(hLine);
			hLine = ghReader.readLine();
		}
	}
	
	public List<VocabLessonPair> getVLessons(int level){
		return vocabLessons.get(level);
	}
	
	public List<GrammarLessonPair> getGLessons(int level){
		return grammarLessons.get(level);
	}
	
	public String getVLessonHelp(int level){
		return vocabHelp.get(level);
	}
	
	public String getGlessonHelp(int level){
		return grammarHelp.get(level);
	}
}
