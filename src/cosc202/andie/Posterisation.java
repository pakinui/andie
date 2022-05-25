package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * <p>
 * ImageOperation to apply a posterisation effect.
 * </p>
 * 
 * <p>
 * A posterise effect reduces the number of unique colours in an 
 * image.
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 */
public class Posterisation implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new Poserisation operation.
     * </p>
     */
    Posterisation(){
    }

    /**
     * <p>
     * Apply a posterise effect to an image.
     * </p>
     * 
     * <p>
     * The approach used for this posterise effect was to change the RGB 
     * values of each pixel to the nearest of 0, 128, 255. Calls the 
     * {@link #getCol} method to determine the new RBG values.
     * </p>
     * 
     * <p>
     * For example if a pixel has RGB values of (56, 5, 230), the RGB value 
     * of the pixel would be changed to (0, 0, 255).
     * </p>
     * 
     * @see #getCol(Color)
     * @param input The image to apply a posterise effect to.
     * @return The resulting posterised image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        try{
            int width = input.getWidth();
            int height = input.getHeight();

            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    int i = input.getRGB(x, y);
                    Color c = getCol(new Color(i));
                    i = c.getRGB();
                    input.setRGB(x, y, i);
                }
            }
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("Error");
        }
        return input;
    }

    /**
     * <p>
     * A method to find the new RBG colour values in order 
     * to produce a posterise effect.
     * </p>
     *
     * @param c The pixel colour to change.
     * @return The new pixel colour.
     */
    public Color getCol(Color c){

        Color newCol = c;
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        int[] colArr = new int[]{r, g, b};

        for(int i = 0; i < 3; i++){
            if(colArr[i] < 64){
                colArr[i] = 0;
            }else if( colArr[i] >= 64 && colArr[i] < 191){
                colArr[i] = 128;
            }else{
                colArr[i] = 255;
            }
        }
        newCol = new Color(colArr[0], colArr[1], colArr[2]);
        return newCol;
    }
}

