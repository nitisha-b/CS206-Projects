/** Name:    Nitisha Bhandari
 * File:    LookupZip.java
 * Desc:
 *
 * The LookupZip() class for Assignment 5.
 *
 * This program contains different classes to read two csv files, parse every line
 * from the files, create an ArrayList of either a Place, PopulatedPlace or
 * LocatedPlace object, depending on the available information for a
 * particular zipcode, and finally looks through the zipcode using a binary search
 * the user enters and returns the information for that zipcode
 *
 */
import java.util.*;
import java.io.*;

public class LookupZip {
    public static final int ZIP = 0, TOWN = 1, STATE = 2, POP = 3, LAT = 5, LONG = 6;

    /** Parses one line of input(from the csv file) by creating a Place that
     *  denotes the information in the given line
     *  @param lineNumber The line number of this line
     *  @param line One line from the zip codes file
     *  @return Either a Place or a PopulatedPlace object that contains the relevant
     *  information from that line
     */
    public static Place parseCodeLine(int lineNumber, String line){
	String zip, town, state, population;
	double latitude = 0.0, longitude = 0.0;

	String[] fields = line.split(",", -1);
	zip = fields[ZIP];
	town = fields[TOWN];
	state = fields[STATE];
	population = fields[POP];

	if (!population.equals("")){ //check if the line has Population
	    int pop = Integer.parseInt(population);
	    PopulatedPlace popPlace = new PopulatedPlace(zip, town, state, latitude,
							 longitude, pop);
	    return popPlace;
	}

	Place placeEntries = new Place(zip, town, state);
	return placeEntries;
    }//end of method parseCodeLine()

    /**  Parses one line of input(from the ziplocs.csv file) and either updates a
     *  PopulatedPlace or creates a new LocatedPlace object
     *  @param secFileLine One line from the locations file(ziplocs.csv)
     *  @param places ArrayList of either Place or PopulatedPlace objects that
     *  parseCodeLine() returns
     */
    public static void updateObjects(String secFileLine, ArrayList<Place> places){
	String secondZip, latitude, longitude;

	String[] tokens = secFileLine.split(",");
	secondZip = tokens[ZIP];
	secondZip = secondZip.replaceAll("\"", "");

	latitude = tokens[LAT];
	longitude = tokens[LONG];

	if(!latitude.equals("") && !longitude.equals("")){
	    double lat = Double.parseDouble(latitude);
	    double longt = Double.parseDouble(longitude);

	    Place foundPlace = lookupZip(places, secondZip);

	    if(foundPlace == null){ return; }

	    else{
		if(foundPlace instanceof PopulatedPlace){
		    PopulatedPlace foundPop = (PopulatedPlace)foundPlace;
		    foundPop.setLatitude(lat);
		    foundPop.setLongitude(longt);
		}
		else {
		    LocatedPlace foundLoc = new LocatedPlace(secondZip, foundPlace.getTown(),
							     foundPlace.getState(), lat, longt);
		    //System.out.println(foundLoc);
		    int j = places.indexOf(foundPlace);
		    places.set(j, foundLoc);
		}
	    }
	}
    }//end of method

    /** Reads two files, and parses every line, and changes and updates the object
     *  types
     *  @param zipFilename The name of the zipcodes file
     *  @param locFileName The name of the location file
     *  @return A Place ArrayList containing either a Place, PopulatedPlace object,
     *  or LocatedPlace object
     */
    public static ArrayList<Place> readZipCodes(String zipFileName, String locFileName)
	throws FileNotFoundException {

	//First file - uszipcodes.csv
	Scanner readFile = new Scanner(new File (zipFileName));
	// read the first line to skip it in the loop
	String readLine = readFile.nextLine();

	//create an ArrayList to hold information from each line
	ArrayList<Place> places = new ArrayList<Place>();
	int lineNumber = 0;

	while(readFile.hasNext()){
	    String line = readFile.nextLine(); //read the datafile from line 2
	    //call parseCodeLine() and add the objects to places
	    places.add(parseCodeLine(lineNumber, line));
	    lineNumber++;
	}

	//Second File - ziplocs.csv
	Scanner readLocationFile = new Scanner(new File (locFileName));
	String LocFile = readLocationFile.nextLine();

	int i = 0; //index counter for places
	//Place parseLineOutput;
	while(readLocationFile.hasNext()){
	    String secFileLine = readLocationFile.nextLine();
	    updateObjects(secFileLine, places);
	    i++;
	}
	return places;
    }//end of method readZipCodes()

    /** Find a Place that corresponds to a given zip code
     *  @param zip The user-input zip code, as a String, to look up
     *  @param places A Place ArrayList that contains either a Place, PopulatedPlace,
     *  or LocatedPlace object
     *  @return A Place, PopulatedPlace,or LocatedPlace objectthat matches the
     *  given zip code, or null if no such place exists.
     */
    public static Place lookupZip(ArrayList<Place> places, String zip){
	Place zipCodeExists = null;
	Place input = new Place(zip, "", "");
	int low = 0;
	int high = places.size();
	int mid = 0;

	//Binary search to find the input zipcode
	while(high >= 0 && low < places.size()){
	    mid = (low + high)/2;
	    if(mid == low || mid == high){
		return zipCodeExists;
	    }
	    if(places.get(mid).compareTo(input) < 0){
		low = mid + 1;
	    }
	    else if(places.get(mid).compareTo(input) > 0){
		high = mid - 1;
	    }
	    else {
		zipCodeExists = places.get(mid);
		return zipCodeExists;
	    }
	}
	return zipCodeExists;   //returns null if the zipcode does not exist
    }//end of method lookupzip()
}//end of class
