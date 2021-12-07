package codes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import api.*;
import codes.*;

public class Stam {
    public static void main(String[] args) {
//        DirectedWeightedGraph graph = Ex2.getGrapg("data/G1.json");
//        MyNodeData node= new MyNodeData(0, "0,0,0");
//        Iterator<NodeData> iter = graph.nodeIter();
//        iter.next();
//        iter.remove();
//        //graph.addNode(node);
//        while (iter.hasNext()) {
//            System.out.println(iter.next().getKey());
//        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        Iterator<Integer> iter = map.values().iterator();
        System.out.println(iter.next());
        iter.remove();
        System.out.println(map.toString());
    }

}
