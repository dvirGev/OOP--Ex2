package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import api.NodeData;

import java.awt.event.*;
import java.awt.*;

public class GetNode extends JFrame implements ActionListener {
    private JTextField key;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraph graph;
    // default constructor
    public GetNode(DirectedWeightedGraph graph) {
        // create a new frame to store text field and button
        super("Get Node");
        this.graph = graph;
        // create a label to display text
        text = new JLabel("Enter Key Of Node:");
        // create a new button
        button = new JButton("Enter");
        // addActionListener to button
        button.addActionListener(this);
        // create a object of JTextField with 16 columns
        key = new JTextField(8);
        key.addKeyListener(new KeyAdapter() {
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
        p.add(text);
        p.add(key);
        p.add(button);
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
            NodeData node = graph.getNode(Integer.parseInt(key.getText()));
            if (node != null) {
                String message = "Node:\nKey: " + node.getKey() + "\nLocation: " + node.getLocation();
                JOptionPane.showMessageDialog(new JFrame(), message, "Node", JOptionPane.DEFAULT_OPTION);
            }
            else {
                String message = "The Node Not Found :(";
                JOptionPane.showMessageDialog(new JFrame(), message, "Node Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
    }
}
