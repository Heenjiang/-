package data_compressionAnddecompression;



public class Get_characterCount_hafmantree_codes {

	
	 public static String[] getCode(Tree.Node root) {
		    if (root == null) return null;    
		    String[] codes = new String[256];
		    assignCode(root, codes);
		    return codes;
		  }//�õ�ÿ���ַ���Hfm����
		  
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
		  }//�õ�һ��hfm��
		  
		  public static int[] getCharacterFrequency(String text) {
		    int[] counts = new int[256]; // 256 ASCII characters
		    
		    for (int i = 0; i < text.length(); i++)
		      counts[(int)text.charAt(i)]++; // Count the character in text
		    
		    return counts;
		  }//�õ�һ���ַ�����ÿ���ַ���Ƶ��
		  
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
			      char element=0; // �洢�ڵ���ַ�
			      int weight; // �ڵ������е�subtree
			      Node left; // ��ڵ�
			      Node right; // �ҽڵ�
			      String code = ""; // �ýڵ��Hfm����

			      public Node() {
			      }
			      public Node(int weight, char element) {
			        this.weight = weight;
			        this.element = element;
			      }
			    }

		    
		  }  
		  

}
