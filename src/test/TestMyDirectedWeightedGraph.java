package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import codes.Ex2;
import codes.MyNodeData;
import gui.pop.GetEdge;

public class TestMyDirectedWeightedGraph {
    private DirectedWeightedGraph graph;
    private NodeData node;
    TestMyDirectedWeightedGraph() {
        graph = Ex2.getGrapg("C:/Users/dvir_/.vscode/OOP_2021/OOP--Ex2/data/G1.json");
        node = new MyNodeData(138, "138,138,138");
    }
    @Test
    void testAddNode() {
        graph.addNode(node);
        assertEquals(node, graph.getNode(node.getKey()));
        graph.removeNode(node.getKey());
    }

    @Test
    void testConnect() {
        graph.addNode(node);
        graph.connect(node.getKey(), 0, 138);
        assertEquals(138, graph.getEdge(node.getKey(),0).getWeight());
        graph.removeEdge(node.getKey(),0);
    }

    @Test
    void testEdgeIter() {
        Iterator<EdgeData> iter = graph.edgeIter();
        if (iter.hasNext()) {
            EdgeData edge = iter.next();
            iter.remove();
            graph.getEdge(edge.getSrc(), edge.getDest());
        }
    }

    @Test
    void testEdgeIterByNode() {

    }

    @Test
    void testEdgeSize() {

    }

    @Test
    void testGetEdge() {

    }

    @Test
    void testGetMC() {

    }

    @Test
    void testGetNode() {

    }

    @Test
    void testNodeIter() {

    }

    @Test
    void testNodeSize() {

    }

    @Test
    void testRemoveEdge() {

    }

    @Test
    void testRemoveNode() {

    }
}
