import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class Dijkstra {
	static final Integer infinity = Integer.MAX_VALUE;
	public static void main(String[] args){
		Dijkstra searcher = new Dijkstra();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int vertices = in.nextInt();
		int edges = in.nextInt();
		Node[] graph = new Node[vertices+1];
		Integer[] distances = new Integer[vertices+1];
		Integer v1;
		Integer v2;
		Integer weight;
		Node node1;
		Node node2;
		LinkedList<Edge> edgeList1;
		LinkedList<Edge> edgeList2; 
		Edge edge;

		//Create directed graph, distances to infinity,  & 
		for(int i = 0; i < edges; i++){
			v1 = new Integer(in.nextInt());
			v2 = new Integer(in.nextInt());
			weight = new Integer(in.nextInt());
			distances[v1.intValue()] = infinity;
			distances[v2.intValue()] = infinity;
			if(graph[v2] != null){
				node2 = graph[v2];
			}	

			else{
				edgeList2 = new LinkedList<Edge>();
				node2 = new Node(v2.intValue(), edgeList2);
				graph[v2] = node2;
			}

			if(graph[v1] != null ){
				node1 = graph[v1];
				edgeList1 = node1.edges;
				edge = new Edge(node2, weight);
				//if(edgeList1.contains()) for duplicates
				edgeList1.add(edge);
				node1.numEdges = node1.numEdges + 1;

			}
			else if(graph[v1] == null){
				edgeList1 = new LinkedList<Edge>();
				node1 = new Node(v1.intValue(), edgeList1);
				edge = new Edge(node2, weight);
				graph[v1] = node1;
				edgeList1.add(edge);
				node1.numEdges = node1.numEdges + 1;
			}
		}

		//Search for a path
		Integer u = new Integer(in.nextInt());
		Integer v = new Integer(in.nextInt());
		if(graph[u.intValue()] == null || graph[v.intValue()] == null)
			System.out.println(-1);
		else{
			searcher.Dijkstra(graph, graph[u], distances);
			Integer result = distances[v.intValue()];
			if(result == infinity)
				System.out.println(-1);
			else System.out.println(result.intValue());
		}
	}

	public void Dijkstra(Node[] G, Node start, Integer[] distances){
		start.distance = 0;
		distances[(start.index).intValue()] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(11, new NodeComparator());
		LinkedList<Edge> edgeList;
		queue.add(start);
		Node cur;
		Iterator<Edge> iter;
		Node neighbor;
		Edge neighborEdge;
		Integer newDist;

		while(!queue.isEmpty()){
			cur = queue.remove();
			edgeList = cur.edges;
			iter = edgeList.iterator();

			for(int i = 0; i < cur.numEdges; i++){
				neighborEdge = iter.next();
				neighbor = neighborEdge.endNode;
				//newDist = new Integer((cur.distance).intValue() + (neighborEdge.weight).intValue());
				newDist = new Integer((distances[(cur.index).intValue()]).intValue() + (neighborEdge.weight).intValue());
				if((distances[(neighbor.index).intValue()]).compareTo(newDist) > 0){
					neighbor.distance = newDist;
					distances[(neighbor.index).intValue()] = newDist;
					neighbor.prev = neighborEdge;
					queue.add(neighbor);
				} //end if
			}//end for
		}//end while
	}//end Dijkstra
}//end class

class Node{
	static final Integer infinity = Integer.MAX_VALUE;
	Integer index;
	LinkedList<Edge> edges; 
	Edge prev;
	int numEdges;
	Integer distance;

	Node(int index, LinkedList<Edge> edges){
		this.index = index;
		this.edges = edges;
		this.numEdges = edges.size();
		this.prev = null;
		this.distance = infinity;
	}
}

class NodeComparator implements Comparator<Node>
 {
     public int compare(Node n1, Node n2)
     {
         return (n1.distance).compareTo(n2.distance);
     }
 }

class Edge{
	Integer weight;
	Node endNode;

	Edge(Node endNode,Integer weight){
		this.weight = weight;
		this.endNode = endNode;
	}
}

