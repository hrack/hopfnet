import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;


public class Grid extends JComponent {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int columns;
	private int height;
	private int width;
	private int[][] arr;
		
	public Grid(int rowsCnt, int columnsCnt) {
		rows = rowsCnt;
		columns = columnsCnt;
		addMouseListener(new GridMouseListner());
		arr = new int[rows][columns];
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				arr[i][j] = Image.LOWER_STATE;
	}

	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		height = rows != 0 ? (getHeight()-1)/rows : 0;
		width = columns != 0 ? (getWidth()-1)/columns : 0;
		drawGrid(g);
	}
	
	int getRows() {
		return rows;
	}
	
	int getColumns() {
		return columns;
	}
	
	private void drawGrid(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<=rows;i++)
			g.drawLine(0, i*height, columns*width, i*height);
		for(int i=0;i<=columns;i++)
			g.drawLine(i*width, 0, i*width, rows*height);
		
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				setCell(i, j, arr[i][j], g);
	}
	
	private void invertCell(int i, int j) {
		 if(arr[i][j] == Image.UPPER_STATE)
			setCell(i, j, Image.LOWER_STATE, getGraphics());
		else
			setCell(i, j, Image.UPPER_STATE, getGraphics());
	}
	
	private void setCell(int i, int j, int c, Graphics g) {
		arr[i][j] = c;
		int stx = j*width + 1;
		int sty = i*height + 1;
		if(c == 1) {
			float[] hsb = new float[3];
			Color.RGBtoHSB(255, 67, 249, hsb);
			g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
		}
		else
			g.setColor(Color.LIGHT_GRAY);
		g.fillRect(stx, sty, width-1 , height-1);
	}
	
	public Image getImage() {
		Image ans = new Image(rows*columns);
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				ans.set(i*rows+j, arr[i][j]);
		return ans;
	}
	
	public void setImage(Image image) {
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				setCell(i, j, image.get(i*rows+j), getGraphics());
	}
	
	private class GridMouseListner extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int i = e.getY()/height;
			int j = e.getX()/width;
			if(j >= columns || i >= rows)
				return;
			invertCell(i, j);
		}
	}
	
}
