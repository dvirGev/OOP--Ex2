package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import codes.MyNodeData;
import gui.MyFrame;
import gui.MyPanel;

import java.awt.event.*;
import java.awt.*;

public class Connect extends JFrame implements ActionListener {
    private JTextField inputSrc;
    private JTextField inputDest;
    private JTextField inputWeight;
    private JButton button;
    private JLabel textSrc;
    private JLabel textDest;
    private JLabel textWeight;

    private DirectedWeightedGraph graph;
    private MyPanel panel;

    // default constructor
    public Connect(DirectedWeightedGraph graph, MyPanel panel) {
        // create a new frame to store text field and button
        super("Connect Nodes");
        this.graph = graph;
        this.panel = panel;
        // create a label to display text
        textSrc = new JLabel("Src:");
        textDest = new JLabel("Dest:");
        textWeight = new JLabel("Weight:");
        // create a new button
        button = new JButton("Enter");
        // addActionListener to button
        button.addActionListener(this);
        // create a object of JTextField with 16 columns
        inputSrc = new JTextField(8);
        inputSrc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inputDest.requestFocusInWindow();
                }
            }
        });
        inputDest = new JTextField(8);
        inputDest.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inputWeight.requestFocusInWindow();
                }
            }
        });
        inputWeight = new JTextField(8);
        inputWeight.addKeyListener(new KeyAdapter() {
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
        p.add(textSrc);
        p.add(inputSrc);
        p.add(textDest);
        p.add(inputDest);
        p.add(textWeight);
        p.add(inputWeight);
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

    private void closeWindow() {
        // set the text of the label to the text of the field
        setVisible(false);
        try {
            int src = Integer.parseInt(inputSrc.getText());
            int dest = Integer.parseInt(inputDest.getText());
            double weight = Double.parseDouble(inputWeight.getText());
            graph.connect(src, dest, weight);
            panel.repaint();
        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
