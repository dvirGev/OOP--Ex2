package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import api.*;
import codes.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class MyDirectedWeightedGraphTest {
    DirectedWeightedGraph graph;
    MyDirectedWeightedGraphAlgorithms algoGraph;

    MyDirectedWeightedGraphTest() {
        //******** important for the tests work *********// |
        //                                                  V
        graph = Ex2.getGrapg("data/G1.json"); // enter here the path for G1 json file
        algoGraph = new MyDirectedWeightedGraphAlgorithms();
        algoGraph.init(graph);
    }

    public static void main(String[] args) {
        MyDirectedWeightedGraphTest test = new MyDirectedWeightedGraphTest();
        Iterator<NodeData> iter = test.algoGraph.getGraph().nodeIter();
        int i = 0;
        List<NodeData> tsp = new ArrayList<>();
            tsp.add(test.graph.getNode(2));
        tsp.add(test.graph.getNode(3));
        tsp.add(test.graph.getNode(4));
        tsp.add(test.graph.getNode(5));
        tsp.add(test.graph.getNode(6));
        for (NodeData n :tsp) {
            System.out.println(n.getKey());
        }
        for (NodeData n :test.algoGraph.tsp(tsp)) {
            System.out.println(n.getKey());
        }
        System.out.println(test.algoGraph.tsp(tsp));
//        while (iter.hasNext())
//        {
//            EdgeData t =iter.next();
//            System.out.println(t);
//        }
//        System.out.println(test.graph.nodeSize());
//        System.out.println(test.algoGraph.isConnected());
//        System.out.println(test.algoGraph.shortestPathDist(1,7));
//        System.out.println(test.algoGraph.shortestPath(0,10));
//        for (NodeData node:test.algoGraph.shortestPath(0,10)) {
//            System.out.println(node.getKey());
//        }
//        System.out.println(test.algoGraph.center().getKey());




    }

    @Test
    void testGetNode() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        GeoLocation g = new MyGeoLocation("35.19589389346247,32.10152879327731,0.0");
        assertEquals(g.toString(), test1.graph.getNode(0).getLocation().toString());
    }
    @Test
    void testGetEdge() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(1.8015954015822042,test1.graph.getEdge(1,2).getWeight());

    }

    @Test
    void testNodeIterator() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        Iterator<NodeData> iter = test1.graph.nodeIter();
        int key = iter.next().getKey();
        iter.remove();
        assertEquals(null, test1.graph.getNode(key));
        test1.graph.addNode(new MyNodeData(55,"0,0,0"));
//        Exception exception = assertThrows(Exception.class, () -> {
//            iter.next();
        ;
    }
        @Test
        void testEdgeIterator() {
            MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
            MyEdgeData edge = new MyEdgeData(0,1,1);
            Iterator<EdgeData> iter = test1.graph.edgeIter();
            int key = iter.next().getSrc();
            iter.remove();
            assertEquals(null, test1.graph.getEdge(0,1));

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
        assertEquals(1, test1.algoGraph.shortestPath(2,3));

    }

    @Test
    void testShortestPathDis() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(1.0631605142699874, test1.algoGraph.shortestPathDist(2,3));

    }

     @Test
     void testEdgeSize() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(36, test1.graph.edgeSize());
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
