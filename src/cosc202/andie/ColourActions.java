package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * </p>
 * 
 * <p>
 * Colour actions include:
 * </p>
 * 
 * <ul>
 * <li> {@link BrightnessContrast} </li>
 * <li> {@link ConvertToGrey} </li>
 * </ul>
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
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", null));
        actions.add(new BrightnessAction("Brightness and Contrast", null, "Change brightness and/or Contrast", null));
        actions.add(new PostAction("Posterisation", null, "Apply a posterise effect", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * <p>
     * Adds a keyboard shortcut to the JMenuItem is a value has been provided in the constructor
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu colourMenu = new JMenu("Colour");

        //for(Action action: actions){
            for(Action action : actions){
                JMenuItem menu = new JMenuItem(action);
                int menuKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx(); //identifies the modifier key for the OS
                //if shortcut is not null add shortcut
                if(action.getValue("MnemonicKey") != null){
                    int key = (int) action.getValue("MnemonicKey");
                    char mn = (char) key;//shortcut key
                    menu.setAccelerator(KeyStroke.getKeyStroke(mn ,menuKey));
                }
                colourMenu.add(menu);
            }
        return colourMenu;
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
     * @see BrightnessContrast
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
         * This method is called whenever the {@code BrightnessAction} is triggered.
         * It prompts the user for the brightness and contrast percentage
         * they would like to change the image.
         * It then calls the {@link BrightnessContrast} class, providing the 
         * selected percentage values for changes in brightness and contrast.
         * </p>
         * 
         * <p>
         * It changes the contrast and brightness of the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            //create a Jframe to ask the user the changes they would like
            JFrame frame = new JFrame();
            frame.setLayout(new GridLayout(5,0));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the pop-up window 

            
            //JPanel brightPanel = new JPanel(); // panel with brightness buttons
            JLabel bLabel = new JLabel("Brightness Adjustment");
            JLabel cLabel = new JLabel("Contrast Adjustment");

            //sliders
            JSlider brightSlide = new JSlider(JSlider.HORIZONTAL, -50, 50, 0);
            JSlider contSlide = new JSlider(JSlider.HORIZONTAL, -50, 50, 0);
            
            //sets sliders to have paint labels
            Hashtable valueTable = new Hashtable();
            valueTable.put(Integer.valueOf(0), new JLabel("0%"));
            valueTable.put(Integer.valueOf(-25), new JLabel("-25%"));
            valueTable.put(Integer.valueOf(25), new JLabel("25%"));
            brightSlide.setLabelTable(valueTable);
            brightSlide.setPaintLabels(true);
            contSlide.setLabelTable(valueTable);
            contSlide.setPaintLabels(true);

            //change listener for both sliders
            ChangeListener change = new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider)e.getSource();
                    if(source == brightSlide){
                        brightness = source.getValue();
                    }else if(source == contSlide){
                        contrast = source.getValue();
                    }else{
                        System.out.println("Error");// should never reach this
                    }
                }
            };
            contSlide.addChangeListener(change);
            brightSlide.addChangeListener(change);

            //done button to confirm value selection
            JButton close = new JButton("Done");
            close.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // call BrightnessContrast(int, int), with user selected percentages
                     target.getImage().apply(new BrightnessContrast(brightness, contrast)); 
                     target.repaint();
                     target.getParent().revalidate();
                     brightness = 0;// reset brightness
                     contrast = 0;// reset contrast
                     frame.dispose(); // close the pop-up
                }
            });
            //building the frame
            frame.add(bLabel);
            frame.add(brightSlide);
            frame.add(cLabel);
            frame.add(contSlide);
            frame.add(close);
            frame.setSize(300,200);
            frame.pack();
            frame.setVisible(true); 
        }
    }

    /**
     * <p>
     * Action to add a posterise effect to the image
     * </p>
     * 
     * @see Posterisation
     */
    public class PostAction extends ImageAction{

        /**
         * <p>
         * Create a new posterise effect.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        PostAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when a posterise action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the {@code PostAction} is 
         * triggered.
         * </p>
         * 
         * <p>
         * It applies a posterisation effect to the image by calling
         * the {@link Posterisation} class.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Posterisation());
            target.repaint();
            target.getParent().revalidate();
        }
        
    }
}



