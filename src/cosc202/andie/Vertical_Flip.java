package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * class to flip a image horizontally or vertically
 * 
 */

public class Vertical_Flip  implements ImageOperation, java.io.Serializable {
   
    

    /**
     * default constructor for flip operatoion(flip horizontal)
     */
    Vertical_Flip(){
        
    }

    


    /**
     * method to apply the desired flip to the image
     * @param input the image to be flipped
     * @return flipped image
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        
        //BufferedImage fliped = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        int pixel_hold;
        int height = input.getHeight()-1;
        int width = input.getWidth()-1;

        for(int x = 0; x< height; x++){
            for(int y = 0; y< (width/2); y++){
                int a = input.getRGB(x, y);
                int b = input.getRGB(x, (width-y));

                pixel_hold = a;
                input.setRGB(x, y, b);
                input.setRGB(x, (width-y), pixel_hold);


            }
        }

        return input;
    }




}
