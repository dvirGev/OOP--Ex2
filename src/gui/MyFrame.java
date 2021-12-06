package gui;

import api.DirectedWeightedGraph;
//import api.NodeData;
import api.DirectedWeightedGraphAlgorithms;
import gui.pop.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
    private MyPanel panel;
    private JMenuBar mb;

    private JMenu graphOp;
    private JMenuItem getNode;
    private JMenuItem getEdge;
    private JMenuItem addNode;
    private JMenuItem connect;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private JMenuItem nodeSize;
    private JMenuItem edgeSize;

    private JMenu grapgAlgoOp;
    private boolean needToAddNode = false;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public MyFrame(DirectedWeightedGraphAlgorithms ans) {
        super();
        graphAlgo = ans;

        panel = new MyPanel(ans.getGraph());
        // this.setLayout(new BorderLayout());
        buildBar();
        this.add(panel);
        this.addMouseListener(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void buildBar() {
        graphOp = new JMenu("Graph");
        
        getNode = new JMenuItem("Get Node");
        getNode.addActionListener(this);
        getEdge = new JMenuItem("Get Edge");
        getEdge.addActionListener(this);
        addNode = new JMenuItem("Add Node");
        addNode.addActionListener(this);
        connect = new JMenuItem("Connect Nodes");
        connect.addActionListener(this);
        removeNode = new JMenuItem("Remove Node");
        removeNode.addActionListener(this);
        removeEdge = new JMenuItem("Remove Edge");
        removeEdge.addActionListener(this);
        nodeSize = new JMenuItem("Size Of Nodes");
        nodeSize.addActionListener(this);
        edgeSize = new JMenuItem("Size Of Edges");
        edgeSize.addActionListener(this);

        graphOp.add(getNode);
        graphOp.add(getEdge);
        graphOp.add(addNode);
        graphOp.add(connect);
        graphOp.add(removeNode);
        graphOp.add(removeEdge);
        graphOp.add(nodeSize);
        graphOp.add(edgeSize);
        mb = new JMenuBar();
        mb.add(graphOp);
        setJMenuBar(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getNode) {
            new GetNode(graphAlgo.getGraph());
        }
        else if(e.getSource() == getEdge) {
            new GetEdge(graphAlgo.getGraph());
        }
        else if(e.getSource() == addNode) {
            needToAddNode = true;
            //new AddNode(graphAlgo.getGraph(), panel, e.getX(),e.getY());
        }
        else if(e.getSource() == connect) {
            new Connect(graphAlgo.getGraph(), panel);
        }
        else if(e.getSource() == removeNode) {
            new RemoveNode(graphAlgo.getGraph(), panel);
        }
        else if(e.getSource() == removeEdge) {
            new RemoveEdge(graphAlgo.getGraph(), panel);
        }
        else if (e.getSource() == nodeSize) {
            String message = "The Size Of The Nodes In The Graph is: " + graphAlgo.getGraph().nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == edgeSize) {
            String message = "The Size Of The Edges In The Graph is: " + graphAlgo.getGraph().edgeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Edges", JOptionPane.DEFAULT_OPTION);
        }
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (needToAddNode) {
            new AddNode(graphAlgo.getGraph(), panel, e.getX(),e.getY());
            needToAddNode = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
