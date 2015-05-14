import java.util.Scanner;
import java.io.*;
import java.lang.String;
import java.lang.Integer;


class SegmentTree {
	int[] array;
	
	final int INFINITY = Integer.MAX_VALUE;
	int root;

	public static void main(String[] args) {
		SegmentTree tree = new SegmentTree();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[] preArray;

		//Create initial array
		preArray = new int[n+1]
		for(int i = 1; i <= n; i++){
			preArray[i] = in.nextInt();
		}

		//Create array with size expanded to nearest power of two
		int expandedSize = tree.nextPowerOfTwo(n);
		int[] expandedArray = new int[expandedSize+1];
		for(int i = 1; i <= n; i++) {
			expandedArray[i] = preArray[i]
		}
		for(int i = n+1; i<= expandedSize; i++){
			expandedArray[i] = INFINITY;
		}

		//Create & build representational segment tree array
		tree.array = new int[2*expandedSize];
		int expIndex = 1;
		for(int i = expandedSize/2; i <= (2*expandedSize)-1; i++) {
			tree.array[i] = expandedArray[expIndex];
			expIndex++;
		}
		for(int i = (2*expandedSize)-2; i >= 2; i-=2){
			if(tree.array[i] < tree.array[i+1])
				tree.array[i/2] = tree.array[i];
			else tree.array[i/2] = tree.array[i+1];
		} 

		//Execute commands
		String command;
		int left, right, index, value;
		for(int i = 0; i < m; i++) {
			command = in.next();
			
			if(command.equals(Min)) {
				//set arguments to actual index in array
				left = in.nextInt();
				left = left + (expandedSize/2) - 1;
				right = in.nextInt();
				right = right + (expandedSize/2) - 1;
				tree.Min(left, right)
			}

			if(command.equals(Set)){
				index = in.nextInt();
				index = index + (expandedSize/2) - 1;
				value = in.nextInt();
				tree.array[index] = value;


			}
		}


	}

	public int nextPowerOfTwo(int num){
        int pwr = 1;
        while (pwr < num)
        {
            pwr = pwr << 1;
        }
        System.out.println(pwr);
        return pwr;
    }

    //Recursive function to get minimum 
    public int Min(int left, int right){
    	if(left )
    }

    public Set(int index, int value){

    }

}