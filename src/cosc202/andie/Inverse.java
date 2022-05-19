package cosc202.andie;

import java.awt.image.BufferedImage;

    public class Inverse implements ImageOperation{

        public Inverse(){

        }

            public BufferedImage apply(BufferedImage image){
            
                for (int x = 0; x < image.getWidth(); x++) {
                    for (int y = 0; y < image.getHeight(); y++) {
                        int p = image.getRGB(x,y);
                        int a = (p>>24)&0xff;
                        int r = (p>>16)&0xff;
                        int g = (p>>8)&0xff;
                        int b = p&0xff;
                        //subtract RGB from 255
                        r = 255 - r;
                        g = 255 - g;
                        b = 255 - b;
                        //set new RGB value
                        p = (a<<24) | (r<<16) | (g<<8) | b;
                        image.setRGB(x, y, p);
                    }
                }
                return image;
            }
     }

           

    