package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

public class DrawAgain implements ImageOperation, java.io.Serializable {

    BufferedImage target;
    ImagePanel panel;
    boolean draw;

    DrawAgain(){

    }

    DrawAgain(BufferedImage buff){

    }

    DrawAgain(ImagePanel panel){
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

    DrawAgain(ImagePanel panel, boolean draw){
        this.panel = panel;
        this.draw = draw;
        //System.out.println("one");
        Graphics2D g = (Graphics2D) panel.getGraphics();
        target = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // panel.paint(target.getGraphics());
        // panel.repaint();
        g.drawImage(target, null, 0 ,0);
    
    }

    // private void display(){
    //     JFrame frame = new JFrame("test");
    //     frame.add(panel);
    //     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //     frame.setLocation(500,0);
    //     frame.pack();
    //     frame.setVisible(true);
        
    // }

    
    @Override
    public BufferedImage apply(BufferedImage input) {
        
        // JFrame f = new JFrame("pannnnn");
        // f.setLayout(new GridLayout(3,1));
        // JLabel lab = new JLabel(new ImageIcon(input));
        // f.add(panel);
        // f.add(lab);
        // f.setLocation(900,0);
        // f.setVisible(true);
        // f.pack();


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
