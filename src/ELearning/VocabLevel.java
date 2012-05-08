package ELearning;

import java.util.*;

public class VocabLevel extends LevelInstance {
		private String help;
		//the exercises still to go
		private List<VocabPicturePair> upNext;
		private Queue<VocabPicturePair> waiting;
		private List<VocabPicturePair> fallen;
		
		public String getHelp(){
			return help;
		}
		
		public VocabLevel(List<VocabPicturePair> un, String h, int num){
			super(100, 0, num);
			help = h;
			upNext = un;
			fallen=new LinkedList<VocabPicturePair>(upNext);
			Collections.copy(fallen, upNext);
			waiting = new LinkedList<VocabPicturePair>();
		}
		
		public Queue<VocabPicturePair> getWaiting(){
			return waiting;
		}
		
		public VocabPicturePair addToWaiting(){
			try{
			VocabPicturePair next = upNext.remove(0);
			waiting.add(next);
			return next;
			} catch (IndexOutOfBoundsException e){
				this.isOver = true;
				return null;
			}
		}
	/*private String help;
	//the exercises still to go
	private List<VocabPicturePair> upNext;
	private Queue<VocabPicturePair> waiting;
	
	public String getHelp(){
		return help;
	}
	
	public VocabLevel(List<VocabPicturePair> un, String h, int num){
		super(20, 0, num);
		help = h;
		upNext = un;
		waiting = new LinkedList<VocabPicturePair>();
	}
	
	public Queue<VocabPicturePair> getWaiting(){
		return waiting;
	}
	
	public VocabPicturePair addToWaiting(){
		try{
		VocabPicturePair next = upNext.remove(0);
		waiting.add(next);
		return next;
		} catch (IndexOutOfBoundsException e){
			this.isOver = true;
			return null;
		}
	}
	*/
		
	public String getCurEnglish(){
		return waiting.peek().getEnglish();
	}
	
	public boolean tryAnswer(String answer){
		answer.toLowerCase();
		if (waiting.peek().checkWord(answer)){
			waiting.poll();
			if(upNext.isEmpty())
			{
				upNext=new LinkedList<VocabPicturePair>(fallen);
				Collections.copy(upNext, fallen);
				Collections.shuffle(upNext);
			}
			score+=2;
			return true;
		} else {
			score --;
			return false;
		}
	}
	
	
	public static void main(String[] args){
		List<VocabPicturePair> levelList = new LinkedList<VocabPicturePair>();
		levelList.add(new VocabPicturePair("path1", "hello", "hi"));
		levelList.add(new VocabPicturePair("path2", "world", "world"));
		levelList.add(new VocabPicturePair("path3", "testing", "test"));
		levelList.add(new VocabPicturePair("path4", "word", "word"));
		VocabLevel myLevel = new VocabLevel(levelList, "this is the help", 1);
			myLevel.addToWaiting();
			myLevel.tryAnswer("hello");
			myLevel.addToWaiting();
			myLevel.tryAnswer("wrong");
			myLevel.tryAnswer("world");
			myLevel.addToWaiting();
			myLevel.addToWaiting();
			myLevel.tryAnswer("word"); //expect to not increment score
			myLevel.tryAnswer("testing");
			myLevel.tryAnswer("word");
			myLevel.addToWaiting();
		
	}
	
}
