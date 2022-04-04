package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GussianFilter implements ImageOperation, java.io.Serializable {
    int radius;
    double value = 0;
    double distance = 0;
    double variance = (0.33 * radius);
    int sizex;
    int sizey;
    int xmiddle = (radius - 1);
    int ymiddle = (radius - 1);

    GussianFilter(int radius) {
        this.radius = radius;
    }

    /*
     * In this section we are creating the equation. Split the equation into two
     * separate parts in order to make it easier to read and then printing it
     * THE CURRENT PROBLEM IM HAVING IS THAT VARIANCE IS EQUALLING TO ZERO, IM THINKING THIS IS A PROBLEM TO DO WITH RADIUS.
     */
    public float GussianEqaution(int x, int y, double variance) {

        double one = (1 / (2 * Math.PI * Math.pow(variance, 2))); // part1
        System.out.println(one);
        System.out.println("Varance :" + variance);
        double two = Math.exp(-((Math.pow(y, 2) + (Math.pow(x, 2)))) / (2 * (Math.pow(variance, 2)))); // part 2\
        System.out.println(two);
        double three = one * two; // multiplying them together.
        float fin = (float) three;
        System.out.println(fin);
        return fin;

    }

    public BufferedImage apply(BufferedImage input) {

        float[][] matrix = new float[2 * radius + 1][2 * radius + 1]; // creating a 2d float for the kernel
        float[] flat = new float[(2 * radius + 1) * (2 * radius + 1)]; // creating a 1d float, we need to flatten 2d to
                                                                       // 1d
        int i = 0;

        /*
         * Creating a for loop that goes through the 2d float and adds the
         * GussianEquation to each x,y point on the kernal.
         */
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = GussianEqaution(xmiddle + x - matrix.length / 2, ymiddle + y - matrix.length / 2,
                        1 / 3 * radius);
            }

        }
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x] + " ");

            }
            System.out.println();
        }

        /* Flattening the 2d float to a 1d float. */
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                flat[i] = matrix[y][x];
                System.out.print(flat[i]);
                i++;
            }
        }
        return input;

    }

}
