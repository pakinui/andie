package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.*;

/**
 * <p>
 * A class to change the brightness and/or contrast of an image in ANDIE.
 * </p>
 * //finish JavaDoc comments
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 */
 public class BrightnessUp implements ImageOperation, java.io.Serializable {

    int contrast;
    int brightness; 
    
     /**
      * constructor for BrightnessUp class
      * @param bright1
      * @param cont1
      */
      BrightnessUp(int b, int c){
        brightness = b;
        contrast = c;
    
       // System.out.println("brightness percentage: " + brightness);
       // System.out.println("Contrast percentage: " + contrast);
      }

      BrightnessUp(){
          
      }

      /**
       * makes the image light by raising RGB value of pixel by 10?
       * @param input the image to be brightned
       * @return resulting brighter image
       */
      public BufferedImage apply(BufferedImage input){
          
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                //colour of the current pixel
                Color colour = new Color(input.getRGB(x,y));

                //alpha, red, green and blue values from the pixel colour(colour)
                int a = colour.getAlpha();
                int r = colour.getRed();
                int g = colour.getGreen();
                int b = colour.getBlue();
                
                //the new values for these colour values via the rgbMath() method
                //adjusted for brightness and contrast
                int newR = rgbMath(r);
                int newG = rgbMath(g);
                int newB = rgbMath(b);

                //new colour from calculated values (and alpha which remains the same)
                Color newARGB = new Color(newR,newG,newB,a);

                //get the integer value for the new colour and change this pixel to that colour
                int finalColour = newARGB.getRGB();
                input.setRGB(x, y, finalColour);
            }
        }
        
        return input;
    }

   /**
    * a method to calculate the new R,G or B value for the brightness and contrast
    * variables initalised in the constructor
    * @param v value to be changed
    * @return changed value
    */
    public int rgbMath( int v){
        
        //using the equation provided in the lab book
        int fin = (int)Math.round(((1+(contrast/100.0)) * (v-127.5)) + (127.5 * (1+(brightness/100.0)))); 
        fin = inside(fin);

        return fin;
    }
      
    /**
     * a method to test if an integer is between 0 and 255
     * if x<0 x becomes 0 and if x>255 it becomes 255
     * @param x integer to check
     * @return checked integer between 0 and 255
     */
    public int inside(int x){
        if(x<0) x =0;
        if(x>255) x =255;

        return x;
    }

 }
