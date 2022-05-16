package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.*;


public class Post implements ImageOperation, java.io.Serializable{

    Post(){

    }


    @Override
    public BufferedImage apply(BufferedImage input) {
        try{

            int width = input.getWidth();
            int height = input.getHeight();

            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    int i = input.getRGB(x, y);

                    Color c = getCol(new Color(i));
                    i = c.getRGB();
                    input.setRGB(x, y, i);
                }
            }



        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("done");
        return input;
    }

    public Color getCol(Color c){

        Color newCol = c;
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        int rr = Math.abs(0 - r);
        int gg = Math.abs(128 - g);
        int bb = Math.abs(255 - b);

        int col; // r = 1, g = 2, b = 3
        int diff = 300; // to find closeest number
        
        // Color red = new Color(c.getRed());
        // Color green = new Color(c.getGreen());
        // Color blue = new Color(c.getBlue());
        
        int[] colArr = new int[]{r, g, b};

        for(int i = 0; i < 3; i++){

            if(colArr[i] < 64){
                colArr[i] = 0;
            }else if( colArr[i] >= 64 && colArr[i] < 191){
                colArr[i] = 128;
            }else{
                colArr[i] = 255;
            }

        }

        // if(rr < gg && rr < bb){
        //     newCol = new Color(0, 0, 0);
        // }else if(gg < rr && gg < bb){
        //     newCol = new Color(0, 128, 0);
        // }else{
        //     newCol = new Color(0, 0, 255);
        // }

        newCol = new Color(colArr[0], colArr[1], colArr[2]);
        
        return newCol;
    }

    public int closest(int i){


        return 0;
    }
    
}

