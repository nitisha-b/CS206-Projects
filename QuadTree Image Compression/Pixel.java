/** Name: Nitisha Bhandari & Nigina Daniyarova
 * File: Pixel.java
 * Desc: Class, constructor, getters and toString methods
 * for a Pixel object with red, green, blue colors.
 *
 */

public class Pixel {
    private int red, green, blue;

    /** Constructs a Pixel with red, green and blue colors
     */
    public Pixel(int red, int green, int blue) {
	this.red = red;
	this.green = green;
	this.blue = blue;
    }

    public int getRed() {return red;}
    public int getGreen() {return green;}
    public int getBlue() {return blue;}

    public void setRed(int r) { red = r; }
    public void setGreen(int g) { green = g;}
    public void setBlue(int b) { blue = b; }

    public String toString() {
	return "(" + red + ", " + green + ", "+ blue + ")";
    }
}
