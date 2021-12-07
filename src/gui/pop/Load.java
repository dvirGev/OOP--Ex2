package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import gui.MyPanel;

import java.awt.event.*;
import java.awt.*;

public class Load extends JFrame implements ActionListener {
    private JTextField inputName;
    private JButton button;
    private JLabel textName;

    private DirectedWeightedGraphAlgorithms graphAlgo;
    private MyPanel panel;

    // default constructor
    public Load(DirectedWeightedGraphAlgorithms graphAlgo, MyPanel panel) {
        // create a new frame to store text field and button
        super("Load New Graph");
        this.graphAlgo = graphAlgo;
        this.panel = panel;
        // create a label to display text
        textName = new JLabel("Name Of The New Graph:");
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

    public void closeWindow() {
        // set the text of the label to the text of the field
        setVisible(false);
        try {
            if (!graphAlgo.load(inputName.getText())) {
                String message = "The Graph Not Found :(";
                JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
            }
            panel.init(graphAlgo.getGraph());
            panel.repaint();
        }
        catch (Exception e) {
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
