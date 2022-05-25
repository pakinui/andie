package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;

public class NewEmboss implements ImageOperation, java.io.Serializable {

    @Override
    public BufferedImage apply(BufferedImage input) {
        try {
            Kernel kernel = new Kernel(3, 3, new float[] {
                    -2, 0, 0,

                    0, 1, 0,

                    0, 0, 2 });
            ConvolveOp convOp = new ConvolveOp(kernel);
            BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                    input.isAlphaPremultiplied(), null);
            convOp.filter(input, output);
            return output;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please open an image first");
        }
        return input;
    }
}