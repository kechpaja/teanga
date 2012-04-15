package ELearning;

import java.util.*;

public class VocabLevel extends LevelInstance {
	private String help;
	//the exercises still to go
	private List<VocabPicturePair> upNext;
	private Queue<VocabPicturePair> waiting;
	
	public String getHelp(){
		return help;
	}
	/*public boolean isOver(){
		return ((upNext.isEmpty())&&(waiting.isEmpty()));
	}*/
	public VocabLevel(List<VocabPicturePair> un, String h){
		super();
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
	
	public boolean tryAnswer(String answer){
		if (waiting.peek().checkWord(answer)){
			waiting.poll();
			this.score++;
			return true;
		} else {
			if (this.score > 0){
				score --;
			}
			return false;
		}
	}
	
	public static void main(String[] args){
		List<VocabPicturePair> levelList = new LinkedList<VocabPicturePair>();
		levelList.add(new VocabPicturePair("path1", "hello"));
		levelList.add(new VocabPicturePair("path2", "world"));
		levelList.add(new VocabPicturePair("path3", "testing"));
		levelList.add(new VocabPicturePair("path4", "word"));
		VocabLevel myLevel = new VocabLevel(levelList, "this is the help");
			myLevel.addToWaiting();
			myLevel.tryAnswer("hello");
			System.out.println(myLevel.score);
			myLevel.addToWaiting();
			myLevel.tryAnswer("wrong");
			myLevel.tryAnswer("world");
			System.out.println(myLevel.score);
			myLevel.addToWaiting();
			myLevel.addToWaiting();
			myLevel.tryAnswer("word"); //expect to not increment score
			System.out.println(myLevel.score);
			myLevel.tryAnswer("testing");
			myLevel.tryAnswer("word");
			System.out.println(myLevel.score);
			myLevel.addToWaiting();
			System.out.println(myLevel.isOver());
		
	}
	
}
