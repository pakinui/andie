package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;

import java.awt.*;

/**
 * <p>
 * ImageOperation to change the size of an image.
 * </p>
 * 
 * <p>
 * Method to resize the image by a scale factor (integer {@code scale} value) 
 * given by the user in the transform menu (percentage). Users can use this operation 
 * to edit the image to be smaller or larger. Implements {@link ImageOperation} 
 * to apply the operation to the image. The edited image is them applied using the 
 * {@link BufferedImage} class. 
 * </p>
 * 
 * @author Pippi Priestley King
 * @version 1.0
 */
public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * This is the scale variable declaration entered by the user in the transform menu. 
     * </p>
     */
    int scale;

    /**
     * <p>
     * . . . 
     * </p>
     * 
     * @param scale sets the scale to be called in the method. 
     */
    Resize(int scale){
       this.scale = scale;
    }
   
    /**
     * <p>
     * This is the buffered image method where the image is edited using the buffered image class and the scale variable entered by the user. 
     * This should be automatic once the user has entered the scale value and pressed ok. 
     * The returned image should appear on the screen, an error message will appear had they not opened an image first. 
     * </p>
     * 
     * @param input is the image opened by the user.
     * @return the edited, scaled image. 
     */
    @Override
    public BufferedImage apply(BufferedImage input){
        try{
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
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please open an image first");
        }
        return input;
    }
}