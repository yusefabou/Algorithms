import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.math.BigInteger;

class MergeSort {
	public static void main(String[] args) {
		MergeSort merger = new MergeSort();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n  = in.nextInt();

		BigInteger[] array = new BigInteger[n];
		for(int i = 0; i < n; i++){
			array[i] = new BigInteger(in.next());
		}

		System.out.println(merger.mergeSort(array,n));
		for(int i = 0; i < n; i++){
			System.out.println(array[i]);
		}
	}

	public long merge(BigInteger[] arr, BigInteger[] left,
	 int lCount, BigInteger[] right, int rCount) {
		int i = 0, j = 0, k = 0;
		long inv = 0;

		while(i < lCount && j < rCount){
			if(left[i].compareTo(right[j]) == -1 || 
				left[i].compareTo(right[j]) == 0) {
				arr[k] = left[i];
				k++;
				i++;
			}
			else{
				arr[k] = right[j];
				k++;
				j++;
				inv = inv + lCount - i;
			}

		}
		while(i < lCount){
			arr[k] = left[i];
			k++;
			i++;
		}
		while(j < rCount){
			arr[k] = right[j];
			k++;
			j++;
		}
		return inv;
	}

	public long mergeSort (BigInteger[] arr, int n) {
		long inv = 0;
		if(n < 2) 
			return inv;

		int mid = n/2;
		BigInteger[] left = new BigInteger[mid];
		BigInteger[] right = new BigInteger[n-mid];

		for(int i = 0; i < mid; i++){
			left[i] = arr[i];
		}
		for(int i = mid; i < n; i++){
			right[i-mid] = arr[i];
		}

		inv = mergeSort(left,mid);
		inv += mergeSort(right,n-mid);
		inv += merge(arr, left, mid, right, n-mid);
		return inv;
	}
}
