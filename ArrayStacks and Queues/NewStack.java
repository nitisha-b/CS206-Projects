/** Name:    Nitisha Bhandari
 * File:    NewStack.java
 * Desc:
 *
 * NewStack class for Assignment 4.
 *
 * This program creates an object NewStack that stores integers and support the
 * operations push, pop and minElement, among others, and returns the minimum
 * element in the stack, with a runtime of O(1), and prints the stack in the
 * format provided in the project description.
 *
 */
public class NewStack{
    //Default capacity of the deque
    public static final int CAPACITY = 1000;

    //Integer array to store the numbers entered by the user
    private Integer[] stack;
    private int top = -1; //Top of the stack

    // Integer ArrayStack to store all the different minimums, from everytime the
    // original array is modified
    private ArrayStack<Integer> minStack;


    /** Creates an integer array and an integer ArrayStack object with the default
     * capacities of size 1000
     */
    public NewStack(){
	this(CAPACITY);
	minStack =  new ArrayStack<Integer>();
    }

    /** Creates integer array and ArrayStack objects with the capacity entered by the
     * user
     * @param capacity The capacity of the desired queue
     */
    public NewStack(int capacity){
	stack = new Integer[capacity];
	minStack = new ArrayStack<Integer>(capacity);
    }

    /**
     * Returns the number of elements in the stack.
     * @return number of elements in the stack
     */
    public int size() {return (top+1);}

    /**
     * Tests whether the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {return (top == -1);}

    /**
     * Inserts an element at the top of the stack.
     * @param e  the element to be inserted
     * @throws IllegalStateException if the array storing the elements is full
     */
    public void push(int n) throws IllegalStateException {
	//Insert the first element in both the stacks
	if(isEmpty()){ minStack.push(n);}

	//Check with the current minimum
	else if(n <= minElement()){ minStack.push(n); }

	if (size() == stack.length) throw new IllegalStateException("Stack is full");
	stack[++top] = n; // increment top before storing new item
    }

    /**
     * Returns the element at the top of the stack.
     * @return top element in the stack (or null if empty)
     */
    public int top() {
	if (isEmpty()) throw new IllegalStateException("Stack is empty.");
	return stack[top];
    }

    /**
     * Removes and returns the top element from the stack.
     * @return element removed (or null if empty)
     */
    public int pop() {
	if (isEmpty()) throw new IllegalStateException("Stack is empty.");
	if(top() == minElement()){minStack.pop();}
	int answer = stack[top];
	stack[top] = null;      // dereference to help garbage collection
	top--;

	return answer;
    }

    /** Returns the top of the stack that stores the minimum elements
     */
    public int minElement(){
	return minStack.top();
    }

    /**
     * Produces a string representation of the contents of the stack.
     * (ordered from top to bottom). This exists for debugging purposes only.
     *
     * @return textual representation of the stack
     */
    public String toString() {
	StringBuilder sb = new StringBuilder("(");
	for (int j = 0; j <= top; j++) {
	    sb.append(stack[j]);
	    if (j < top) sb.append(", ");
	}
	sb.append(")");
	//  System.out.println("minimum Stack " + minStack);
	return sb.toString();
    }

}
