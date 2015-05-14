import java.util.Scanner;
import java.io.*;

class CountingSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n = in.nextInt();
		int maxVal = 10;

		int[] array = new int[n+1];
		int[] values = new int[maxVal+1];

		for(int i = 1; i <= n; i++){
			array[i] = in.nextInt();
		}

		for(int i = 1; i <= maxVal; i++){
			values[i] = 0;
		}

		for(int i = 1; i <= n; i++){
			values[array[i]] = values[array[i]]+1;
		}

		int k = 1;

		for(int i = 1; i <= maxVal; i++){
			for(int j = 1; j <= values[i]; j++){
				//array[k] = i; real implementation
				System.out.println(i);
				k++;
			}
		}
	}
}