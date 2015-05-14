import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.lang.Integer;

class HashPointsAndSegments {
	public static final int MAX = 100000000;
	public static void main(String[] args) {
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int numSegments = in.nextInt();
		int numPoints = in.nextInt();

		int[] posArr = new int[MAX+1];
		for(int i = 0; int i <= MAX){
			posArr[i] = 0;
		}

		int[] negArr = new int[MAX+1];
		for(int i = 0; int i <= MAX){
			negArr[i] = 0;
		}

		int segStart;
		int segEnd;
		int count;
		for(int i = 1; i <= numSegments; i++){
			segStart = in.nextInt();
			segEnd = in.nextInt();

			if(segStart >= 0 && segEnd >= 0){
				for(int j = segStart; j <= segEnd; j++){
					posArr[j] = posArr[j] + 1;
					}
			}
			else if(segStart < 0 && segEnd < 0){
				for(int j = -segEnd; j <= -segStart; j+=){
					negArr[j] = negArr[j] + 1;
				}
			}
			else{
				for(int j = -segStart; j > 0; j--){
					negArr[j] = negArr[j] + 1;
				}
				for(int k = 0; k <= segEnd; k++){
					posArr[k] = posArr[k] + 1;
				}
			}
		}
		
		for(int i = 1; i <= numPoints; i++){
			point = in.nextInt();
			if(point >= 0)
				System.out.println(posArr[point]);
			else System.out.println(negArr[point]);
		}
	}
}