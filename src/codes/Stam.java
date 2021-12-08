package codes;

import java.util.Arrays;
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
        map.put(1,2);
        map.replace(1,  1);
        System.out.println(map.values());
    }

}
