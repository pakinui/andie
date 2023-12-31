package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.image.*;

import javax.swing.JOptionPane;

/**
 * <p>
 * Created an array named Sharpen in the {@link #apply(BufferedImage)}, have
 * then applied it to a kernel {@code sharpen}, it takes in
 * the height, width and the float data.
 * </p>
 * 
 * @author Chistopher Mairs
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Created an array named Sharpen in the {@link #apply(BufferedImage)}, have
     * then applied it to a kernel {@code sharpen}, it takes in
     * the height, width and the float data. Then we constuct a ConvloveOp passing
     * through
     * the kernel we have just made. Then we BufferedImage the {@code output},
     * passing through
     * its ColorModel, copyData and that is Alpha Premultiplied. It preforms a
     * ConvolveOp on the BufferedImage,
     * using the source and destination, and then returns the output. There is also
     * an catch if there is no image
     * open and youre trying to preform Sharpen.
     * </p>
     * 
     * @param input Image to be too sharpen filter.
     * @return Sharpened Image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        try {
            float[] sharpen = { // Returns the data in row major order using floats.
                    0.0f, -0.5f, 0.0f,
                    -0.5f, 3f, -0.5f,
                    0.0f, -0.5f, 0.0f,
            };
            int kernelWidth = 3;
            int kernelHeight = 3;

            int xOffset = (kernelWidth - 1) / 2;
            int yOffset = (kernelHeight - 1) / 2;
            BufferedImage modInput = new BufferedImage(input.getWidth() + kernelWidth - 1,
                    input.getHeight() + kernelHeight - 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = modInput.createGraphics();
            g2.drawImage(input, xOffset, yOffset, null);
            g2.dispose();
            Kernel kernel = new Kernel(3, 3, sharpen); // Constructs the kernel's width, height & the array of float.
            ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null); // Constructs a ConvloveOp with the
                                                                                     // given Kernel.
            BufferedImage output = new BufferedImage(modInput.getColorModel(), modInput.copyData(null),
                    modInput.isAlphaPremultiplied(), null); // Constructs a new BufferedImage with a ColorModel and
                                                            // Raster
                                                            // and
                                                            // is AlphaPremulitplied returned true.
            convOp.filter(modInput, output); // Preforms a convlop on the buffered image with the source and destination
            return output; // Returns the output. // passed through.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please open an image first");
        }
        return input;
    }
}
