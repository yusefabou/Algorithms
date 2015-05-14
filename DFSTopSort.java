/* Sorts a directed acyclic graph topologically */
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class DFSTopSort{
	static int[] pre;
	static int[] post;
	static int[] topArr;
	static int topCount;
	static int clock = 1;
	public static void main(String[] args){
		//Set up variables
		DFSTopSort searcher = new DFSTopSort();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int vertices = in.nextInt();
		int edges = in.nextInt();
		HashMap<Integer,LinkedList> graph = new HashMap<Integer,LinkedList>();
		boolean[] visited = new boolean[vertices+1];
		Integer v1;
		Integer v2;
		topArr = new int[vertices+1];
		topCount = topArr.length - 1;
		pre = new int[vertices+1];
	    post = new int[vertices+1];

		//Build a graph
		for(int i = 0; i < edges; i++){
			v1 = new Integer(in.nextInt());
			v2 = new Integer(in.nextInt());
			visited[v1.intValue()] = false;
			visited[v2.intValue()] = false;
			LinkedList list1 = new LinkedList();

			if(graph.containsKey(v1)){
				list1 = graph.get(v1);
				list1.add(v2);
				graph.put(v1,list1);

			}
			if(!graph.containsKey(v1)){
				list1.add(v2);
				graph.put(v1,list1);
			}
		}


		Integer u;
		for(int i = 1; i < visited.length; i++){
			u = new Integer(i);
			if(visited[i] == false){
				searcher.explore(u, graph, visited);
			}
		}
		
		for(int i = 1; i < topArr.length; i++){
			System.out.println(topArr[i]);
		}
	}

	public void explore(Integer u, HashMap<Integer,LinkedList> graph, boolean[] visited){
		visited[u.intValue()] = true;
		previsit(u);
		LinkedList list = new LinkedList();
		if(graph.containsKey(u)){
			list = graph.get(u);
			int listSize = list.size();
			Iterator<Integer> iter = list.iterator();

			for(int i = 0; i < listSize; i++){
				Integer cur = iter.next();
				if(visited[cur.intValue()] == false)
					this.explore(cur, graph, visited);
			}
		}
		postvisit(u);
	}

	public void previsit(Integer u){
		this.pre[u] = this.clock;
		this.clock = this.clock + 1; 
	}

	public void postvisit(Integer u){
		this.post[u] = this.clock;
		this.topArr[this.topCount] = u.intValue();
		this.topCount = this.topCount - 1;
		this.clock = this.clock + 1;
	}
}