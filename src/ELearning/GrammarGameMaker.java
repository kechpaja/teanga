package ELearning;

import java.util.*;

public class GrammarGameMaker implements GameMaker{
	Exercises exercises;
	HelpBox helpBox;
	
	public GrammarGameMaker(Exercises e, HelpBox hb){
		exercises = e;
		helpBox = hb;
	}
	public GrammarLevel makeLevel(int levelNum){
		List<SentencePicturePair> spps = exercises.selectGRandomly(levelNum);
		String help = helpBox.getGHelp(levelNum);
		return new GrammarLevel(spps, help, levelNum);
	}

}
