package cosc202.andie;

import java.awt.image.*;
//import java.util.Arrays;
import java.util.*;
import javax.swing.*;

import java.awt.*;
//import java.awt.event.*;


public class CropFunction implements ImageOperation, java.io.Serializable {

    
    Rectangle rect;

    BufferedImage inputImg;
    

    

    CropFunction(Rectangle rect){
        this.rect = rect;
        //createMenu();
        //System.out.println(rect);
        
    }

    Rectangle getRectangle(){
        return rect;
    }

    
    @Override
    public BufferedImage apply(BufferedImage input) {
        //inputImg = input;
        
        inputImg = cropImg(input, rect);
        
        return inputImg;
    }

    BufferedImage cropImg(BufferedImage buff, Rectangle r){
       
        BufferedImage cropped = buff.getSubimage(r.x, r.y, r.width, r.height);
        
        return cropped;
    }
}

