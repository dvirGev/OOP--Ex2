import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class My_DirectedWeightedGraphTest {
    My_DirectedWeightedGraph graph;

    My_DirectedWeightedGraphTest() {
        graph = new My_DirectedWeightedGraph();
        addNodeToGrpth(10);
        
    }
    @Test
    void testAddNode() {
        System.out.println(graph.nodeSize());
        assertEquals(10, graph.nodeSize());
    }

    // @Test
    // void testConnect() {

    // }

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
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int randId;
            do {
                randId = rand.nextInt(size+1);
            } while (graph.getNode(randId) != null);
            String location = randId + "," + randId + ",0"; 
            My_NodeData newNode = new My_NodeData(randId, location);
            graph.addNode(newNode);
        }
    }
}
