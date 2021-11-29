import api.EdgeData;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class MyPanel extends JPanel {
    static int SCREEN_WIDTH;
    static int SCREEN_HIGHT;
    static  int UNIT_SIZE ;
    static double MIN_X;
    static double MIN_Y;
    static double MAX_X;
    static double MAX_Y;
    static int FRAME_ZISE;
    static double FRAME_SIZE;

    static int GAME_UNITS;
    My_DirectedWeightedGraph ans;
    MyPanel(My_DirectedWeightedGraph ans)
    {
        this.setPreferredSize(new Dimension(720,720));
        this.setFocusable(true);
        this.ans = ans;
        findEdge();
    }
    private void findEdge()
    {

        Iterator<NodeData> n = ans.nodeIter();
        NodeData node = n.next();
        this.MIN_X = node.getLocation().x();
        this.MIN_Y = node.getLocation().y();
        while(n.hasNext())
        {
            node = n.next();
            this.MIN_X = Math.min(MIN_X,node.getLocation().x());
            this.MIN_X = Math.min(MIN_X,node.getLocation().y());


        }

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawNodes(g);
//        drawEdges(g);
    }
    public void drawNodes(Graphics g)
    {
        Iterator<NodeData> it = ans.nodeIter();
        while(it.hasNext())
        {
            NodeData n = it.next();
            g.setColor(Color.RED);
            System.out.println(n.getLocation());
            int x =(int) ((n.getLocation().x()-35)*100);
            int y =(int) ((n.getLocation().x()-32)*100);
            System.out.println(x + " " + y);
            g.fillOval(x,y,6,6);
        }

    }
    public void drawEdges(Graphics g)
    {

    }
}
