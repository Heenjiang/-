package data_compressionAnddecompression;



public class Get_characterCount_hafmantree_codes {

	
	 public static String[] getCode(Tree.Node root) {
		    if (root == null) return null;    
		    String[] codes = new String[256];
		    assignCode(root, codes);
		    return codes;
		  }//得到每个字符的Hfm编码
		  
		  /* Recursively get codes to the leaf node */
		  private static void assignCode(Tree.Node root, String[] codes) {
		    if (root.left != null) {
		      root.left.code = root.code + "0";
		      assignCode(root.left, codes);
		      
		      root.right.code = root.code + "1";
		      assignCode(root.right, codes);
		    }
		    else {
		      codes[(int)root.element] = root.code;
		    }
		  }
 
		  public static Tree getHuffmanTree(int[] counts) {
		    // Create a heap to hold trees
		    Heap<Tree> heap = new Heap<Tree>(); // Defined in Listing 24.10
		    for (int i = 0; i < counts.length; i++) {
		      if (counts[i] > 0)
		        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
		    }
		    
		    while (heap.getSize() > 1) { 
		      Tree t1 = heap.remove(); // Remove the smallest weight tree
		      Tree t2 = heap.remove(); // Remove the next smallest weight 
		      heap.add(new Tree(t1, t2)); // Combine two trees
		    }

		    return heap.remove(); // The final tree
		  }//得到一个hfm树
		  
		  public static int[] getCharacterFrequency(String text) {
		    int[] counts = new int[256]; // 256 ASCII characters
		    
		    for (int i = 0; i < text.length(); i++)
		      counts[(int)text.charAt(i)]++; // Count the character in text
		    
		    return counts;
		  }//得到一个字符串中每个字符的频数
		  
		  public static class Tree implements Comparable<Tree> {
		    Node root; // The root of the tree

		    /** Create a tree with two subtrees */
		    public Tree(Tree t1, Tree t2) {
		      root = new Node();
		      root.left = t1.root;
		      root.right = t2.root;
		      root.weight = t1.root.weight + t2.root.weight;
		    }
		    
		    /** Create a tree containing a leaf node */
		    public Tree(int weight, char element) {
		      root = new Node(weight, element);
		    }
		    
		    /** Compare trees based on their weights */
		    public int compareTo(Tree o) {
		      if (root.weight < o.root.weight) // Purposely reverse the order
		        return 1;
		      else if (root.weight == o.root.weight)
		        return 0;
		      else
		        return -1;
		    }
		    public class Node {
			      char element=0; // 存储节点的字符
			      int weight; // 节点在树中的subtree
			      Node left; // 左节点
			      Node right; // 右节点
			      String code = ""; // 该节点的Hfm编码

			      public Node() {
			      }
			      public Node(int weight, char element) {
			        this.weight = weight;
			        this.element = element;
			      }
			    }

		    
		  }  
		  

}
