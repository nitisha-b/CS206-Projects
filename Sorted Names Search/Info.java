/* Name:    Nitisha Bhandari
 * File:    Info.java
 * Desc:
 *
 * The Info() class for Assignment 3.
 *
 * This class creates an object that contains the year, yearly number and rank for each name, for
 * different years, and can be used to access the data in a different class through the name object.
 *
 */
public class Info{
    // Instance variables to store the year, total for the name for 1 year,
    // and rank for a name for one year
    private int year, num, rank;
    private double percentage;
    
    /** Creates an Info with a year, yearly number for a name, and yearly rank 
     *  @param year List of  year for each name for given files
     *  @param num List of yearly numbers for each name for given files
     *  @param rank List of rank for each name in all files
     */
    public Info(int year, int num, int rank){
	this.year = year;
	this.num = num;
	this.rank = rank;
    }

    //getters to get the values of instance variables
    public int getYear(){ return this.year; }

    public int getNum(){ return this.num; }

    public int getRank() { return this.rank; }

    //setters to update the values
    public void setYear(int y){ year = y; }

    public void setRank(int r) {rank = r;}

    public void setNum(int n){ num = n; }

    public String toString(){
	return year+" "+num+" "+rank;
    }
}
