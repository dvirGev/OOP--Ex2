package gui;

import api.DirectedWeightedGraph;
//import api.NodeData;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class MyFrame extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu grapgOp;
    private JMenuItem nodeSize;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public MyFrame(DirectedWeightedGraphAlgorithms ans) {
        super();
        graphAlgo = ans;
       
        MyPanel myPanel = new MyPanel(ans.getGraph());
        // this.setLayout(new BorderLayout());
        buildBar();
        this.add(myPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    private void buildBar() {
        nodeSize = new JMenuItem("Size Of Nodes");
        nodeSize.addActionListener(this);
        grapgOp = new JMenu("Graph");
        grapgOp.add(nodeSize);
        mb = new JMenuBar();
        mb.add(grapgOp);
        setJMenuBar(mb);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nodeSize) {
            String message = "The Size Of The Nodes In The Graph is: " + graphAlgo.getGraph().nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        }

    }
}
