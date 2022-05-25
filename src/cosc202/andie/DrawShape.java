package cosc202.andie;

import java.awt.image.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class DrawShape implements ImageOperation, java.io.Serializable {

    ImagePanel target;
    boolean finished;
    ArrayList<Point> line = new ArrayList<>();
    ArrayList<Color> lineColour = new ArrayList<>();
    Point pixel = new Point();
    JFrame frame;
    JPanel topPanel;
    JPanel botPanel;
    JButton undo;
    JButton clear;
    JComboBox<String> shapeSelect;
    String selected;
    JButton foreground;
    JButton background;
    JLabel coords;
    GridBagLayout grid;
    GridBagConstraints gbc;
    MyPanel pan;
    BufferedImage input;

    JCheckBox filled;
    JCheckBox gradient;
    JCheckBox dashed;

    JTextField dLength;
    JTextField lWidth;

    Color foregroundColour;
    Color backgroundColour;

    JButton done;

    public DrawShape(ImagePanel target) {
        this.target = target;
        finished = false;

        Graphics2D g = (Graphics2D) target.getGraphics();
        input = new BufferedImage(target.getWidth(), target.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g.drawImage(input, null, 0, 0);
        g.dispose();
        addOverlay(target);
        createListeners(target);
        createFrame(target);
        selected = "";
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        this.input = input;

        return input;
    }

    protected BufferedImage getImage() {

        return input;
    }

    private void addOverlay(ImagePanel target) {

        pan = new MyPanel();
        pan.setOpaque(false);
        // pan.setVisible(true);

        // pan.setBackground(new Color(0,0,0,65));
        LayoutManager over = new OverlayLayout(target);
        target.setLayout(over);
        pan.setSize(target.getSize());
        target.add(pan);

    }

    private void createListeners(ImagePanel target) {

        target.addMouseMotionListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent e) {
                if (!finished) {
                    line.add(e.getPoint());
                    lineColour.add(foregroundColour);
                    // pixel.setLocation(e.getPoint());
                    pan.repaint();
                }

            }

            public void mouseMoved(MouseEvent e) {

                coords.setText("(" + e.getX() + ", " + e.getY() + ")");
                // target.repaint();
            }

        });

        target.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                try {

                    Graphics2D g = (Graphics2D) input.getGraphics();
                    // BufferedImage newImg = new BufferedImage(input.getWidth(), input.getHeight(),
                    // BufferedImage.TYPE_INT_ARGB);
                    BufferedImage panBuff = new BufferedImage(pan.getWidth(), pan.getHeight(),
                            BufferedImage.TYPE_INT_ARGB);

                    g.drawImage(panBuff, null, 0, 0);
                    g.dispose();

                    JFrame f = new JFrame();

                    f.add(pan);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.pack();
                    f.setVisible(true);
                    // g.drawImage(pan, 0, 0);
                    // line = new ArrayList<>();
                    line.clear();
                    if (line.isEmpty())
                        System.out.println("EMPTY");
                    // lineColour = new ArrayList<>();
                    lineColour.clear();
                } catch (Exception ex) {
                    // System.out.println("error");
                    ex.printStackTrace();
                }

            }
        });

    }

    private void createFrame(ImagePanel target) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(700, 550));

        grid = new GridBagLayout();
        frame.setLayout(grid);
        gbc = new GridBagConstraints();

        // top panel
        topPanel = createTopPanel();

        gbc.gridwidth = GridBagConstraints.REMAINDER;

        grid.setConstraints(topPanel, gbc);

        // bottom panel
        botPanel = createBotPanel();

        gbc.ipady = 10;

        grid.setConstraints(botPanel, gbc);

        // coords
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        coords = new JLabel("(" + 0 + ", " + 0 + ")");
        // coords.setHorizontalAlignment(JLabel.WEST);
        labelPanel.add(coords);
        // labelPanel.setSize(new Dimension(700,50));
        // gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 0;
        grid.setConstraints(labelPanel, gbc);

        done = new JButton("Done");
        done.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                finished = true;
                frame.dispose();
                for (int i = 0; i < line.size(); i++) {

                    Point p = line.get(i);
                    System.out.println(p.getX() + " - " + p.getY());
                }

            }

        });

        labelPanel.add(done);

        frame.add(topPanel);
        frame.add(botPanel);
        frame.add(labelPanel);
        frame.setLocation(500, 10);

        frame.pack();
        // frame.revalidate();
        // frame.repaint();
        frame.setVisible(true);
    }

    private JPanel createBotPanel() {

        ImagePanel panel = new ImagePanel();
        // panel.setSize(new Dimension(700,500));
        int height = target.getHeight();
        int width = target.getWidth();
        panel.setSize(new Dimension(width + 10, height + 50));
        System.out.println("height: " + height + ", pan hieght: " + panel.getHeight());
        // gbc.insets = new Insets(20,20,20,20);

        grid.setConstraints(panel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 0, 20, 30)));

        panel.add(target);
        return panel;
    }

    private JPanel createTopPanel() {

        JPanel panel = new JPanel();
        // panel.setLayout(new GridLayout(1,11));
        panel.setLayout(new FlowLayout());

        // gbc.fill = GridBagConstraints.BOTH;
        // gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        grid.setConstraints(undo, gbc);
        panel.add(undo);

        // clear button
        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // add

            }

        });

        grid.setConstraints(clear, gbc);
        panel.add(clear);

        // shape selection
        JLabel shapeLabel = new JLabel("Shape:");
        grid.setConstraints(shapeLabel, gbc);

        panel.add(shapeLabel);

        shapeSelect = new JComboBox<>(new String[] { "Rectangle", "Oval", "Line", "Eclipse", "Paint Brush" });
        shapeSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selected = (String) shapeSelect.getSelectedItem();
                System.out.println(selected);

            }

        });

        grid.setConstraints(shapeSelect, gbc);
        panel.add(shapeSelect);

        // 1st col
        foreground = new JButton("1st Colour");
        foreground.setBackground(Color.BLACK);
        foreground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // color1 = JColorChooser.showDialog(null, "Pick your
                // color", Color.BLACK);
                foregroundColour = JColorChooser.showDialog(null, "Pick your color", Color.BLACK);
                foreground.setBackground(foregroundColour);

            }
        });

        grid.setConstraints(foreground, gbc);
        panel.add(foreground);

        // 2nd col
        background = new JButton("2nd Colour");
        background.setBackground(Color.WHITE);
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

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        grid.setConstraints(background, gbc);
        panel.add(background);

        filled = new JCheckBox("Filled");
        grid.setConstraints(filled, gbc);
        panel.add(filled);

        gradient = new JCheckBox("Use Gradient");
        grid.setConstraints(gradient, gbc);
        panel.add(gradient);

        dashed = new JCheckBox("Dashed");
        grid.setConstraints(dashed, gbc);
        panel.add(dashed);

        JLabel dashLabel = new JLabel("Dash Length:");
        grid.setConstraints(dashLabel, gbc);
        panel.add(dashLabel);

        dLength = new JTextField("10");
        grid.setConstraints(dLength, gbc);
        panel.add(dLength);

        JLabel lineLabel = new JLabel("Line Width:");
        grid.setConstraints(lineLabel, gbc);
        panel.add(lineLabel);

        lWidth = new JTextField("2");
        grid.setConstraints(lWidth, gbc);
        panel.add(lWidth);

        grid.setConstraints(panel, gbc);
        return panel;
    }

    private class MyPanel extends ImagePanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                Graphics2D g2 = (Graphics2D) g;

                // g.fillOval(p.x, p.y, 10, 10);
                g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                // g.fillOval(pixel.x, pixel.y, 10, 10);
                for (int i = 1; i < line.size(); i++) {
                    Point p = line.get(i);
                    Point pBefore = line.get(i - 1);
                    Color c = lineColour.get(i);
                    g2.setColor(c);
                    g2.draw(new Line2D.Float(pBefore, p));

                }
            } catch (Exception e) {

            }

        }
    }

}
