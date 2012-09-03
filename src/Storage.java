import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class Storage {
	
	FileFilter ff1 = new FileFilter() {
		
		@Override
		public String getDescription() {
			return "*.txt";
		}
		
		@Override
		public boolean accept(File f) {
			return (f.getName().endsWith(".txt"));
		}
	};
	
	public  Image loadImage() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("."));
		fc.setFileFilter(ff1);
		fc.showOpenDialog(null);
		File f = fc.getSelectedFile();
		Image img = new Image(6, 6);
		if (f == null)
			return img;
		try {
			Scanner sc = new Scanner(f);
			String str, all = new String("");
			while (sc.hasNext()) {
				str = sc.next();
				if (str.length() != 6) 
					break;
				all += str;
			}
			if (all.length() != 36)
				JOptionPane.showMessageDialog(null, "Wrong image size");
			char []arr = all.toCharArray();
			for(int i = 0; i < all.length(); i ++) {
				if (arr[i] != '0' && arr[i] != '1') {
					JOptionPane.showMessageDialog(null, "Wrong image format. \nYou should use only 0, 1");
					break;
				}
				img.set(i, arr[i] == '1' ? 1 : 0);	
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public  void saveImage(Image img) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("."));
		fc.setFileFilter(ff1);
		fc.showSaveDialog(null);
		File f = fc.getSelectedFile();
		try {
			if (!f.getName().endsWith(".txt"))
				f = new File(f.getAbsoluteFile() + ".txt");
			if (f.exists()) {
				JOptionPane.showMessageDialog(null, "File with the same name is already exists");
				return;
			}
			else
				f.createNewFile();
			PrintWriter pw = new PrintWriter(f);
			for(int i = 0; i < img.getRowsCount(); i ++) {
				for(int j = 0; j < img.getColumnsCount(); j ++)
					pw.print(img.get(i, j));
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}
