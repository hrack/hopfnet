
public class Image {
	public static final int UPPER_STATE = 1;
	public static final int LOWER_STATE = 0;
	
	private int[] img;
	private boolean isUnipolar;
	public Image(int size) {
		img = new int[size];
		isUnipolar = true;
	}
	
	int get(int idx) {
		return img[idx];
	}
	
	void set(int idx, int val) {
		img[idx] = val;
	}
	
	void setUpperState(int idx) {
		img[idx] = 1;
	}
	
	void setLowerState(int idx) {
		img[idx] = isUnipolar ? 0 : -1;
	}
	
	int getSize() {
		return img.length;
	}
	
	public Image toBipolar() {
		Image res = new Image(img.length);
		res.isUnipolar = false;
		for(int i=0;i<img.length;i++)
			res.img[i] = img[i] == 1 ? 1 : -1;
		return res;
	}
	
	public Image toUnipolar() {
		Image res = new Image(img.length);
		res.isUnipolar = true;
		for(int i=0;i<img.length;i++) 
			res.img[i] = img[i] == 1 ? 1 : 0;
		return res;
	}
	
	public void clear() {
		for(int i = 0; i < img.length; i ++)
			img[i] = 0;
	}
}
