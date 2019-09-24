/** Name:    Nitisha Bhandari
 * File:    TwoStacksQueue.java
 * Desc:
 *
 * TwoStacksQueue class for Assignment 4.
 *
 * This program creates a TwoStacksQueue object that implements Queue and uses
 * a First In First Out strategy to make a queue using two ArrayStack objects.
 *
 */
public class TwoStacksQueue<E> implements Queue<E> {
    //Default capacity of the deque
    public static final int CAPACITY = 1000;

    // Two generic ArrayStack objects used for storage of queue elements.
    private ArrayStack<E> stack1;
    private ArrayStack<E> stack2;

    /** Creates two generic ArrayStack objects with the default capacity of size
     * 1000
     */
    public TwoStacksQueue(){
	stack1 = new ArrayStack<>(CAPACITY);
	stack2 = new ArrayStack<>(CAPACITY);
    }

    /** Creates two generic ArrayStack objecrs with the capacity entered by the
     * user
     * @param capacity The capacity of the desired queue
     */
    public TwoStacksQueue(int capacity){
	stack1 = new ArrayStack<>(capacity);
	stack2 = new ArrayStack<>(capacity);
    }

    /**
     * Calls the size() method for the ArrayStack object to return the size of the queue.
     * @return number of elements in the queue
     */
    public int size(){
	return stack1.size();
    }

    /**
     * Calls the isEmpty() method for the ArrayStack check if the queue is empty
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
	return stack1.isEmpty();
    }

    /**
     * Inserts an element at the end of the queue.
     * @param e the element to be inserted
     */
    public void enqueue(E e){
	stack1.push(e);
    }

    /**
     * Returns the first element of the queue by moving all the elements in
     * stack1 to stack2 and getting the top for stack2, and moving them back again
     * @return the first element of the queue (or null if empty)
     */
    public E first(){
	while(!stack1.isEmpty()){
	    E e = stack1.pop();
	    stack2.push(e);
	}

	E first = stack2.top();

	// Move all the elements to the second stack in order to get the first
	// element as the top in that stack and using pop to retrieve it
	while(!stack2.isEmpty()){
	    E push = stack2.pop();
	    stack1.push(push);
	}
	return first;
    }

    /**
     * Removes and returns the first element of the queue, by using the same
     * strategy as the first() method.
     * @return element removed (or null if empty)
     */
    public E dequeue(){
	while(!stack1.isEmpty()){
	    E pop = stack1.pop();
	    stack2.push(pop);
	}
	E first = stack2.top();
	stack2.pop();

	while(!stack2.isEmpty()){
	    E push = stack2.pop();
	    stack1.push(push);
	}
	return first;
    }

    /** Moves all the elements from stack1 into stack2 and uses the toString()
     * for the ArrayStack to print stack2, and moves all the elements back to
     * stack1
     * @return the queue with the element entered first to be on the left and
     * the ones entered later moving toward the right
     *
     */
    public String toString(){
	while(!stack1.isEmpty()){
	    E e = stack1.pop();
	    stack2.push(e);
	}
	String s = stack2.toString();

	while(!stack2.isEmpty()){
	    E pop = stack2.pop();
	    stack1.push(pop);
	}
	return s;
    }
}
