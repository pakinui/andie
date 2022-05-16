package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.*;
import java.io.BufferedInputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Sticker implements ImageOperation, java.io.Serializable {

    int x;
    int y;
    double size;
    Image sticker;
    //icon size 50x50?

    Sticker(int x, int y, int size, Image sticker){
        this.x = x;
        this.y = y;
        this.size = (double) size;
        this.sticker = sticker;
        
    }

    Sticker(){}


    @Override
    public BufferedImage apply(BufferedImage input) {
        try{

            int one = sticker.getWidth(null);
            int two = sticker.getHeight(null);
            //System.out.println("Size: " + (size/100));
            //size maths
            int sizeX = (int) Math.floor((size/100)*one);
            int sizeY = (int) Math.floor((size/100)*two);

            //System.out.println("width: " + one + ", height: " + two);
            
            //icon to BufferedImage
            BufferedImage b;
            if(size != 0.0){
                one = sizeX;
                two = sizeY;
                b = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
            }else{
                b = new BufferedImage(one, two, BufferedImage.TYPE_INT_ARGB);
            }
            // Graphics gg = b.createGraphics();
            
            // sticker.paintIcon(null, gg, 0, 0);
            
            //gg.dispose();
            //Graphics2D zoom = b.createGraphics();
            sticker = sticker.getScaledInstance(one, two, java.awt.Image.SCALE_SMOOTH);

            //if(sticker != null) System.out.println("widtah: " + one + ", height: " + two);

            //sticker.paintIcon(null,zoom, 0, 0);
            //zoom.drawImage(sticker, null, null);
            //b = (BufferedImage) sticker;

            ImageIcon ic = new ImageIcon();
            ic.setImage(sticker);
            //System.out.println("widtah: " + ic.getIconHeight() + ", height: " + ic.getIconWidth());
            
            //zoom.scale(one, two);
            //zoom.drawImage(b, 0, 0, one, two, null);
            //zoom.dispose();


            //image to buffered image
            //BufferedImage bb = new BufferedImage(one, two, BufferedImage.TYPE_INT_ARGB);
            // Graphics2D d = b.createGraphics();
            // d.drawImage(sticker,null, null);
            // d.dispose();
            //sticker is now buff image

            

            Graphics2D g = (Graphics2D) input.createGraphics();
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            //g.drawImage(sticker, (x), (y), null);
            //System.out.println(x + "\t" + y);
            //g.drawImage(sticker, null,null);
            //g.drawImage(sticker, x, y, null);
            ic.paintIcon(null, g, (x-(one/2)), (y-(two/2)));
            //g.draw3DRect(x, y, 20, 10, true);
            g.dispose();



        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error!");
            e.printStackTrace();
        }
        return input;
    }
    
}


