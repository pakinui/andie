package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

 /**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * Edit actions include:
 * </p>
 * 
 * <ul>
 * <li> {@link UndoAction} </li>
 * <li> {@link RedoAction} </li>
 * </ul>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EditActions {
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        actions.add(new UndoAction("Undo", null, "Undo", Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new RedoAction("Redo", null, "Redo", Integer.valueOf(KeyEvent.VK_Y)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * <p>
     * Adds a keyboard shortcut to the JMenuItem is a value has been provided in the constructor
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Edit");

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
                editMenu.add(menu);
                
            }
        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(MNEMONIC_KEY, mnemonic);
            //setAccelerator(KeyStroke.getKeyStroke((KeyEvent.VK_Z),InputEvent.CTRL_DOWN_MASK);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

     /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */   
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        
        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
