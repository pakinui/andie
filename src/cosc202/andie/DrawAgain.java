package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

public class DrawAgain implements ImageOperation, java.io.Serializable {

    BufferedImage target;
    ImagePanel panel;

    DrawAgain(){

    }

    DrawAgain(BufferedImage buff){

    }

    DrawAgain(ImagePanel panel){
        this.panel = panel;
        target = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) target.createGraphics();
        //g.drawImage(target, null, 0, 0);
        
        //g.dispose();
        panel.paint(target.getGraphics());
        panel.repaint();

        


    }
    @Override
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g = (Graphics2D) input.getGraphics();
        
        
        g.drawImage(target, null, 0, 0);
        return input;
    }
    
}
