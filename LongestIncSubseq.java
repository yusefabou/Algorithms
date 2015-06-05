import java.util.*;
import java.io.*;

class LongestIncSubseq {
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n = in.nextInt();
		int[] A = new int[n+1];
		int[] D = new int[n+1];

		for(int i = 1; i <= n; i++){
			A[i] = in.nextInt();
		}

		for(int i = 1; i <= n; i++){
			D[i] = 1;
			for(int j = 1; j <= i-1; j++){
				if(A[i]%A[j] == 0 && D[j] + 1 > D[i])
					D[i] = D[j] + 1;
			}
		}

		int ans = 0;
		for(int i = 1; i <= n; i++){
			ans = Math.max(ans, D[i]);
		}
		System.out.println(ans);
	}
}