package cosc202.andie;

import java.awt.image.*;
import java.awt.image.Kernel.*;

public class SharpenFilter implements ImageOperation, java.io.Serializable {

    SharpenFilter() {

    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        float[] sharpen = {
                0.0f, -0.5f, 0.0f,
                -0.5f, 3f, -0.5f,
                0.0f, -0.5f, 0.0f,
        };

        Kernel kernel = new Kernel(3, 3, sharpen);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }

}
