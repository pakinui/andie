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
        actions.add(new Rotate("Rotate", null, "Rotate Image 180 Degrees", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateR("Rotate Right", null, "Rotate Image 90 Degrees Right", null));
        actions.add(new RotateL("Rotate Left", null, "Rotate Image 90 Degrees Left", null));
        actions.add(new Scale("Scale", null, "Scale by a %", null));
        
    }

    public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        for(Action action: actions){
            transformMenu.add(new JMenuItem(action));
        }
        return transformMenu;
    }




    

    public class Rotate extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

        Rotate(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
            
        }
    }

    public class RotateR extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

        RotateR(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            target.getImage().apply(new RotateRight());
            target.repaint();
            target.getParent().revalidate();
            
        }
    }

    public class RotateL extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

        RotateL(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            target.getImage().apply(new RotateLeft());
            target.repaint();
            target.getParent().revalidate();
            
        }
    }

     

    public class Scale extends ImageAction {

        /**
         * create a flip action
         * 
         * @param name name of action(if ignored, null)
         * @param icon an icon to rep the action (if ignored, null)
         * @param desc brief desc of ation (if ignored, null)
         * @param mnemonic a mnemonic key to use as a shortcut (if ignored, null)
         */

        Scale(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            int scale=0;
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(0, 0, 500, 10);
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            double option = JOptionPane.showOptionDialog(null,scaleSpinner, "Scale %", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scale = scaleModel.getNumber().intValue();
                
            }
            target.getImage().apply(new Resize(scale));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
