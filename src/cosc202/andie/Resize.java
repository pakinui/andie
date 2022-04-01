package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

public class Resize implements ImageOperation, java.io.Serializable {
    int scale;


    Resize(int scale){
       this.scale = scale;
    }
    /**
     * Method to resize the image by a scale factor
     * @param image being rotated
     * @return rotated image
     */

    @Override
    public BufferedImage apply(BufferedImage input){
        final int w = input.getWidth();
        final int h = input.getHeight();
        int newW  = (int)((double)scale/100.0 * w);
        int newH = (int)((double)scale/100.0 * h);

       Image tmp = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
