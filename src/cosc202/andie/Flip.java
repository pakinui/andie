package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip an image vertically or horizontally.
 * </p>
 * 
 * <p>
 * This operation flips the pixels(pixel by pixel) in an image either 
 * vertically or horizontally.
 * The direction is specified by the user.
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
     * Flip the image in the default direction(horizontal).
     * </p>
     * 
     * @see #Flip(char)
     */
    Flip(){
        direction = 'H';
    }

    /**
     * <p>
     * Flip the image in a given directino.
     * </p>
     * 
     * <p>
     * The direction is provided by the user.
     * 'H' for a Horizontal Flip.
     * 'C' for a Vertical Flip.
     * </p>
     * 
     * @param c The character representing the direction to flip.
     * @see #Flip()
     */
    Flip(char c){
        
        if(Character.toUpperCase(c) == 'H'){ 

            direction = 'H'; // if c is 'H' set it to the direction.
        }else if(Character.toUpperCase(c) == 'V'){
            direction = 'V'; // if c is 'V' set it to the direction.
        }else{
            direction = 'H'; // if there is an error set the direction th the default 'H'
            System.out.println("errorsss");
        }
        
    }

    /**
     * <p>
     * Apply a Flip to an image.
     * </p>
     * 
     * <p>
     * The direction the the flip is determined by whatever the {@link Flip}
     * method has initalised the char variable {@code direction} to.
     * The appropriate flip will be applied.
     * </p>
     * 
     * <p>
     * The nested for-loops loop through every row (height), but only half-way
     * though the columns(width).
     * This is to ensure that pixels are flipped around the half-way point.
     * </p>
     * 
     * <p>
     * If the nested for-loops looped through the whole column, then after the
     * half-way point the pixel values would be switched back to its original
     * spot.
     * This would result in the pixel values being changed (and then changed 
     * back to where they started) but the image would not look any different.
     * </p>
     * 
     * @param input The image to flip.
     * @return The resulting flipped image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {

        if(direction == 'H'){ // horizontal flip
            
            int pixel_hold; // temporary pixel
            int height = input.getHeight()-1;
            int width = input.getWidth()-1;

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

            //looping through every row and half way though columns
            for(int x = 0; x< height; x++){
                for(int y = 0; y< (width/2); y++){
                    int a = input.getRGB(x, y);//RGB value of pixel a
                    int b = input.getRGB(x, (width-y));// RGB value of pixel b

                    pixel_hold = a;// temporary element hold RGB value of pixel a
                    input.setRGB(x, y, b);//set a's values to b's values
                    input.setRGB(x, (width-y), pixel_hold);// set b's values to temporary element(a)'s values
                }
            }
        }
        return input;
    }

}

