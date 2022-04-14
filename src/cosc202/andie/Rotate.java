package cosc202.andie;

import java.awt.image.BufferedImage;

/**
     * Rotate operation allows user to rotate the image 90, 180 or 270 degrees 
     * determined by the number they select (integer {@code direction}) in the transform menu. This class uses
     *  {@link ImageOperation} to apply the operation to the input image. 
     * @param input image being rotated
     * @author  Poppy
     * @return rotated image
     */


public class Rotate implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * A char to represent the input which determines the rotate being applied
     * <p>
     * <ul>
     * <li>'1' for 90 degree right</li>
     * <li>'2' for 90 degree left</li>
     * <li>'3' for 180 degree</li>
     * </ul>
     */
    char direction;

     /**
     * <p>
     * Rotate the image in the default(Right)
     * </p>
     */
    Rotate(){
        direction = '1';
    }

    /**
     * <p>
     * Rotate the image in a given direction
     * </p>
     * 
     * <p>
     * The {@code direction} is provided by the user.
     * </p>
     * <ul>
     * <li>'1' for 90 degree right</li>
     * <li>'2' for 90 degree left</li>
     * <li>'3' for 180 degree</li>
     * </ul>
     * 
     * @param c the char representng the rotate direction being used
     */
    Rotate(char c){

        if(Character.compare(c, '1') == 0){
            direction = '1';
        }else if(Character.compare(c, '2') == 0){
            direction = '2';
        }else if(Character.compare(c, '3') == 0){
            direction = '3';
        }else{
            // make this do something
            System.out.println("error2");
        }
    }

     /**
     * <p>
     * Rotates an image.
     * The {@code direction} of the rotate is determined by the constructors 
     * and the appropriate rotate will be applied.
     * </p>
     */

    @Override
    public BufferedImage apply(BufferedImage input) {
        
        if(direction == '1')  return RotateRight(input);
        if(direction == '2')  return RotateLeft(input);
        if(direction == '3')  return RotateFull(input);
        
        return input;
    }

    /**
     * <p>
     * Method to rotate an image 180 degrees
     * A nested loop goes through the top half of the image switching the pixels from the other corner of the image
     * The result is the returned image looking as it has being rotated 180 degrees
     * </p>
     * 
     * @param input The image being roated
     * @return input the rotated image
     * @throws NullPointerException if input is null
     */

    BufferedImage RotateFull(BufferedImage input){

        int pixel;
        int height = input.getHeight()-1;
        int width = input.getWidth()-1;

        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y < height; y++){
            int a = input.getRGB(x,y);
            int b = input.getRGB(width-x, height-y);

            pixel = a;
            input.setRGB(x, y, b);
            input.setRGB(width-x, height-y, pixel);
            }
        }
        return input;

    }

    /**
     * <p>
     * Method to rotate an image 90 degrees to the left
     * A nested loop goes with half the width and height spilts the image into 4 sections
     * The loop then moves one pixel from each of these sections into the one on the left of it
     * The result is returned the image looking as it has being rotated 90 degrees to the left
     * </p>
     * 
     * @param input The image being roated
     * @return input the rotated image
     * @throws NullPointerException if input is null
     */
    BufferedImage RotateLeft(BufferedImage input){

        int pixel;
        int pixel2;
        int height = input.getWidth()-1;
        int width = input.getHeight()-1;

        //Same as RotateRight but locations are reversed
        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y <= (height/2); y++){
            
            int a = input.getRGB(y,x);//Top left
            int b = input.getRGB(x, width-y);//Bottom left
            int c = input.getRGB(height-y, width-x);//Bottom right
            int d = input.getRGB(height-x, y);//Top right

            pixel = b;
            // pixel a moved
            input.setRGB(x, width-y, a);

            pixel2 = c;
            // pixel b moved
            input.setRGB(height-y, width-x, pixel);

           
            pixel = d;
            // pixel c moved
            input.setRGB(height-x, y, pixel2);
            
            //pixel d moved
            input.setRGB(y,x, pixel);
            }
        }
        return input;

    }


    /**
     * <p>
     * Method to rotate an image 90 degrees to the right
     * A nested loop goes with half the width and height spilts the image into 4 sections
     * The loop then moves one pixel from each of these sections into the one on the right of it
     * The result is returned the image looking as it has being rotated 90 degrees to the right
     * </p>
     * 
     * @param input The image being roated
     * @return input the rotated image
     * @throws NullPointerException if input is null
     */
    BufferedImage RotateRight(BufferedImage input){

        int pixel;
        int pixel2;
        int height = input.getWidth()-1;
        int width = input.getHeight()-1;

        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y <= (height/2); y++){
            
            int a = input.getRGB(x, y);//Top Left
            int b = input.getRGB(width-y, x);//Top Right
            int c = input.getRGB(width-x, height-y);//Bottom Right
            int d = input.getRGB(y, height-x);//Bottom Left

            pixel = b;// Save pixel b
            // pixel a moved to pixel b location
            input.setRGB(width-y, x, a);

            pixel2 = c;// save pixel c
            // pixel b moved to pixel c location
            input.setRGB(width-x, height-y, pixel);

           
            pixel = d;// save pixel d
            // pixel c moved to pixel d location
            input.setRGB(y, height-x, pixel2);
            
            //pixel d moved to pixel a loaction
            input.setRGB(x,y, pixel);
            }
        }
        return input;

    }


    
}

