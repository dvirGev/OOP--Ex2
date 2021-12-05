package gui;
import api.DirectedWeightedGraph;
//import api.NodeData;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.*;    
import java.io.*; 
import java.awt.*;

public class MyFrame extends JFrame implements ActionListener{
    private JMenuBar mb;    
    private JMenu grapgOp;    
    private JMenuItem nodeSize; 

    DirectedWeightedGraph graph;

    public MyFrame(DirectedWeightedGraphAlgorithms ans){
        super();
        graph = ans.getGraph();
        nodeSize=new JMenuItem("Size Of Nodes");    
        nodeSize.addActionListener(this);         
        grapgOp=new JMenu("Graph");    
        grapgOp.add(nodeSize);             
        mb=new JMenuBar();  
        mb.setBounds(0, 0, 50, 20);
        
        mb.add(grapgOp);                         
        setJMenuBar(mb);
        MyPanel myPanel = new MyPanel(ans.getGraph());
        this.setLayout(new BorderLayout());
        

            
        
        this.add(myPanel);  

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nodeSize) {
            String message = "The Size Of The Nodes In The Graph is: " + graph.nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        }
        
    }
}
