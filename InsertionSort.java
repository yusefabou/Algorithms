import java.util.Scanner;
import java.io.*;

class InsertionSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n = in.nextInt();
		int[] unsorted = new int[n+1];

		for(int i = 1; i <= n; i++){
			unsorted[i] = in.nextInt();
		}

		int holder;
		int j;

		//Sort
		for(int i = 2; i <= n; i++){
			j = i;
			while(j > 1 && unsorted[j] < unsorted[j-1]) {
				holder = unsorted[j];
				unsorted[j] = unsorted[j-1];
				unsorted[j-1] = holder;
				j--;
			}
		}

		//Print 
		for(int i = 1; i <=n; i++){
			System.out.print(unsorted[i] + " ");
		}
	}
}