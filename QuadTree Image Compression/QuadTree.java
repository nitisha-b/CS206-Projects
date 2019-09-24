/** Name: Nitisha Bhandari & Nigina Daniyarova
 * File: QuadTree.java
 * Desc: Program consists of a QuadTree constructor that creates an array of pixels that 
 *   is used to eiher compress the image or detect the edges. It consists of a Node class with a 
 *   constructor and QuadTree that has 2 constructors.     
 */

import java.util.*;
import java.io.*;

public class QuadTree{
    private class Node{
	private int x, y;
	private int height, width;
	private Pixel pixel;
	private Node child[];

	/** Constructs a Node object that stores x and y coordinates of 
	 *  a pixel and the height and width of the pixture
	 */
	private Node(int x, int y, int height, int width){
	    this.x = x;
	    this.y = y;
	    this.height = height;
	    this.width = width;
	    child = new Node[4];
	}
	public int getX(){
	    return x;
	}
	public int getY(){
	    return y;
	}
	public int getHeight(){
	    return height;
	}
	public int getWidth(){
	    return width;
	}

	public boolean isLeaf() {
	    return (child[0] == null && child[1] == null && child[2] == null && child[3] == null);
	}

	public Node[] getChildren(){return child;}
	public void setPixel(Pixel p){pixel = p;}
	public Pixel getPixel() {
	    return pixel;
	}

	/** Creates a Pixel with average colors of the given area set by 
	 *  array of pixels of the image array, x and y coordinates, height and width  
	 * @param img Two dimentional array of pixels created by the ppm file
	 * @param x X coordinate of the pixel
	 * @param y Y coordinate of the pixel
	 * @param height The height of the image or the number of rows
	 * @param width The width of the image or the number of columns
	 * @return The pixel constructed by the average colors
	 */
	public Pixel average(Pixel[][]img, int x, int y, int height, int width) {
	    int sumRed = 0;
	    int sumBlue =0;
	    int sumGreen = 0;
	    double avgRed =0.0, avgBlue=0.0, avgGreen=0.0;

	    for(int i = x; i < x+height; i++) {
		for(int j = y; j < y+width; j++) {
		    pixel = img[j][i];
		    sumRed += pixel.getRed();
		    sumBlue += pixel.getBlue();
		    sumGreen += pixel.getGreen();
		}
	    }
	    avgRed = sumRed/(height*width);
	    avgGreen = sumGreen/(height*width);
	    avgBlue = sumBlue/(height*width);
	    Pixel avgColor = new Pixel((int)avgRed, (int)avgGreen, (int)avgBlue);
	    return avgColor;
	}

	/** Calculates a mean error of the required area in the image by using
	 * the calculated average of the same area  
	 * @param img Two dimensional array of pixels created by the ppm file
	 * @param x X coordinate of the pixel
	 * @param y Y coordinate of the pixel
	 * @param height The height of the image or the number of rows
	 * @param width The width of the image or the number of columns
	 * @return The error
	 */
	public double meanError(Pixel[][]img, int x, int y, int height, int width) {
	    double error = 0.0;
	    double errorRed = 0.0, errorBlue = 0.0, errorGreen = 0.0;
	    Pixel averageColor = average(img, x, y, width, height);

	    for(int i = x; i < x+height; i++) {
		for(int j = y; j < y+width; j++) {
		    errorRed += ((img[j][i].getRed() - averageColor.getRed())* (img[j][i].getRed() - averageColor.getRed()));
		    errorGreen += ((img[j][i].getGreen() - averageColor.getGreen())*(img[j][i].getGreen() - averageColor.getGreen()));
		    errorBlue += ((img[j][i].getBlue() - averageColor.getBlue())*(img[j][i].getBlue() - averageColor.getBlue()));
		}
	    }
	    error = errorRed + errorGreen + errorBlue;
	    return error;
	}
    }

    public Pixel[][]img;
    private Node root;
    private int height;
    private int width;
    private Pixel[][] newImg;
    private double compression;
    private int nodeCount; 
    private double threshold;
    private Pixel[][] edgeArray;

    /** Constructs a QuadTree object that stores the two dimensional array
     *  of pixels from the ppm file, and the threshold to that is used to calculate
     *  compression level 
     */
    public QuadTree(Pixel[][]img, double compression) {
	this.img = img;
	this.compression = compression;
	this.height = img.length;
	this.width = img[0].length;
	this.newImg = new Pixel[height][width];
	this.edgeArray = new Pixel[height][width];
	root = nodeRec(img, 0, 0, height, width);
    }

    public double getCompression(){
	return compression;
    }
    public int getNodeCount() { return nodeCount; }

    /** Calculates the compression levels by the formula
     *  compression level = number of leaves / total number of pixels
     *  @return the calculated compression level
     */
    public double getThreshold() { 
	threshold = (double)nodeCount/(this.height*this.width);
	return threshold;
    }

    /** Calculates a mean error of the required area in the image by using
     * the calculated average of the same area  
     * @param img Two dimensional array of pixels created by the ppm file
     * @param x X coordinate of the pixel
     * @param y Y coordinate of the pixel
     * @param height The height of the image or the number of rows
     * @param width The width of the image or the number of columns
     * @return The error
     */
    private Node nodeRec(Pixel[][]img, int x, int y, int height, int width) {
	Node current = new Node (x, y, height, width);
	Pixel p = current.average(img, x, y, height, width);

	if (height ==1 || width ==1){
	    current.setPixel(img[y][x]);
	}
	else {

	    // checks if the difference between current node and average is smaller than the threshold
	    if(current.meanError(img, x, y, height, width) < compression) {
		img[y][x].setRed(p.getRed());
		img[y][x].setGreen(p.getGreen());
		img[y][x].setBlue(p.getBlue());
	    }
	    else{
		//keep splitting height and width of the picture
		int height2 =(height)/2;
		int width2 = (width)/2;

		current.getChildren()[0] = nodeRec(img, x, y+width2,  height2, width - width2); //upper right
		current.getChildren()[1] = nodeRec(img, x, y, height2, width2); //upper left
		current.getChildren()[2] = nodeRec(img, x+height2, y,  height - height2, width2); //lower left
		current.getChildren()[3] = nodeRec(img, x+height2, y+width2,  height -height2, width -width2); //lower right
	    }
	}
	return current;
    }

    /** The helper method for the recursion to complete compression
     *  @param node the current node that is being used
     */
    private void edgeRec(Node node) {
	Pixel black = new Pixel(0, 0, 0);
	Pixel white = new Pixel(255, 255, 255);

	if(!node.isLeaf()) {
	    edgeRec(node.getChildren()[0]);
	    edgeRec(node.getChildren()[1]);
	    edgeRec(node.getChildren()[2]);
	    edgeRec(node.getChildren()[3]);
	}
	else {
	    //checks if the total of number of pixels is bigger than 6000
	    if(node.getHeight() * node.getWidth() > 6000) {
		for(int i = node.getX(); i < node.getX()+node.getHeight(); i++) {
		    for(int j = node.getY(); j < node.getY()+node.getWidth(); j++) {

			edgeArray[j][i] = black;
		    }
		}
	    }
	    else {
		edgeDet(node);
	    }
	}

    }

    public Pixel[][] getEdgeDet() { 
	edgeRec(this.root);
	return edgeArray;
    } 

    /** calls the helper method that outlines the picture 
     *  showing where the compression worked
     *  @param newImg array that will be used to draw the outline int
     */
    public void draw(Pixel[][] newImg) {
	drawHelper(this.root, newImg);
    }

    /** Helper recursive method to drow the lines that woudl show the compression
     * @param current The first node that method woudl use
     * @param newImg the array that will be used
     */
    private void drawHelper(Node current, Pixel[][] newImg) {
	if (!current.isLeaf()) {
	    drawHelper(current.getChildren()[0], newImg);
	    drawHelper(current.getChildren()[1], newImg);
	    drawHelper(current.getChildren()[2], newImg);
	    drawHelper(current.getChildren()[3], newImg);
	}
	else {
	    for (int i = current.getX(); i < (current.getX()+current.getHeight()); i++){
		for (int j = current.getY(); j < (current.getY()+current.getWidth()); j++){
		    if (i == current.getX() || i == current.getX() + current.getWidth() 
			|| j == current.getY() || j == current.getY() + current.getHeight()){
			newImg[j][i] = new Pixel(0,255,255);
		    }
		}
	    }
	}
    }

    /** Getter that returns an array of processed image
     * @return an Array of processed image
     */
    public Pixel[][] getImg(){
	writeImage();
	return newImg;
    }

    /** calls the helper method update to process the image
     * does not return 
     */
    private void writeImage(){
	update(this.root);
    }

    /** Helper recursive method to update the array that woudl show the compression
     * @param current The first node that method woudl use
     */
    private void update(Node current) {
	if (!current.isLeaf()){
	    update(current.getChildren()[0]);
	    update(current.getChildren()[1]);
	    update(current.getChildren()[2]);
	    update(current.getChildren()[3]);
	}
	else {
	    //counts the number of leafs
	    nodeCount++;
	    for (int i = current.getX(); i < (current.getX()+current.getHeight()); i++){
		for (int j = current.getY(); j < (current.getY()+current.getWidth()); j++){
		    newImg[j][i] = current.getPixel();
		}
	    }
	}
    }

    /** Checks the difference between the current node and the 8 surrounding 
     * it nodes and sets the color by calcuating it using a certain formula (kernel) 
     * @param node The current node
     */
    private void edgeDet(Node node) {
	int edgeRed = 0, edgeGreen = 0, edgeBlue = 0;
	int x = node.getX();
	int y = node.getY();
	int height = node.getHeight();
	int width = node.getWidth();

	for(int j = x; j < x+height; j++) {
	    for(int i = y; i < y+width; i++) {

		int i1 =i-1; int i2 = i+1; 
		int j1 = j-1; int j2 = j+1;
		//checks for the edges of the image
		if (i1 < 0) i1 = 0;
		if (j1 < 0) j1 = 0;
		if (i2 >= img.length) i2 = img.length -1;
		if (j2 >= img[0].length) j2 = img[0].length-1;

		edgeRed = (-img[i1][j1].getRed()-
			   img[i1][j].getRed()-
			   img[i1][j2].getRed()-
			   img[i][j1].getRed()
			   +8*img[i][j].getRed()
			   -img[i][j2].getRed()
			   -img[i2][j1].getRed()
			   -img[i2][j].getRed()
			   -img[i2][j2].getRed());

		//sets a color to 0 if the answer is negative
		if(edgeRed < 0) edgeRed = 0;

		//sets a color to 0 if the answer is out of the range
		if(edgeRed > 255) edgeRed = 255;
		edgeGreen = (-img[i1][j1].getGreen()-
			     img[i1][j].getGreen()-
			     img[i1][j2].getGreen()-
			     img[i][j1].getGreen()
			     +8*img[i][j].getGreen()
			     -img[i][j2].getGreen()
			     -img[i2][j1].getGreen()
			     -img[i2][j].getGreen()
			     -img[i2][j2].getGreen());
		if(edgeGreen < 0) edgeGreen = 0;
		if(edgeGreen > 255) edgeGreen = 255;

		edgeBlue =(-img[i1][j1].getBlue()-
			   img[i1][j].getBlue()-
			   img[i1][j2].getBlue()-
			   img[i][j1].getBlue()
			   +8*img[i][j].getBlue()
			   -img[i][j2].getBlue()
			   -img[i2][j1].getBlue()
			   -img[i2][j].getBlue()
			   -img[i2][j2].getBlue());
		if(edgeBlue < 0) edgeBlue = 0; 
		if(edgeBlue > 255) edgeBlue = 255;
		edgeArray[i][j] = new Pixel(edgeRed, edgeGreen, edgeBlue);
	    }
	}
    }
}
