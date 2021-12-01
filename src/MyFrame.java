import api.DirectedWeightedGraph;
//import api.NodeData;

import javax.swing.*;
import java.util.HashMap;

public class MyFrame extends JFrame {
    MyFrame(DirectedWeightedGraph ans){
        super();
        //this.add(new PanelelForMe());
        this.add(new MyPanel(ans));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
