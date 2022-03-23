package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GussianFilter implements ImageOperation, java.io.Serializable {
    private int radius;
    double value = 0;
    double distance = 0;
    double variance = (1 / 3) * radius;
    int sizex;
    int sizey;
    int xmiddle = (sizex / 2);
    int ymiddle = (sizey / 2);

    GussianFilter(int radius) {
        this.radius = radius;
    }

    public float GussianEqaution(int x, int y, double variance) {

        double one = (1 / (2 * Math.PI * Math.pow(variance, 2)));
        double two = Math.exp(-((Math.pow(y, 2) + (Math.pow(x, 2)))) / (2 * (Math.pow(variance, 2))));
        double three = one * two;
        float fin = (float) three;

        return fin;
    }

    public BufferedImage apply(BufferedImage input) {

        float[][] gussian = new float[9][2];

        float[] gussian2 = {
                0.000f, 0.011f, 0.000f,
                0.011f, 0.957f, 0.011f,
                0.000f, 0.011f, 0.000f
        };

        Kernel kernel = new Kernel(3, 3, gussian2);
        ConvolveOp convOp = new ConvolveOp(kernel);

        for (int x = 0; x < gussian.length; x++) {
            for (int y = 0; y < gussian.height; y++)
                GussianEquation(x, y, 1 / 3 * radius);

        }

    }

}
