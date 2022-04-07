package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to change the brightness and contrast of an image.
 * </p>
 * 
 * <p>
 * A colour value filter to change the brightness and/or contrast by changing each pixel's
 * RGB values based of an equation provided in the lab book on
 * BlackBoard (page 99).
 * </p>
 * 
 * <p>
 * <a href= "https://cosc202.cspages.otago.ac.nz/lab-book/COSC202LabBook.pdf">COSC202 LabBook</a>
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 * @see #rgbMath(int)
 */
 public class BrightnessContrast implements ImageOperation, java.io.Serializable {

    /**
    * The percentage (-25%, 0% or +25%) of brightness/contrast to
    * apply to the image.
    */
    int contrast;
    int brightness; 

  
    
    /**
     * <p>
     * Change the brightness and/or contrast values of an image.
     * </p>
     * 
     * <p>
     * The percentage to change the brightness can be either positive
     * or negative making the image brighter or darker, respectively.
     * The percentage to change the contrast can be either positive 
     * or negative making the image more saturated or less saturated,
     * respectively.
     * The values may also be a 0 in which case the brightness and/or
     * contrast levels do not change.
     * </p>
     * 
     * <p>
     * There are three percentage options:
     * 1. -25% = (int) -25
     * 2. 0% = (int) 0
     * 3. +25% = (int) 25
     * </p>
     * 
     * @param b The brightness percentage change to apply to the image.
     * @param c The contrast percentage change to apply to the image.
     */
    BrightnessContrast(int b, int c){
        brightness = b;
        contrast = c;
    }

    /**
     * <p>
     * Default constructor for changing the brightness and contrast of
     * an image.
     * It is is set to 0 for both brightness and contrast.
     * </p>
     * 
     * <p>
     * By default the image remains the same.
     * </p>
     * 
     * @see BrightnessContrast(int, int)
     */
    BrightnessContrast(){
        brightness = 0;
        contrast = 0;
    }

        /**
    * <p>
    * This method applies the percentage of {@code brightness} and {@code contrast}
    * initalised in the contructor ({@code BrigtnessUp}).
    * 
    * @param input the image to be brightned
    * @return resulting brighter image
    * @see #rgbMath(int)
    * @see #inside(int)
    */

    /**
     * <p>
     * Apply a brightness/contrast change to an image.
     * </p>
     * 
     * <p>
     * It apllies the percentage of {@code brightness} and {@code contrast}
     * initalised in the constructor({@code BrightnessContrast}) to the image.
     * </p>
     * 
     * <p>
     * It goes though and changes the RGB values of each pixel separately.
     * It then changes the colour or each pixel according to the new RGB values.
     * </p>
     * 
     * @param input The image to have its colour values changed.
     * @return The resulting (changed) image.
     */
    public BufferedImage apply(BufferedImage input){
          
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                //colour of the current pixel
                Color colour = new Color(input.getRGB(x,y));

                //alpha, red, green and blue values from the pixel colour(colour)
                int a = colour.getAlpha(); //alpha value remains the same
                int r = colour.getRed();
                int g = colour.getGreen();
                int b = colour.getBlue();
                
                //The new values for the RGB colour values via the rgbMath() method
                //adjusted for brightness and contrast
                int newR = rgbMath(r);
                int newG = rgbMath(g);
                int newB = rgbMath(b);

                //New colour from calculated values (and alpha which remains the same)
                Color newARGB = new Color(newR,newG,newB,a);

                //Get the integer value for the new colour and change this pixel 
                //to that colour
                int finalColour = newARGB.getRGB();
                input.setRGB(x, y, finalColour);
            }
        }
        return input;
    }

    /**
    * <p>
    * A method to calculate the new R,G or B values which need to change 
    * based on the {@code brightness} and {@code contrast} values 
    * initialised in the constructor ({@code BrightnessContrast(int, int)}).
    * </p>
    *
    *<p>
    * See the <a href="https://cosc202.cspages.otago.ac.nz/lab-book/COSC202LabBook.pdf">LabBook</a>
    * (page 99) for the equation used.
    *</p>
    *
    * <p>
    * Uses a method called {@link inside(int)} to make sure that the value
    * remains in the valid range of 0-255.
    * </p>
    *
    * @param v The RGB value to be changed.
    * @return The new changed RBG value.
    * @see #inside(int)
    */
    public int rgbMath( int v){
        
        //using the equation provided in the lab book
        int fin = (int)Math.round(((1+(contrast/100.0)) * (v-127.5)) + (127.5 * (1+(brightness/100.0)))); 
        fin = inside(fin);

        return fin;
    }
      
    /**
     * <p>
     * A method to check if the RGB value lies in the appropriate range of
     * 0-255.
     * </p>
     * 
     * <p>
     * If the value is less than 0, the value gets changed to 0.
     * If the value if more than 255, the value gets changed to 255.
     * </p>
     * 
     * @param x Integer to check is in range.
     * @return Checked integer between 0 and 255.
     * @see #rgbMath(int)
     */
    public int inside(int x){
        if(x<0) x =0;
        if(x>255) x =255;

        return x;
    }

 }

