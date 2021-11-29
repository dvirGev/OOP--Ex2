import api.EdgeData;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class MyPanel extends JPanel {
    // static int SCREEN_WIDTH;
    // static int SCREEN_HIGHT;
    // static int UNIT_SIZE ;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double unitX;
    private double unitY;
    private Dimension screenSize;
    // private int FRAME_ZISE;
    // private double FRAME_SIZE;

    static int GAME_UNITS;
    My_DirectedWeightedGraph graph;

    MyPanel(My_DirectedWeightedGraph ans) {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.graph = ans;
        findEdge();

        unitX = screenSize.getWidth() / Math.abs(maxX - minX) - 1100;
        unitY = screenSize.getHeight() / Math.abs(maxY - minY) - 7500;

        System.out.println("minX " + minX);
        System.out.println("minY " + minY);
        System.out.println("maxX " + maxX);
        System.out.println("maxY " + maxY);
        System.out.println("unitX " + unitX);
        System.out.println("unitY " + unitY);
    }

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
        drawEdges(g);
        drawNodes(g);
        
    }

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
            g.drawString("" + node.getKey(), x + 8, y + 15);
        }
    }

    public void drawEdges(Graphics g) {
        Iterator<EdgeData> iter = graph.edgeIter();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();

            double srcX = graph.getNode(edge.getSrc()).getLocation().x();
            srcX = ((srcX - minX) * unitX) + 8;
            double srcY = graph.getNode(edge.getSrc()).getLocation().y();
            srcY = ((srcY - minY) * unitY) + 15;

            double destX = graph.getNode(edge.getDest()).getLocation().x();
            destX = ((destX - minX) * unitX) + 8;
            double destY = graph.getNode(edge.getDest()).getLocation().y();
            destY = ((destY - minY) * unitY) + 15;

            g.setColor(Color.CYAN);
            drawArrowLine(g, (int)srcX, (int)srcY, (int)destX, (int)destY, 30, 7);
            System.out.println(edge);
        }
    }

    public void drawLines(Graphics g) {
        for (int i = 0; i < screenSize.width * maxX; i += unitX) {
            g.drawLine(i, 0, i, (int) screenSize.getHeight());
        }
        for (int i = 0; i < screenSize.getHeight() * maxY; i += unitY) {
            g.drawLine(0, i, (int) screenSize.getWidth(), i);
        }
    }
    /**
    * Draw an arrow line between two points.
    * @param g the graphics component.
    * @param x1 x-position of first point.
    * @param y1 y-position of first point.
    * @param x2 x-position of second point.
    * @param y2 y-position of second point.
    * @param d  the width of the arrow.
    * @param h  the height of the arrow.
 */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;
    
        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;
    
        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
    
        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
    
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
