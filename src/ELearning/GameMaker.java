package ELearning;

public interface GameMaker {
	//factory class interface generates a new instance of game at specified level
	//boolean is true if it is a vocab game, false if it is a grammar game
	public LevelInstance makeLevel(int levelNumber);
}
