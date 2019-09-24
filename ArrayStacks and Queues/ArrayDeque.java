/** Name:    Nitisha Bhandari
 * File:    ArrayDeque.java
 * Desc:
 *
 * ArrayDeque class for Assignment 4.
 *
 * This program creates an object that implements Deque<E>, ArrayDeque<E>, which
 * is a double sided queue, allowing the user to enter and remove elements from
 * both ends of the queue.
 *
 */
public class ArrayDeque<E> implements Deque<E>{
    // Generic array used for storage of queue elements. */
    private E[] info;

    //Default capacity of the deque
    public static final int CAPACITY = 1000;
    private int sz = 0; //size of the Queue
    private int f = 0; //index of the front element

    public ArrayDeque(){
	this(CAPACITY);
    }

    // constructs queue with given capacity
    public ArrayDeque(int capacity) {
	info = (E[]) new Object[CAPACITY];   // safe cast; compiler may give warning
    }

    /**
     * Returns the size of the deque.
     * @return number of elements in the deque
     */
    public int size(){
	return sz;
    }

    /**
     * Tests whether the deque is empty.
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty(){
	return (sz == 0);
    }

    /**
     * Returns the first element of the deque.
     * @return first element of the deque (or null if empty)
     */
    public E first(){
	if(isEmpty()) { return null;}
	E first = info[f];
	return first;
    }

    /**
     * Returns the last element of the deque.
     * @return last element of the deque (or null if empty)
     */
    public E last(){
	if(isEmpty()){return null;}

	int r = (f + sz) % info.length;
	if(r == 0){
	    return info[info.length-1];
	}
	else {
	    return info[r-1];
	}
    }

    /**
     * Inserts an element at the front of the deque.
     * @param e the new element to enter
     */
    public void addFirst(E e){
	if (sz == info.length) throw new IllegalStateException("Queue is full");

	//Insert an element in the first index if deque is empty
	if(isEmpty()){
	    info[0] = e;
	    sz++;
	}

	/* Insert the element at the end of the deque if the first element is at the
	   first index */
	else if(f == 0){
	    f = info.length-1;
	    info[f] = e;
	    sz++;
	}

	/* Insert the element before the first element in the deque */
	else{
	    int r = f-1;
	    info[r] = e;
	    sz++;
	    f--;
	}
    }

    /**
     * Inserts an element at the back of the deque.
     * @param e the element to enter
     */
    public void addLast(E e) throws IllegalStateException{
	if (sz == info.length) throw new IllegalStateException("Queue is full");

	int r = (f + sz) % info.length;   // use modular arithmetic
	info[r] = e;
	sz++;
    }

    /**
     * Removes and returns the first element of the deque.
     * @return element removed (or null if empty)
     */
    public E removeFirst(){
	if(isEmpty()) { return null; }

	E remove = info[f];
	info[f] = null;
	f = (f+1) % info.length;
	sz--;
	return remove;
    }

    /**
     * Removes and returns the last element of the deque.
     * @return element removed (or null if empty)
     */
    public E removeLast(){
	if(isEmpty()) {return null; }
	else{
	    int r = (f + sz) % info.length;
	    E target = info[r-1];
	    info[r-1] = null;
	    sz--;
	    return target;
	}
    }

    /**
     * Prints the circular ArrayDeque
     */
    public String toString(){
	String s = "(";
	int k = f;
	for(int i = 0; i < sz-1; i++){

	    s += info[k]+", ";
	    k = (k+1) % info.length;
	}
	s = s + (info[k] + ")");
	return s;
    }

}
