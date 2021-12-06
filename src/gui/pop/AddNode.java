package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import codes.MyNodeData;
import gui.MyFrame;
import gui.MyPanel;

import java.awt.event.*;
import java.awt.*;

public class AddNode extends JFrame implements ActionListener {
    private JTextField inputKey;
    private JTextField inputX;
    private JTextField inputY;
    private JButton button;
    private JLabel textKey;
    private JLabel textX;
    private JLabel textY;

    private DirectedWeightedGraph graph;
    private MyPanel panel;

    // default constructor
    public AddNode(DirectedWeightedGraph graph, MyPanel panel) {
        // create a new frame to store text field and button
        super("Add Node");
        this.graph = graph;
        this.panel = panel;
        // create a label to display text
        textKey = new JLabel("Key:");
        textX = new JLabel("X:");
        textY = new JLabel("Y:");
        // create a new button
        button = new JButton("Enter");
        // addActionListener to button
        button.addActionListener(this);
        // create a object of JTextField with 16 columns
        inputKey = new JTextField(8);
        inputKey.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inputX.requestFocusInWindow();
                }
            }
        });
        inputX = new JTextField(8);
        inputX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inputY.requestFocusInWindow();
                }
            }
        });
        inputY = new JTextField(8);
        inputY.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    closeWindow();
                }
            }
        });

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();
        // add buttons and textfield to panel
        p.add(textKey);
        p.add(inputKey);
        p.add(textX);
        p.add(inputX);
        p.add(textY);
        p.add(inputY);
        p.add(button);

        //p.setPreferredSize(new Dimension(125, 100));
        // add panel to frame
        add(p);

        // set the size of frame
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(true);
        setVisible(true);
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Enter")) {
            closeWindow();
        }
    }

    public void closeWindow() {
        // set the text of the label to the text of the field
        setVisible(false);
        try {
            int key = Integer.parseInt(inputKey.getText());
            String loc = inputX.getText() + "," + inputY.getText() + ",0";
            graph.addNode(new MyNodeData(key, loc));
            panel.repaint();
        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
