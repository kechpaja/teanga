package ELearning;

import java.util.List;

public class VocabGameMaker implements GameMaker{
	Exercises exercises;
	HelpBox helpBox;
	
	public VocabGameMaker(Exercises e, HelpBox hb){
		exercises = e;
		helpBox = hb;
	}
	public VocabLevel makeLevel(int levelNum){
		List<VocabPicturePair> vpps = exercises.selectVRandomly(levelNum);
		String h = helpBox.getVHelp(levelNum);
		return new VocabLevel(vpps, h, levelNum);
	}

}
