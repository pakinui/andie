package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.*;
// import java.io.BufferedInputStream;

// import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to add stickers to an image.
 * </p>
 * 
 * <p>
 * Adds a sticker to the current image where the user has selected.
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 */
public class Sticker implements ImageOperation, java.io.Serializable {

    int x;//sticker location x
    int y;//sticker locaiton y
    double size;// size of sticker
    Image sticker;//sticker image
    
    /**
     * <p>
     * Contructor for Sticker.
     * </p>
     * 
     * @param x sticker location x
     * @param y sticker location y
     * @param size sticker size
     * @param sticker sticker image
     */
    Sticker(int x, int y, int size, Image sticker){
        this.x = x;
        this.y = y;
        this.size = (double) size;
        this.sticker = sticker;
    }

    Sticker(){}

    /**
     * <p>
     * Add a sticker to the image.
     * </p>
     * 
     * @param input BufferedImage to add the sticker to.
     * @return the new BufferedImage with the sticker on it.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        try{

            int one = sticker.getWidth(null);
            int two = sticker.getHeight(null);
            
            //size maths
            int sizeX = (int) Math.floor((size/100)*one);
            int sizeY = (int) Math.floor((size/100)*two);

            //icon to BufferedImage
            BufferedImage b;
            if(size != 0.0){
                one = sizeX;
                two = sizeY;
                b = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
            }else{
                b = new BufferedImage(one, two, BufferedImage.TYPE_INT_ARGB);
            }
        
            sticker = sticker.getScaledInstance(one, two, java.awt.Image.SCALE_SMOOTH);//scale to size
            ImageIcon ic = new ImageIcon();
            ic.setImage(sticker);
           
            Graphics2D g = (Graphics2D) input.createGraphics();
            ic.paintIcon(null, g, (x-(one/2)), (y-(two/2)));//paint the sticker 
            g.dispose();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error!");
            e.printStackTrace();
        }
        return input;
    }
}


