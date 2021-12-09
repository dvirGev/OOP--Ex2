package gui;


import api.DirectedWeightedGraphAlgorithms;
import gui.pop.*;

import javax.swing.*;
import java.awt.event.*;


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
    private JMenuItem isConnected;
    private JMenuItem shortestPathDist;
    private JMenuItem shortestPath;
    private JMenuItem center;
    private JMenuItem tsp;//need to do
    private JMenuItem save;
    private JMenuItem load;
    
    private boolean needToAddNode = false;
    private DirectedWeightedGraphAlgorithms graphAlgo;

    public MyFrame(DirectedWeightedGraphAlgorithms ans) {
        super();
        graphAlgo = ans;

        panel = new MyPanel(ans.getGraph());
        buildBar();
        this.add(panel);
        this.addMouseListener(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
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

        grapgAlgoOp = new JMenu("Algorithms");
        isConnected = new JMenuItem("Is Connected");
        isConnected.addActionListener(this);
        shortestPathDist = new JMenuItem("Shortest Path Dist");
        shortestPathDist.addActionListener(this);
        shortestPath = new JMenuItem("Shortest Path");
        shortestPath.addActionListener(this);
        center = new JMenuItem("Center");
        center.addActionListener(this);
        tsp = new JMenuItem("TSP");
        tsp.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);


        graphOp.add(getNode);
        graphOp.add(getEdge);
        graphOp.add(addNode);
        graphOp.add(connect);
        graphOp.add(removeNode);
        graphOp.add(removeEdge);
        graphOp.add(nodeSize);
        graphOp.add(edgeSize);
        
        grapgAlgoOp.add(isConnected);
        grapgAlgoOp.add(shortestPathDist);
        grapgAlgoOp.add(shortestPath);
        grapgAlgoOp.add(center);
        grapgAlgoOp.add(tsp);
        grapgAlgoOp.add(save);
        grapgAlgoOp.add(load);


        mb = new JMenuBar();
        mb.add(graphOp);
        mb.add(grapgAlgoOp);
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
        else if(e.getSource() == isConnected) {
            String message;
            if(graphAlgo.isConnected()) {
                message = "The Graph Is Connected :)";
            }
            else {
                message = "The Graph Isn't Connected :(";
            }
            JOptionPane.showMessageDialog(new JFrame(), message, "Is The Graph Connected", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == shortestPathDist) {
            new ShortestPathDist(graphAlgo);
        }
        else if(e.getSource() == shortestPath) {
            new ShortestPath(graphAlgo);
        }
        else if(e.getSource() == center) {
            String message = "The Center Node In The Graph is: " + graphAlgo.center().getKey();
            JOptionPane.showMessageDialog(new JFrame(), message, "Center In Graph", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == tsp) {
            new TSP(graphAlgo);
        }
        else if(e.getSource() == save) {
            new Save(graphAlgo);
        }
        else if(e.getSource() == load) {
            new Load(graphAlgo, panel);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (needToAddNode) {
            new AddNode(panel, e.getX(),e.getY());
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
