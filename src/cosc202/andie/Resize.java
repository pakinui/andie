package cosc202.andie;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

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
        int frog  = (int)((double)scale/100.0 * w);
        int rabbit = (int)((double)scale/100.0 * h);

        BufferedImage scaledImage = new BufferedImage(frog, rabbit, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(input, scaledImage);

        
        return scaledImage;
    }
}
