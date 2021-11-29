import api.NodeData;

import javax.swing.*;
import java.util.HashMap;

public class MyFrame extends JFrame {
    MyFrame(My_DirectedWeightedGraph ans){
        super();
        this.add(new MyPanel(ans));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);

        this.setVisible(true);
    }


}
