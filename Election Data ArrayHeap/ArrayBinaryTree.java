/** Name:   Nitisha Bhandari
 *  File:   ArrayBinaryTree.java
 *  Desc:
 *
 *  The ArrayBinaryTree implementation for Assignment 7.
 *
 *  This program creates a new ArrayBinaryTree that implements BinaryTree<E>
 *  and uses an array as the underlying data stucture to implement the methods.
 *
 */
import java.util.ArrayList;

public class ArrayBinaryTree<E extends Comparable<E>> implements BinaryTree<E>{
    public static final int SUB = 0;
    public static final String COMMA = ",", OPENP = "(", CLOSEP = ")";
    protected ArrayList<E> tree;

    //constructor for the array binary tree
    public ArrayBinaryTree(){
	tree = new ArrayList<>();
    }

    public int size(){
	return tree.size();
    }

    public boolean isEmpty(){
	return (tree.size() == 0);
    }

    /** Returns the element of the current root. */
    public E getRootElement(){
	if(isEmpty()) { return null; }
	else { return tree.get(0); }
    }

    /**Inserts an element into the tree
     * @param element The element to insert
     */
    public void insert(E element){
	tree.add(element);
    }

    /** Traverses through the tree to find the index of the element to remove and
     *  replaces it with the last element and removes the last element
     * @param element The element to remove
     * @return the node the element to remove is in
     */
    public boolean remove(E element){
	if(isEmpty()) { return false; }
	else{
	    int r = tree.indexOf(element);
	    tree.set(r, tree.get(tree.size()-1));
	    tree.remove(tree.size()-1);
	    return true;
	}
    }

    /** Finds the index of the parent when given the index of the child
     * @param i The index of the child element
     * @return The index of the parent
     */
    protected int parent(int i){
	if(isEmpty()) { return 0;}
	else if(i == 0) { return i;}
	else{ return (i-1)/2; }
    }

    /** Finds the index of the left child when given the index of the parent
     * @param i The index of the parent element
     * @return The index of the left child
     */
    protected int left(int i){
	return (2*i + 1);
    }

    /** Finds the index of the right child when given the index of the parent
     * @param i The index of the parent element
     * @return The index of the right child
     */
    protected int right(int i){
	return (2*i+2);
    }

    /** Swaps the two elements at the given indices
     * @param i The element to swap
     * @param j The element to put in place of i
     */
    protected void swap(int i, int j){
	E temp = tree.get(i);
	tree.set(i, tree.get(j));
	tree.set(j, temp);
    }

    /** Calls containsIdx to check if the element is in the tree
     * @param element The element to check for
     * @return true if the element is in the tree, and false otherwise
     */
    public boolean contains(E element){
	if(containsIdx(element) == -1){
	    return false;
	}
	else{
	    return true;
	}
    }

    /** Checks if the element exists in the tree, and finds its index
     * @param element The element to check for
     * @return the index if the element is in the tree, and -1 otherwise
     */
    protected int containsIdx(E element){
	for(int i = 0; i < size(); i++){
	    if(tree.get(i).equals(element)){
		return i;
	    }
	}
	return -1;
    }

    /** Returns the breadth first traversal of the tree by concatenating the elements
     *  of the tree into a string
     */
    public String toStringBreadthFirst(){
	String print = "";
	for(int i = 0; i < tree.size(); i++){
	    print += tree.get(i) + " ";
	}
	return print;
    }

    /** Concatenates a string into pre-order traversal of the array binary tree
     * @param i The index of the arraylist
     * @return The concatenated string with the elements in preorder
     */
    private String preOrderRec(int i){
	if(!isEmpty() && i < size()){
	    String s = "";
	    s += tree.get(i) + ", ";
	    s += preOrderRec(left(i));
	    s += preOrderRec(right(i));
	    return s;
	}
	return "";
    }

    /** Formats the string to match the pre-order format described in the project despription */
    public String toStringPreOrder(){
	String pre = OPENP;
	pre += preOrderRec(0);
	int r = pre.lastIndexOf(COMMA);
	pre = pre.substring(SUB, r);
	pre += CLOSEP;
	return pre;
    }

    /** Concatenates a string into an in-order traversal
     * @param i The index of the arraylist
     * @return The string that is an in-order traversal of the array binary tree
     */
    private String inOrderRec(int i){
	if(i < size()){
	    String s = "";
	    s += inOrderRec(left(i));
	    s += tree.get(i) + COMMA;
	    s += inOrderRec(right(i));
	    return s;
	}
	return "";
    }

    /** Formats the string to match the in-order format described in the project despription */
    public String toStringInOrder(){
	String in = OPENP;
	in += inOrderRec(0);
	int r = in.lastIndexOf(COMMA);
	in = in.substring(SUB, r);
	in += CLOSEP;
	return in;
    }

    /** Concatenates a string into post-order traversal of the binary search tree
     * @param i The index of the arraylist
     * @return The concatenated string with the elements in postorder
     */
    private String postOrderRec(int i){
	if(!isEmpty() && i < size()){
	    String s = "";
	    s += postOrderRec(left(i));
	    s += postOrderRec(right(i));
	    s += tree.get(i) + COMMA;
	    return s;
	}
	return "";
    }

    /** Formats the string to match the post-order format described in the project despription */
    public String toStringPostOrder(){
	String post = OPENP;
	post += postOrderRec(0);
	int r = post.lastIndexOf(COMMA);
	post = post.substring(SUB, r);
	post += CLOSEP;
	return post;
    }

    /** Overrides the toString to return a string that concatenates the strings
     *  the three traversal methods return and creates the final format for the
     *  tree to print.
     */
    public String toString(){
	String print = "Tree: \n";
	print += toStringPreOrder() + "\n" + toStringInOrder() + "\n"
	    + toStringPostOrder() + "\n";
	return print;
    }
}
