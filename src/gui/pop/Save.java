package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import gui.MyPanel;

import java.awt.event.*;
import java.awt.*;

public class Save extends JFrame implements ActionListener {
    private JTextField inputName;
    private JButton button;
    private JLabel textName;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    // default constructor
    public Save(DirectedWeightedGraphAlgorithms graphAlgo) {
        // create a new frame to store text field and button
        super("Save New Graph");
        this.graphAlgo = graphAlgo;
        // create a label to display text
        textName = new JLabel("Name Of The New File:");
        // create a new button
        button = new JButton("Enter");
        // addActionListener to button
        button.addActionListener(this);
        // create a object of JTextField with 16 columns
        inputName = new JTextField(8);
        inputName.addKeyListener(new KeyAdapter() {
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
        p.add(textName);
        p.add(inputName);
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
            if (!graphAlgo.save(inputName.getText())) {
                String message = "Faild To Save The Graph :(";
                JOptionPane.showMessageDialog(new JFrame(), message, "Faild", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String message = "Succeeded :)";
                JOptionPane.showMessageDialog(new JFrame(), message, "Succeeded", JOptionPane.DEFAULT_OPTION);
            }

        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
