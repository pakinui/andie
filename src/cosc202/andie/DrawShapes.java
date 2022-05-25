package cosc202.andie;

import java.awt.image.BufferedImage;
// import java.awt.image.*;
// import javax.swing.*;
import java.awt.*;

public class DrawShapes implements ImageOperation, java.io.Serializable {

    BufferedImage target;
    ImagePanel panel;

    DrawShapes() {

    }

    DrawShapes(ImagePanel panel) {
        this.panel = panel;
        target = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        panel.paint(target.getGraphics());
        panel.repaint();

    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        Graphics2D g = (Graphics2D) input.getGraphics();

        g.drawImage(target, null, 0, 0);
        return input;

    }

}
