package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu contains actions that change the how the image is displayed, 
 * and the contents of the image.
 * </p>
 * 
 * <p>
 * Transform actions include:
 * </p>
 * 
 * <ul>
 * <li> {@link Rotate} </li>
 * <li> {@link Flip} </li>
 * <li> {@link Resize} </li>
 * </ul>
 * 
 * @version 1.0
 */

public class TransformActions {

    /**A list of actions in Transform menu */
    protected ArrayList<Action> actions;
    
    protected JFrame frame; //frame for pop-ups
    protected JPanel mainPanel; //panel for pop-ups
    
    public TransformActions(){
        actions = new ArrayList<Action>();
        actions.add(new RotateActions("Rotate", null, "Rotate image either 90º or 180º", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FlipAction("Flip", null, "Flip image vertically or horizontally", Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new Scale("Scale", null, "Scale the size of an image", null));
       
        //actions to be able to access for shortcuts and the toolbar, but not in the transformMenu
        actions.add(new RotateRight(null,null,null,null));
        actions.add(new RotateLeft(null,null,null,null));
        actions.add(new FlipHorizontal(null,null,null,null));
        actions.add(new FlipVertical(null,null,null,null));
        actions.add(new RotateFull(null, null, null, null));
    }

    /**
     * <p>
     * Create a menu contianing the list of Transform actions.
     * </p>
     * 
     * <p>
     * Adds a keyboard shortcut to the JMenuItem is a value has been provided in the constructor
     * </p>
     * 
     * @return The transform menu UI element.
     */
    public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        //for(Action action: actions){
            for(int i = 0; i < 3 ; i++){
                JMenuItem menu = new JMenuItem(actions.get(i));
                int menuKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();//identifies the modifier key for the OS
                //if shortcut is not null add shortcut
                if(actions.get(i).getValue("MnemonicKey") != null){
                    int idx = (int) actions.get(i).getValue("MnemonicKey");
                    char mn = (char) idx;//shortcut key
                    menu.setAccelerator(KeyStroke.getKeyStroke(mn ,menuKey));
                }
                transformMenu.add(menu);
            }
        return transformMenu;
    }
     /**
     * <p>
     * Action to open the rotate-actions pop-up JFrame.
     * </p>
     * 
     * <p>
     * From the JFrame user can select:
     * </p>
     * <ul>
     * <li>{@link Rotate#RotateLeft}</li>
     * <li>{@link Rotate#RotateRight}</li>
     * <li>{@link Rotate#RotateFull}</li>
     * </ul>
     * 
     * <p>
     * User can continue to rotate the image until either
     * closing the JFrame pop-up, or selecting the {@code done} JButton.
     * </p>
     * 
     * @see Rotate
     */
    public class RotateActions extends ImageAction {

        /**
         * <p>
         * The {@code direction} to rotate the image, which is then passed
         * onto {@link Rotate} as a variable.
         * </p>
         */
        char direction;

        /**
         * <p>
         * Create a new rotate action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        /**
         * <p>
         * Callback for when the rotate action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateActions is triggered.
         * It rotates the image one of three ways:
         * </p>
         * 
         * <ul>
         * <li> left 90 degrees: calls {@link RotateLeft}</li>
         * <li> right 90 degrees: calls {@link RotateRight} </li>
         * <li> 180 degrees: calls {@link RotateFull} </li>
         * </ul>
         * 
         * @param e The event triggering this callback.
         */
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

            left.addActionListener(actions.get(4)); //RotateLeftActions
            right.addActionListener(actions.get(3)); //RotateRightActions
            full.addActionListener(actions.get(7)); //RotateFullActions

            buttonPanel.add(left);
            buttonPanel.add(right);
            buttonPanel.add(full);

            //done button to confirm finished with rotations
            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                }
            });
            //making the frame
            panel.add(label);
            panel.add(buttonPanel);
            panel.add(done);
            frame.add(panel);   
            frame.setSize(300,200);
            frame.pack();
            frame.setVisible(true);
        }
    }
    
    /**
     * <p>
     * Action to open the flip-actions pop-up JFrame.
     * </p>
     * 
     * <p>
     * From the JFrame user can select:
     * </p>
     * <ul>
     * <li>{@link FlipVertical}</li>
     * <li>{@link FlipHorizontal}</li>
     * </ul>
     * 
     * <p>
     * User can continue to flip the image until either
     * closing the JFrame pop-up, or selecting the {@code done} JButton.
     * </p>
     * 
     * @see Flip
     */
    public class FlipAction extends ImageAction{

        /**
         * <p>
         * The {@code direction} to flip the image, which is then passed
         * onto {@link Flip} as a variable.
         * </p>
         */
        char direction;
        /**
         * <p>
         * Create a new flip action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }

        /**
         * <p>
         * Callback for when the flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipAction is triggered.
         * It flips the image one of two ways:
         * </p>
         * 
         * <ul>
         * <li> vertically: calls {@link FlipVertical}</li>
         * <li> horizontally: calls {@link FlipHorizontal} </li>
         * </ul>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //frame components
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
            //done button to confirm finished with fliping the image
            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                }
            });
            vert.addActionListener(actions.get(6)); //FlipVertical
            hor.addActionListener(actions.get(5));//FlipHorizontal

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

    /**
     * <p>
     * Action to open the resize-actions pop-up JFrame.
     * </p>
     * 
     * @see Resize
     */
    public class Scale extends ImageAction {

        /**
         * <p>
         * Create a resize action.
         * </p>
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
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever Scale is triggered.
         * It prompts the user for a resize percentage between
         * 0 and 200, then applys an appropriate {@link Resize}.
         * </p>
         * 
         * <p>
         * A number larger than 100 will make the image larger, and a
         * number smaller than 100 will make the image smaller.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int scale=0;
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(100, 10, 200, 10);
            // percentages between 10 and 200% only can be selected to ensure appropriate value
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            // jspinner for user to select the value for the scale - forces them to choose an appropriate value
            double option = JOptionPane.showOptionDialog(null,scaleSpinner, "Scale %", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scale = scaleModel.getNumber().intValue();
                //scale value determined when they click okay
            }//new image presented on the screen
            target.getImage().apply(new Resize(scale));
            target.repaint();
            target.getParent().revalidate();
        }
    }
    
    /**
     * <p>
     * Action to rotate an image 180 degrees.
     * </p>
     * 
     * @see Rotate#RotateFull
     */
    public class RotateFull extends ImageAction{
        /**
         * <p>
         * Create a new rotate-full action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateFull(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        /**
         * <p>
         * Callback for when the rotate-full action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever RotateFull is triggered.
         * It rotates the image 180 degrees.
         * </p>
         * 
         * <p>
         * It calls the {@link Rotate#RotateFull} method.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
                // full rotation - no extra data needed from user
            target.getImage().apply(new Rotate('3'));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image right 90 degrees.
     * </p>
     * 
     * @see Rotate#RotateRight
     */
    public class RotateRight extends ImageAction{

        /**
         * <p>
         * Create a new rotate-right action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateRight(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }
        /**
         * <p>
         * Callback for when the rotate-right action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever RotateRight is triggered.
         * It rotates the image right 90 degrees.
         * </p>
         * 
         * <p>
         * It calls the {@link Rotate#RotateRight} method.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate('1'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    /**
     * <p>
     * Action to rotate an image left 90 degrees.
     * </p>
     * 
     * @see Rotate#RotateLeft
     */
    public class RotateLeft extends ImageAction{
        /**
         * <p>
         * Create a new rotate-left action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateLeft(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }
        /**
         * <p>
         * Callback for when the rotate-left action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever RotateLeft is triggered.
         * It rotates the image left 90 degrees.
         * </p>
         * 
         * <p>
         * It calls the {@link Rotate#RotateLeft} method.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate('2'));
            target.repaint();
            target.getParent().revalidate();
            
        }

        
    }

    /**
     * <p>
     * Action to flip an image horizontally.
     * </p>
     * 
     * @see Flip
     */
    public class FlipHorizontal extends ImageAction{
        /**
         * <p>
         * Create a new flip-horizontal action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipHorizontal(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }
        /**
         * <p>
         * Callback for when the flip-horizontal action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever FlipHorizontal is triggered.
         * It flips the image horizontally.
         * </p>
         * 
         * <p>
         * It calls {@link Flip}, using the character {@code 'H'} to 
         * identify a horizontal flip.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Flip('H'));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to flip an image vertically.
     * </p>
     * 
     * @see Flip
     */
    public class FlipVertical extends ImageAction{

        /**
         * <p>
         * Create a new flip-vertical action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipVertical(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }
        /**
         * <p>
         * Callback for when the flip-vertical action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever FlipVertical is triggered.
         * It flips the image vertically.
         * </p>
         * 
         * <p>
         * It calls {@link Flip}, using the character {@code 'V'} to 
         * identify a vertical flip.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Flip('V'));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
    

