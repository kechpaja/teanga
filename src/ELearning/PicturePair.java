package ELearning;

public abstract class PicturePair {
	private String picturePath;
	
	public String getPicturePath(){
		return picturePath;
	}
	
	public PicturePair(String pp){
		picturePath = pp;
	}

}
