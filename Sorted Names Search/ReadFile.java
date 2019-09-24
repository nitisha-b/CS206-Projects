/** Name:    Nitisha Bhandari
 * File:    ReadFile.java
 * Desc:
 *
 * The ReadFile() class for Assignment 3.
 *
 * This file contains a ReadFile method that takes in multiple parameters and
 * is designed to read through a file and update the male and female doubly 
 * linked lists and the ArrayLists for total babies of the file for the two 
 * genders and also updates an arraylist that keeps track of the years the
 * files are entered for.
 *
 */
import java.util.*;
import java.io.*;

public class ReadFile{
    public static final int RANKS = 0, MNAME = 1, MNUMBER = 2, FNAME = 3,  FNUMBER = 4;
    /** Reads one csv file at a time and stores and updates information in the male and female doubly linked lists 
     *  as it reads a file, and updates the lists as it is called multiple times in the main for multiple files    
     *  @param fileTotalsM  Empty arraylist to store the total male babies for each year
     *  @param filename The file the method is reading
     *  @param mList Empty doubly linked list for male names
     *  @param fList Empty doubly linked list for female names
     *  @param fileTotalsM  Empty arraylist to store the total male babies for each year
     *  @param fileTotalsF  Empty arraylist to store the total female babies for each year
     *  @param years Empty arraylist to store the information for the year for each file called
     * 
     */
    public static void readFiles(String filename, NameDLL mList, NameDLL fList,
				 ArrayList<Integer> fileTotalsM, ArrayList<Integer> fileTotalsF, ArrayList<Integer> years)
	throws FileNotFoundException{

	Scanner file = new Scanner(new File(filename));
	int total = 0;
	int mTotal = 0; int fTotal =0;
	int mNum = 0; int fNum = 0;
	int year = 0;
	
	while(file.hasNext()){
	    String line = file.nextLine();
	    String ranks, mName, mNumber, fName, fNumber, yearNum;

	    //split line to get the information from each line of the file
	    String[] tokens = line.split(",");

	    ranks = tokens[RANKS];
	    mName = tokens[MNAME];
	    mNumber = tokens[MNUMBER];
	    fName = tokens[FNAME];
	    fNumber = tokens[FNUMBER];

	    yearNum = filename.substring(filename.length()-8, filename.length()-4); //get year from the filename

	    //parse all the non integer variables to integers
	    year = Integer.parseInt(yearNum);
	    int rank = Integer.parseInt(ranks);
	    fNum = Integer.parseInt(fNumber);
	    mNum = Integer.parseInt(mNumber);
	    
	    //calculate the file totals
	    mTotal += mNum;
	    fTotal += fNum;

	    //check if female name exists
	    ArrayList<Info> fStatList = new ArrayList<> ();
	    Info fStat = new Info(year, fNum, rank);
	    fStatList.add(fStat);
	    Name newFName = new Name(fName, fStatList, total);
	    Name updateFemaleName = fList.searchName(fName);

	    if(updateFemaleName == null){
		newFName.setNameTotal(fNum);   //set the total name for all years
		fList.insertSorted(newFName); //add information for a new name to the linked list
	    }
	    else{ //if already exists
		updateFemaleName.getStats().add(fStat);  //add information for a new year to the array list
		updateFemaleName.setNameTotal(updateFemaleName.getNameTotal() + fNum); //add the total name for all years
	    }
	    
	    //check if male name exists
	    ArrayList<Info> mStatList = new ArrayList<> ();
	    Info mStat = new Info(year, mNum, rank);
	    mStatList.add(mStat);
	    Name newMName = new Name(mName, mStatList, total);
	    
	    Name updateMaleName = mList.searchName(mName); 
	    
	    if(updateMaleName == null){
		newMName.setNameTotal(mNum);   //set the total name for all years
		mList.insertSorted(newMName); //add information for a new name to the linked list
	    }
	    
	    else{ //if already exists
		updateMaleName.getStats().add(mStat);   //add information for a new year to the array list
		updateMaleName.setNameTotal(updateMaleName.getNameTotal() + mNum);  //add the total name for all years
	    }
	}//while-loop
	
	fileTotalsM.add(mTotal);
	fileTotalsF.add(fTotal);
	years.add(year);
    } //end of class readFiles()
}
