
public class Image {
	public static final int UPPER_STATE = 1;
	public static final int LOWER_STATE = 0;
	
	private int[] img;
	private boolean isNormal;
	public Image(int size) {
		img = new int[size];
		isNormal = true;
	}
	
	public Image toBipolar() {
		Image res = new Image(img.length);
		for(int i=0;i<img.length;i++);		
		return res;
	}
	
	public Image toUnipolar() {
		Image res = new Image(img.length);
		return res;
	}
	
}
