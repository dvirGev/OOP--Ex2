package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.awt.event.*;
import java.awt.*;

public class GetEdge extends JFrame implements ActionListener {
    private JTextField inputSrc;
    private JTextField inputDest;
    private JButton button;
    private JLabel textSrc;
    private JLabel textDest;

    private DirectedWeightedGraph graph;

    // default constructor
    public GetEdge(DirectedWeightedGraph graph) {
        // create a new frame to store text field and button
        super("Get Edge");
        this.graph = graph;
        // create a label to display text
        textSrc = new JLabel("Src:");
        textDest = new JLabel("Dest:");
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
        p.add(button);

        p.setPreferredSize(new Dimension(140, 100));
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
            EdgeData edge = graph.getEdge(Integer.parseInt(inputSrc.getText()), Integer.parseInt(inputDest.getText()));
            if (edge != null) {
                String message = "Edge:\nSrc: " + edge.getSrc() + "\nDest: " + edge.getDest() + "\nWeight: " + edge.getWeight();
                JOptionPane.showMessageDialog(new JFrame(), message, "Edge", JOptionPane.DEFAULT_OPTION);
            }
            else {
                String message = "The Edge Not Found :(";
                JOptionPane.showMessageDialog(new JFrame(), message, "Edge Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
