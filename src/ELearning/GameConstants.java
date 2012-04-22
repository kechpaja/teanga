package ELearning;

public final class GameConstants {
	public static final int DEFAULT_NUM = 5;
	//Stores the number of exercises in each game level
	public static final int G_NUM_LEVEL_ONE = 5;
	
	public static final int G_NUM_LEVEL_TWO = 5;
	
	public static final int G_NUM_LEVEL_THREE = 5;
	
	public static final int G_NUM_LEVEL_FOUR = 5;
	
	
	public static int getGNum(int level){
		switch (level){
		case 0: return G_NUM_LEVEL_ONE;
		case 1: return G_NUM_LEVEL_TWO;
		case 2: return G_NUM_LEVEL_THREE;
		case 3: return G_NUM_LEVEL_FOUR;
		default: return DEFAULT_NUM;
		}
	}

}
