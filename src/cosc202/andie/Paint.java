package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

public class Paint implements ImageOperation, java.io.Serializable {

    BufferedImage target;
    ImagePanel panel;
    boolean draw;

    Paint(){

    }

    Paint(BufferedImage buff){

    }

    Paint(ImagePanel panel){
        this.panel = panel;
        target = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // Graphics2D g = (Graphics2D) target.createGraphics();
        //System.out.println("two");
        // JLabel lab = new JLabel(new ImageIcon(target));
        // JFrame f = new JFrame("pop up");
        // f.setLayout(new GridLayout(2,1));
        
        // f.add(panel);
        // f.add(lab);
        // f.setVisible(true);
        // f.pack();
        
        //g.dispose();
        panel.paint(target.getGraphics());
        panel.repaint();

        


    }

    Paint(ImagePanel panel, boolean draw){
        this.panel = panel;
        this.draw = draw;
        //System.out.println("one");
        Graphics2D g = (Graphics2D) panel.getGraphics();
        target = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        g.drawImage(target, null, 0 ,0);
    
    }

    
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        
        

        if(!draw){
            Graphics2D g = (Graphics2D) input.getGraphics();
            //System.out.println("on1e");
            
            g.drawImage(target, null, 0, 0);
            return input;
        }else{
            Graphics2D g = (Graphics2D) panel.getGraphics();
            //System.out.println("tw2o");
            
            g.drawImage(target, null, 0, 0);
            JLabel labe = new JLabel(new ImageIcon(input));
            //f.add(labe);
            
            return input;
        }



        //return input;
    }
    
}
