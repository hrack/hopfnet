
public class Image {
	
	private int rows;
	private int columns;
	private int[] img;
	private boolean isUnipolar;
	
	public Image(int rowsCount, int columnCount) {
		rows = rowsCount;
		columns = columnCount;
		img = new int[rows*columns];
		isUnipolar = true;
	}
	
	int get(int idx) {
		return img[idx];
	}
	
	void set(int idx, int val) {
		img[idx] = val;
	}
	
	void set(int row, int column, int val) {
		img[rows*row+column] = val;
	}
	
	int get(int row, int column) {
		return img[rows*row+column];
	}
	
	void setToUpperState(int idx) {
		img[idx] = 1;
	}
	
	void setToLowerState(int idx) {
		img[idx] = isUnipolar ? 0 : -1;
	}
	
	boolean isSetToUpperState(int row, int col) {
		return img[rows*row+col] == 1 ? true : false;
	}
	
	int getSize() {
		return img.length;
	}
	
	int getColumnsCount() {
		return columns;
	}
	
	int getRowsCount() {
		return rows;
	}
	
	void invert(int row, int col) {
		if(isSetToUpperState(row, col))
			setToLowerState(rows*row+col);
		else
			setToUpperState(rows*row+col);
	}
	
	public Image toBipolar() {
		Image res = new Image(rows, columns);
		res.isUnipolar = false;
		for(int i=0;i<img.length;i++)
			res.img[i] = img[i] == 1 ? 1 : -1;
		return res;
	}
	
	public Image toUnipolar() {
		Image res = new Image(rows, columns);
		res.isUnipolar = true;
		for(int i=0;i<img.length;i++) 
			res.img[i] = img[i] == 1 ? 1 : 0;
		return res;
	}
}
