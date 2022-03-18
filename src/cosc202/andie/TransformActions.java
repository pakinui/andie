package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

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
        actions.add(new H_FlipAction("Flip Horizontally", null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_X)));
        actions.add(new V_FlipAction("Flip Vertically", null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_Y)));
        
    }

    public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        for(Action action: actions){
            transformMenu.add(new JMenuItem(action));
        }
        return transformMenu;
    }




    public class V_FlipAction extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

        V_FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        /**
         * callback for when vertical flip is triggered
         * this method will flip the image vertically
         * 
         * @param e event triggering this callback
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Vertical_Flip());
            target.repaint();
            target.getParent().revalidate();
            
        }


        
    }


    public class H_FlipAction extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

         H_FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        
        }

        /**
         * callback for when horizontal flip is triggered
         * this method will flip the image horizontally
         * 
         * @param e event triggering this callback
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Horizontal_Flip());
            target.repaint();
            target.getParent().revalidate();
            
        }


    
    }

}
