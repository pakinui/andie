package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GussianFilter implements ImageOperation, java.io.Serializable {
    int radius;
    double rad;
    double value = 0.0;
    double distance = 0.0;
    //int variance = (0.33 * radius);
    double variance;
    int sizex;
    int sizey;
    int xmiddle = (radius - 1);
    int ymiddle = (radius - 1);

    GussianFilter(int radius) {
        this.radius = radius;
        rad = (double) radius; // use a double for radius so it works in the equations with doubles an floats
        variance = (rad/3);
    }

    /*
     * In this section we are creating the equation. Split the equation into two
     * separate parts in order to make it easier to read and then printing it
     * THE CURRENT PROBLEM IM HAVING IS THAT VARIANCE IS EQUALLING TO ZERO, IM THINKING THIS IS A PROBLEM TO DO WITH RADIUS.
     */
    public float GussianEqaution(int x, int y, double variance) {

        // double one = (1 / (2 * Math.PI * Math.pow(variance, 2))); // part1
        // System.out.println("one: " + one);
        // System.out.println("Variance :" + variance);
        // double two = Math.exp(-((Math.pow(y, 2) + (Math.pow(x, 2)))) / (2 * (Math.pow(variance, 2)))); // part 2\
        // System.out.println("two: " + two);
        // double three = one * two; // multiplying them together.
        // float fin = (float) three;
        // System.out.println("fin: " + fin);
        // return fin;
        
        //System.out.println("variance: " + variance);
        double one = 1 / (2*Math.PI*Math.pow(variance, 2));
        //System.out.print("one: " + one);
        double two = Math.exp(-((Math.pow(x,2) + Math.pow(y,2))/(2*Math.pow(variance,2))));
        //System.out.print("\t two: " + two);
        double three = one * two;
        //System.out.print("\tthree: " + three);
        //System.out.println();

        float fin = (float) three;
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
                        variance);
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
