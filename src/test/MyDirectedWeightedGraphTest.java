package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import api.*;
import codes.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class MyDirectedWeightedGraphTest {
    DirectedWeightedGraph graph;
    MyDirectedWeightedGraphAlgorithms algoGraph;

    MyDirectedWeightedGraphTest() {
        graph = Ex2.getGrapg("C:/Users/dvir_/.vscode/OOP_2021/OOP--Ex2/data/G1.json");
        algoGraph = new MyDirectedWeightedGraphAlgorithms();
        algoGraph.init(graph);
    }

    public static void main(String[] args) {
        MyDirectedWeightedGraphTest test = new MyDirectedWeightedGraphTest();
        System.out.println(test.graph.nodeSize());
        System.out.println(test.algoGraph.isConnected());
        System.out.println(test.algoGraph.shortestPathDist(1,7));
        System.out.println(test.algoGraph.shortestPath(0,10));
//        for (NodeData node:test.algoGraph.shortestPath(0,10)) {
//            System.out.println(node.getKey());
//        }
//        System.out.println(test.algoGraph.center().getKey());




    }

     @Test
     void testConnect() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(true, test1.algoGraph.isConnected());
         MyDirectedWeightedGraphTest test2 = new MyDirectedWeightedGraphTest();
         for (int i = 0; i <1 ; i++) {
             for (int j = 1; j <= test2.graph.nodeSize(); j++) {
                 test2.graph.removeEdge(i,j);
                 test2.graph.removeEdge(j,i);
             }
         }
         assertEquals(false, test2.algoGraph.isConnected());

     }

    @Test
     void testCenter() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(8, test1.algoGraph.center().getKey());

    }
    @Test
    void testShortestPath() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(1.0631605142699874, test1.algoGraph.shortestPathDist(2,3));

    }

     @Test
     void testEdgeSize() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(36, test1.graph.edgeSize());
     }

     @Test
     void testGetEdge() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(1.8015954015822042,test1.graph.getEdge(1,2).getWeight());

     }

     @Test
     void testGetMC() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeEdge(0,1);
         test1.graph.removeEdge(1,0);
         test1.graph.removeEdge(2,1);
         assertEquals(92, test1.graph.getMC());

     }

     @Test
     void testGetNode() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         GeoLocation g = new MyGeoLocation("35.19589389346247,32.10152879327731,0.0");
         assertEquals(g.toString(), test1.graph.getNode(0).getLocation().toString());
     }

     @Test
     void testNodeSize() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(17, test1.graph.nodeSize());
     }

     @Test
     void testRemoveEdge() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeEdge(0,1);
         assertEquals(null, test1.graph.getEdge(0,1));

     }

     @Test
     void testRemoveNode() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeNode(1);
         assertEquals(16, test1.graph.nodeSize());

     }
}
