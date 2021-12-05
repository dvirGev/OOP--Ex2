package gui;
import java.awt.event.*;
import javax.swing.*;

import codes.Ex2;

import java.awt.*;

/**
 * create open screen
 */
public class OpenScreen extends JFrame implements ActionListener {
    static JTextField name;
    static JFrame f;
    static JButton button;
    static JLabel text;
    String ans;

    // default constructor
    public OpenScreen() {
        // create a new frame to store text field and button
        super("Open Screan");
        // create a label to display text
        text = new JLabel("Enter json file name or path");
        // create a new button
        button = new JButton("Enter");
        // addActionListener to button
        button.addActionListener(this);
        // create a object of JTextField with 16 columns
        name = new JTextField(16);
        name.addKeyListener(new KeyAdapter() {
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
        p.add(name);
        p.add(button);
        // add panel to frame
        add(p);

        // set the size of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    // private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
    // 	if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
    // 		closeWindow();
    // 	 }
    // }
    public void closeWindow() {

        // set the text of the label to the text of the field
        Ex2.json_file = name.getText();
        setVisible(false);
        this.dispose();
    }
}
