package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.*;
/**
 * <p>
 * ImageOperation to flip an image vertically or horizontally.
 * </p>
 * 
 * <p>
 * This operation flips the pixels from one end of a row (horizontal flip)
 * or column (vertical flip) with one from the other end. This results in an
 * image flipped either vertically or horizontally.
 * The direction is specified by the user and initalised in the constructor.
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 */
public class Flip implements ImageOperation, java.io.Serializable{

    /**
     * A char to represent the direction to flip.
     * 'H' = Horizontal Flip.
     * 'V' = Vertical Flip.
     */
    char direction;

    /**
     * <p>
     * Flip the image in the default {@code direction} (horizontal).
     * </p>
     * 
     * @see #Flip(char)
     */
    Flip(){
        try{
        direction = 'H';
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please open Image first");
        }
    }

    /**
     * <p>
     * Flip the image in a given direction.
     * </p>
     * 
     * <p>
     * The {@code direction} is provided by the user.
     * </p>
     * <ul>
     * <li>'H' for a Horizontal Flip.</li>
     * <li>'C' for a Vertical Flip.</li>
     * </ul>
     * 
     * @param c The character representing the {@code direction} to flip.
     * @see #Flip()
     */
    Flip(char c){
    try{  
        if(Character.toUpperCase(c) == 'H'){ 
            direction = 'H'; // if c is 'H' set it to the direction.
        }else if(Character.toUpperCase(c) == 'V'){
            direction = 'V'; // if c is 'V' set it to the direction.
        }else{
            direction = 'H'; // if there is an error set the direction the the default 'H'
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Please open Image first");
    }
    }

    /**
     * <p>
     * Flips an image.
     * </p>
     * 
     * <p>
     * The {@code direction} of the flip is determined by the constructors 
     * and the appropriate flip will be applied.
     * </p>
     * 
     * <p>
     * For a horizontal flip the nested for-loops, loop through every row 
     * (height), but only half-way though the columns(width). For a vertical
     * flip the nested for-loops, loop through every column(width), but only
     * half-way through the rows(height).
     * This is to ensure that pixels are flipped around the half-way point.
     * </p>
     * 
     * <p>
     * If the nested for-loops looped through the whole column and row, then 
     * after the half-way point the pixel values would be switched back to its
     * original spot.
     * This would result in the pixel values being changed (and then changed 
     * back to where they started) but the image would not look any different.
     * </p>
     * 
     * @param input The image to flip.
     * @return The resulting flipped image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
    try{
        if(direction == 'H'){ // horizontal flip
            
            int pixel_hold; // temporary pixel
            int height = input.getHeight()-1; //number of pixels tall
            int width = input.getWidth()-1; //number of pixels wide

            //looping through every row and half way though columns
            for(int y = 0; y< height; y++){
                for(int x = 0; x< (width/2); x++){
                    int a = input.getRGB(x, y);  //RGB value of pixel a
                    int b = input.getRGB((width-x), y); // RGB value of pixel b

                    pixel_hold = a; // temporary element hold RGB value of pixel a
                    input.setRGB(x, y, b); //set a's values to b's values
                    input.setRGB((width-x), y , pixel_hold);// set b's values to temporary element(a)'s values
                }
            }
        }else if(direction == 'V'){ //vertical flip
 
            int pixel_hold; // temporary pixel
            int height = input.getHeight()-1;
            int width = input.getWidth()-1;

            //looping through every column and half way though rows
            for(int y = 0; y < (height/2); y++){
                for(int x = 0; x < width; x++){
                    int a = input.getRGB(x, y);//RGB value of pixel a
                    int b = input.getRGB(x, (height-y));// RGB value of pixel b

                    pixel_hold = a;// temporary element hold RGB value of pixel a
                    input.setRGB(x, y, b);//set a's values to b's values
                    input.setRGB(x, (height-y), pixel_hold);// set b's values to temporary element(a)'s values
                }
            }
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Please open Image first");
    }
        return input;
    }

}

