/** Name:   Nitisha Bhandari
 *  File:   ArrayHeap.java
 *  Desc:
 *
 *  The ArrayHeap implementation for Assignment 7.
 *
 *  This program is the implementation of an ArrayHeap, where the generic element
 *  extends Comparable and ArrayHeap extends ArrayBinaryTree.
 *
 */
import java.util.ArrayList;
import java.util.Collections;

public class ArrayHeap<E extends Comparable<E>> extends ArrayBinaryTree<E>
    implements PriorityQueue<E>{


    /** Insert an element into the heap nad upheaps and downheaps to maintain heap order
     * @param element The element to insert
     */
    public void insert(E element){
	int idx = containsIdx(element);

	if(idx != -1){        //if element exists
	    tree.set(idx, element);       //replace the element
	}
	else{
	    tree.add(element);
	}
	upHeap(tree.size()-1);
	downHeap(0);
    }

    /** Compares the element at the given index with its parent and swaps them if
     * necessary to maintain heap order
     * @param i The index of the element to insert in or remove from the heap
     */
    protected void upHeap(int i){
	while(i > 0){
	    int parent = parent(i);
	    int compare = tree.get(i).compareTo(tree.get(parent));
	    if( compare >= 0) break;
	    else{
		swap(i, parent);
		i = parent;
	    }
	}
    }

    /** Compares the element at the given index with the grater child, in this case,
     * and swaps them if necessary to maintain heap order
     * @param i The index of the element to insert in or remove from the heap
     */
    protected void downHeap(int i){
	while(left(i) < size()){    //while left child exists
	    int left = left(i);
	    int smaller = left;       //if right child does not exist

	    if(right(i) < size()){
		int right = right(i);
		if(tree.get(right).compareTo(tree.get(left)) < 0) {
		    smaller = right;    //if right child is smaller than left
		}
	    }
	    int compare = tree.get(i).compareTo(tree.get(smaller));
	    if(compare <= 0) break;
	    else{
		swap(i, smaller);       //swap current index with the smaller child
		i = smaller;
	    }
	}
    }

    /** Calls containsIdx to check whether or not the element exists and removes it
     * if it does and returns true, or returns false if it does not. Also calls
     * upheap and downheap to restore heap order
     * @param element The element to remove
     * @return true if the element exists and false otherwise
     */
    public boolean remove(E element){
	boolean remove = false;
	if(isEmpty()) { return false; }
	else{
	    int r = containsIdx(element);

	    if(r != -1){      //if exists
		tree.remove(element);
		upHeap(r);
		downHeap(r);
		remove = true;
	    }
	}
	return remove;
    }

    /** Returns, but does not remove the top element of the heap, which is the
     * root of the tree
     */
    public E peek(){
	return getRootElement();
    }

    /** Returns and removes the top element of the heap */
    public E poll(){
	if(isEmpty()){ return null; }
	E target = tree.get(0);
	remove(tree.get(0));
	return target;
    }

    /** Copies the existing arraylist and sorts it from the highest percentage down,
     * and puts the "n" number of candidates with the highest percentages in an
     * arraylist
     * @param n The top "n" candidates to return
     * @return The arrayList of the top n candidates in the heap
     */
    public ArrayList<E> peekTopN(int n){
	ArrayList<E> copy = new ArrayList<>();     //copy the original arraylist
	ArrayList<E> topN = new ArrayList<>();    //to store the top n candidates
	copy = tree;
	Collections.sort(copy);         //sort the array list in ascending order

	for(int i = 0; i < n; i++){
	    E temp = copy.get(0);
	    copy.remove(0);
	    topN.add(temp);          //add to the arraylist
	}
	return topN;
    }
}
