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
    
    public TransformActions(){
        actions = new ArrayList<Action>();
       // actions.add(new Rotate("Rotate", null, "Rotate Image 180 Degrees", Integer.valueOf(KeyEvent.VK_R)));
       // actions.add(new RotateR("Rotate Right", null, "Rotate Image 90 Degrees Right", null));
        //actions.add(new RotateL("Rotate Left", null, "Rotate Image 90 Degrees Left", null));
        actions.add(new RotateActions("Rotate", null, "Rotate image either 90º or 180º", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FlipAction("Flip", null, "Flip image vertically or horizontally", Integer.valueOf(KeyEvent.VK_F)));
    }

    public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        for(Action action: actions){
            transformMenu.add(new JMenuItem(action));
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
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //components
            JPanel panel = new JPanel(); //main panel
            panel.setLayout(new GridLayout(3,1));
            JPanel buttonPanel = new JPanel(); // panel for buttons
            buttonPanel.setLayout(new GridLayout(1,3));
            JButton done = new JButton("Done");
            JLabel label = new JLabel("Which direction would you like to rotate the image?");
            JRadioButton left = new JRadioButton("Left 90º");
            JRadioButton right = new JRadioButton("Right 90º");
            JRadioButton full = new JRadioButton("180º");
            ButtonGroup group = new ButtonGroup();
            group.add(left);
            group.add(right);
            group.add(full);
            ActionListener listen = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton a = (AbstractButton) e.getSource();
                    String text = a.getText();
                    if(text.equals("Left 90º")) direction = '2';
                    if(text.equals("Right 90º")) direction = '1';
                    if(text.equals("180º")) direction = '3';
                    
                }

                
            };

            done.setMaximumSize(new Dimension(100,50));
            done.setBorder(new EmptyBorder(30,0,30,0));
            done.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                   
                   //create and apply filter
                   target.getImage().apply(new Rotate(direction));
                   target.repaint();
                   target.getParent().revalidate();
                   //frame.setVisible(false);
                   frame.dispose();
                    
                }
                
            });

            left.addActionListener(listen);
            right.addActionListener(listen);
            full.addActionListener(listen);

            buttonPanel.add(left);
            buttonPanel.add(right);
            buttonPanel.add(full);


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
            
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setPreferredSize(new Dimension(300,200));
            mainPanel.setLayout(new GridLayout(3,1));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1,2));
            
            //buttons
            JRadioButton vert = new JRadioButton("Vertical");
            JRadioButton hor = new JRadioButton("Horizontal");
            JButton done = new JButton("Done");
            ButtonGroup group = new ButtonGroup();
            group.add(vert);
            group.add(hor);

            ActionListener listen = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(e.getSource() == hor) direction = 'H';
                    else direction = 'V';
                    
                }
                
            };
            vert.addActionListener(listen);
            hor.addActionListener(listen);

            done.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {

                    // Create and apply the filter
                     target.getImage().apply(new Flip(direction));
                     target.repaint();
                     target.getParent().revalidate();
                     frame.dispose();
                    
                }
                
            });

            JLabel label = new JLabel("Which direction would you like to flip the image?");
            mainPanel.add(label);

            buttonPanel.add(vert);
            buttonPanel.add(hor);

            mainPanel.add(buttonPanel);
            mainPanel.add(done);

            frame.add(mainPanel);
            frame.setVisible(true);
            frame.pack();



        }

    }






}
    






























    // public class Rotate extends ImageAction {

    //     /**
    //      * create a flip action
    //      * 
    //      * @param name name of action(if ignored, null)
    //      * @param icon an icon to rep the action (if ignored, null)
    //      * @param desc brief desc of ation (if ignored, null)
    //      * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
    //      */

    //     Rotate(String name, ImageIcon icon, String desc, Integer mnemonic) {
    //         super(name, icon, desc, mnemonic);
            
    //     }

    //     /**
    //      * callback for when vertical flip is triggered
    //      * this method will flip the image vertically
    //      * 
    //      * @param e event triggering this callback
    //      */
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         target.getImage().apply(new Rotate180());
    //         target.repaint();
    //         target.getParent().revalidate();
            
    //     }
    // }

//     public class RotateR extends ImageAction {

//         /**
//          * create a flip action
//          * 
//          * @param name name of action(if ignored, null)
//          * @param icon an icon to rep the action (if ignored, null)
//          * @param desc brief desc of ation (if ignored, null)
//          * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
//          */

//         RotateR(String name, ImageIcon icon, String desc, Integer mnemonic) {
//             super(name, icon, desc, mnemonic);
            
//         }

//         /**
//          * callback for when vertical flip is triggered
//          * this method will flip the image vertically
//          * 
//          * @param e event triggering this callback
//          */
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             target.getImage().apply(new RotateRight());
//             target.repaint();
//             target.getParent().revalidate();
            
//         }
//     }

//     public class RotateL extends ImageAction {

//         /**
//          * create a flip action
//          * 
//          * @param name name of action(if ignored, null)
//          * @param icon an icon to rep the action (if ignored, null)
//          * @param desc brief desc of ation (if ignored, null)
//          * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
//          */

//         RotateL(String name, ImageIcon icon, String desc, Integer mnemonic) {
//             super(name, icon, desc, mnemonic);
            
//         }

//         /**
//          * callback for when vertical flip is triggered
//          * this method will flip the image vertically
//          * 
//          * @param e event triggering this callback
//          */
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             target.getImage().apply(new RotateLeft());
//             target.repaint();
//             target.getParent().revalidate();
            
//         }
//     }
// }

