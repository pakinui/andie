package cosc202.andie;

import java.awt.image.*;
import java.awt.image.Kernel.*;

public class SharpenFilter implements ImageOperation, java.io.Serializable {

    SharpenFilter() {

    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        float[] sharpen = {
                0, -1 / 2, 0,
                -1 / 2, 3, -1 / 2,
                0, -1 / 2, 0
        };

        Kernel kernel = new Kernel(3, 3, sharpen);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }

}
