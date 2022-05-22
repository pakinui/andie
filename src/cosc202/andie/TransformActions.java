package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu contains actions that change the how the image is
 * displayed,
 * and the contents of the image.
 * </p>
 * 
 * <p>
 * Transform actions include:
 * </p>
 * 
 * <ul>
 * <li>{@link Rotate}</li>
 * <li>{@link Flip}</li>
 * <li>{@link Resize}</li>
 * </ul>
 * 
 * @version 1.0
 */

public class TransformActions {

    /** A list of actions in Transform menu */
    protected ArrayList<Action> actions;

    protected JFrame frame; // frame for pop-ups
    protected JPanel mainPanel; // panel for pop-ups

    public TransformActions() {
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
        actions.add(new StickerAction("Sticker", null, "Add stickers to an image", null)); // 8
        actions.add(new CropAction("Crop Image", null, "Crop an Image", null));//9
        actions.add(new DrawAction( "Draw", null, "Draw", null));
    }   

    /**
     * <p>
     * Create a menu contianing the list of Transform actions.
     * </p>
     * 
     * <p>
     * Adds a keyboard shortcut to the JMenuItem is a value has been provided in the
     * constructor
     * </p>
     * 
     * @return The transform menu UI element.
     */
    public JMenu createMenu() {
        JMenu transformMenu = new JMenu("Transform");

        // for(Action action: actions){
        for (int i = 0; i < 3; i++) {
            JMenuItem menu = new JMenuItem(actions.get(i));
            int menuKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();// identifies the modifier key for the
                                                                                 // OS
            // if shortcut is not null add shortcut
            if (actions.get(i).getValue("MnemonicKey") != null) {
                int idx = (int) actions.get(i).getValue("MnemonicKey");
                char mn = (char) idx;// shortcut key
                menu.setAccelerator(KeyStroke.getKeyStroke(mn, menuKey));
            }
            transformMenu.add(menu);
        }
        transformMenu.add(actions.get(8));
        transformMenu.add(actions.get(9));
        transformMenu.add(actions.get(10));
       
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
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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
         * <li>left 90 degrees: calls {@link RotateLeft}</li>
         * <li>right 90 degrees: calls {@link RotateRight}</li>
         * <li>180 degrees: calls {@link RotateFull}</li>
         * </ul>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // frame
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // components
            JPanel panel = new JPanel(); // main panel
            panel.setLayout(new GridLayout(3, 1));
            JPanel buttonPanel = new JPanel(); // panel for buttons
            buttonPanel.setLayout(new GridLayout(1, 3));

            JLabel label = new JLabel("Which direction would you like to rotate the image?");
            JButton left = new JButton("Left 90º");
            JButton right = new JButton("Right 90º");
            JButton full = new JButton("180º");
            ButtonGroup group = new ButtonGroup();
            group.add(left);
            group.add(right);
            group.add(full);

            left.addActionListener(actions.get(4)); // RotateLeftActions
            right.addActionListener(actions.get(3)); // RotateRightActions
            full.addActionListener(actions.get(7)); // RotateFullActions

            buttonPanel.add(left);
            buttonPanel.add(right);
            buttonPanel.add(full);

            // done button to confirm finished with rotations
            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            // making the frame
            panel.add(label);
            panel.add(buttonPanel);
            panel.add(done);
            frame.add(panel);
            frame.setSize(300, 200);
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
    public class FlipAction extends ImageAction {

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
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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
         * <li>vertically: calls {@link FlipVertical}</li>
         * <li>horizontally: calls {@link FlipHorizontal}</li>
         * </ul>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // frame components
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            mainPanel = new JPanel();
            mainPanel.setPreferredSize(new Dimension(300, 200));
            mainPanel.setLayout(new GridLayout(3, 1));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2));

            // buttons
            JButton vert = new JButton("Vertical");
            JButton hor = new JButton("Horizontal");
            ButtonGroup group = new ButtonGroup();
            group.add(vert);
            group.add(hor);
            // done button to confirm finished with fliping the image
            JButton done = new JButton("Done");
            done.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            vert.addActionListener(actions.get(6)); // FlipVertical
            hor.addActionListener(actions.get(5));// FlipHorizontal

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
         * @param name     name of action(if ignored, null)
         * @param icon     an icon to rep the action (if ignored, null)
         * @param desc     brief desc of ation (if ignored, null)
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
            int scale = 0;
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(100, 10, 200, 10);
            // percentages between 10 and 200% only can be selected to ensure appropriate
            // value
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            // jspinner for user to select the value for the scale - forces them to choose
            // an appropriate value
            double option = JOptionPane.showOptionDialog(null, scaleSpinner, "Scale %", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scale = scaleModel.getNumber().intValue();
                // scale value determined when they click okay
            } // new image presented on the screen
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
    public class RotateFull extends ImageAction {
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
    public class RotateRight extends ImageAction {

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
    public class RotateLeft extends ImageAction {
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
    public class FlipHorizontal extends ImageAction {
        /**
         * <p>
         * Create a new flip-horizontal action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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
    public class FlipVertical extends ImageAction {

        /**
         * <p>
         * Create a new flip-vertical action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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

    public class DrawAction extends ImageAction {


        DrawAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame("Draw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Draw drawing = new Draw(target);
        JLabel coordinates = new JLabel("Mouse coordinates");
        coordinates.setForeground(Color.BLUE);
        frame.add(coordinates, BorderLayout.SOUTH);
		frame.setLayout(new BorderLayout());
        frame.add(drawing, BorderLayout.NORTH);

		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);

    }
    }
     /**
     * <p>
     * Action to add Stickers to an image.
     * </p>
     * 
     * @see Sticker
     */
    public class StickerAction extends ImageAction {

        int num;//0-5 for same num in arr
        
        int x;//location of mouse click
        int y;
        
        int size;//size of icon
        
        boolean fin = false;//if false mouse listener work, once done is clicked it becomes true and it wont work
        

        /**
         * <p>
         * Create a new sticker action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StickerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            fin = false;
        }

        /**
         * <p>
         * Callback for when the sticker action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever StickerAction is triggered.
         * It allows the user to add stickers to the image.
         * </p>
         * 
         * <p>
         * It calls {@link Sticker} when a mouseClick occurs with the 
         * specified sticker selected in the pop-up JFrame.
         * </p>
         * 
         * <p>
         * The size of the stickers is able to be altered.
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            fin = false;
            
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setPreferredSize(new Dimension(300,300));

            JButton[] buttons = new JButton[6];
            ImageIcon[] icons = new ImageIcon[6];

            ImageIcon smile = new ImageIcon((new ImageIcon("src/stickers/smile.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            ImageIcon fear = new ImageIcon((new ImageIcon("src/stickers/fear.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            ImageIcon wink = new ImageIcon((new ImageIcon("src/stickers/wink.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            ImageIcon sunglasses = new ImageIcon((new ImageIcon("src/stickers/sunglasses.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            ImageIcon tears = new ImageIcon((new ImageIcon("src/stickers/happyTears.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            ImageIcon heart = new ImageIcon((new ImageIcon("src/stickers/heartEye.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
            icons[0] = smile;
            icons[1] = fear;
            icons[2] = wink;
            icons[3] = sunglasses;
            icons[4] = tears;
            icons[5] = heart;

            for(int i = 0; i < buttons.length; i++){
                buttons[i] = new JButton(icons[i]);//create JButtons
            }
            //JButton action listener
            ActionListener listen = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton select = (JButton) e.getSource();
                    for(int i = 0; i < buttons.length; i++){
                        if(select == buttons[i]){
                            num = i; //set num to the associated number for the seleced sticker
                        }
                    } 
                }
            };

            //adjust sticker size
            JPanel scrollPanel = new JPanel();
            JSlider bar = new JSlider(JSlider.HORIZONTAL);
            bar.setMaximum(190);
            bar.setValue(100);
            bar.setMinimum(10);
            scrollPanel.add(new JLabel("Adjust sticker size."));
            scrollPanel.add(bar);

            bar.addChangeListener(new ChangeListener(){

                @Override
                public void stateChanged(ChangeEvent e) {
                    size = bar.getValue();//change of sticker size if applicable
                }
            });

           
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3,1));

            JPanel bPanel = new JPanel();//button panel
            bPanel.setLayout(new GridLayout(2,3));
            bPanel.setBorder(new EmptyBorder(10,10,10,10));

            ButtonGroup group = new ButtonGroup();
            for(JButton b : buttons){
                b.setSize(new Dimension(50, 50));
                bPanel.add(b);
                group.add(b);
                b.addActionListener(listen);
            }

            JButton done = new JButton("Done");// end of sticker action
            done.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                    fin = true;

                }
            });
            
            bPanel.setSize(new Dimension(290, 100));
            panel.add(bPanel);
            panel.add(scrollPanel);
            panel.add(done);
            frame.add(panel);
            frame.setVisible(true);
            frame.pack();

            //mouse listener stuff
            MouseListener mouse = new MouseListener(){

                /**
                 * <p>
                 * Calls the {@code Sticker} class to apply a sticker 
                 * where the mouse is clicked onto the target image.
                 * </p>
                 * 
                 * @param e MouseEvent mouseClicked.
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!fin && frame.isVisible()){
                        x = e.getX();
                        y = e.getY();
                        
                       // Icon icon = buttons[num].getIcon();
                        Image img = icons[num].getImage();
                        target.getImage().apply(new Sticker(x, y, size, img));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mouseClicked(e);   
                }

                @Override
                public void mouseEntered(MouseEvent e) {  
                }

                @Override
                public void mouseExited(MouseEvent e) { 
                }
            };
            target.addMouseListener(mouse);
        } 
    }
    
    /**
     * <p>
     * Action to crop an image.
     * </p>
     * 
     * @see CropFunction
     * @see MouseCrop
     */
    public class CropAction extends ImageAction {

        /**
         * <p>
         * A new {@code MouseCrop}.
         */
        MouseCrop m;

        boolean once;//boolean to track if crop has happened

        /**
         * <p>
         * Create a new crop action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            once = false;
        }

        /**
         * <p>
         * Callback for when the crop action is triggered.
         * </p>
         * 
         * <p>
         * Creates a {@code MouseCrop} to find the reactangle which the user 
         * inputs. A mouseMotionListener is added to target which calls the 
         * {@code CropFunction} class when the {@code MouseCrop} rectangle is complete. 
         * The crop is then applied to target.
         * </p>
         * 
         * 
         * @param e The event triggering this callback.
         * @see MouseCrop
         * @see CropFunction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                m = new MouseCrop(target);
                once = false;
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Unexpected Error, please try again.");
            }
            target.addMouseMotionListener(new MouseAdapter(){
                
                /**
                 * <p>
                 * Listener to call the {@code CropFunction} class 
                 * when the rectangle from {@code MouseCrop - m} has been completed.
                 * </p>
                 * 
                 * @param e MouseEvent mouseMoved.
                 */
                public void mouseMoved(MouseEvent e){

                    if(m.finalRect != null && !once){
                        
                        Rectangle r = m.getRect();
                        target.getImage().apply(new CropFunction(r));
                        target.repaint();
                        target.getParent().revalidate();
                        m.reset();
                        once = true;//rectangle is complete
                    }
                }
            });
          
        }
    }
}
