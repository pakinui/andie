package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.*;


import java.awt.image.BufferedImage;

public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int radius;
    private int length;



    MedianFilter(int radius){
        this.radius = radius;
        length = (radius*2)+1;
    }

    MedianFilter(){
        radius = 1;
        length = (radius*2)+1;

    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        
        ArrayList<Integer> alphaNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> redNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> greenNeighbours = new ArrayList<Integer>();
        ArrayList<Integer> blueNeighbours = new ArrayList<Integer>();

        for(int x = radius; x < input.getWidth()-radius; x = x+length){
            for(int y = radius; y < input.getHeight()-radius; y = y+length){


                for(int i = 0; i< radius; i++){

                    Color col = new Color(input.getRGB(x,y))
                    

                }
                


            }

        }
        
        return null;
    }
    
}

