import java.util.*;

class HuffmanEncoding {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String str = in.next();

		int[] freqs = new int[256];
		for(int i = 0; i < str.length(); i++)
			freqs[(int)str.charAt(i)]++;

		HuffmanTree tree = new HuffmanTree(freqs);
		String encoded = "";
		for(int i = 0; i < str.length(); i++)
			encoded += tree.leaves.get(str.charAt(i)).code;

		System.out.println(tree.inOrderLeaves.size() + " " + encoded.length());
		while(!tree.inOrderLeaves.isEmpty()){
			TreeNode leaf = tree.inOrderLeaves.poll();
			System.out.println(leaf.character + ": " + leaf.code);
		}
		System.out.println(encoded);
	}
}

class HuffmanTree {
	TreeNode root;
	HashMap<Character, TreeNode> leaves;
	LinkedList<TreeNode> inOrderLeaves;

	public HuffmanTree(int[] freqs){
		this.leaves = new HashMap<Character, TreeNode>();
		this.inOrderLeaves = new LinkedList<TreeNode>();

		PriorityQueue<TreeNode> pq 
				= new PriorityQueue<TreeNode>(11, new TreeNodeComp());

		for(int i = 0; i < freqs.length; i++){
			if(freqs[i] != 0){
				TreeNode node = new TreeNode((char)i, freqs[i]);
				leaves.put(node.character, node);
				inOrderLeaves.add(node);
				pq.add(node);
			}
		}


		if(pq.size() == 1){
			TreeNode node = pq.poll();
			node.code = "0";
			this.root = node;
			return;
		}

		while(pq.size() > 1) {
			TreeNode node1 = pq.poll();
			TreeNode node2 = pq.poll();

			TreeNode newNode = new TreeNode(node1.character, node1.freq + node2.freq);
			newNode.c0 = node1;
			newNode.c1 = node2;
			node1.parent = newNode;
			node2.parent = newNode;
			pq.add(newNode);
		}
		this.root = pq.poll();

		for(TreeNode node : inOrderLeaves)
			node.code = getCode(node.character);
	}

	public String getCode(char c){
		if(leaves.get(c) == null)
			return null;

		String code = "";
		TreeNode node = leaves.get(c);
		while(node != root){
			if(node == node.parent.c0)
				code = "0" + code;
			if(node == node.parent.c1)
				code = "1" + code;
			node = node.parent;
		}
		return code;
	}
}

class TreeNode {
	char character;
	int freq;
	String code;
	TreeNode parent;
	TreeNode c0;
	TreeNode c1;

	public TreeNode(char character, int freq){
		this.character = character;
		this.freq = freq;
		String code = new String();
		c0 = null;
		c1 = null;
		parent = null;
	}
}

class TreeNodeComp implements Comparator<TreeNode>{
	public int compare(TreeNode first, TreeNode second){
		return first.freq - second.freq;
	}
}