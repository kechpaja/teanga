package annie;
import java.util.LinkedList;

public class SingleGame {
	
	public Boolean unlocked;
	//public LinkedList<String> files;
	public int bestScore;
	public int neededScore;
	
	public SingleGame(Boolean u,  int nS)
	{
		unlocked=u;
		neededScore=nS;
		bestScore=0;
	}
	
	public boolean isDefeated()
	{
		return bestScore>=neededScore;
	}
	

	int updateSingleGame(int score) 
	{
		int ret=0;
		if(score>bestScore)
		{
			ret=score-bestScore;
			bestScore=score;
			if(bestScore>=neededScore)
			{
				unlocked=true;
			}
		}
		return ret;
	}

}
