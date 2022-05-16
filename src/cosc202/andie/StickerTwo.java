package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.BufferedInputStream;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class StickerTwo implements ImageOperation, java.io.Serializable {

    int num;
    // 0-5 for same num in arr
    int x;
    int y;
    // location of mouse click
    int size;
    // size of icon
    boolean fin = false;
    // if false mouse listener work, once done is clicked it becomes true and it
    // wont work
    MouseListener mouse;
    JButton done;
    JFrame frame;
    BufferedImage over;
    boolean finished;
    ImagePanel img;
    BufferedImage input;

    StickerTwo(ImagePanel img) {
        finished = false;
        this.img = img;
        createPanel();
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        System.out.println("starting. . .");
        this.input = input;
        over = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        img.addMouseListener(mouse);
        //done = new JButton("Done");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                finished = true;
                System.out.println("reach?");
                //Graphics2D gg = (Graphics2D) input.getGraphics();
                //gg.drawImage(over, 0, 0, null);
                

            }
        });

        return input;
    }

    public BufferedImage getStickers(){
        return input;
    }

    public void paint(int x, int y, int size, Image sticker){
        try{
            //System.out.println("paint @ " + x + ", " + y);
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

            sticker = sticker.getScaledInstance(one, two, java.awt.Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon();
            ic.setImage(sticker);


            Graphics2D g = (Graphics2D) input.getGraphics();
            ic.paintIcon(null, g, (x-(one/2)), (y-(two/2)));
            
            //g.dispose();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error!");
                e.printStackTrace();
        }
    }

    public void createPanel() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 300));

        JButton[] buttons = new JButton[6];
        ImageIcon[] icons = new ImageIcon[6];

        ImageIcon smile = new ImageIcon((new ImageIcon("src/stickers/smile.png").getImage()).getScaledInstance(20, 20,
                java.awt.Image.SCALE_SMOOTH));
        ImageIcon fear = new ImageIcon((new ImageIcon("src/stickers/fear.png").getImage()).getScaledInstance(20, 20,
                java.awt.Image.SCALE_SMOOTH));
        ImageIcon wink = new ImageIcon((new ImageIcon("src/stickers/wink.png").getImage()).getScaledInstance(20, 20,
                java.awt.Image.SCALE_SMOOTH));
        ImageIcon sunglasses = new ImageIcon((new ImageIcon("src/stickers/sunglasses.png").getImage())
                .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        ImageIcon tears = new ImageIcon((new ImageIcon("src/stickers/happyTears.png").getImage()).getScaledInstance(20,
                20, java.awt.Image.SCALE_SMOOTH));
        ImageIcon heart = new ImageIcon((new ImageIcon("src/stickers/heartEye.png").getImage()).getScaledInstance(20,
                20, java.awt.Image.SCALE_SMOOTH));
        icons[0] = smile;
        icons[1] = fear;
        icons[2] = wink;
        icons[3] = sunglasses;
        icons[4] = tears;
        icons[5] = heart;

        for (int i = 0; i < buttons.length; i++) {

            buttons[i] = new JButton(icons[i]);
        }

        ActionListener listen = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton select = (JButton) e.getSource();

                for (int i = 0; i < buttons.length; i++) {

                    if (select == buttons[i]) {
                        num = i;

                    }
                }
            }
        };

        // sticker size
        JPanel scrollPanel = new JPanel();
        JSlider bar = new JSlider(JSlider.HORIZONTAL);
        bar.setMaximum(190);
        bar.setValue(100);
        bar.setMinimum(10);
        scrollPanel.add(new JLabel("Adjust sticker size."));
        scrollPanel.add(bar);

        bar.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                size = bar.getValue();
            }
        });

        // example icon size

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(2, 3));
        bPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        ButtonGroup group = new ButtonGroup();
        for (JButton b : buttons) {
            b.setSize(new Dimension(50, 50));
            bPanel.add(b);
            group.add(b);
            b.addActionListener(listen);
            // b.setBorder(new EmptyBorder(10,10,10,10));
        }

        // overlay panel

        bPanel.setSize(new Dimension(290, 100));
        panel.add(bPanel);
        panel.add(scrollPanel);

        done = new JButton("Done");
        panel.add(done);

        frame.add(panel);
        frame.setLocation(Andie.frame.getWidth() + 10, 10);
        frame.setVisible(true);
        frame.pack();

        // mouse listener stuff
        mouse = new MouseAdapter() {

            // over.setSize(target.getSize());

            public void mouseReleased(MouseEvent e) {
                if (!fin && frame.isVisible()) {
                    x = e.getX();
                    y = e.getY();
                    System.out.println("x-" + x + ", y-" + y);
                    // num = sticker num
                    Icon icon = buttons[num].getIcon();
                    Image img = icons[num].getImage();
                    // over.getImage().apply(new Sticker(x, y, size, img));
                    // over.repaint();
                    paint(x, y, size, img);

                    // over.getParent().revalidate();
                    // target.getImage().apply(new Sticker(x, y, size, img));
                    // target.repaint();
                    // target.getParent().revalidate();
                }
            }
        };
        
    }

}
