package gui;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import codes.MyNodeData;

import javax.swing.*;
import java.awt.*;

import java.util.Iterator;


public class MyPanel extends JPanel {
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double unitX;
    private double unitY;

    private DirectedWeightedGraph graph;

    //constructor
    public MyPanel(DirectedWeightedGraph graph) {
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        init(graph);
    }
    public void init(DirectedWeightedGraph graph) {
        this.graph = graph;
        findEdge();
    }

    //find edge to use for the draw
    private void findEdge() {
        Iterator<NodeData> n = graph.nodeIter();
        NodeData node = n.next();
        minX = node.getLocation().x();
        minY = node.getLocation().y();
        maxX = node.getLocation().x();
        maxY = node.getLocation().y();
        while (n.hasNext()) {
            node = n.next();
            minX = Math.min(minX, node.getLocation().x());
            minY = Math.min(minY, node.getLocation().y());

            maxX = Math.max(maxX, node.getLocation().x());
            maxY = Math.max(maxY, node.getLocation().y());
        }

    }

    //draw the components edges and nodes(verticals)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        unitX = this.getWidth() / Math.abs(maxX - minX) * 0.975;
        unitY = this.getHeight() / Math.abs(maxY - minY) * 0.9;
        //drawArrowLine(g, 20, 20, 200, 200, 30, 7);
        //drawLines(g);
        drawEdges(g);
        drawNodes(g);
    }

    //draw nodes (verticals) on the panel
    public void drawNodes(Graphics g) {
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            NodeData node = iter.next();
            // draw the node
            int x = (int) ((node.getLocation().x() - minX) * unitX);
            int y = (int) ((node.getLocation().y() - minY) * unitY);
            g.setColor(Color.BLUE);
            g.fillOval(x, y, 24, 24);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", Font.BOLD, 13));
            g.drawString("" + node.getKey(), x + 8, y + 15);
        }
    }

    //draw the Edge by using arrow and lines
    public void drawEdges(Graphics g) {
        Iterator<EdgeData> iter = graph.edgeIter();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();

            double srcX = graph.getNode(edge.getSrc()).getLocation().x();
            srcX = ((srcX - minX) * unitX) + 12;
            double srcY = graph.getNode(edge.getSrc()).getLocation().y();
            srcY = ((srcY - minY) * unitY) + 12;

            double destX = graph.getNode(edge.getDest()).getLocation().x();
            destX = ((destX - minX) * unitX) + 12;
            double destY = graph.getNode(edge.getDest()).getLocation().y();
            destY = ((destY - minY) * unitY) + 12;

            g.setColor(Color.CYAN);
            drawArrowLine(g, (int) srcX, (int) srcY, (int) destX, (int) destY, 30, 7);

            
            String weightString = edge.getWeight() + "";
            try {
                weightString = weightString.substring(0,weightString.indexOf(".")+3);
            } catch(Exception e) {
                e.printStackTrace();
            }
            g.setColor(Color.PINK);
            g.setFont(new Font("Ariel", Font.BOLD, 15));
            g.drawString(weightString, (int)(srcX*0.25 + destX*0.75),(int)(srcY*0.25 + destY*0.75));
        }
    }
    /**
     * Draw an arrow line between two points.
     *
     * @param g  the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void addNode(int key, int x, int y) {
        y -= 50;
        x-=5;
        double newX = (x-12)/unitX + minX;
        double newY = (y-12)/unitY + minY;
        graph.addNode(new MyNodeData(key,newX+","+newY+",0"));
        repaint();
    }

    
}
