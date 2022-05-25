package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>
 * A class which is used to show the rectangle the user is selecting 
 * when the crop an image.
 * </p>
 * 
 * <p>
 * This selection can be done via a mouse drag or two clicks of the mouse.
 * </p>
 * 
 * @author Poppy Schlaadt
 */
public class MouseCrop {

    ImagePanel target;
    boolean rectDone;//boolean to see if rectangle is complete
    // start with the drag
    int[] topL;// topL[0] = top left corner x, topL[1] = top left corner y
    int[] botR;// topL[0] = bottom corner x, topL[1] = bottom right corner y
    int height;//rectangle height
    int width;//rectangle width
    Rectangle current;
    Rectangle oldRect = new Rectangle();
    Rectangle drawRect;
    Rectangle newRect;
    Graphics g;
    MyPanel pan;

    // two clicks to select rect
    boolean clickOne;//has the first click happened
    int clicked;// another way to track if the first click has happned
    int[] clickTop;//same as topL but for the two-click rectangle option
    int[] clickBot;//same as botR but for the two-click rectangle option
    Rectangle clickRect;//rectangle for two-click option

    // label
    String rectLabel;
    
    Rectangle finalRect;


    MouseCrop(ImagePanel target) {

        addOverlay(target);
        addListeners(target);
        
    }

    /**
     * <p>
     * Adds a transparent layer to the target ImagePanel.
     * </p>
     * 
     * <p>
     * Uses the {@code MyPanel} class which extends {@code JPanel} 
     * to override the {@code paintComponent} method.
     * </p>
     * 
     * <p>
     * Allows the user to know what area of the image they are 
     * selecting.
     * </p>
     * 
     * @param target The ImagePanel to lay the transparent {@code MyPanel} on top of.
     * @see MyPanel
     */
    private void addOverlay(ImagePanel target){

        rectDone = false;
        this.target = target;

        // add a transpaernt overlay panel to draw the rect on
        pan = new MyPanel();
        pan.setOpaque(false);
        pan.setVisible(true);
        LayoutManager over = new OverlayLayout(target);
        target.setLayout(over);
        pan.setSize(target.getSize());
        target.add(pan); // transparent layer now ontop of target

        clickOne = false;
        clicked = 0;


        g = (Graphics2D) pan.getGraphics();

        botR = new int[] { -1, -1 };// 0 = x, 1 = y
        topL = new int[] { -1, -1 };

        clickBot = new int[] { -1, -1 };// 0 = x, 1 = y
        clickTop = new int[] { -1, -1 };
    }

    /**
     * <p>
     * A method to add both a MouseListener and a {@code MouseMotionListener} 
     * to the target ImagePanel.
     * </p>
     * 
     * @param target The ImagePanel to add the action listeners to.
     */
    private void addListeners(ImagePanel target){

        target.addMouseListener(new MouseAdapter() {
            boolean cropDone = false;

            /**
             * <p>
             * Listener which initalises the location of a mouse press 
             * to make a rectangle with a drag.
             * </p>
             * 
             * @param e MouseEvent of mouse pressed.
             */
            public void mousePressed(MouseEvent e) {
                
                if (!rectDone) {// if there is not a rectangle already started
                    //for a rectangle from a drag
                    topL[0] = e.getX();
                    topL[1] = e.getY();

                    current = new Rectangle(topL[0], topL[1], 0, 0);//create rectangle
                }
            }
            /**
             * <p>
             * Listener which deals with creating a rectangle with 
             * two-clicks.
             * </p>
             * 
             * @param e MouseEvent of mouse clicked.
             */
            public void mouseClicked(MouseEvent e) {
                if (!cropDone || !rectDone) {//if there is no rectangle
                    
                    if (!clickOne || clicked == 0) {//if it is the first click
                        clickTop = new int[] { e.getX(), e.getY() };
                        clickOne = true;
                       
                        clicked = 1;
                        clickRect = new Rectangle(clickTop[0], clickTop[1], 0, 0);

                        //updates the label for the rectangle location
                        rectLabel = "rectangle from: " + clickRect.x + ", " + clickRect.y + " -> ";
                        if (!cropDone)
                            pan.repaint();
                    } else if (clickOne || clicked == 1) {// if its the second click

                        clickBot = new int[] { e.getX(), e.getY() };
                        calcRect(clickRect, clickTop, clickBot);//update rectangle
                        finalRect = clickRect;//create final rectangle to crop
                        reset();// reset all values
                        pan.repaint();
                        cropDone = true;
                        rectDone = true;
                    } else {
                        System.out.println("Error");
                    }
                }
            }

            /**
             * <p>
             * Listener for when the mouse is released for a rectange created by 
             * a drag.
             * </p>
             * 
             * @param e MouseEvent of mouse release.
             */
            public void mouseReleased(MouseEvent e) {
                try{
                    if (current.width != 0) {
                        if (!cropDone || !rectDone) {

                            rectUpdate(e, current);
                            width = current.width;
                            height = current.height;
                            finalRect = current;//create final rectangle to crop
                            reset();
                            pan.repaint();
                            cropDone = true;
                            rectDone = true;
                        }
                    }
                }catch(Exception ex){

                }
            }
        });

        target.addMouseMotionListener(new MouseAdapter() {

            /**
             * <p>
             * Listener to deal with a rectangle via dragging.
             * </p>
             * 
             * <p>
             * If rectanlge has been created via a mouse press, then 
             * the mouse drag will update the current rectangle size. 
             * Aswell as the label which shows the location of the rectangle.
             * </p>
             * 
             * @param e MouseEvent of mouseDragged.
             */
            public void mouseDragged(MouseEvent e) {
                try{
                    if (!rectDone) {
                        updateSize(e);
                        int botX = current.x + current.width;
                        int botY = current.y + current.height;
                        rectLabel = "rectangle from: " + current.x + ", " + current.y + " -> " + botX + ", " + botY;
                        pan.repaint();
                    }
                }catch(Exception ex){

                }
            }

            /**
             * <p>
             * Listener to deal with a mouse move action.
             * </p>
             * 
             * <p>
             * Handles the rectangle to identify the selected area when making a rectangle 
             * with two-clicks. Aswell as updating the label of teh rectangles location.
             * </p>
             * 
             * @param e MouseEvent of mouseMoved.
             */
            public void mouseMoved(MouseEvent e) {

                if (clickOne && !rectDone) {
                    clickBot[0] = e.getX();
                    clickBot[1] = e.getY();
                    calcRect(clickRect, clickTop, clickBot);
                    rectLabel = "rectangle from: " + clickTop[0] + ", " + clickTop[1] + " -> " + e.getX() + ", "
                            + e.getY();
                    topL[0] = clickRect.x;
                    topL[1] = clickRect.y;
                    botR[0] = (clickRect.width);
                    botR[1] = (clickRect.height);
                    pan.repaint();
                }
            }
        });
    }

    /**
     * <p>
     * A method to access the rectangle which has been selected.
     * </p>
     * 
     * @return the selected rectangle to crop.
     */
    protected Rectangle getRect() {

        return finalRect;
    }

    /**
     * <p>
     * Method to calculate the rectangle size.
     * </p>
     * 
     * @param r Rectangle to calculate the size of.
     * @param top array to update for r's top left x and y coordinates.
     * @param bot array to update for r's bottom right x and y coordinates.
     */
    void calcRect(Rectangle r, int[] top, int[] bot) {

        int x = top[0];//rectangle top left corner x coordinate
        int y = top[1];//rectangle top left corner y coordinate

        int w = bot[0];//width
        int h = bot[1];//height

        if (x < w && y < h) {
            top = new int[] { x, y };
            bot = new int[] { w, h };
        } else if (x < w && y > h) {
            top = new int[] { x, h };
            bot = new int[] { w, y };
        } else if (x > w && y > h) {
            top = new int[] { w, h };
            bot = new int[] { x, y };
        } else {
            top = new int[] { w, y };
            bot = new int[] { x, h };
        }
        width = bot[0] - top[0];
        height = bot[1] - top[1];

        bot[0] = width;
        bot[1] = height;

        r.setBounds(top[0], top[1], width, height);//update r size.
    }

    /**
     * <p>
     * Method to update the current size of the rectangle while the
     * mouse is being dragegd.
     * </p>
     * 
     * @param e The mouse event with new location to update current
     */
    void updateSize(MouseEvent e) {
       try{
       
            int x = e.getX();
            int y = e.getY();
            
            current.setSize(x - current.x, y - current.y);
            updateRect(x, y);

            newRect = drawRect.union(oldRect); //newRect is a union of draw and old rect
            topL[0] = newRect.x;
            topL[1] = newRect.y;
            botR[0] = newRect.width;
            botR[1] = newRect.height;
            pan.repaint();
       }catch(Exception ex){

       }
    }

    /**
     * <p>
     * MyPanel class which extends {@code ImagePanel} class.
     * </p>
     * 
     * <p>
     * Instance is created to override the {@code paintComponent} method.
     * </p>
     */
    public class MyPanel extends ImagePanel {
        /**
         * <p>
         * Method to override the {@code ImagePanel} 
         * {@code paintComponent} method. Paints a rectangle so the 
         * user knows where they will be cropping as well as a label to 
         * show the rectangle information.
         * </p>
         * 
         * @param g Graphics for panel.
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (current != null || clickRect != null) {

                    //rectangle
                    g.setXORMode(Color.white);
                    g.drawRect(topL[0], topL[1], botR[0], botR[1]);
                    //label
                    Font fnt = new Font("Ariel", Font.BOLD, 10);
                    g.setFont(fnt);
                    g.drawString(rectLabel, 10, 20);
  
            }
        }
    }

    /**
     * <p>
     * Method called by the mouseListeners on target. Used when 
     * creating a rectangle with two-clicks.
     * </p>
     * 
     * <p>
     * When the mouse in released this method is called, it updates the rectangle 
     * for the final time when the mouse is released.
     * </p>
     * 
     * @param e MouseEvent of mouseRelease.
     * @param r Rectangle to update.
     */
    void rectUpdate(MouseEvent e, Rectangle r) {

        int w = e.getX();
        int h = e.getY();

        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;

        if (x < w && y < h) {
            topL = new int[] { x, y };
            botR = new int[] { w, h };
        } else if (x < w && y > h) {
            topL = new int[] { x, h };
            botR = new int[] { w, y };
        } else if (x > w && y > h) {
            topL = new int[] { w, h };
            botR = new int[] { x, y };
        } else {
            topL = new int[] { w, y };
            botR = new int[] { x, h };
        }
        width = botR[0] - topL[0];
        height = botR[1] - topL[1];

        r.setBounds(topL[0], topL[1], width, height);//updates r
    }

    /**
     * <p>
     * Method to call when the rectangle has been completed to 
     * reset all variables.
     * </p>
     * 
     * <p>
     * This means you are able to create multiple crops.
     * </p>
     * 
     */
    void reset() {
        topL = new int[] { -1, -1 };
        botR = new int[] { -1, -1 };
        current = new Rectangle();
        newRect = new Rectangle();
        oldRect = new Rectangle();
        drawRect = new Rectangle();

        // clicks
        clickOne = false;
        clickTop = new int[] { -1, -1 };
        clickBot = new int[] { -1, -1 };
        clickRect = new Rectangle();
        clicked = 0;

        // label
        rectLabel = new String();
        
    }

    /**
     * <p>
     * A method to update the rectangle while the mouse is being dragged.
     * </p>
     * 
     * @param w Rectangle width.
     * @param h Rectangle height.
     */
    void updateRect(int w, int h) {
        
        int x = current.x;
        int y = current.y;
        int width = current.width;
        int height = current.height;

        if (x < w && y < h) {
            topL = new int[] { x, y };
            botR = new int[] { w, h };

        } else if (x < w && y > h) {
            topL = new int[] { x, h };
            botR = new int[] { w, y };
        } else if (x > w && y > h) {
            topL = new int[] { w, h };
            botR = new int[] { x, y };
        } else {
            topL = new int[] { w, y };
            botR = new int[] { x, h };
        }
        width = botR[0] - topL[0];
        height = botR[1] - topL[1];

        x = topL[0];
        y = topL[1];

        if (drawRect != null) {//updates drawRect and oldRect
            oldRect.setBounds(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
            drawRect.setBounds(x, y, width, height);
        } else {//if drawRect has not been created
            drawRect = new Rectangle(x, y, width, height);//rectangle to draw is created
        }
    }
}
