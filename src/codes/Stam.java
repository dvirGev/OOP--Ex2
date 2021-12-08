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
        int[] arr1 = {1,2,3};
        int[] arr2 = new int[3];
        MyDirectedWeightedGraphAlgorithms graphAlgo = new MyDirectedWeightedGraphAlgorithms();
        graphAlgo.copy(arr2, arr1);
        System.out.println(Arrays.toString(arr2));
    }

}
