import java.util.*;
import java.io.*;

class Knapsack {
	public static void main(String[] args){
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int W = in.nextInt();
		int n = in.nextInt();
		int[][] D = new int[W+1][n+1];
		int[] wi = new int[n+1];

		for(int i = 1; i <= n; i++){
			wi[i] = in.nextInt();
		}

		for(int w = 0; w <= W; w++){
			D[w][0] = 0;
		}
		for(int i = 0; i <= n; i++){
			D[0][i] = 0;
		}

		for(int i = 1; i <= n; i++){
			for(int w = 1; w <= W; w++){
				D[w][i] = D[w][i-1];
				if(wi[i] <= w)
					D[w][i] = Math.max(D[w][i], D[w-wi[i]][i-1] + wi[i]);
			}
		}

		System.out.println(D[W][n]);
	}
}