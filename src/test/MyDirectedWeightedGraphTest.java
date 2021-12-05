package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import api.*;
import codes.*;

import org.junit.jupiter.api.Test;

public class MyDirectedWeightedGraphTest {
    DirectedWeightedGraph graph;
    MyDirectedWeightedGraphAlgorithms algoGraph;

    MyDirectedWeightedGraphTest() {
//        graph = new MyDirectedWeightedGraph();
        graph = Ex2.getGrapg("data/G1.json");
        algoGraph = new MyDirectedWeightedGraphAlgorithms();
        algoGraph.init(graph);
//        addNodeToGrpth(5);
//        for (int i = 1; i <=5 ; i++) {
//            for (int j = 1; j <=5 ; j++) {
//                if (i != j){
//                    graph.connect(i,j,1);
//                }
//            }
//        }
//        StringBuilder s = new StringBuilder();
//        for (NodeData node: algoGraph.shortestPath(1,4)) {
//            s.append(node.getKey());
//        }
    }

    public static void main(String[] args) {
        MyDirectedWeightedGraphTest test = new MyDirectedWeightedGraphTest();
        System.out.println(test.graph.nodeSize());
        System.out.println(test.algoGraph.isConnected());
        System.out.println(test.algoGraph.shortestPathDist(0,4));
        System.out.println(test.algoGraph.shortestPath(0,10));
//        for (NodeData node:test.algoGraph.shortestPath(0,10)) {
//            System.out.println(node.getKey());
//        }
        System.out.println(test.algoGraph.center().getKey());




    }

    @Test
    void testAddNode() {
//        System.out.println(graph.nodeSize());
        assertEquals(5, graph.nodeSize());
    }

     @Test
     void testConnect() {
//         System.out.println(algoGraph.isConnected());
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(true, test1.algoGraph.isConnected());
         MyDirectedWeightedGraphTest test2 = new MyDirectedWeightedGraphTest();
         for (int i = 1; i <2 ; i++) {
             for (int j = 2; j < 6; j++) {
                 test2.graph.removeEdge(i,j);
                 test2.graph.removeEdge(j,i);
             }
         }
         assertEquals(false, test2.algoGraph.isConnected());

     }
    @Test
    void testShortestPath() {
//        System.out.println(algoGraph.shortestPathDist(2,3));
        assertEquals(1.0, algoGraph.shortestPathDist(2,3));

    }

    // @Test
    // void testEdgeIter() {

    // }

    // @Test
    // void testEdgeIter2() {

    // }

    // @Test
    // void testEdgeSize() {

    // }

    // @Test
    // void testGetEdge() {

    // }

    // @Test
    // void testGetMC() {

    // }

    // @Test
    // void testGetNode() {

    // }

    // @Test
    // void testNodeIter() {

    // }

    // @Test
    // void testNodeSize() {

    // }

    // @Test
    // void testRemoveEdge() {

    // }

    // @Test
    // void testRemoveNode() {

    // }
    private void addNodeToGrpth(int size) {
        for (int i = 1; i <= size; i++) {
            int randId;
            do {
                randId = i;
            } while (graph.getNode(randId) != null);
            String location = randId + "," + randId + ",0";
            System.out.println(location);
            MyNodeData newNode = new MyNodeData(randId, location);
            graph.addNode(newNode);
        }
    }
}
