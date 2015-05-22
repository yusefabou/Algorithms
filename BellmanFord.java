import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.*;

class BellmanFord {
	static int vertices;
	static LinkedList<Edge> edgeList;
	static final int infinity = 10000;

	public static void main(String[] args){
		BellmanFord searcher = new BellmanFord();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		vertices = in.nextInt();
		int edges = in.nextInt();
		Node[] graph = new Node[vertices+1];
		int[] distances = new int[vertices+1];
		edgeList = new LinkedList<Edge>();
		int v1;
		int v2;
		int weight;
		Node node1;
		Node node2;
		LinkedList<Edge> adjList1;
		LinkedList<Edge> adjList2;
		Edge edge;

		//Create the graph, and add edges to adjacency list and edge list
		for(int i = 1; i <= vertices; i++){
			adjList1 = new LinkedList<Edge>();
			graph[i] = new Node(i, adjList1);
			distances[i] = infinity;
		}

		for(int i = 0; i < edges; i++){
			v1 = in.nextInt();
			v2 = in.nextInt();
			weight = in.nextInt();

			if(graph[v2] != null){
				node2 = graph[v2];
			}
			else{
				adjList2 = new LinkedList<Edge>();
				node2 = new Node(v2, adjList2);
			}

			if(graph[v1] != null) {
				node1 = graph[v1];
				adjList1 = node1.adjList;
				edge = new Edge(node1, node2, weight);
				adjList1.add(edge);
				edgeList.add(edge);
				node1.numEdges = node1.numEdges + 1;
			}

			else{
				adjList1 = new LinkedList<Edge>();
				node1 = new Node(v1, adjList1);
				edge = new Edge(node1, node2, weight);
				graph[v1] = node1;
				adjList1.add(edge);
				searcher.edgeList.add(edge);
				node1.numEdges = node1.numEdges + 1;
			}
		}

		//Find a valid source
		Node start = null;
		boolean result = false;
		for(int i = 1; i <= vertices; i++){
			if(graph[i] != null){
				start = graph[i];
				break;
			}
		}

		//If no valid source, there are no negative cycles
		if(start == null){
			System.out.println(0);
		}
		//Otherwise, use Bellman-Ford to find a negative cycle
		else{
			result = searcher.BellmanFord(graph, start, distances);
			if(result == true)
				System.out.println(1);
			else System.out.println(0);
		}

	}

	public boolean BellmanFord(Node[] G, Node start, int[] distances){
		start.distance = 0;
		distances[start.index] = 0;
		Iterator<Edge> edgeIter;
		Edge curEdge;
		int newDistance;
		boolean negCycle = false;


		//Relax Edges
		for(int i = 1; i <= this.vertices - 1; i++){
			edgeIter = this.edgeList.iterator();
			for(int j = 0; j < this.edgeList.size(); j++){
				curEdge = edgeIter.next();
				newDistance = distances[(curEdge.startNode).index] + curEdge.weight;
				if(newDistance < distances[(curEdge.endNode).index]){
					distances[(curEdge.endNode).index] = newDistance;
					(curEdge.endNode).distance = newDistance;
					(curEdge.endNode).prev = (curEdge).startNode;
				}
			}
		}

		//Check for negative cycles
		edgeIter = this.edgeList.iterator();
		for(int i = 0; i < this.edgeList.size(); i++){
			curEdge = edgeIter.next();
			newDistance = distances[(curEdge.startNode).index] + curEdge.weight;
			if(newDistance < distances[(curEdge.endNode).index]){
				negCycle = true;
				return negCycle;
			}
		}
		return negCycle;
	}
}

class Node{
	static final int infinity = 10000;
	int index;
	LinkedList<Edge> adjList; 
	Node prev;
	int numEdges;
	int distance;

	Node(int index, LinkedList<Edge> adjList){
		this.index = index;
		this.adjList = adjList;
		this.numEdges = adjList.size();
		this.prev = null;
		this.distance = infinity;
	}
}

class NodeComparator implements Comparator<Node>
 {
     public int compare(Node n1, Node n2)
     {
     	Integer dist1 = new Integer(n1.distance);
     	Integer dist2 = new Integer(n2.distance);
     	return (dist1).compareTo(dist2);
     }
 }

class Edge{
	int weight;
	Node endNode;
	Node startNode;

	Edge(Node startNode, Node endNode,int weight){
		this.weight = weight;
		this.endNode = endNode;
		this.startNode = startNode;
	}
}