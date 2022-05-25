package cosc202.andie;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

//comment to test push and pull
/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * //change to code for lab 5
 * <p>//this is for lab 5.5
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {


    protected static JFrame frame;
    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggerd via 
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see TransformActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Set up the main GUI frame
        frame = new JFrame("ANDIE");

        Image image = ImageIO.read(new File("./src/icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());


        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        //Actions that transform the image
        TransformActions transformActions = new TransformActions();
        menuBar.add(transformActions.createMenu());

        //Make the toolBar
        JToolBar toolBar = new JToolBar();

        // Open Button
        JButton openButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/openIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        openButton.addActionListener(fileActions.actions.get(0));
        toolBar.add(openButton);

        // Save Button
        JButton saveButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/saveIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        saveButton.addActionListener(fileActions.actions.get(1));
        toolBar.add(saveButton);

        // undo Button
        JButton undoButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/undoIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        undoButton.addActionListener(editActions.actions.get(0));
        toolBar.add(undoButton);

        // redo Button
        JButton redoButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/redoIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        redoButton.addActionListener(editActions.actions.get(1));
        toolBar.add(redoButton);

        // horizontal Flip Button
        JButton hFlipButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/horizontalIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        hFlipButton.addActionListener(transformActions.actions.get(5));
        toolBar.add(hFlipButton);

        // vertical flip Button
        JButton vFlipButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/verticalIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        vFlipButton.addActionListener(transformActions.actions.get(6));
        toolBar.add(vFlipButton);

        //left rotate button
        JButton leftButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/rotateLeftIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        leftButton.addActionListener(transformActions.actions.get(4));
        toolBar.add(leftButton);

        //right rotate button
        JButton rightButton = new JButton(new ImageIcon((new ImageIcon("src/TBicons/rotateRightIcon.png").getImage()).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH)));
        rightButton.addActionListener(transformActions.actions.get(3));
        toolBar.add(rightButton);

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
        frame.add(toolBar, BorderLayout.NORTH);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        });
    }
}
