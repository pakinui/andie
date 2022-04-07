package cosc202.andie;

import java.awt.image.*;

public class RotateRight implements ImageOperation, java.io.Serializable {

    RotateRight(){

    }
    /**
     * Method to roate image 90 degress Right
     * @param image being rotated
     * @return rotated image
     */
    @Override
    public BufferedImage apply(BufferedImage input){ 
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