package annie;
import java.util.LinkedList;

public class SingleGame {
	
	public Boolean unlocked;
	public Boolean timeRelevent;
	//public LinkedList<String> files;
	public int bestScore;
	public int neededScore;
	public int bestTime;
	
	public SingleGame(Boolean u, Boolean t, int nS)
	{
		unlocked=u;
		timeRelevent=t;
		neededScore=nS;
		bestScore=0;
		bestTime=0;
	}
	
	public boolean isDefeated()
	{
		return bestScore>=neededScore;
	}
	

	int updateSingleGame(int score, int time) 
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
		if(timeRelevent)
		{
			if(time>bestTime)
			{
				bestTime=time;
				
			}
		}
		return ret;
	}

}
