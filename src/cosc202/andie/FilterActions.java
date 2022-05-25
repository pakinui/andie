package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * </p>
 * 
 * <p>
 * Filter actions include:
 * </p>
 * 
 * <ul>
 * <li>{@link MeanFilter}</li>
 * <li>{@link MedianFilter}</li>
 * <li>{@link SharpenFilter}</li>
 * <li>{@link GaussianFilter}</li>
 * </ul>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", null));
        actions.add(new GaussianFilterAction("Gaussian filter", null, "Apply a Gaussian filter", null));
        actions.add(new MedianFilterAction("Median filter", null, "Apply a median filter", null));
        actions.add(new SharpenFilterAction("Sharpen filter", null, "Apply a sharpen filter", null));

        actions.add(new EmbossAction("Emboss", null, "Emboss image filter", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu filterMenu = new JMenu("Filter");
        // for(Action action: actions){
        for (Action action : actions) {
            filterMenu.add(new JMenuItem(action));
        }
        return filterMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a gaussian filter.
     * </p>
     * 
     * @see GaussianFilter
     */
    public class GaussianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new gaussian-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the gaussian-filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link GaussianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MedianFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to sharpen an image with a sharpen filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen-filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It applys an appropriately sized {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class EmbossAction extends ImageAction {

        int filterNo;
        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

    public void actionPerformed(ActionEvent e) {

        // Determine what emboss we are going to use - ask the user.
        filterNo = 0;

        // Pop-up dialog box to ask for the filter no value.
        // SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 8, 1);
        // JSpinner radiusSpinner = new JSpinner(radiusModel);
        // int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter what emboss you want (1-8)",
        //         JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);


        JFrame f = new JFrame("Emboss Filters");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocation(500,0);
        f.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(5,2));
        JButton[] buttons = new JButton[]{new JButton("1"), new JButton("2"), new JButton("3"), new JButton("4"), new JButton("5"), new JButton("6"), new JButton("7"), new JButton("8"), new JButton("9"), new JButton("10")};
        ButtonGroup bg = new ButtonGroup();
        for(JButton b : buttons){
            bg.add(b);
            buttonPanel.add(b);
            b.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    filterNo = Integer.parseInt(b.getText());
                    target.getImage().apply(new Emboss(filterNo));
                    target.repaint();
                    target.getParent().revalidate();
                    f.dispose();
                }

            });
        }
        JLabel label = new JLabel("Pick an Emboss Filter.");
        f.add(label, BorderLayout.NORTH);
        f.add(buttonPanel, BorderLayout.SOUTH);

        f.setVisible(true);
        f.pack();
        
        // Check the return value from the dialog box.
        // if (option == JOptionPane.CANCEL_OPTION) {
        //     return;
        // } else if (option == JOptionPane.OK_OPTION) {
        //     filterNo = radiusModel.getNumber().intValue();
        // }

        // // Create and apply the filter
        // target.getImage().apply(new Emboss(filterNo));
        // target.repaint();
        // target.getParent().revalidate();
    }

    }

}
