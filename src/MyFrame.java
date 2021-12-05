import api.DirectedWeightedGraph;
//import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * frame obj for the GUI
 */
public class MyFrame extends JFrame {
    MyFrame(DirectedWeightedGraph ans) {
        super();
        //this.add(new PanelelForMe());
        MyPanel myPanel = new MyPanel(ans);
//        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        this.add(myPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }
}
