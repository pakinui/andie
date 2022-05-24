package cosc202.andie;

import java.awt.image.BufferedImage;

import java.awt.image.*;

    public class Emboss implements ImageOperation{

    public BufferedImage apply(BufferedImage image){

    Kernel kernel = new Kernel(3, 3,
 
    new float[] {-2, 0, 0,0, 1, 0, 0, 0, 2});
   
    BufferedImageOp op = new ConvolveOp(kernel);
   
    image = op.filter(image, null);
    return image;
    }
}
