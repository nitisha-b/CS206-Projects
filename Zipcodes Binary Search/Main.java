/** Name:    Nitisha Bhandari
 * File:    Main.java
 * Desc:
 *
 * The main driver program for Assignment 5.
 *
 * This program takes in a zip code from the user, searches the file
 * uszipcodes.csv and ziplocs.csv for all the zip codes in the United States,
 * and prints out the town, state, latitude, longitude and population for the
 * zip code in the format specified in the project description.
 *
 */
import java.util.*;
import java.io.*;

public class Main{
    public static final String ZEROS = "00000";

    public static void main(String[] args){
	try
	    {
		String zipFile = "uszipcodes.csv";
		String locationFile = "ziplocs.csv";
		ArrayList<Place> places = LookupZip.readZipCodes(zipFile, locationFile); //call readZipCodes()
		String userInput;

		Scanner input = new Scanner(System.in); //Standard input method

		do{
		    System.out.print("\nzipcode: ");
		    userInput = input.nextLine(); //Zipcode input

		    if(userInput.equals(ZEROS))
			break; //break out of the loop if the input is "00000"

		    Place finalOutput = LookupZip.lookupZip(places, userInput);
		    if(finalOutput == null){
			System.out.println("No such zipcode");
		    }
		    else {
			System.out.println(finalOutput);
		    }
		    //repeat until user enters "00000"
		} while(true);
		System.out.println("Good Bye!");

	    }
	catch(FileNotFoundException e)
	    {
		System.out.println("File does not exist.");
	    }
    }//main()
}//end of Main()
