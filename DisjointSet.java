import java.util.Scanner;
import java.io.*;
import java.lang.String;

class DisjointSet {
	int[] array;
	public static void main(String[] args) {
		DisjointSet set = new DisjointSet();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n = in.nextInt();
		int m = in.nextInt();

		set.array = new int[n+1];
		for(int i = 1; i <= n; i++){
			set.array[i] = -1;
		}

		//Execute commands
		String command;
		int arg1;
		int arg2;
		for(int i = 0; i < m; i++){
			command = in.next();
			arg1 = in.nextInt();
			arg2 = in.nextInt();
			if(command.equals("Check")) {
				set.Check(arg1, arg2);
			}

			else if(command.equals("Union")){
				set.Union(arg1, arg2);
			}

		}
	}

	public void Union(int first, int second){
		this.array[second] = first;
	}

	public void Check(int first, int second){
		if(this.Find(first) == this.Find(second))
			System.out.println(1);
		else System.out.println(0);
	}

	public int Find(int num){
		if(this.array[num] == -1)
			return num;

		int sentinel = this.Find(this.array[num]);
		this.array[num] = sentinel;
		return sentinel;
	}
}