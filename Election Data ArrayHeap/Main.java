/** Name:    Nitisha Bhandari
 *  File:    Main.java
 *  Desc:
 *
 *  The main driver program for Assignment 7.
 *
 *  This program takes in filenames for election data, reads the data to make
 *  a new object and inserts them in a binary tree sorted by the percentage. The
 *  array heap prints out in-order, pre-order and post-order traversals. This
 *  program also has optional command line arguments to print the top "n" candiates
 *  with the highest votes, and another optional argument that allows the user to
 *  exclude one or more people from being considered for the top "n" list
 *
 */
import java.util.*;
import java.io.*;

public class Main{
    public static final String CSV = ".csv", REMOVE = "-r", NUM = "-n";
    public static final int LAST = 0, FULL = 1, PERCENT = 2;

    public static void main(String[] args){
	ArrayHeap<Poll> tree = new ArrayHeap<>();
	try{
	    ArrayList<String> filenames = new ArrayList<>();

	    //Loop to check for the data files and make an arraylist of the filenames
	    for(int i = 0; i < args.length; i++){
		if(args[i].contains(CSV)){
		    filenames.add(args[i]);
		}
	    }
	    Collections.sort(filenames);

	    //Loop to call readData with all the filenames entered in the command line
	    for(int i = 0; i < filenames.size(); i++){
		readData(filenames.get(i), tree); //calls the filenames from the sorted array list
		System.out.println(tree.toStringBreadthFirst()+"\n");
		System.out.println(tree);
	    }

	    //Loop to check for the -r flags and to remove those candidates from consideration
	    for(int i = 0; i < args.length; i++){
		if(args[i].equals(REMOVE)){
		    while(!args[i+1].equals(NUM) && !args[i+1].contains(CSV)){
			Poll toRemove = new Poll(args[i+1], "", 0.0);
			tree.remove(toRemove);
			i++;
		    }
		}
	    }

	    //Loops to check for -n flags to call the peekTopN method and print the list
	    for(int i = 0; i < args.length; i++){
		if(args[i].equals(NUM)){
		    int k = Integer.parseInt(args[i+1]);
		    ArrayList<Poll> topN = tree.peekTopN(k);
		    System.out.println("Top " + k + " Candidates:");

		    for(int j = 0; j < k; j++){       //to print the top candidates
			System.out.printf("%s\n", topN.get(j));
		    }
		}
	    }
	} catch (FileNotFoundException e) { System.out.println("File not found!!"); }
    } //end of main

    /** Reads one csv file at a time and stores and updates information in a binary tree
     *  as it reads a file, and updates the tree as it is called multiple times in the
     *  main for multiple files
     *  @param filename The file the method is reading
     *  @param tree Empty LinkedBinaryTree to store the voting information
     */
    public static void readData(String filename, ArrayHeap<Poll> tree)
	throws FileNotFoundException {
	String last, full, percent;

	Scanner file = new Scanner(new File(filename));
	String line = file.nextLine();

	while(file.hasNext()){
	    line = file.nextLine();

	    String[] fields = line.split(",");
	    last = fields[LAST];
	    full = fields[FULL];
	    percent = fields[PERCENT];

	    double percentage = Double.parseDouble(percent);

	    Poll newData = new Poll(last, full, percentage);
	    tree.insert(newData);
	}

    }//end of readData
}
