/** Name:   Nitisha Bhandari
 *  File:   Main.java
 *  Desc:
 *
 *  The main driver program for Assignment 6.
 *
 *  This program creates a new LinkedBinaryTree that implements BinaryTree<E>
 *  and uses private nodes to reference the nodes. It uses the nodes to traverse
 *  through the tree and implement the different methods.
 *
 */
public class LinkedBinaryTree<E extends Comparable<E>> implements BinaryTree<E>{
    public static final int SUB = 0;
    protected static class Node<E>{
	//instance variables for the current element and the left and right references of the element
	protected E element;
	protected Node<E> left;
	protected Node<E> right;
  protected Node<E> parent;
  protected int height = 1;

	protected Node(E element, Node <E> left, Node<E> right, Node<E> parent){
	    this.element = element;
	    this.left = left;
	    this.right = right;
      this.parent = parent;
	}


	//getters to access the instance variables
	protected E getElement(){ return element; }
	protected Node<E> getLeft() { return left; }
	protected Node<E> getRight() { return right; }
  protected Node<E> getParent(){ return parent; }
//  protected int getHeight(){return height; }

	//setters to update the instance variables
	protected void setElement(E e){ element = e; }
	protected void setLeft(Node<E> l) {
    left = l;
    //left.setParent(parent);
    //parent.setLeft(left);
  }
	protected void setRight(Node<E> r) {
    right = r;
    //right.setParent(parent);
    //parent.setParent(right);
  }
  protected void setParent(Node<E> p){ parent = p; }
  protected boolean hasParent(){
    return (left.getParent() != null || right.getParent() != null);
  }



}//node class

    public Node<E> root;
    protected int size;                   //size of the tree

    public LinkedBinaryTree() {}       //constructor for the binary search tree

    /** returns the size of the tree */
    public int size(){ return size; }

    /** Checks if the tree is empty */
    public boolean isEmpty(){ return (size == 0);}

    /**Inserts a user-input element into the tree
     * @param element The element to insert
     */
    public void insert(E element){
	     root = insertRec(root, element);
	      size++;
    }

  //  public int getBalance(){
  //    int b = root.balanceFactor(root);
  //    return b;
  //  }

    /** Returns the element of the current root. */
    public E getRootElement(){
	if(isEmpty()) { return null; }
	else{
	    return root.getElement();
	}
    }

    /** Recursive insert method to insert an element in the left tree if it's smaller
     * than the current element, in the right tree if it is bigger and replaces it
     * if it is equal
     * @param key The element to insert
     * @param root The current node
     * @return the current Node where the new element is supposed to be inserted
     */
    protected Node<E> insertRec(Node<E> root, E key){
	if(root == null){
	    root = new Node(key, null, null, null);
	    return root;
	}

	if(root.getElement().compareTo(key) > 0){
	    root.setLeft(insertRec(root.getLeft(), key));
      //root.getLeft().setParent(root);
	}
	else if(root.getElement().compareTo(key) < 0) {
	    root.setRight(insertRec(root.getRight(), key));
      //root.getRight().setParent(root);
	}
	else{
	    root.setElement(key);
	    size--;
	}


	return root;
    }

    /** returns true if the element exits in the tree and false otherwise */
    public boolean contains(E element){
	return containsRec(root, element);
    }

    /** Recursive contains method to check if an element exists in the tree
     * by setting the root to the left node if it is greater than the root and to
     * the right if it is smaller than the root.
     * @param key The element to insert
     * @param root The current node
     * @return true if the element exists and false if otherwise
     */
    private boolean containsRec(Node<E> root, E key){
	if(root == null){
	    return false;
	}
	if(root.getElement().compareTo(key) == 0){
	    return true;
	}
	if(root.getElement().compareTo(key) > 0){
	    return containsRec(root.getLeft(), key);
	}
	else{
	    return containsRec(root.getRight(), key);
	}
    }

    /** Recursive remove method to traverse through the binary search tree to find which node
     * the element to remove is in
     * @param key The element to remove
     * @param root The current node
     * @return the node the element to remove is in
     */
    private Node<E> removeRec(Node<E> root, E key){
	if(root == null){ return null; }

	if(root.getElement().compareTo(key) > 0){
	    root.setLeft(removeRec(root.getLeft(), key));

	}
	else if(root.getElement().compareTo(key) < 0){
	    root.setRight(removeRec(root.getRight(), key));
	}
	else{
	    //check the number of children the node has
	    if(root.getLeft() == null){
		return root.getRight();
	    }
	    else if(root.getRight() == null){
		return root.getLeft();
	    }
	    else{
		root.setElement(minKey(root.getRight()));
		root.setRight(removeRec(root.getRight(), root.getElement()));
	    }
	}
	return root;
    }

    /** Finds the minimum element in the right subtree to replace the removed element
     * if it has two children
     * @param root The current node
     * @return the in-order successor of the element to be removed
     */
    private E minKey(Node<E> root){
	if(root.getLeft() == null){
	    return root.getElement();
	}
	else{
	    return minKey(root.getLeft());
	}
    }

    /** Method the users call to remove a specific element */
    public boolean remove(E element){
	if(contains(element) && removeRec(root, element) != null){
	    root = removeRec(root, element);
	    size--;
	    return true;
	}
	else{
	    return false;
	}
    }
    /*
    protected int maxDepth(Node<E> root){
      if(root == null){ return 0; }
      else {
        int leftDep = maxDepth(root.getLeft());
        int rightDep = maxDepth(root.getRight());

        if(leftDep > rightDep) { return (leftDep + 1);}
        else{ return (rightDep + 1); }
      }
    }
    */
/*
  public void inOrderRec(Node<E> root){
      if(root != null){
        inOrderRec(root.getLeft());

        System.out.print(root.getElement() + " ");
        inOrderRec(root.getRight());
      }
  }
*/
    /** Concatenates a string into an in-order traversal
     * @param root The root of the subtree
     * @return The string that is an in-order traversal of the binary search tree
     */
    private String inOrderRec(Node<E> root){
	if(root != null){
	    String s = "";
	    s += inOrderRec(root.getLeft());
	    s += root.getElement() + ",";
	    s += inOrderRec(root.getRight());
	    return s;
	}
	return "";
    }

    /** Formats the string in-order in the form described in the project despription */
    public String toStringInOrder(){
	String in = "(";
	in += inOrderRec(root);
	int remove = in.lastIndexOf(",");
	in = in.substring(SUB, remove);      //remove the last comma
	in += ")";
	return in;
    }

    /** Concatenates a string into pre-order traversal of the binary search tree
     * @param root The root of the subtree
     * @return The concatenated string with the elements in preorder
     */
    private String preOrderRec(Node<E> root){
	if(root != null){
	    String s = "";
	    s += root.getElement() + ",";
	    s += preOrderRec(root.getLeft());
	    s += preOrderRec(root.getRight());
	    return s;
	}
	return "";
    }

    /** Formats the string pre-order in the form described in the project despription */
    public String toStringPreOrder(){
	String pre = "(";
	pre += preOrderRec(root);
	int remove = pre.lastIndexOf(",");
	pre = pre.substring(SUB, remove);      //remove the last comma
	pre += ")";
	return pre;
    }

    /** Concatenates a string into post-order traversal of the binary search tree
     * @param root The root of the subtree
     * @return The concatenated string with the elements in postorder
     */
    private String postOrderRec(Node<E> root){
	if(root != null){
	    String s = "";
	    s += postOrderRec(root.getLeft());
	    s += postOrderRec(root.getRight());
	    s += root.getElement() + ",";
	    return s;
	}
	return "";
    }

    /** Formats the string pre-order in the form described in the project despription */
    public String toStringPostOrder(){
	String post = "(";
	post += postOrderRec(root);
	int remove = post.lastIndexOf(",");
	post = post.substring(SUB, remove);      //remove the last comma
	post += ")";
	return post;
    }

    /** Overrides the toString to return a string that concatenates the strings
     * the three traversal methods return and creates the final format for the
     * tree to print.*/
    public String toString(){
	String print = "Tree: \n";
	print += toStringPreOrder() + "\n" + toStringInOrder() + "\n" +
	    toStringPostOrder() + "\n";
	return print;
    }
}
