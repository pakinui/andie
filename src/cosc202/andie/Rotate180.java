package cosc202.andie;

import java.awt.image.*;

public class Rotate180 implements ImageOperation, java.io.Serializable {

    Rotate180(){

    }
    /**
     * Method to roate image 180 degress
     * @param image being rotated
     * @return rotated image
     */
    @Override
    public BufferedImage apply(BufferedImage input){ 
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
    
}
