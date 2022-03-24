package cosc202.andie;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Resize implements ImageOperation, java.io.Serializable {
    double scale;


    Resize(double scale){
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
        BufferedImage scaledImage = new BufferedImage((int)(w * scale), (int)(h * scale), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(input, scaledImage);

        return input;
    }
}
