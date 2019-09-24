/* Name:    Nitisha Bhandari
 * File:    NameDLL.java
 * Desc:
 *
 * The NameDLL() class for Assignment 3.
 *
 * This class is takes a doubly linked list for a name object. This class is used to create 
 * doubly linked lists for Name objects, and is also used to traverse through the list in order  to 
 * get information like the index or the node for a certain Name object or even for strings and integers 
 * when required.
 *
 */

import java.util.*;
import java.io.*;

public class NameDLL{
    private static class Node{
	// Instance variables to store the Name object, and Node objects for the previous and next nodes
	// of the linked list
	private Name data; 
	private Node previous, next;

       /** Creates a Node with the Name object, previous and next nodes
	*  @param data The Name object the node stores
	*  @param previous The Node that comes before the current node
	*  @param next The Node that comes after the current node
	*/
	public Node(Name data, Node previous, Node next){
	    this.data = data;
	    this.previous = previous;
	    this.next = next;
	}
	
	//getters to get the values of instance variables
	public Name getData(){ return this.data;}
	public Node getPrevious(){ return this.previous;}
	public Node getNext(){ return this.next;}

	//setters to update the values
	public void setNext(Node n) {next = n;}
	public void setPrevious(Node n) {previous = n;}
    }
    
    private Node header, trailer; //sentinels
    private int size = 0;
    
    /** Creates a doubly linked list    
     *  Sets the values for header and trailer sentinels so that the list is never empty
     */
    public NameDLL(){
	header = new Node(null, null, null);
	trailer = new Node(null, null, null);
	header.setNext(trailer);
	trailer.setPrevious(header);
    }

    public int size() {
	return size;
    }
    
    public boolean isEmpty() {
	return size == 0;
    }

    public Name first() {
	if (isEmpty()) { return null; }
	else {
	    return header.getNext().getData();
	}
    }

    public Name last() {
	if (isEmpty()) {
	    return null;
	}
	else {
	    return trailer.getPrevious().getData();
	}
    }

    public Name remove(Node n){
	Node previous = n.getPrevious();
	Node next = n.getNext();
	previous.setNext(next);
	next.setPrevious(previous);
	size--;
	
	return n.getData();
    }

    public Name removeFirst() {
	if (isEmpty()) { return null;}
	else { remove(header.getNext()); }
	return header.getNext().getData();
    }

    public Name removeLast(){
	if(isEmpty()){ return null;}

	else { remove(trailer.getPrevious());}

	return trailer.getPrevious().getData();
    }

    /** Adds a Name object in between two specified nodes in the list    
     *  @param name The name object to insert
     *  @param previous The node before the current node
     *  @param next The node after the current node
     */
    public void addBtw(Name name, Node previous, Node next){
	Node newest = new Node(name, previous, next);
	previous.setNext(newest);
	next.setPrevious(newest);
	size++;
    }

    public void addFirst(Name name) {
	addBtw(name, header, header.getNext());
    }

    public void addLast(Name name) {
	addBtw(name, trailer.getPrevious(), trailer);
    }

    /** Adds a Name object before a specified node in the list    
     *  @param name The name object to insert
     *  @param n The node before which the Name object is inserted
     */
    public void insertBefore(Name name, Node n){
	if (isEmpty()){addFirst(name);}
	else if(size == 1){addFirst(name);}
	else {
	    addBtw(name, n.getPrevious(), n);
	}
    }

    /** Inserts a Name object in an alphabetically sorted list by using the insertBefore method     
     *  @param name The name object to insert
     */
    public void insertSorted(Name name){
	if (size == 0){addFirst(name);}
	else if(size == 1){
	    if(name.getName().compareTo(header.getNext().getData().getName()) > 0){ addLast(name);}
	    else{ addFirst(name);}
	}
	else{
	    Node n = header.getNext();
	    while(n.getData() != null){
		if(name.getName().compareTo(n.getData().getName()) < 0){
		    insertBefore(name, n);
		    break;
		}
		else if(name.getName().compareTo(n.getData().getName()) == 0){
		    break;
		}

		else if(n == trailer.getPrevious() &&
			name.getName().compareTo(n.getData().getName()) > 0) {
		    addLast(name);
		    break;
		}
		n= n.getNext();
	    }
	}
    }

    /** Takes in a Name object and looks for the position of the object in the sorted list 
     * @param n The name object to search 
     * @return an int for the position of the name in the list 
     */
    public int searchNode(Name n){
	Node node = header.getNext();
	int position = 0;

	while(node.getNext() != trailer && !node.getData().getName().equals(n.getName())){
	    position++;
	    node = node.getNext();
	}
	return position+1;
    }
    
    /** Takes in a Name object and looks for the total rank of the object in the final linked list
     * from all the files 
     * @param n The name object to search 
     * @return an int for the rank of the given name
     */
    public int findRank(Name name){
	Node node = header.getNext();
	int totRank = 0;

	while(name.getNameTotal() > node.getData().getNameTotal() && node.getData() != null){
	    totRank++;
	    node = node.getNext();
	}

	return totRank + 1;
    }
    
    /** Takes in a String and looks for the Name object of the string in the list
     * from all the files 
     * @param name The String to search 
     * @return Name object of the given name
     */
    public Name searchName(String name){

	Node node = header.getNext();
	Name n = null;

	while(node.getData() != null){
	    if(node.getData().getName().equals(name)) {
		n = node.getData();
		return n;
	    }
	    node = node.getNext();
	}
        return n;
    }
     
    /** Takes in an integer and find the index for the given integer
     * @param index The index or the position to locate 
     * @return Name object in that index
     */
    public Name getIndex(int index){
	Node n = header.getNext();

	for (int i = 0; i < index; i++) {
	    n = n.getNext();
	}
	return n.getData();
     }

    public String toString(){
	String printList = new String();
	for(Node n = header.getNext(); n != trailer; n=n.getNext()){
	    printList = printList + n.getData();
	    if (n != trailer.getPrevious()){
		printList = printList+ "\n";
	    }
	}
	return printList;
    }
}
