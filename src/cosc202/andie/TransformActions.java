package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

/**
 * actions provided in the transform menu
 * 
 * view menu contains actions that change the how the image is displayed, and
 * the contents of the image.
 */

public class TransformActions {

    //list of actions in transform menu
    protected ArrayList<Action> actions;
    JFrame frame;
    JPanel mainPanel;
    
    public TransformActions(){
        actions = new ArrayList<Action>();
       // actions.add(new Rotate("Rotate", null, "Rotate Image 180 Degrees", Integer.valueOf(KeyEvent.VK_R)));
       // actions.add(new RotateR("Rotate Right", null, "Rotate Image 90 Degrees Right", null));
        //actions.add(new RotateL("Rotate Left", null, "Rotate Image 90 Degrees Left", null));
        actions.add(new RotateActions("Rotate", null, "Rotate image either 90º or 180º", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FlipAction("Flip", null, "Flip image vertically or horizontally", Integer.valueOf(KeyEvent.VK_F)));
        //rotateActions = new ArrayList<Action>();
        actions.add(new RotateRight(null,null,null,null));
        actions.add(new RotateLeft(null,null,null,null));
        actions.add(new FlipHorizontal(null,null,null,null));
        actions.add(new FlipVertical(null,null,null,null));
        actions.add(new RotateFull(null, null, null, null));
        
    }

    public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        //for(Action action: actions){
            for(int i = 0; i < 2 ; i++){
            transformMenu.add(new JMenuItem(actions.get(i)));
        }
        return transformMenu;
    }



    public class RotateActions extends ImageAction {


        char direction;

        RotateActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //frame
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //components
            JPanel panel = new JPanel(); //main panel
            panel.setLayout(new GridLayout(3,1));
            JPanel buttonPanel = new JPanel(); // panel for buttons
            buttonPanel.setLayout(new GridLayout(1,3));
            
            JLabel label = new JLabel("Which direction would you like to rotate the image?");
            JButton left = new JButton("Left 90º");
            JButton right = new JButton("Right 90º");
            JButton full = new JButton("180º");
            ButtonGroup group = new ButtonGroup();
            group.add(left);
            group.add(right);
            group.add(full);

            left.addActionListener(actions.get(3));
            right.addActionListener(actions.get(2));
            full.addActionListener(actions.get(6));

            buttonPanel.add(left);
            buttonPanel.add(right);
            buttonPanel.add(full);

            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                }

            });


            panel.add(label);
            panel.add(buttonPanel);
            panel.add(done);
            frame.add(panel);
            
            frame.setSize(300,200);
            frame.pack();
            frame.setVisible(true);
            
        }
        
    }
    

    public class FlipAction extends ImageAction{

        char direction;

        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            mainPanel = new JPanel();
            mainPanel.setPreferredSize(new Dimension(300,200));
            mainPanel.setLayout(new GridLayout(3,1));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1,2));
            
            //buttons
            JButton vert = new JButton("Vertical");
            JButton hor = new JButton("Horizontal");
            ButtonGroup group = new ButtonGroup();
            group.add(vert);
            group.add(hor);

            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                }

            });


            vert.addActionListener(actions.get(5));
            hor.addActionListener(actions.get(4));



            JLabel label = new JLabel("Which direction would you like to flip the image?");
            mainPanel.add(label);

            buttonPanel.add(vert);
            buttonPanel.add(hor);

            mainPanel.add(buttonPanel);
            mainPanel.add(done);

            frame.add(mainPanel);
            frame.pack();
            frame.setVisible(true);



        }

    }

    public class RotateFull extends ImageAction{

        RotateFull(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate('3'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    public class RotateRight extends ImageAction{

        RotateRight(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate('1'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    public class RotateLeft extends ImageAction{

        RotateLeft(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate('2'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    public class FlipHorizontal extends ImageAction{

        FlipHorizontal(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Flip('H'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    public class FlipVertical extends ImageAction{

        FlipVertical(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Flip('V'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }



}
    

