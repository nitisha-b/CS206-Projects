/** Name:    Nitisha Bhandari
 * File:    LocatedPlace.java
 * Desc:
 *
 * The LocatedPlace() class for Assignment 5.
 *
 * This class is a subclass of the Place class, and uses instance variables and
 * inherits other variables for latitude and longitude to create objects and
 * uses get and set methods to access and update their values
 *
 */
public class LocatedPlace extends Place{
    //longitude, latitude instance variables to store relevant information
    private double longitude, latitude;

    /**  Creates a LocatedPlace with the given zip, town name, state, latitude and
     *  longitude
     *  @param zip The 5-digit zip code from the super class- Place
     *  @param town The town name from the super class- Place
     *  @param state The state abbreviation from the super class- Place
     *  @param latitude The latitude of the location
     *  @param longitude The longitude of the location
     */
    public LocatedPlace(String zip, String town, String state, double latitude, double longitude){
	super(zip, town, state);
	this.latitude = latitude;
	this.longitude = longitude;
    }//LocatedPlace()

    //getters to get the values of instance variables
    public double getLatitude(){ return this.latitude; }
    public double getLongitude(){ return this.longitude; }

    //setters to update the values
    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}

    public String toString(){ //overridden toString
	String addLocation = super.toString()+" "+latitude+" "+longitude;
	return addLocation;
    }
}//end of class
