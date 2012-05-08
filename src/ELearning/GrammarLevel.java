package ELearning;

import java.util.*;

public class GrammarLevel extends LevelInstance {
	private List<SentencePicturePair> upNext;
	private SentencePicturePair current;
	private String help;
	private int currentNum;
	private int totalNum=30;
	
	
	public int getCurrentNum(){
		return currentNum;
	}
	
	public int getTotalNum(){
		return totalNum;
	}
	
	
	public String getHelp(){
		return help;
	}
	
	public GrammarLevel(List<SentencePicturePair> un, String h, int ln){
		super(100, 1, ln);
		upNext = un;
		current = upNext.remove(0);
		help = h;
		currentNum = 1;
		//totalNum = un.size()+1;
	}
	
	public SentencePicturePair getCurrent(){
		return current;
	}
	
	public boolean submitPart(int indexDragged, int indexDropped){
		return (current.submitAnswer(indexDragged, indexDropped));
	}
	
	public boolean submitWhole(){
		if (current.isFinished()){
			try{
			current = upNext.remove(0);
			currentNum++;
			} catch(IndexOutOfBoundsException e){
				this.isOver = true;
			}
			score+=4;
			if(currentNum==totalNum+1)
				this.isOver = true;
			return true;
		} else {
			score -= 2;
			return false;
		}
	}
	
	public void skipCurrent(){
		try{
			current = upNext.remove(0);
			currentNum++;
		} catch(IndexOutOfBoundsException e){
			this.isOver = true;
		}
			score -=2;
	}
	
	public static void main(String[] args){
		String[] one = {"is"};
		String[] two = {"is", "this", "what", "you", "meant"};
		String[] three = {};
		String[] four = {"you", "don't", "need", "these"};
		String[] five = {"about", "this"};
		String[] six = {"this", "is", "all", "about", "me"};
		List<SentencePicturePair> levelList = new LinkedList<SentencePicturePair>();
		levelList.add(new SentencePicturePair("path1", "this ~0~ a test", one, two));
		levelList.add(new SentencePicturePair("path2", "this is fine the way it is", three, four));
		levelList.add(new SentencePicturePair("path3", "how ~0~ ~1~ one", five, six));
		
		GrammarLevel myLevel = new GrammarLevel(levelList, "this is the help", 1);
		myLevel.submitPart(1, 0);
		myLevel.submitPart(0, 0);
		myLevel.submitPart(0, 1);
		myLevel.submitPart(1, 0);
		myLevel.submitPart(3, 0);
	}
	
}
