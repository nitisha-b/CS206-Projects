/** Name:    Nitisha Bhandari
 * File:    Place.java
 * Desc:
 *
 * The Place() class for Assignment 5.
 *
 * This class uses an instance variables to create Place objects with zip code,
 * town and state,and uses get and set methods to access and update their values
 * It is a super class of LocatedPlace() and PopulatedPlace() classes. 
 *
 */
public class Place implements Comparable{
    // Instance variables to store the zip code, town, and state, latitude,
    // longitude, and population
    private String zip, town, state;

    /** Creates a Place with the given zip, town name, and state
     *  @param zip The 5-digit zip code
     *  @param town The town name
     *  @param state The state abbreviation
     */
    public Place(String zip, String town, String state){
	this.zip = zip;
	this.town = town;
	this.state = state;
    }//Place()

    //getters to get the values of instance variables
    public String getZip(){ return zip; }
    public String getTown(){ return town; }
    public String getState(){ return state; }

    //setters to update the values
    public void setZip(String zip) {this.zip = zip;}
    public void setTown(String town) {this.town = town;}
    public void setState(String state) {this.state = state;}

    /** Compares zip codes of two place objects
     *  @param e The Place object with the zipcode to compare 
     *  @return An integer, 1 if the current zip is greater than the entered zip,
     *  -1 if smaller and 0 if equal
     */
    public int compareTo(Object e){
	Place userZip = (Place)e;
	int r = getZip().compareTo(userZip.getZip());
	return r;
    }

    public String toString(){
	return town + ", " + state;
    }
}// end of class
