
public class Main {
	public static void main(String[] args) {
		AVLTree<String> tree = new AVLTree<String>();

		tree.insert("M");
		tree.insert("N");
		tree.insert("O");

		tree.insert("L");
		
		tree.insert("K");
		tree.insert("Q");
		tree.insert("P");
		tree.insert("H");
		tree.insert("I");
		tree.insert("A"); 
		System.out.println(tree);
		//System.out.println(tree.getBalance());

	}
}
