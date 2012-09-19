import java.util.Arrays;
import java.util.Collections;

public class HopfieldNetwork {
	
	private static int n;
	
	private int w[][];
	
	private static int[] order;
	
	private static int pos = 100500;
	
	public HopfieldNetwork(int neuronsCount) {
		n = neuronsCount;
		w = new int[n][n];
		if (pos == 100500)
			order = new int[n];
	}
	
	void train(Image image) {
		image = image.toBipolar();
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(i != j)
					w[i][j] += image.get(i)*image.get(j);
				else
					w[i][j] = 0;
	}
	
	Image step(Image image) {
		Image ans = new Image(image.getRowsCount(), image.getColumnsCount());
		for(int i=0;i<n;i++)
			ans.set(i, image.get(i));
		ans = ans.toBipolar();
		if (pos >= n) {
			for(int i = 0; i < n; i ++)
				order[i] = i;
			Collections.shuffle(Arrays.asList(order));
			pos = 0;
		}
		int v = order[pos];
		ans.set(v, 0);
		for(int i=0;i<n;i++)
			ans.set(v, w[i][v]*ans.get(i)+ans.get(v));
		if(ans.get(v) > 0) 
			ans.setToUpperState(v);
		else 
			ans.setToLowerState(v);
		pos ++;
		return ans.toUnipolar();
	}
	
	Image recognise(Image image) {		
		Image ans = new Image(image.getRowsCount(), image.getColumnsCount());
		for(int i=0;i<n;i++)
			ans.set(i, image.get(i));
		ans = ans.toBipolar();
		if (pos >= n) {
			for(int i = 0; i < n; i ++)
				order[i] = i;
			Collections.shuffle(Arrays.asList(order));
			pos = 0;
		}
		int rounds = 1000, cur = 0;
		while(cur < rounds)	{
			Collections.shuffle(Arrays.asList(order));
			for(int j=0;j<n;j++) {
				int v = order[j];
				ans.set(v, 0);
				for(int i=0;i<n;i++)
					ans.set(v, w[i][v]*ans.get(i)+ans.get(v));
				
				if(ans.get(v) > 0) 
					ans.setToUpperState(v);
				else 
					ans.setToLowerState(v);
			}
			cur++;
		}
		pos = n;
		return ans.toUnipolar();
	}
}
