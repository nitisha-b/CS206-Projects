/* Name:    Nitisha Bhandari
 * File:    Main.java
 * Desc:
 *
 * The main driver program for Assignment 3.
 *
 * This program takes in command line inputs from the user that are names and files, and looks up a name 
 * according to the male or female flag each name is entered with. It then prints the data for each name for 
 * all the years the datafiles have been inputted for, in the format described in the assignment.
 *
 */

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
	try{
	    //Make new doubly linked lists for male and female names
	    NameDLL mList = new NameDLL();
	    NameDLL fList = new NameDLL();
	    
	    //array lists for file totals to store the total babies for each gender for each year
	    ArrayList<Integer> fileTotalsM = new ArrayList<>();
	    ArrayList<Integer> fileTotalsF = new ArrayList<>();

	    //Array list of years for the files input for all the years
	    ArrayList<Integer> years = new ArrayList<>();

	    //Male and female names arraylist to store all the user input names in the respective lists
	    ArrayList<String> maleNames = new ArrayList<>();
	    ArrayList<String> femaleNames = new ArrayList<>();
	    
	    int fGrandTot = 0; int mGrandTot = 0; //represents the total for all files for all years
    	    int index = 0;
	    
	    /* for loop to check where the csv files begin and call the readFiles method
	     * that number of times, and compute the grand total after each time the
	     * file is read
	     */
	    double mGrandPercent = 0; //Represents the total percentage for a name for the given years
	    double fGrandPercent = 0;


	    // Go through the command line and call ReadFiles for the number of files the user has input
	    // and calculate the grand total for all input files
	    for(int i = 0; i < args.length; i++){
		Scanner input = new Scanner(args[i]);
		if(args[i].contains(".csv")){
		    String filename = args[i];
		    ReadFile.readFiles(filename, mList, fList, fileTotalsM, fileTotalsF, years);
		    mGrandTot += fileTotalsM.get(index);
		    fGrandTot += fileTotalsF.get(index);
		    index++;
		}
	    }

	    //Checks the flags for male and female names and puts the names in the respective lists
	    for(int i = 0; i < args.length; i++){
		if(args[i].equals("-f")){
		    femaleNames.add(args[i+1]);
		    i++;
		}
		else if(args[i].equals("-m")){
		    maleNames.add(args[i+1]);
		    i++;
		}
	    }
	    
	    //females
	    // Loops that go through the array list of input names and the statistics array list of the input names
	    // to compute the relevant information and print them out
	    
	    double fPercent = 0.0; //female yearly percentage
	    
	    for(int f = 0; f < femaleNames.size(); f++){
		Name fName = fList.searchName(femaleNames.get(f));
		if(fName == null){ System.out.println("Name does not exist.");} //if Name does not exist
		else{
		    
		    int position = fList.searchNode(fName); //alphabetical position
		    System.out.println(position);
		    System.out.println();
		    int fRank = 0; //represents total rank of the name in all input files
		    
		    fRank = fList.findRank(fName); //total rank
 		   
		    //Go through the stats arraylist and compute yearly percentage and print relevant information
		    for(int j = 0; j < fName.getStats().size(); j++){
			
			if(years.get(j) == fName.getStats().get(j).getYear()){
			    fPercent = (double)fName.getStats().get(j).getNum()/fileTotalsF.get(j);
			}
			
			System.out.print(fName.getStats().get(j).getYear()+"\n"+fName.getName()
					 +": "+fName.getStats().get(j).getRank()+" "+fName.getStats().get(j).getNum()+" ");
			System.out.printf("%.6f\n", fPercent);
			System.out.println();
		    }
		    //Total percentage for each name for all the years in all input files
		    fGrandPercent = (double)fName.getNameTotal()/fGrandTot;
		    System.out.print("Total\n"+ fName.getName()+": "+fRank+" "+fName.getNameTotal()+" ");
		    System.out.printf("%.6f\n", fGrandPercent);
		    System.out.println();
		}
	    }

	    //males
	    // Loops that go through the array list of input names and the statistics array list of the input names	    // to compute the relevant information and print them out

	    double mPercent = 0.0; //male yearly percentage
	    
	    for(int m = 0; m < maleNames.size(); m++){
		Name mName = mList.searchName(maleNames.get(m));
		if(mName == null){ System.out.println("Name does not exist.");} //if Name does not exist
		else{
		    int position = mList.searchNode(mName); //alphabetical position
		    System.out.println(position);
		    System.out.println();
		    
		    int mRank = 0; //represents total rank of the name in all input files
		    mRank = mList.findRank(mName); //total rank
		    
		    //Go through the stats arraylist and compute yearly percentage and print relevant information
		    for(int k = 0; k < mName.getStats().size(); k++){
			if(years.get(k) == mName.getStats().get(k).getYear()){
			    mPercent = (double)mName.getStats().get(k).getNum()/fileTotalsM.get(k);
			}
			System.out.print(mName.getStats().get(k).getYear()+"\n"+mName.getName()
					 +": "+mName.getStats().get(k).getRank()+" "+mName.getStats().get(k).getNum()+" ");
			
			System.out.printf("%.6f\n", mPercent);
			System.out.println();
		    }
		    //Total percentage for each name for all the years in all input files
		    mGrandPercent = (double)mName.getNameTotal()/mGrandTot;
		    System.out.print("Total\n"+ mName.getName()+": "+mRank+" "+mName.getNameTotal()+" ");
		    System.out.printf("%.6f\n", mGrandPercent);
		    System.out.println();
		}
	    }
	}//try
	catch(FileNotFoundException e) {System.out.println("File not found.");}
    }
}
