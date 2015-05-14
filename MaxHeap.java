import java.util.Scanner;
import java.io.*;
import java.lang.String;

class MaxHeap {
	int[] array;
	int next = 1;
	int last = -1;
	int curr = 0;
	int root = 1;
	String command = null;
	int commandNum = 0;
	int n;
	//"next" is the next empty space to insert, last location
	//inserted into. "size" is number of elements in array
	public static void main(String[] args) {
		//Get the number of commands, n
		MaxHeap heap = new MaxHeap();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		heap.n = in.nextInt();
		heap.array = new int[heap.n+1];
		//Execute commands
		for(int i = 0; i < heap.n; i++){
			heap.command = in.next();
			if(heap.command.equals("Insert") && in.hasNextInt()){
				heap.commandNum = in.nextInt();
				heap.insert(heap.commandNum, heap.array);
			}
			else if(heap.command.equals("Extract"))
				heap.extract(heap.array);
		}

	}

	public void insert(int num, int[] arr) {
		this.array[next] = num;
		this.curr = this.last = this.next;
		this.next++;
		//while num larger than parent, swap positions
		while(this.curr > 1 && arr[this.curr/2] < num){
			swap(this.curr, this.curr/2, array);
		}
	}

	public void swap(int ind1, int ind2, int[] arr){
		int cNum = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = cNum;
		this.curr = ind2;
	}

	public void extract(int[] arr){
		//remove and print max
		System.out.println(arr[root]);
		this.swap(this.root, this.last, arr);
		arr[this.last] = 0;
		this.next = this.last;
		this.last--;

		//place new root in correct postion
		this.curr = this.root;
		while(arr[this.curr] < arr[this.curr*2] 
			|| arr[this.curr] < arr[this.curr*2 + 1]){
			if(arr[this.curr*2] > arr[this.curr*2 + 1]){
				this.swap(this.curr, this.curr*2, arr);
			}
			else this.swap(this.curr,this.curr*2 + 1, arr);
		}
	}
}

