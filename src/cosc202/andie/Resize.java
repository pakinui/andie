package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

 /**
     * Method to resize the image by a scale factor (integer {@code scale} value) 
     * given by the user in the transform menu (percentage). Users can use this operation 
     * to edit the image to be smaller or larger. Implements {@link ImageOperation} 
     * to apply the operation to the image. The edited image is them applied using the 
     * {@link BufferedImage} class. 
     * @param input image being resized
     * @author Pippi Priestley King
     * @return scaled image
     */


public class Resize implements ImageOperation, java.io.Serializable {
    int scale;

    Resize(int scale){
       this.scale = scale;
    }
   
    @Override
    public BufferedImage apply(BufferedImage input){
        final int w = input.getWidth();
        final int h = input.getHeight();
        // Code determines height and width then adjusts them to the new size by multiplying by the 
        //scale factor (converted to decimal).
        int newW  = (int)((double)scale/100.0 * w);
        int newH = (int)((double)scale/100.0 * h);

        /**gets scaled instance of the original image*/
       Image img = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
       //creates the new buffered image
        BufferedImage newImg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        //draws the new image and returns the final resized image
        return newImg;
    }
}