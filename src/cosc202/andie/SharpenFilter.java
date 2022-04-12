package cosc202.andie;

import java.awt.image.*;
import java.awt.image.Kernel.*;
 /**
     * Method to sharpern images allowing the user to make the image appear more defined, using a kernel. This class uses
     *  {@link ImageOperation} to apply the operation to the input image. 
     * @param input image 
     * @author Pippi Priestley King
     * @return sharpened image
     */



/**
 * <p>
 * Created an array named Sharpen in the {@link #apply(BufferedImage)}, have
 * then applied it to a kernel {@code sharpen}, it takes in
 * the height, width and the float data. 
 * </p>
 * 
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable {

    SharpenFilter() {

    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        float[] sharpen = { // Returns the data in row major order using floats.
                0.0f, -0.5f, 0.0f,
                -0.5f, 3f, -0.5f,
                0.0f, -0.5f, 0.0f,
        };

        Kernel kernel = new Kernel(3, 3, sharpen); // Constructs the kernel's width, height & the array of float.
        ConvolveOp convOp = new ConvolveOp(kernel); // Constructs a ConvloveOp with the given Kernel.
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null); // Constructs a new BufferedImage with a ColorModel and Raster and
                                                     // is AlphaPremulitplied returned true.
        convOp.filter(input, output); // Preforms a convlop on the buffered image with the source and destination
                                      // passed through.

        return output; // Returns the output.
    }

}
