package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;

    /**
     * <p>
     * ImageOperation to apply a Gaussian filter.
     * </p>
     * 
     * <p>
     * A Gaussian filter blurs the image by taking into account the pixels in a local
     * neighbourhood (kernel). The closer the pixel is to the centre of the kernel the
     * more weight those pixel values hold.
     * </p>
     *  
     * @see java.awt.image.ConvolveOp
     * @author Poppy Schlaadt and Christopher Mairs
     * @version 1.0
     */

    public class GaussianFilter implements ImageOperation, java.io.Serializable {

        /**
        * The {@code radius} which is selected by the user. This determines
        * the size of the filter to be applied. A radius of 1 is a 3x3 filter,
        * a radius of 2 is a 5x5 filter, and so on.
        */
        private int radius; 
        /**
        * The {@code variance} is determined by the {@link radius}.
        */
        private double variance; //variance calculated based on radius
        private int size; //number of elements in kernel
        private int length; //length of edges in kernel

        /**
         * <p>
         * Construct a Gaussian filter with the given size.
         * </p>
         * 
         * <p>
         * The size of the filter is determined by the {@link radius} that the 
         * user selects. This radius is the size of the convolution kernel used.
         * Larger radii give a stronger blurring effect.
         * </p>
         * 
         * @param radius The radius of the new GaussianFilter.
         */
        GaussianFilter(int radius) {
            this.radius = radius;
            double rad = (double) radius; //change radius into a double for variance equation
            variance = (rad/3); //set variance
            size = (2*radius + 1) * (2*radius+1);
            length = (2*radius+1);
        }

        /**
         * <p>
         * A method that takes three variables and calculates the Gaussian.
         * </p>
         * 
         * <p>
         * The equation for this math is found in page 95 of the 
         * <a href = "https://cosc202.cspages.otago.ac.nz/lab-book/COSC202LabBook.pdf">
         * COMP202 Lab Book</a>.
         * </p>
         * 
         * <p>
         * It takes two integers, {@code x} and {@code y}, which are the distance the 
         * current pixel is from the middle of the kernel. The {@code variance} is 
         * calculated and initalised in {@link #GaussianFilter}.
         * </p>
         * 
         * @param x The horizontal distance from the current pixel to the middle of the kernel.
         * @param y The vertical distance from the current pixel to the middle of the kernel.
         * @param variance The variance determined by the {@code radius}.
         * @return The Gaussian of that pixel.
         * @see #arrFill
         */
        public float gaussianEquation(int x, int y, double variance) {

            double one = 1 / (2*Math.PI*Math.pow(variance, 2));
            double two = Math.exp(-((Math.pow(x,2) + Math.pow(y,2))/(2*Math.pow(variance,2))));
            double three = one * two;
            float fin = (float) three;
            return fin;

        }

        /**
         * <p>
         * A method to fill an array of floats with the new Gaussian values
         * caluculated in the {@link #GaussianEquation} method.
         * </p>
         * 
         * <p>
         * The values to fill the array with are calculated using the 
         * {@link #gaussianEquation} method.
         * </p>
         * 
         * @param arr The array of floats to fill.
         * @return The filled array.
         * @see #gaussianEquation
         */
        float[] arrFill(float[] arr){

            int index = 0;//index to loop through float arr
            /*
            Nested for-loop to determine the x and y length from the centre
            of the kernel.
            */
            for(int x = (radius*-1) ; x <= radius ; x++){
                for(int y = (radius*-1) ; y <= radius ; y++){

                    int xMid = x;// x length from middle of kernel
                    int yMid = y;// y length from middle of kernel
                    //if x or y are negative make them positive
                    if(x < 0) xMid = x*-1;
                    if(y < 0) yMid = y*-1;
                    //calculate Gaussian value
                    arr[index] = gaussianEquation(xMid, yMid, variance); 
                    index++;//iterate through the float array
                }
            }
            //calculate the sum of all floats in the array
            float sum = 0;
            for(float a : arr){
                sum += a;
            }
            /*
            make a new float and populate it with the values of the 
            arr/sum.
            */
            float[] fin = new float[arr.length];
            for(int f = 0; f < fin.length ; f++){
                fin[f] = arr[f]/sum;
            }
            return fin;//the final float with Gaussian values.
        }

        /**
         * <p>
         * Apply a Gaussian filter to an image.
         * </p>
         * 
         * <p>
         * The Gaussian filter is implemented via convolution.
         * The {@link radius} provided by the user determines the size
         * the convolution kernel.
         * Larger radii lead to stronger blurring.
         * </p>
         * 
         * @param input The image to apply the Mean filter to.
         * @return The resulting (blurred)) image.
         * @see #gaussianEquation
         * @see #arrFill
         */
        public BufferedImage apply(BufferedImage input) {

                float[] arr = new float[size];
                arr = arrFill(arr); //fill the array with Gaussian values.
                
                //applies the filter using the array above,kernel and convolveop
                Kernel kernel = new Kernel(length,length, arr);
                ConvolveOp conOp = new ConvolveOp(kernel);
                //create a new buffered image with the resulting Gaussian blur
                BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
                conOp.filter(input, output);
            
            return output;//return the blurred image
        }
    }
