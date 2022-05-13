package cosc202.andie;

import java.awt.image.*;
//import java.util.Arrays;
import java.util.*;
import javax.swing.*;

import java.awt.*;


import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class MouseCrop {

    ImagePanel target;
    boolean rectDone;
    // start with the drag
    int[] topL;
    int[] botR;
    int height;
    int width;
    Rectangle current;
    Rectangle oldRect = new Rectangle();
    Rectangle drawRect;
    Rectangle newRect;
    Graphics g;

    // two clicks to select rect
    boolean clickOne;
    int clicked;
    int[] clickTop;
    int[] clickBot;
    Rectangle clickRect;

    // label
    String comb;
    String strX;
    String strY;
    String strW;
    String strH;

    JPanel panel;
    JButton crop;
    JButton two;
    JButton three;
    Rectangle finalRect;

    MouseCrop(ImagePanel target){
        rectDone = false;
        this.target = target;
        // add a transpaernt overlay panel to draw the rect on
        MyPanel pan = new MyPanel();
        pan.setOpaque(false);
        LayoutManager over = new OverlayLayout(target);
        target.setLayout(over);
        pan.setSize(target.getSize());
        target.add(pan); // transparent layer now ontop of target

        clickOne = false;
        clicked = 0;

        g = (Graphics2D) target.getGraphics();

            botR = new int[] { -1, -1 };// 0 = x, 1 = y
            topL = new int[] { -1, -1 };

            clickBot = new int[] { -1, -1 };// 0 = x, 1 = y
            clickTop = new int[] { -1, -1 };

            target.addMouseListener(new MouseAdapter() {
                boolean cropDone = false;

                public void mousePressed(MouseEvent e) {
                    // System.out.println("Click @ " + e.getX() + ", " + e.getY());
                    if(!rectDone){
                        // for drag
                        topL[0] = e.getX();
                        topL[1] = e.getY();

                        // create rect
                        current = new Rectangle(topL[0], topL[1], 0, 0);
                    }
                }

                public void mouseClicked(MouseEvent e) {
                    if (!cropDone || !rectDone) {
                        //System.out.println("\n - " + clickOne + ", " + clicked);
                        if (!clickOne || clicked == 0) {
                            clickTop = new int[] { e.getX(), e.getY() };
                            clickOne = true;
                            // System.out.println("boo: " + clickOne);
                            clicked = 1;
                            clickRect = new Rectangle(clickTop[0], clickTop[1], 0, 0);

                            // System.out.println("first click @ " + Arrays.toString(clickTop));
                            comb = "rectangle from: " + clickRect.x + ", " + clickRect.y + " -> ";
                            if (!cropDone)
                                target.repaint();

                        } else if (clickOne || clicked == 1) {// if its the second click

                            clickBot = new int[] { e.getX(), e.getY() };
                            // System.out.println("second click @ " + Arrays.toString(clickBot));

                            calcRect(clickRect, clickTop, clickBot);
                            finalRect = clickRect;
                            // System.out.println("rect: " + clickRect.x + ", " + clickRect.y + ", " +
                            // clickRect.width + ", " + clickRect.height);
                            // System.out.println("rect: " + clickTop[0] + ", " + clickTop[1] + ", " +
                            // clickBot[0] + ", " + clickBot[1]);

                            //System.out.println("one: " + finalRect);
                            //target.getImage().apply(new CropFunction(finalRect));
                            reset();
                            target.repaint();
                            // System.out.println("repaint");
                            target.getParent().revalidate();
                            cropDone = true;
                            rectDone = true;
                        } else {

                            System.out.println("Error");
                        }

                    } else {
                        //System.out.println("done");
                    }
                    // System.out.println();
                }

                public void mouseReleased(MouseEvent e) {
                    if (current.width != 0) {
                        if (!cropDone || !rectDone) {
                            // System.out.println("Release @ " + e.getX() + ", " + e.getY());
                            rectUpdate(e, current);

                            // System.out.println(Arrays.toString(topL) + " --> " + Arrays.toString(botR));
                            // System.out.println("rect: " + current.x + ", " + current.y + ", " +
                            // current.width + ", " + current.height);
                            width = current.width;
                            height = current.height;
                            // System.out.println("drawRect: " + newRect.x + ", " + newRect.y + ", " +
                            // newRect.width + ", " + newRect.height);
                            finalRect = current;

                            //System.out.println("two: " + finalRect);
                            //target.getImage().apply(new CropFunction(finalRect));
                            reset();
                            target.repaint();
                            // System.out.println("repaint");
                            target.getParent().revalidate();
                            cropDone = true;
                            rectDone = true;
                        } else {

                            System.out.println("done");
                        }
                    } else {
                        // System.out.println("release");
                    }

                }
            });

            target.addMouseMotionListener(new MouseAdapter() {

                // boolean cropDone = false;
                public void mouseDragged(MouseEvent e) {
                    // update rect while dragged
                    if(!rectDone){
                        updateSize(e);  
                        int botX = current.x + current.width;
                        int botY = current.y + current.height;
                        comb = "rectangle from: " + current.x + ", " + current.y + " -> " + botX + ", " + botY;
                    }
                }

                public void mouseMoved(MouseEvent e) {

                    if (clickOne && !rectDone) {
                        clickBot[0] = e.getX();
                        clickBot[1] = e.getY();
                        calcRect(clickRect, clickTop, clickBot);

                        comb = "rectangle from: " + clickTop[0] + ", " + clickTop[1] + " -> " + e.getX() + ", "
                                + e.getY();
                        topL[0] = clickRect.x;
                        topL[1] = clickRect.y;
                        botR[0] = (clickRect.width);
                        botR[1] = (clickRect.height);
                        target.repaint();
                    }
                }

            });

    }

    protected Rectangle getRect(){
        
        return finalRect;
    }

    

    void calcRect(Rectangle r, int[] top, int[] bot) {

        int x = top[0];
        int y = top[1];

        int w = bot[0];
        int h = bot[1];

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

        r.setBounds(top[0], top[1], width, height);
    }

    /**
     * Method to update the current size of the rectangle while the
     * mouse is being dragegd
     * 
     * @param e The mouse event with new location to update current
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        /*
         * i.e.,
         * curr x = 35, y = 56
         * e.x = 45, e.y = 67
         * - this means width of triangle should be 10
         * - and height should be 11
         * 
         * (45 - 35) (67 - 56)
         * 10 11
         * 
         */
        current.setSize(x - current.x, y - current.y);

        updateRect(x, y);

        newRect = drawRect.union(oldRect);
        topL[0] = newRect.x;
        topL[1] = newRect.y;
        botR[0] = newRect.width;
        botR[1] = newRect.height;

        target.repaint(); // works if dragged in any direction
        // rect still there after mouse release - needs to disapear

    }

    public class MyPanel extends ImagePanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (current != null || clickRect != null) {

                g.setXORMode(Color.white);
                g.drawRect(topL[0], topL[1], botR[0], botR[1]);

                Font fnt = new Font("Ariel", Font.BOLD, 10);
                g.setFont(fnt);
                g.drawString(comb, 10, 20);
            }
        }
    }

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

        r.setBounds(topL[0], topL[1], width, height);
    }

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
        comb = new String();
        // System.out.println("reset");
    }

    void updateRect(int w, int h) {
        // System.out.println("3");
        int x = current.x;
        int y = current.y;
        int width = current.width;
        int height = current.height;

        // topL = new int[2]; // og click
        // botR = new int[2];

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

        if (drawRect != null) {
            oldRect.setBounds(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
            drawRect.setBounds(x, y, width, height);

        } else {
            drawRect = new Rectangle(x, y, width, height);
        }
        // System.out.println("current width: " + current.width + ", height: " +
        // current.height);

    }
    
}
