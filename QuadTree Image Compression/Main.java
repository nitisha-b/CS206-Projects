/** Name:   Nigina Daniyarova, Nitisha Bhandari
 *  File:   Main.java
 *  Desc:
 *
 *  The Main driver program for Assignment 9.
 *
 *  This program takes in command line inputs and sets the variables to 
 *  call methods accordingly. It reads in an image and arranges the pixels 
 *  into a 2D array. Then it checks for whether or not the user 
 *  wants to compress the picture or perform edge detection on the picture. 
 *  It then calls the compression on quadtree with a the thresholds calculated
 *  to approximately match the compression levels described on the description, 
 *  and finally calls the method to print out the ppm image. 
 */

import java.util.*;
import java.io.*;

public class Main{
    public static final String INPUT = "-i", OUTPUT = "-o", COMPRESSION = "-c", EDGE = "-e", 
	OUTLINE = "-t", PPM = ".ppm"; 

    public static void main(String[]args)throws IOException{
	try{
	    String filename = "", outputFilename = ""; 
	    boolean edge = false, outline = false, compression = false; 

	    /* Goes through the command line arguments to check for what methods 
	       should be applied to the given picture */ 
	    for(int i = 0; i < args.length; i++) {
		if(args[i].equals(INPUT)) filename = args[i+1];

		if(args[i].equals(OUTPUT)) outputFilename = args[i+1];

		if(args[i].equals(COMPRESSION)) compression = true;

		if(args[i].equals(EDGE)) edge = true;

		if(args[i].equals(OUTLINE)) outline = true;
	    }

	    Pixel[][] inputArray = readImg(filename);

	    //If the picture needs to be compressed
	    if(compression) {
		//Uses thresholds calculated to approximately match the given compression levels
		Double[] thresholds = {800000.0, 300000.0, 36000.0, 7100.0, 1700.0, 200.0, 18.0, 3.0};

		for(int i = 1; i <= 8; i++) {
		    QuadTree compressTree = new QuadTree(inputArray, thresholds[i-1]);
		    Pixel[][] img1 = compressTree.getImg();
		    if(outline) {
			compressTree.draw(img1);
		    }

		    String count = Integer.toString(i);
		    String output = outputFilename + "-" + count + PPM; //final output filename

		    writeImg(output, img1);	
		}
	    }

	    //If edge detection 
	    else if(edge) {
		//Uses pre-defined threshold in case the user wants to see the quadtree outline
		QuadTree edgeTree = new QuadTree(inputArray, 100000); 
		Pixel[][] img2 = edgeTree.getEdgeDet();
		if(outline) {
		    edgeTree.draw(img2);
		}

		String output = outputFilename + PPM;
		writeImg(output, img2);
	    }
	}	
	catch(IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Takes in a .ppm file, extracts the numbers in the file into groups of 3, 
     * creates a new Pixel object with it and inserts that object into a 2D array
     * @param filename The name of the ppm file to be read into an array
     * @return A two-dimensional array containing the pixels of the given picture
     * @throws FileNotFoundException
     */
    public static Pixel[][] readImg(String filename)throws FileNotFoundException{
	Pixel[][] img = null;
	try {
	    Scanner file = new Scanner(new File(filename));

	    while (!file.hasNextInt()){  //skip the lines with picture informations
		file.next();
	    }

	    //dimensions of the picture
	    int cols = file.nextInt(); 
	    int rows = file.nextInt();
	    int max = file.nextInt();  //skip the max value for each color

	    img = new Pixel [cols][rows];
	    Pixel [][] output = new Pixel [cols][rows];
	    Pixel black = new Pixel(255, 255, 255);	
	    Pixel color = black;

	    for(int i = 0; i < cols; i++) {
		for(int j = 0; j < rows; j++) {
		    int red = file.nextInt();
		    int green = file.nextInt();
		    int blue = file.nextInt();

		    color = new Pixel(red, green, blue);
		    img[i][j] = color;
		}
	    }
	    file.close();
	}
	catch(FileNotFoundException e) {System.out.println("File not found.");}
	return img;
    }

    /**
     * Takes in the array of pixels to be printed into an image and prints
     * them out in a .ppm file format with the user-given output filename.
     * @param filename Name of the image to be print/output
     * @param img The array containing the pixels of the image to output
     * @throws IOException 
     */
    public static void writeImg(String filename, Pixel[][] img) throws IOException {
	try {
	    PrintWriter output = new PrintWriter(filename);
	    output.print("P3 ");
	    output.println(img[0].length+ " " + img.length + " 255")
		;

	    for(int i = 0; i<img.length; i++) {
		for(int j = 0; j<img[0].length; j++) {
		    output.print(img[i][j].getRed()+" "+img[i][j].getGreen()+" "+img[i][j].getBlue()+" ");
		}
		output.println();
	    }
	    output.close();
	}
	catch(IOException e) {System.out.println("Io exception");}
    }

}

