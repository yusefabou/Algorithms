import java.util.Scanner;
import java.io.*;

class NaivePointsAndSegments {
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int segments = in.nextInt();
		int points = in.nextInt();

		int[] segStart = new int[segments+1];
		int[] segEnd = new int[segments+1];

		for(int i = 1; i <= segments; i++){
			segStart[i] = in.nextInt();
			segEnd[i] = in.nextInt();
		}

		int count;
		int point;
		for(int i = 1; i <= points; i++){
			count = 0;
			point = in.nextInt();
			for(int j = 1; j <= segments; j++){
				if( point >= segStart[j] && point <= segEnd[j])
					count++;
			}
			System.out.println(count);
		}
	}
}