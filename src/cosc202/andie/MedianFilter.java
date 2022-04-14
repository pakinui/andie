package cosc202.andie;

import java.util.*;

import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel with the 
 * median pixel values(alpha, red, green, blue) of the surrounding
 * pixel neighbours.
 * </p>
 * 
 * @author Poppy Schlaadt
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply.
     * A radius of 1 is a 3x3 filter, a radius of 2 is a 5x5 filter,
     * and so on.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is determined by the {@code radius}.
     * A radius of 1 is a 3x3 pixel filter, 2 is a 5x5 pixel filter,
     * and so on.
     * The larger the radius size the strong effect the blurring has.
     * </p>
     * 
     * @param radius The radius of the newly constructured MeadianFilter.
     * @see #MedianFilter()
     */
    MedianFilter(int radius){
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p>
     * 
     * <p>
     * By default, the Median filter has a radius of 1.
     * </p>
     * 
     * @see #MedianFilter(int)
     */
    MedianFilter(){
        radius = 1;
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * The Median filter is implemented by taking the RGBA values of 
     * local neighbouring pixels puting them into arrays, and sorting them.
     * The median value from the RGBA arrays is then used to create and set 
     * a new colour for the current pixel.
     * </p>
     * 
     * <p>
     * It iterates through every pixel in the image with nested for-loops.
     * The loops start at index {@link radius} rather than 0 and end at 
     * height-{@link radius}/width-{@link radius} so that the arrays which
     * act as kernels are not effected at the borders.
     * </p>
     * 
     * <p>
     * The size of {@link radius} which is specified by the user effects 
     * the size of the arrays and the strength of the blur.
     * The larger the radii the strong the blur.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred) image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        try{
       
            final int length = (radius*2)+1; //length of 'kernel'
            final int size = length*length; // size of 'kernel'
            final int middle = (int) Math.floor(size/2); // middle value of 'kernel' to get median value
            int[] aValues = new int[size]; // array of alpha values from local neighbours
            int[] rValues = new int[size]; // array of red values from local neighbours
            int[] gValues = new int[size]; // array of green values from local neighbours
            int[] bValues = new int[size]; // array of blue values from local neighbours
            
            //looping though all pixels in the image but avoiding the edge pixels of size radius
            for(int x = radius; x < input.getWidth()-radius; x++){ //from radius to width-radius
                for(int y = radius; y < input.getHeight()-radius; y++){ //from radius to hight-radius
                    //index to iterate though the local neighbours
                    int kernelIdx = 0;

                    for(int i = (x-radius); i <= (x+radius) ; i++){ //itterating through local neighbours
                        for(int ii = (y-radius); ii <= (y+radius); ii++){
                            Color colour = new Color(input.getRGB(i,ii)); 
                            aValues[kernelIdx] = colour.getAlpha(); 
                            rValues[kernelIdx] = colour.getRed(); 
                            gValues[kernelIdx] = colour.getGreen(); 
                            bValues[kernelIdx] = colour.getBlue();  
                            kernelIdx++;                      
                        }
                    }
                    kernelIdx = 0; //set to 0 for each new kernel
                    //finding the median RGBA value in the array
                    Arrays.sort(aValues);
                    Arrays.sort(rValues);
                    Arrays.sort(gValues);
                    Arrays.sort(bValues);
                    //new colour with median RGBA values
                    Color newCol = new Color(rValues[middle], gValues[middle], bValues[middle], aValues[middle]);
                    input.setRGB(x,y, newCol.getRGB());
                }  
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please open an image first");
        }      
        return input;
    }
}


