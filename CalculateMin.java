import java.util.Scanner;
import java.io.*;
import java.lang.String;
import java.lang.Integer;


class CalculateMin {
	int[] array;
	public static void main(String[] args) {
		CalculateMin tree = new CalculateMin();
		Scanner in = new Scanner(new InputStreamReader(System.in));

		int n = in.nextInt();
		int m = in.nextInt();

		tree.array = new int[n+1];
		for(int i = 1; i <= n; i++){
			tree.array[i] = in.nextInt();
		}

		//Execute commands
		String command;
		for(int i = 0; i < m; i++){
			command = in.next();

			if(command.equals("Min")) {
				System.out.println(tree.Min(in.nextInt(), in.nextInt()));
			}
			else tree.Set(in.nextInt(), in.nextInt());
		}
	}

	public int Min(int left, int right){
		if(left == right)
			return this.array[left];

		int minimum = this.array[left];
		for(int i = left; i <= right; i++){
			if(this.array[i] < minimum)
				minimum = this.array[i];
		}
		return minimum;
	}

	public void Set(int index, int value){
		this.array[index] = value;
	}
}