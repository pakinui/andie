package cosc202.andie;

import java.awt.image.BufferedImage;

//need to make sure this is okay and delete the other three rotate classes


public class Rotate implements ImageOperation, java.io.Serializable{

    char direction;
    // 1 = right90
    // 2 = left 90
    // 3 = 180

    Rotate(){
        
        direction = '1';
    }

    Rotate(char c){

        if(Character.compare(c, '1') == 0){
            direction = '1';
        }else if(Character.compare(c, '2') == 0){
            direction = '2';
        }else if(Character.compare(c, '3') == 0){
            direction = '3';
        }else{
            // make this do something
            System.out.println("error2");
        }
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        
        if(direction == '1')  return RotateRight(input);
        if(direction == '2')  return RotateLeft(input);
        if(direction == '3')  return RotateFull(input);
        
        return input;
    }

    BufferedImage RotateFull(BufferedImage input){

        int pixel;
        int height = input.getHeight()-1;
        int width = input.getWidth()-1;

        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y < height; y++){
            int a = input.getRGB(x,y);
            int b = input.getRGB(width-x, height-y);

            pixel = a;
            input.setRGB(x, y, b);
            input.setRGB(width-x, height-y, pixel);
            }
        }
        return input;

    }

    BufferedImage RotateLeft(BufferedImage input){

        int pixel;
        int pixel2;
        int height = input.getWidth()-1;
        int width = input.getHeight()-1;

        //Same as RotateRight but locations are reversed
        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y <= (height/2); y++){
            
            int a = input.getRGB(y,x);//Top left
            int b = input.getRGB(x, width-y);//Bottom left
            int c = input.getRGB(height-y, width-x);//Bottom right
            int d = input.getRGB(height-x, y);//Top right

            pixel = b;
            // pixel a moved
            input.setRGB(x, width-y, a);

            pixel2 = c;
            // pixel b moved
            input.setRGB(height-y, width-x, pixel);

           
            pixel = d;
            // pixel c moved
            input.setRGB(height-x, y, pixel2);
            
            //pixel d moved
            input.setRGB(y,x, pixel);
            }
        }
        return input;

    }


    BufferedImage RotateRight(BufferedImage input){

        int pixel;
        int pixel2;
        int height = input.getWidth()-1;
        int width = input.getHeight()-1;

        for(int x = 0; x <= (width/2); x++){
         for(int y = 0; y <= (height/2); y++){
            
            int a = input.getRGB(x, y);//Top Left
            int b = input.getRGB(width-y, x);//Top Right
            int c = input.getRGB(width-x, height-y);//Bottom Right
            int d = input.getRGB(y, height-x);//Bottom Left

            pixel = b;// Save pixel b
            // pixel a moved to pixel b location
            input.setRGB(width-y, x, a);

            pixel2 = c;// save pixel c
            // pixel b moved to pixel c location
            input.setRGB(width-x, height-y, pixel);

           
            pixel = d;// save pixel d
            // pixel c moved to pixel d location
            input.setRGB(y, height-x, pixel2);
            
            //pixel d moved to pixel a loaction
            input.setRGB(x,y, pixel);
            }
        }
        return input;

    }


    
}

