import java.util.*;

class ConnectingPointsOnAPlane {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numPoints = in.nextInt();

		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(11, new EdgeComparator());
		LinkedList<Point> disjointSets = new LinkedList<Point>();
		for(int i = 1; i <= numPoints; i++){
			Point newPoint = new Point(in.nextInt(), in.nextInt());
			for(Point p : disjointSets)
				edges.add(new Edge(newPoint, p));
			disjointSets.add(newPoint);
		}
		System.out.printf("%.8f", Kruskal(edges, disjointSets));
		System.out.println();
	}



	public static double Kruskal(PriorityQueue<Edge> edges, LinkedList<Point> disjointSets){
		double totalLength = 0;

		while(!edges.isEmpty()){
			Edge curr = edges.poll();
			Point firstSet = getSet(curr.first);
			Point secondSet = getSet(curr.second);
			if(firstSet == secondSet)
				continue;

			totalLength += curr.weight;
			secondSet.parent = firstSet;
		}
		return totalLength;
	}



	public static Point getSet(Point p){
		if(p.parent != null){
			p.parent = getSet(p.parent);
			return p.parent;
		}
		return p;
	}
}


class Edge {
	Point first;
	Point second;
	double weight;
	public Edge(Point first, Point second) {
		this.first = first;
		this.second = second;
		this.weight = Distance(first, second); 
	}

	protected double Distance(Point first, Point second){
		return Math.hypot(first.x - second.x, first.y - second.y);
	}
}

class EdgeComparator implements Comparator<Edge>{
	public int compare(Edge first, Edge second){
		if(first.weight - second.weight > 0)
			return 1;
		if(first.weight - second.weight == 0)
			return 0;
		return -1;
	}
}


class Point {
	Point parent;
	double x, y;
	public Point(int x, int y){
		parent = null;
		this.x = (double)x;
		this.y = (double)y;
	}
}
