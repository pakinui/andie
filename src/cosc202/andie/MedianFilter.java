package cosc202.andie;

import java.util.*;
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
     * Apply a median filter to an image.
     * </p>
     * 
     * <p>
     * The median filter is implemented by taking the values of 
     * neighbouring pixel (with a specified {@link radius}), and 
     * changing the current pixel value to the median value of the 
     * local neighbours.
     * Larger radii lead to a stronger blur.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred) image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
       
        ArrayList<Integer> neighbours;

        /*
        looping though all pixels in the image but avoiding the edge pixels
        of size radius
        */
        for(int x = radius; x < input.getWidth()-radius; x++){
            for(int y = radius; y < input.getHeight()-radius; y++){
                neighbours = new ArrayList<Integer>();
                //loop to put all local neighbours in an ArrayList
                for(int i = (x-radius); i<= (x+radius) ; i++){
                    for(int ii = (y-radius); ii<=(y+radius); ii++){
                        neighbours.add(input.getRGB(i,ii));                        
                    }
                }
                /*
                calls getvalues method which returns an array which
                contains median [alpha, red, green, blue] values
                */
                ArrayList<Integer> colourValues = getValues(neighbours);
                //creates a new colours and sets current pixel to this colour
                Color newColour = new Color(colourValues.get(1),colourValues.get(2),colourValues.get(3),colourValues.get(0));
                int finalColour = newColour.getRGB();
                input.setRGB(x,y, finalColour);
            }  
        }       
        return input;
    }

    //arraylist is four long and goes alpha, red, green, blue
    /**
     * <p>
     * A method to get the new colour values (a, r, g, b) from an array of 
     * local neighbouring pixel RGB values.
     * </p>
     * 
     * @param friends array of RGB values for the neighbouring pixels
     * @return an arraylist with the layout [alpha, red, green, blue]
     * of the new colour values
     * @see #median(ArrayList)
     */
    ArrayList<Integer> getValues(ArrayList<Integer> friends){
        //arraylist to return, layout: [alpha, red, green, blue]
        ArrayList<Integer> newColour = new ArrayList<Integer>();
        //arraylists to add corrosponding values from friends array
        ArrayList<Integer> alphaNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> redNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> greenNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> blueNeighbours = new ArrayList<Integer>();
        
        for(int i = 0; i<friends.size();i++){
            Color col = new Color(friends.get(i));
            alphaNeighbours.add(col.getAlpha());
            redNeighbours.add(col.getRed());
            greenNeighbours.add(col.getGreen());
            blueNeighbours.add(col.getBlue());
        }
        /*
        calls median methods on each colour value and adds them
        to newColour in right order
        */
        newColour.add(median(alphaNeighbours));
        newColour.add(median(redNeighbours));
        newColour.add(median(greenNeighbours));
        newColour.add(median(blueNeighbours));

        return newColour;
    }

    /**
     * <p>
     *  A method to calculate the median value from 
     * an array of values
     * </p>
     * 
     * @param values array of values to find the median
     * @return median value from array
     * @see #getValues(ArrayList)
     */
    int median(ArrayList<Integer> values){
        
        Collections.sort(values);
        int half = Math.round(values.size()/2);

        return values.get(half);
    }

    
    
}

