import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class DFSCC{
	public static void main(String[] args){
		//Set up variables
		DFSCC searcher = new DFSCC();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int vertices = in.nextInt();
		int edges = in.nextInt();
		HashMap<Integer,LinkedList> graph = new HashMap<Integer,LinkedList>();
		boolean[] visited = new boolean[vertices+1];
		Integer v1;
		Integer v2;
		int ccnum  = 0; //Number of connected components

		//Build a graph
		for(int i = 0; i < edges; i++){
			v1 = new Integer(in.nextInt());
			v2 = new Integer(in.nextInt());
			visited[v1.intValue()] = false;
			visited[v2.intValue()] = false;
			LinkedList list1 = new LinkedList();
			LinkedList list2 = new LinkedList();

			if(graph.containsKey(v1)){
				list1 = graph.get(v1);
				list1.add(v2);
				graph.put(v1,list1);
			}
			if(graph.containsKey(v2)){
				list2 = graph.get(v2);
				list2.add(v1);
				graph.put(v2,list2);
			}
			if(!graph.containsKey(v1)){
				list1.add(v2);
				graph.put(v1,list1);
			}
			if(!graph.containsKey(v2)){
				list2.add(v1);
				graph.put(v2,list2);
			}
		}

		//Count connected components
		Integer u;
		for(int i = 1; i < visited.length; i++){
			u = new Integer(i);
			if(visited[i] == false){
				ccnum++;
				searcher.explore(u, graph, visited);
			}
		}
		System.out.println(ccnum);
	}

	public void explore(Integer u, HashMap<Integer,LinkedList> graph, boolean[] visited){
		visited[u.intValue()] = true;
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
	}
}