import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class BellmanFord {
	static final Integer infinity = new Integer(10000);
	static int vertices;
	LinkedList<Edge> totalEdges;
	public static void main(String[] args){
		BellmanFord searcher = new BellmanFord();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		vertices = in.nextInt();
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
		for(int i = 0; i < vertices; i++){
			graph[i] = new Node(i)
		}

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
				totalEdges.add(edge);
				node1.numEdges = node1.numEdges + 1;

			}
			else if(graph[v1] == null){
				edgeList1 = new LinkedList<Edge>();
				node1 = new Node(v1.intValue(), edgeList1);
				edge = new Edge(node2, weight);
				graph[v1] = node1;
				edgeList1.add(edge);
				totalEdges.add(edge);
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
		else{
			searcher.BellmanFord(graph, start, distances);
			result = searcher.BellmanFordTest(graph, start, distances);
			if(result == true)
				System.out.println(1);
			else System.out.println(0);
		}
	}

	public void BellmanFord(Node[] G, Node start, Integer[] distances){
		start.distance = new Integer(0);
		distances[(start.index).intValue()] = new Integer(0);
		LinkedList<Edge> edgeList;
		Node cur;
		Node neighbor;
		Iterator<Edge> iter;
		Edge neighborEdge;
		Integer newDist;

		for(int i = 1; i <= vertices - 1; i++){
			if(G[i] != null){
				cur = G[i];
				edgeList = cur.edges;
				if(cur.numEdges > 0){
					iter = edgeList.iterator();

					for(int j = 0; j < cur.numEdges; j++){
						neighborEdge = iter.next();
						neighbor = neighborEdge.endNode;

						if((distances[(cur.index).intValue()]).compareTo(infinity) == 0 ){
							newDist = infinity;
						}
						else {
							newDist = new Integer((distances[(cur.index).intValue()]).intValue() + (neighborEdge.weight).intValue());
						}
						if((distances[(neighbor.index).intValue()]).compareTo(newDist) > 0){
							neighbor.distance = newDist;
							distances[(neighbor.index).intValue()] = newDist;
							neighbor.prev = neighborEdge;
						} 
					}
				}
			}
		}
	}// end BellmanFord

	public boolean BellmanFordTest(Node[] G, Node start, Integer[] distances){
		LinkedList<Edge> edgeList;
		Node cur;
		Node neighbor;
		Iterator<Edge> iter;
		Edge neighborEdge;
		Integer newDist;
		boolean negCycle = false;
		for(int i = 1; i <= vertices - 1; i++){
			if(G[i] != null){
				cur = G[i];
				if(cur.numEdges > 0){
					edgeList = cur.edges;
					iter = edgeList.iterator();

					for(int j = 0; j < cur.numEdges; j++){
						neighborEdge = iter.next();
						neighbor = neighborEdge.endNode;

						if((distances[(cur.index).intValue()]).compareTo(infinity) == 0){
							newDist = infinity;
						}
						else {
							newDist = new Integer((distances[(cur.index).intValue()]).intValue() + (neighborEdge.weight).intValue());
						}

						if((distances[(neighbor.index).intValue()]).compareTo(newDist) > 0){
							negCycle = true;
							return negCycle;
						} 
					}
				}
			}
		}
		return negCycle;
	} //end BellmanFordTest

	public boolean BellmanFord2(Node[] G, Node start, Integer[] distances){
		LinkedList<Edge> edgeList;
		Node neighbor;
		Iterator<Edge> iter;
		Edge neighborEdge;
		Integer newDist;
		boolean negCycle = false;

		for(int )

	}
}//end class


class Node{
	static final Integer infinity = new Integer(10000);
	Integer index;
	LinkedList<Edge> edges; 
	Edge prev;
	int numEdges;
	Integer distance;

	Node(int index, LinkedList<Edge> edges){
		this.index = new Integer(index);
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

