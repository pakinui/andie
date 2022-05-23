package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.geom.Line2D;

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
        actions.add(
                new RotateActions("Rotate", null, "Rotate image either 90º or 180º", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(
                new FlipAction("Flip", null, "Flip image vertically or horizontally", Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new Scale("Scale", null, "Scale the size of an image", null));

        // actions to be able to access for shortcuts and the toolbar, but not in the
        // transformMenu
        actions.add(new RotateRight(null, null, null, null));
        actions.add(new RotateLeft(null, null, null, null));
        actions.add(new FlipHorizontal(null, null, null, null));
        actions.add(new FlipVertical(null, null, null, null));
        actions.add(new RotateFull(null, null, null, null));
        actions.add(new StickerAction("Sticker", null, "Add stickers to an image", null)); // 8
        actions.add(new CropAction("Crop Image", null, "Crop an Image", null));// 9
        actions.add(new DrawAction("Draw", null, "Draw", null));
        actions.add(new Draw2Action("Draw2", null, "Draw", null));
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
        transformMenu.add(actions.get(11));

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
        public void actionPerformed(ActionEvent e) {
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

            target.getImage().apply(drawing);
            target.repaint();
            target.getParent().revalidate();

        }
    }

    public class Draw2Action extends ImageAction {

        BufferedImage buffOver;
        MyPanel pan;
        JFrame f;

        ArrayList<BufferedImage> buffArray = new ArrayList<>();
        ArrayList<Graphics2D> graphicsArray = new ArrayList<>();

        JFrame frame;
        JPanel topPanel;
        JPanel botPanel;
        GridBagLayout grid;
        GridBagConstraints gbc;
        JLabel coords;
        JButton done;
        ArrayList<Point> line = new ArrayList<>();
        ArrayList<Color> lineColour = new ArrayList<>();
        //boolean finished;
        //boolean completed;
        JButton undo;
        JButton clear;
        JComboBox<String> shapeSelect;
        String selected;
        JButton foreground;
        JButton background;
        JCheckBox filled;
        JCheckBox gradient;
        JCheckBox dashed;

        JTextField dLength;
        JTextField lWidth;

        Color foregroundColour;
        Color backgroundColour;

        
        JLabel labelImg;

        BufferedImage buff;
        Image img;
        GridLayout gridLay;
        JLabel lab;

        Draw2Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            //buffOver = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
            selected = "Rectangle";
            target.repaint();
            
        }

        protected void createMenu(){

            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.setSize(new Dimension(100, 500));
            GridBagLayout gridLay = new GridBagLayout();
            frame.setLayout(gridLay);
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            
           

            // top panel
            topPanel = createTopPanel();
            topPanel.setSize(new Dimension(100,400));
            gridLay.setConstraints(topPanel, gbc2);
            


            // coords
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            coords = new JLabel("(" + 0 + ", " + 0 + ")");
            // coords.setHorizontalAlignment(JLabel.WEST);
            labelPanel.add(coords);
            labelPanel.setSize(new Dimension(100,50));
            

            done = new JButton("Done");
            done.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    frame.dispose();
                    //for (int i = 0; i < line.size(); i++) {

                        //Point p = line.get(i);
                        target.getImage().apply(new DrawAgain(pan));
                        target.repaint();
                        target.getParent().revalidate();
                        pan = new MyPanel();
                        buff = null;
                    //}
                    
                }

            });

            labelPanel.add(done);
            gbc2.gridx = 0;
            gbc2.gridy = 1;
            gridLay.setConstraints(labelPanel, gbc2);

            frame.add(topPanel);
            //frame.add(botPanel);
            frame.add(labelPanel);

            frame.setLocation(0, 515);
            frame.pack();
            frame.setVisible(true);

        }

        private JPanel createBotPanel() {

            ImagePanel panel = new ImagePanel();
            // panel.setSize(new Dimension(700,500));
            // int height = target.getHeight();
            // int width = target.getWidth();
            // panel.setSize(new Dimension(width + 10, height + 50));
            //System.out.println("height: " + height + ", pan hieght: " + panel.getHeight());
            // gbc.insets = new Insets(20,20,20,20);
    
            grid.setConstraints(panel, gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 0, 20, 30)));
    
            buff = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) target.getGraphics();
            g.drawImage(buff, null, 0, 0);
            g.setColor(Color.BLACK);
            g.fillRect(10,10, 40,22);
            
            img = (Image) buff;
            JLabel imgLabel = new JLabel(new ImageIcon(img));

            panel.add(target);
            panel.repaint();
            return panel;
        }
    
        private JPanel createTopPanel() {
    
            JPanel panel = new JPanel();
            grid = new GridBagLayout();
            gbc = new GridBagConstraints();
            
            panel.setLayout(grid);
    
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
    
            // undo button
            undo = new JButton("Undo");
            undo.addActionListener(new ActionListener() {
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    // add
    
                }
    
            });
            gbc.gridx = 0;
            gbc.gridy = 0;
            grid.setConstraints(undo, gbc);
            undo.setSize(new Dimension(90,40));
            panel.add(undo);
    
            // clear button
            clear = new JButton("Clear");
            clear.addActionListener(new ActionListener() {
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    // add
    
                }
    
            });
             gbc.gridx = 1;
             gbc.gridy = 0;
             grid.setConstraints(clear, gbc);
            clear.setSize(new Dimension(90,40));
            panel.add(clear);
    
            // shape selection
            JLabel shapeLabel = new JLabel("Shape:");
            JPanel shapePanel = new JPanel();
            
            gbc.gridx = 2;
            gbc.gridy = 0;
            
    
            shapePanel.add(shapeLabel);
    
            shapeSelect = new JComboBox<>(new String[] { "Rectangle", "Oval", "Line", "Eclipse", "Paint Brush" });
            shapeSelect.addActionListener(new ActionListener() {
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = (String) shapeSelect.getSelectedItem();
                    System.out.println(selected);
                    
    
                }
    
            });
            // gbc.gridx = 2;
            //  gbc.gridy = 0;
            shapePanel.add(shapeSelect);
             grid.setConstraints(shapePanel, gbc);
            panel.add(shapePanel);
            


            // 1st col
            foreground = new JButton("1st Colour");
            foreground.setBackground(Color.BLACK);
            foregroundColour = (Color.BLACK);
            foreground.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // color1 = JColorChooser.showDialog(null, "Pick your
                    // color", Color.BLACK);
                    
                    foregroundColour = JColorChooser.showDialog(null, "Pick your color", Color.BLACK);
                    //System.out.println(foregroundColour);
                    foreground.setBackground(foregroundColour);
    
                }
            });
            gbc.gridx = 3;
             gbc.gridy = 0;
             grid.setConstraints(foreground, gbc);
            panel.add(foreground);
    
            // 2nd col
            background = new JButton("2nd Colour");
            background.setBackground(Color.WHITE);
            backgroundColour = (Color.WHITE);
            background.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    /*
                     * color2 = JColorChooser.showDialog(null,
                     * "Pick your color.", color2); if (color2 == null) color2 =
                     * Color.WHITE;
                     */
                    background.setBackground(JColorChooser.showDialog(null, "Pick your color", Color.BLACK));
                }
            });
            gbc.gridx = 4;
             gbc.gridy = 0;
            // gbc.gridwidth = GridBagConstraints.REMAINDER;
             grid.setConstraints(background, gbc);
            panel.add(background);
    
            filled = new JCheckBox("Filled");
            gbc.gridx = 0;
             gbc.gridy = 1;
             grid.setConstraints(filled, gbc);
            panel.add(filled);
    
            gradient = new JCheckBox("Use Gradient");
            gbc.gridx = 1;
             gbc.gridy = 1;
             grid.setConstraints(gradient, gbc);
            panel.add(gradient);
    
            dashed = new JCheckBox("Dashed");
            gbc.gridx = 2;
             gbc.gridy = 1;
             grid.setConstraints(dashed, gbc);
            panel.add(dashed);
    
            
            JPanel dashPanel = new JPanel();
            JLabel dashLabel = new JLabel("Dash Length:");
            gbc.gridx = 3;
             gbc.gridy = 1;
             //grid.setConstraints(dashLabel, gbc);
            dashPanel.add(dashLabel);
    
            dLength = new JTextField("10");
            //gbc.gridx = 4;
             //gbc.gridy = 1;
             //grid.setConstraints(dLength, gbc);
            dashPanel.add(dLength);
            grid.setConstraints(dashPanel, gbc);
            panel.add(dashPanel);
    
            //JPanel linePanel = new JPanel();
            //linePanel.setLayout(new GridLayout(1,2));

            JPanel linePanel = new JPanel();
            JLabel lineLabel = new JLabel("Line Width:");
            gbc.gridx = 4;
            gbc.gridy = 1;
            
            //  grid.setConstraints(lineLabel, gbc);
            linePanel.add(lineLabel);
            
            // gbc.gridx = 6;
            // gbc.gridy = 1;
            
            lWidth = new JTextField("2");
             
            linePanel.add(lWidth);
            grid.setConstraints(linePanel, gbc);
            panel.add(linePanel);

            
            
    
            //grid.setConstraints(panel, gbc);
            
            return panel;
        }

        private void createListeners() {

            target.addMouseMotionListener(new MouseAdapter() {
    
                public void mouseDragged(MouseEvent e) {
                    //if (!finished) {
                        coords.setText("(" + e.getX() + ", " + e.getY() + ")");
                        if(selected.equals("Paint Brush")){
                            line.add(e.getPoint());
                            lineColour.add(foregroundColour);
                            // pixel.setLocation(e.getPoint());
        
                            pan.repaint();
                            target.repaint();
                            
                        }else if(selected.equals("Rectangle")){


                        }
                        
                       
                    //}
    
                }
    
                public void mouseMoved(MouseEvent e) {
    
                    coords.setText("(" + e.getX() + ", " + e.getY() + ")");
                    // target.repaint();
                }
    
            });
    
            target.addMouseListener(new MouseAdapter() {
    
                public void mouseReleased(MouseEvent e) {
                    try {
    
                        
                        // Graphics2D g = (Graphics2D) target.getGraphics();
                        // // BufferedImage newImg = new BufferedImage(input.getWidth(), input.getHeight(),
                        // // BufferedImage.TYPE_INT_ARGB);
                        // //System.out.println(pan.getWidth() + " W - H " + pan.getHeight());
                        // BufferedImage panBuff = new BufferedImage(target.getWidth(), target.getHeight(),
                        //         BufferedImage.TYPE_INT_ARGB);
    
                        // g.drawImage(panBuff, null, 0, 0);
    
                        // Graphics2D g2 = (Graphics2D) target.getGraphics();
                        // BufferedImage buff = new BufferedImage(target.getWidth(), target.getHeight(),
                        //         BufferedImage.TYPE_INT_ARGB);
                        // g2.drawImage(buff, null, 0, 0);// pan as a buffered img
    
                        // Graphics2D gr = (Graphics2D) buff.getGraphics();
                        // //gr.drawImage(input, null, 0, 0);
                        
                        // buffArray.add(buffOver);
                        // //graphicsArray.add()
                        // // Image img = (Image) input;
                        // // labelImg = new JLabel(new ImageIcon(img));
                        
    
                        // line.clear();
                        if (line.isEmpty())
                            System.out.println("EMPTY");
                        // lineColour = new ArrayList<>();
                        // lineColour.clear();
                    } catch (Exception ex) {
                        // System.out.println("error");
                        ex.printStackTrace();
                    }
    
                }
            });
    
        }

        private void addOverlay() {
            target.repaint();
            pan = new MyPanel();
            pan.setOpaque(false);//false = see though, true = see panel
            //pan.setVisible(true);

            //pan.add(new JLabel("PANEL PANEL"));
            //JPanel test = new JPanel();
            //pan.setBackground(Color.RED);
            //test.setSize(new Dimension(100,100));


            LayoutManager over = new OverlayLayout(target);
            target.setLayout(over);
            System.out.println(target.getWidth() + " W -- H" + target.getHeight());
            pan.setSize(target.getSize());
            
            
            //System.out.println(pan.getWidth() + " W -1 H " + pan.getHeight());
            target.add(pan);
            //target.add(test);
            
            
    
        }

        

        protected class MyPanel extends ImagePanel {

            //public BufferedImage buffOver;

    
            MyPanel() {
                
                //buffOver = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
                buff = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
                

                
    
            }
    
            // protected BufferedImage getBuff(){
    
            //     return buffOver;
            // }
    
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    if(buff != null){
                        if(selected.equals("Paint Brush")){
                            Graphics2D g2 = (Graphics2D) g;
                            Graphics2D g3 = (Graphics2D) buff.getGraphics();
        
                            // g.fillOval(p.x, p.y, 10, 10);
                            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                            g3.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
                            // g.fillOval(pixel.x, pixel.y, 10, 10);
                            for (int i = 1; i < line.size(); i++) {
                                Point p = line.get(i);
                                Point pBefore = line.get(i - 1);
                                Color c = lineColour.get(i);
                                g2.setColor(c);
                                g2.draw(new Line2D.Float(pBefore, p));
                                g3.setColor(c);
                                g3.draw(new Line2D.Float(pBefore, p));
                                
                                lab = new JLabel(new ImageIcon(buff));
                            }
                        }else if(selected.equals("Rectangle")){

                            Graphics2D g2 = (Graphics2D) g;

                            //MouseCrop mc = new MouseCrop(target);

                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
    
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            target.repaint();
            createMenu();
            addOverlay();
            createListeners();
            


            // f = new JFrame("trail");
            // f.setLayout(new GridLayout(2,1));
            // lab = new JLabel(new ImageIcon(buff));
            // f.add(pan);
            // f.add(lab);
            // f.setLocation(400,0);
            // f.setVisible(true);
            // f.pack();
            //System.out.println(line);
            //System.out.println(line.size());


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

        int num;// 0-5 for same num in arr

        int x;// location of mouse click
        int y;

        int size;// size of icon

        boolean fin = false;// if false mouse listener work, once done is clicked it becomes true and it
                            // wont work

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
            frame.setPreferredSize(new Dimension(300, 300));

            JButton[] buttons = new JButton[6];
            ImageIcon[] icons = new ImageIcon[6];

            ImageIcon smile = new ImageIcon((new ImageIcon("src/stickers/smile.png").getImage()).getScaledInstance(20,
                    20, java.awt.Image.SCALE_SMOOTH));
            ImageIcon fear = new ImageIcon((new ImageIcon("src/stickers/fear.png").getImage()).getScaledInstance(20, 20,
                    java.awt.Image.SCALE_SMOOTH));
            ImageIcon wink = new ImageIcon((new ImageIcon("src/stickers/wink.png").getImage()).getScaledInstance(20, 20,
                    java.awt.Image.SCALE_SMOOTH));
            ImageIcon sunglasses = new ImageIcon((new ImageIcon("src/stickers/sunglasses.png").getImage())
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            ImageIcon tears = new ImageIcon((new ImageIcon("src/stickers/happyTears.png").getImage())
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            ImageIcon heart = new ImageIcon((new ImageIcon("src/stickers/heartEye.png").getImage())
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            icons[0] = smile;
            icons[1] = fear;
            icons[2] = wink;
            icons[3] = sunglasses;
            icons[4] = tears;
            icons[5] = heart;

            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(icons[i]);// create JButtons
            }
            // JButton action listener
            ActionListener listen = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton select = (JButton) e.getSource();
                    for (int i = 0; i < buttons.length; i++) {
                        if (select == buttons[i]) {
                            num = i; // set num to the associated number for the seleced sticker
                        }
                    }
                }
            };

            // adjust sticker size
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
                    size = bar.getValue();// change of sticker size if applicable
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));

            JPanel bPanel = new JPanel();// button panel
            bPanel.setLayout(new GridLayout(2, 3));
            bPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            ButtonGroup group = new ButtonGroup();
            for (JButton b : buttons) {
                b.setSize(new Dimension(50, 50));
                bPanel.add(b);
                group.add(b);
                b.addActionListener(listen);
            }

            JButton done = new JButton("Done");// end of sticker action
            done.addActionListener(new ActionListener() {
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

            // mouse listener stuff
            MouseListener mouse = new MouseListener() {

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
                    if (!fin && frame.isVisible()) {
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

        boolean once;// boolean to track if crop has happened

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

            try {
                m = new MouseCrop(target);
                once = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Unexpected Error, please try again.");
            }
            target.addMouseMotionListener(new MouseAdapter() {

                /**
                 * <p>
                 * Listener to call the {@code CropFunction} class
                 * when the rectangle from {@code MouseCrop - m} has been completed.
                 * </p>
                 * 
                 * @param e MouseEvent mouseMoved.
                 */
                public void mouseMoved(MouseEvent e) {

                    if (m.finalRect != null && !once) {

                        Rectangle r = m.getRect();
                        target.getImage().apply(new CropFunction(r));
                        target.repaint();
                        target.getParent().revalidate();
                        m.reset();
                        once = true;// rectangle is complete
                    }
                }
            });

        }
    }
}
