package ELearning;

import java.util.List;

import parsing.Parser;

public class BossGameMaker implements GameMaker{
	Exercises exercises;
	HelpBox helpBox;
	Parser parser;
	
	public BossGameMaker(Exercises e, HelpBox hb, Parser p){
		exercises = e;
		helpBox = hb;
		parser = p;
	}
	
	public BossLevel makeLevel(int levelNum){
		List<BossQuestionPair> bqps = exercises.getBLevel(levelNum);
		String picturePath = exercises.getBPic(levelNum);
		String help = helpBox.getBHelp(levelNum);
		return new BossLevel(bqps, help, levelNum, picturePath, parser);	
	}
}
