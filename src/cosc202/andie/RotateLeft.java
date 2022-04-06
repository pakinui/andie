package cosc202.andie;

import java.awt.image.*;

public class RotateLeft implements ImageOperation, java.io.Serializable {

    RotateLeft(){

    }
    /**
     * Method to roate image 90 degress Left
     * @param image being rotated
     * @return rotated image
     */
    @Override
    public BufferedImage apply(BufferedImage input){ 
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
}
