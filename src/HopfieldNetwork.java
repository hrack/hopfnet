import java.util.Arrays;
import java.util.Collections;

public class HopfieldNetwork {
	
	private int n;
	
	private int w[][];
	
	
	public HopfieldNetwork(int neuronsCount) {
		n = neuronsCount;
		w = new int[n][n];
	}
	
	void train(int[] image) {		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(i != j)
					w[i][j] += (2*image[i] - 1)*(2*image[j] - 1);
				else
					w[i][j] = 0;
	}
	
	int[] recognise(int[] image) {		
		int[] ans = new int[n], order = new int[n];
		for(int i=0;i<n;i++) {
			ans[i] = image[i];
			order[i] = i;
		}
		int rounds = 1000, cur = 0;
		while(cur < rounds)	{
			Collections.shuffle(Arrays.asList(order));
			for(int j=0;j<n;j++) {
				int v = order[j];
				for(int i=0;i<n;i++)
					ans[v] += w[i][v]*(2*ans[i]-1);
				ans[v] = ans[v] > 0 ? Image.UPPER_STATE : Image.LOWER_STATE;
			}
			cur++;
		}
		return ans;
	}
}
