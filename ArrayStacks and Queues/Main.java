/** Name:    Nitisha Bhandari
 * File:    Main.java
 * Desc:
 *
 * The main driver program for Assignment 4.
 *
 * This program is designed to test the ArrayDeque, TwoStacksQueue and Newstack
 * objects. It tests all the methods that each of these objects implement and
 * prints them out.
 *
 */

public class Main{
    public static void main(String[] args){

	//TwoStacksQueue tests
	TwoStacksQueue<Integer> test = new TwoStacksQueue<>();

	int a = 1;
	int b = 2;
	int c = 3;
	int d = 4;
	int e = 5;

	System.out.println("\nTwoStacksQueue tests\n");
	System.out.println("Size of queue: " + test.size());
	System.out.println("Empty queue: " + test);

	test.enqueue(a);
	test.enqueue(b);
	test.enqueue(c);
	test.enqueue(d);
	test.enqueue(e);

	System.out.println("Enqueued queue: " + test);
	System.out.println("Size of queue after enqueue: " + test.size());

	test.dequeue();
	test.dequeue();

	System.out.println("Dequeued queue: " + test);
	System.out.println("First element: " + test.first());
	System.out.println("Size of queue after dequeue: " + test.size()+"\n");

	//ArrayDeque tests
	ArrayDeque<Integer> data = new ArrayDeque<>();

	System.out.println("ArrayDeque tests\n");
	System.out.println("Empty deque: " + data);
	System.out.println("Size of deque: " + data.size());

	data.addFirst(1);
	data.addLast(2);
	data.addLast(3);
	data.addLast(4);
	data.addLast(5);

	System.out.println("Deque: " + data);
	System.out.println("Size of deque after adding: " + data.size());

	data.removeFirst();
	System.out.println("Deque after remove first: " + data);

	data.removeLast();
	System.out.println("Deque after remove last: " + data);

	data.addLast(45);
	data.removeFirst();
	System.out.println("Deque after addLast and removeFirst: " + data);

	data.addFirst(666);
	data.removeLast();
	System.out.println("Deque after addFirst and removeLast: " + data);

	data.addLast(54);
	System.out.println("Deque: " + data);

	System.out.println("First element: " + data.first());
	System.out.println("Last element: " + data.last());
	System.out.println("Size of deque:" + data.size()+"\n");

	//NewStack test
	NewStack store = new NewStack();

	System.out.println("\nNewStack tests\n");

	System.out.println("Empty NewStack: " + store); //empty stack
	System.out.println("Size of empty NewStack: " + store.size());

	//store.pop(); //throws IllegalStateException
	store.push(1);
	store.push(2);
	store.push(-4);
	store.push(32);
	store.push(-10);
	store.push(-999);
	System.out.println("Pushed Newstack: " + store);
	System.out.println("Size of pushed NewStack: " + store.size());
	System.out.println("Minimum element in the stack: " + store.minElement());

	//More operations
	store.pop();
	store.pop();
	System.out.println("Popped Newstack: " + store);
	System.out.println("Size of new NewStack: " + store.size());
	System.out.println("Minimum element in the stack: " + store.minElement());

	store.push(-200);

	System.out.println("Final Newstack: " + store);
	System.out.println("Size of final NewStack: " + store.size());
	System.out.println();

	//minimum element in the stack
	int minimum = store.minElement();
	System.out.println("Minimum element in the final stack: " + minimum);
	System.out.println();
    }
}
