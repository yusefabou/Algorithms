import java.util.*;
import java.io.*;

class PriorityQueueSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int n = in.nextInt();

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for(int i = 1; i <= n; i++){
			queue.offer(in.nextInt());
		}

		//Print 
		for(int i = 1; i <=n; i++){
			System.out.println(queue.poll());
		}
	}
}