import java.util.*;
import java.io.*;

class EditDistance{
	public static void main(String[] args){
		Scanner in = new Scanner(new InputStreamReader(System.in));
		String str1 = in.next();
		String str2 = in.next();
		int n = str1.length();
		int m = str2.length();
		char[] A = new char[n + 1];
		char[] B = new char[m + 1];

		for(int i = 1; i <= n; i++){
			A[i] = str1.charAt(i-1);
		}

		for(int i = 1; i <= m; i++){
			B[i] = str2.charAt(i-1);
		}

		int[][] D = new int[n + 1][m + 1];
		for(int i = 0; i <= n; i++){
			D[i][0] = i;
		}
		for(int j = 0; j <= m; j++){
			D[0][j] = j;
		}

		int c;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= m; j++){
				c = 1;
				if(A[i] == B[j])
					c = 0;
				D[i][j] = Math.min(Math.min(D[i-1][j]+1, D[i][j-1]+1), D[i-1][j-1]+c);
			}
		}

		System.out.println(D[n][m]);
	}
}