package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.*;



/**
contrast filter class
 */

 public class BrightnessUp implements ImageOperation, java.io.Serializable {

    double one;
    double two;
    double three;
    double four;
    int fin;
    double twotwo;

   int contrast;//contrast
    int brightness; // brightness
    
     /**
     constructor 
      */
      BrightnessUp(int bright1, int cont1){
        brightness = bright1;
        contrast = cont1;
    
       // System.out.println("brightness percentage: " + brightness);
       // System.out.println("Contrast percentage: " + contrast);
      }

      BrightnessUp(){
          
      }

      /**
       * makes the image light by raising RGB value of pixel by 10?
       * @param input the image to be brightned
       * @return resulting brighter image
       */
      public BufferedImage apply(BufferedImage input){
          
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);

                Color col = new Color(input.getRGB(x,y));
                
                int a = col.getAlpha();
                int r = col.getRed();
                int g = col.getGreen();
                int b = col.getBlue();

                if(x == 0 && y == 0) {
                    System.out.println("red val: " + r);
                    System.out.println("green val: " + g);
                    System.out.println("blue val: " + b);
                    System.out.println("argb: " + argb);
                    System.out.println("b: " + brightness);
                }
              
                int newR = rgbMath(r);
                int newG = rgbMath(g);
                int newB = rgbMath(b);

                Color newARGB = new Color(newR,newG,newB,a);
                int finCol = newARGB.getRGB();

                input.setRGB(x, y, finCol);

                if(x == 0 && y == 0) {
                    

                    System.out.println("\n\nred val: " + newR);
                    System.out.println("green val: " + newG);
                    System.out.println("blue val: " + newB);
                    System.out.println("argb: " + argb);
                    System.out.println("new argb: " + newARGB);

                    System.out.println("Brightness: " + brightness);
                   // System.out.println("one: " + one +"\ntwo: " + two + "\ntwotwo: " + twotwo + "\nthree: " + three + "\nfour: " + four + "\nfin: " + fin);
                }
            }
        }
        
        return input;
    }

    //brightness, contrast, value
    public int rgbMath( int v){

            
            
             double mid;

             //((1+(c1/100))   *  (v-127.5))    + (127.5*  (1+(b1/100)) ) ((one*two)+(127.5*(three)))                          

             one = (1+(contrast/100.0));
             two = (v-127.5);
             
             mid = brightness/100.0;
             three = (1+(mid));
             four = ((one*two)+(127.5*(three)));
            fin = (int) Math.round(four);
            fin = inside(fin);

        return fin;
    }
      

      public int inside(int x){
          if(x<0) x =0;
          if(x>255) x =255;

          return x;
      }

 }
