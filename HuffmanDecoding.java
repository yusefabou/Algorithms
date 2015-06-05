import java.util.*;

class HuffmanDecoding {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numSymbols = in.nextInt();
		int lengthEncoding = in.nextInt();

		HashMap<String, Character> codewords = new HashMap<String, Character>();
		for(int i = 0; i < numSymbols; i++){
			char letter = in.next().charAt(0);
			String code = in.next();
			codewords.put(code, letter);
		}

		String encoded = in.next();
		String decoded = new String();
		String word = new String();
		for(int i = 0; i < encoded.length(); i++){
			word += encoded.charAt(i);
			if(codewords.containsKey(word)){
				decoded += codewords.get(word);
				word = new String();
			}
		}
		System.out.println(decoded);
	}
}