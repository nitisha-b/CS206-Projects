/* Name:    Nitisha Bhandari
 * File:    Name.java
 * Desc:
 *
 * The Name() class for Assignment 3.
 *
 * This class creates a name object with a String, an ArrayList, and an 
 * integer as parameters, and contains relevant information for one name 
 * for all the files the user inputs
 *
 */
import java.util.*;

public class Name{
    // Instance variables to store the name, total for the name in all the
    // given files, and an ArrayList of statistic for each name
    private String name;
    private int nameTotal;
    private ArrayList<Info> stats;
    
    /** Creates a Name with a name, ArrayList of statistics, and total 
     *  @param name Each name in the given files
     *  @param stats Information for each name in all the given years
     *  @param nameTotal Total count of the name in all the given files
     */
    public Name(String name, ArrayList<Info> stats, int nameTotal){
	this.name = name;
	this.stats = stats;
	this.nameTotal = nameTotal;
    }
    
    //getters to get the values of instance variables
    public String getName(){ return this.name; }

    public ArrayList<Info> getStats() {return this.stats; }
    
    public int getNameTotal() { return this.nameTotal;}

    //setters to update the values
    public void setName(String n){ name = n;}

    public void setNameTotal(int t) {nameTotal = t;}

    public String toString(){
	return name+" "+stats+" "+nameTotal;
    }
}
