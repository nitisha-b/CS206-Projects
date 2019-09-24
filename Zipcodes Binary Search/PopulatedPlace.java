/** Name:    Nitisha Bhandari
 * File:    PopulatedPlace.java
 * Desc:
 *
 * The PopulatedPlace() class for Assignment 5.
 *
 * This class is a subclass of the LocatedPlace class, and uses an instance
 * variable, inherits other variables to create objects and uses get and set
 * methods to access and update its value
 *
 */
public class PopulatedPlace extends LocatedPlace{
    private int population;

    /**  Creates a PopulatedPlace with the given zip, town name, state, latitude,
     *  longitude, and population
     *  @param zip The 5-digit zip code from the super class- LocatedPlace
     *  @param town The town name from the super class- LocatedPlace
     *  @param state The state abbreviation from the super class- LocatedPlace
     *  @param latitude The latitude from the super class- LocatedPlace
     *  @param longitude The longitude from the super class- LocatedPlace
     *  @param population Population of the town
     */
    public PopulatedPlace(String zip, String town, String state, double latitude,
			  double longitude, int population){
	super(zip, town, state, latitude, longitude);
	this.population = population;
    }//PopulatedPlace()

    //getter to get the value of instance variable
    public int getPopulation(){ return this.population; }

    //setter to update the value
    public void setPopulation(int population) { this.population = population; }

    public String toString(){ //overridden toString
	String addPopulation = super.toString()+" "+population;
	return addPopulation;
    }
}//end of class
