package cosc202.andie;

import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to crop an image.
 * </p>
 * 
 * <p>
 * The user is able to select with either 2 clicks of their mouse, or 
 * a drag of their mouse to select the area to crop. The class {@code MouseCrop} 
 * is used
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 * @see MouseCrop
 */
public class CropFunction implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * The rectangle which the user wants to be cropped.
     * </p>
     */
    Rectangle rect;
    
    /**
     * <p>
     * Constructor for {@code CropFunction} which initalises rect.
     * </p>
     * 
     * @param rect Rectangle user wants to crop.
     */
    CropFunction(Rectangle rect){
        this.rect = rect;
    }

    /**
     * <p>
     * Method to get rect.
     * </p>
     * 
     * @return rect, the rectangle which wants to be cropped.
     */
    Rectangle getRectangle(){
        return rect;
    }

    /**
     * <p>
     * Applies a crop of the specific area (rectangle) to the image.
     * </p>
     * 
     * <p>
     * Uses the Rectangle initalised in the constructor {@code CropFunction} 
     * and then calls the {@code cropImg} method to crop the image.
     * </p>
     * 
     * @param input Image to be cropped.
     * @return the cropped BufferedImage.
     * @see CropFunction
     * @see #cropImg
     */
    @Override
    public BufferedImage apply(BufferedImage input) {

        try{
            input = cropImg(input, rect);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Unexpected Error, please try again.");
        }
        return input;
    }

    /**
     * <p>
     * A method to crop the image to the desired size.
     * </p>
     * 
     * @param buff The BufferedImage to crop.
     * @param r Rectangle of area to crop.
     * @return Cropped BufferedImage.
     * @see #apply
     */
    BufferedImage cropImg(BufferedImage buff, Rectangle r){
       
        BufferedImage cropped = buff.getSubimage(r.x, r.y, r.width, r.height);
        return cropped;
    }
}

