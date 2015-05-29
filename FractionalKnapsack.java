import java.util.*;

class FractionalKnapsack {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int itemCount = in.nextInt();
		int maxWeight = in.nextInt();

		LinkedList<Item> items = new LinkedList<Item>();
		for(int i = 0; i < itemCount; i++)
			items.add(new Item(in.nextInt(), in.nextInt()));
		Collections.sort(items, new ItemComparator());
		
		int currWeight = 0;
		double currCost = 0;
		while(currWeight < maxWeight && !items.isEmpty()){
			int remainingWeight = maxWeight - currWeight;
			Item curr = items.poll();

			if(curr.weight <= remainingWeight){
				currWeight += curr.weight;
				currCost += curr.cost;
			} 

			if(curr.weight > remainingWeight){
				currCost += remainingWeight*curr.ratio;
				currWeight += remainingWeight;
			}
		}

		System.out.printf("%.3f", currCost);
		System.out.println();		
	}
}



class Item {
	int cost;
	int weight;
	double ratio;

	public Item(int cost, int weight){
		this.cost = cost;
		this.weight = weight;
		ratio = ((double)cost)/weight;
	}
}



class ItemComparator implements Comparator<Item> {
	public int compare(Item first, Item second){
		double difference = first.ratio - second.ratio;
		if(difference > 0)
			return -1;
		else if(difference == 0)
			return 0;
		else 
			return 1;
	}
}