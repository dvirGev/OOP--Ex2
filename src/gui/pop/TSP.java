package gui.pop;

import javax.swing.*;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import gui.MyPanel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;

public class TSP extends JFrame implements ActionListener {
    private JTextField inputName;
    private JButton selectAll;
    private JButton enter;
    private JLabel textName;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    // default constructor
    public TSP(DirectedWeightedGraphAlgorithms graphAlgo) {
        // create a new frame to store text field and button
        super("TSP");
        this.graphAlgo = graphAlgo;
        // create a label to display text
        textName = new JLabel("Enter Nodes: \n (For Example: 1,2,3)");
        // create a new button
        selectAll = new JButton("Select All");
        enter = new JButton("Enter");
        // addActionListener to button
        selectAll.addActionListener(this);
        enter.addActionListener(this);
        // create a object of JTextField with 16 columns
        inputName = new JTextField(8);
        inputName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    readNodes();
                }
            }
        });

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();
        // add buttons and textfield to panel
        p.add(textName);
        p.add(inputName);
        p.add(selectAll);
        p.add(enter);

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
            readNodes();
        }
        else if(s.equals("Select All")) {
            selectAllNode();
        }
    }
    private void readNodes() {
        try {
            String[] nodesId = inputName.getText().split(",");
            List<NodeData> nodes = new ArrayList<>();
            int id;
            NodeData node;
            for (String idT : nodesId) {
                id = Integer.parseInt(idT);
                node = graphAlgo.getGraph().getNode(id);
                if(node == null) {
                    String message = "The Node " + id + " Dosn't Found :(";
                    JOptionPane.showMessageDialog(new JFrame(), message, "The Node Dosn't Found", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    return;
                }
                nodes.add(node);
            }
            closeWindow(nodes);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "Something Gets Wrong :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void selectAllNode() {
        List<NodeData> nodes = new ArrayList();
        Iterator<NodeData> iter = graphAlgo.getGraph().nodeIter();
        while (iter.hasNext()) {
            nodes.add(iter.next());
        }
        closeWindow(nodes);
    }
    private void closeWindow(List<NodeData> nodes) {
        // set the text of the label to the text of the field
        setVisible(false);
        nodes = graphAlgo.tsp(nodes);
        if (nodes == null) {
            String message = "There Is No Path :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "There Is No Path", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        String message = "";
        for (NodeData nodeData : nodes) {
            message += nodeData.getKey() + "-> ";
        }
        JOptionPane.showMessageDialog(new JFrame(), message, "TSP", JOptionPane.DEFAULT_OPTION);
        this.dispose();
    }
}
