import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class BFS {
	static final Integer infinity = Integer.MAX_VALUE;
	public static void main(String[] args){
		BFS searcher = new BFS();
		Scanner in = new Scanner(new InputStreamReader(System.in));
		int vertices = in.nextInt();
		int edges = in.nextInt();
		HashMap<Integer,LinkedList> graph = new HashMap<Integer,LinkedList>();
		Integer[] distance = new Integer[vertices+1];
		Integer v1;
		Integer v2;

		//Create undirected graph & set distances to infinity
		for(int i = 0; i < edges; i++){
			v1 = new Integer(in.nextInt());
			v2 = new Integer(in.nextInt());
			distance[v1.intValue()] = infinity;
			distance[v2.intValue()] = infinity;
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

		//Search for a path
		Integer u = new Integer(in.nextInt());
		Integer v = new Integer(in.nextInt());
		if(!graph.containsKey(u) || !graph.containsKey(v))
			System.out.println(-1);
		else{
			searcher.BFS(graph, u, distance);
			Integer result = distance[v.intValue()];
			if(result == infinity)
				System.out.println(-1);
			else System.out.println(result.intValue());
		}
	}

	public void BFS(HashMap<Integer,LinkedList> G, Integer start, Integer[] dist){
		dist[start.intValue()] = new Integer(0);
		LinkedList<Integer> queue = new LinkedList<Integer>();
		LinkedList list = new LinkedList();
		queue.add(start);
		Integer cur;
		int listSize;
		Iterator<Integer> iter;
		Integer neighbor;
		Integer curDist;

		while(!queue.isEmpty()){
			cur = queue.remove();
			list = G.get(cur);
			listSize = list.size();
			iter = list.iterator();

			for(int i = 0; i < listSize; i++){
				neighbor = iter.next();
				if(dist[neighbor.intValue()] == infinity){
					queue.add(neighbor);
					curDist = dist[cur];
					dist[neighbor] = new Integer(curDist.intValue() + 1);
				} //end if
			}//end for
		}//end while
	}//end BFS
}//end class

