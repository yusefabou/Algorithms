import java.util.*;

class CoveringSegmentsbyPoints {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		LinkedList<Seg> segs = new LinkedList<Seg>();
		for(int i = 1; i <= n; i++){
			segs.add(new Seg(in.nextInt(), in.nextInt()));
		}

		Collections.sort(segs, new SegmentComparator());

		ArrayList<Integer> result = new ArrayList<Integer>();
		while(!segs.isEmpty()){
			Seg cur = segs.poll();
			result.add(cur.right);
			Seg following = segs.peek();
			while(segs.peek() != null && following.left <= cur.right){
				segs.remove();
				following = segs.peek();
			}
		}

		System.out.println(result.size());
		for(int i = 0; i < result.size(); i++){
			System.out.print(result.get(i) + " ");
		}
	}
}

class Seg {
	int left, right;
	public Seg(int l, int r){
		this.left = l;
		this.right = r;
	}
}

class SegmentComparator implements Comparator<Seg>{
	public int compare(Seg first, Seg second){
		return first.right - second.right;
	}
}