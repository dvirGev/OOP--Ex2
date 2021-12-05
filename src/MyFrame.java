import api.DirectedWeightedGraph;
//import api.NodeData;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.util.HashMap;

<<<<<<< HEAD
import java.awt.event.*;    
import java.io.*;

public class MyFrame extends JFrame{
                

    MyFrame(DirectedWeightedGraph ans){
=======
/**
 * frame obj for the GUI
 */
public class MyFrame extends JFrame {
    MyFrame(DirectedWeightedGraph ans) {
>>>>>>> fbf86bb8b48a61083e60654527c7ce2ee33fabe0
        super();
        //this.add(new PanelelForMe());
        MyPanel myPanel = new MyPanel(ans);
//        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        

            
        
        this.add(myPanel);  

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
