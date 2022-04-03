package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessAction("Brightness and Contrast", null, "Change brightness and/or Contrast", Integer.valueOf(KeyEvent.VK_U)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Colour");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change the brightness and contrast values of an image.
     * </p>
     *
     * @see #BrightnessContrast
     */
    public class BrightnessAction extends ImageAction{
        //brightness and contrast percentage cahnge values - ask the user
        int brightness = 0;
        int contrast = 0;
        
        /**
         * <p>
         * Create a new brightness-contrast action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BrightnessAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the brightness-contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessAction is triggered.
         * It prompts the user for the brightness and contrast percentage
         * they would like to change the image.
         * It then calls the {@link BrightnessContrast} class, providing the 
         * selected percentage values for changes in brightness and contrast.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            //create a Jframe to ask the user the changes they would like
            JFrame frame = new JFrame();
            frame.setLayout(new GridLayout(3,0));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the pop-up window 

            //brightness components
            JPanel brightPanel = new JPanel(); // panel with brightness buttons
            JLabel bLabel = new JLabel("Brightness Adjustment");
            brightPanel.add(bLabel);
            //create JradioButtons for percentage selection - brightness
            JRadioButton[] brightnessButtons = {new JRadioButton("-25%"), new JRadioButton("0%", true), new JRadioButton("25%")};
            ActionListener brightListener = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton a = (AbstractButton) e.getSource();
                    String pick = a.getText();
                    if(pick.equals("-25%")) brightness = -25; // setting the brightness to the value user has picked
                    if(pick.equals("0%")) brightness = 0;
                    if(pick.equals("25%")) brightness = 25; 
                }  
            };
            ButtonGroup b = new ButtonGroup();
            for(JRadioButton j : brightnessButtons){
                b.add(j); // add to brightness button group
                j.addActionListener(brightListener);//add brightness action listener
                brightPanel.add(j);//add to brightness panel
            }

            //contrast components
            JPanel contPanel = new JPanel(); //panel with contrast buttons
            JLabel cLabel = new JLabel("Contrast Adjustment");
            contPanel.add(cLabel);
            //create JradioButtons for percentage selection - contrast
            JRadioButton[] contrastButtons = {new JRadioButton("-25%"), new JRadioButton("0%", true), new JRadioButton("25%")};
            ActionListener contListener = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton a = (AbstractButton) e.getSource();
                    String pick = a.getText();
                    if(pick.equals("-25%")) contrast = -25; // setting contrast to the value the user has picked
                    if(pick.equals("0%")) contrast = 0;
                    if(pick.equals("25%")) contrast = 25;     
                }    
            };
            ButtonGroup c = new ButtonGroup();
            for(JRadioButton rb : contrastButtons){
                c.add(rb); //add to contrast button group
                rb.addActionListener(contListener); // add contrast action listener
                contPanel.add(rb); // add to contrast panel
            }

            //done button to confirm value selection
            JButton close = new JButton("okay");
            close.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {

                    // call BrightnessContrast(int, int), with user selected percentages
                     target.getImage().apply(new BrightnessContrast(brightness, contrast)); 
                     target.repaint();
                     target.getParent().revalidate();
                     frame.dispose(); // close the pop-up
                    
                }

            });

            frame.add(brightPanel);
            frame.add(contPanel);
            frame.add(close);
            frame.setSize(300,200);
            frame.pack();
            frame.setVisible(true); 
        }
    }
}



