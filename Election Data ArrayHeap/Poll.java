/** Name:   Nitisha Bhandari
 *  File:    Poll.java
 *  Desc:
 *
 *  The Poll class for Assignment 7.
 *
 *  This class creates a Poll object with two Strings and a double as parameters,
 *  and stores relevant information for one candidate for all the files the user inputs
 *
 */
public class Poll implements Comparable<Poll>{
    private String lastName, fullName;
    private Double percent;  //percentage the candidate is polling at

    public Poll(String lastName, String fullName, Double percent){
	this.lastName = lastName;
	this.fullName = fullName;
	this.percent = percent;
    } 

    //getters to access the instance variables
    public String getLastName() { return lastName; }
    public String getFullName() { return fullName; }
    public Double getPercent() { return percent; }

    //setters to update the instance variables
    public void setLastName(String last){ lastName = last; }
    public void setFullName(String full){ fullName = full; }
    public void setPercent(Double percent){ percent = percent; }

    /** Overrides the compareTo method to sort the Poll objects by the candidates'
     *  percentage of votes by taking in a Poll object
     *  @param o The new Poll object to be inserted into the array heap
     *  @return -1, 1 or 0 if the new object is less than, greater than or equal
     *  to the current object respectively.
     */
    public int compareTo(Poll o){
	if(getPercent().compareTo(o.getPercent()) > 0) { return -1; }
	else if(getPercent().compareTo(o.getPercent()) < 0) { return 1; }
	else {
	    return 0;
	}
    }

    /** Overrides the equals method to check for the same candidate by comparing
     *  their last names
     *  @param o The object to be compare the current Poll with
     *  @return true if the last name of the object equals the current last name
     */
    public boolean equals(Object o){
	Poll name = (Poll)o;
	if(name.getLastName().equals(getLastName())){
	    return true;
	}
	else{
	    return false;
	}
    }

    public String toString(){
	return fullName + ":" + percent;
    }
}
