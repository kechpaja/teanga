import java.util.LinkedList;

public class SingleGame {
	
	public Boolean unlocked;
	public Boolean timeRelevent;
	//public LinkedList<String> files;
	public int bestScore;
	public int neededScore;
	public int bestTime;
	public int neededTime;
	
	public SingleGame(Boolean u, Boolean t, int nS, int nT)
	{
		unlocked=u;
		timeRelevent=t;
		//files=f;
		neededScore=nS;
		neededTime=nT;
		bestScore=0;
		bestTime=0;
	}
	
	public SingleGame()
	{
	}

	int updateSingleGame(int score, int time) 
	{
		int ret=0;
		if(score>bestScore)
		{
			ret=score-bestScore;
			bestScore=score;
		}
		if(time>bestTime)
		{
			bestTime=time;
		}
		return ret;
	}

}
