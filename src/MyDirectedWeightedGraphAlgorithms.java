import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<NodeData> shortestPath(int src, int dest);
 * 5. NodeData center(); // finds the NodeData which minimizes the max distance to all the other nodes.
 *                       // Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
 * 6. List<NodeData> tsp(List<NodeData> cities); // computes a list of consecutive nodes which go over all the nodes in cities.
 *                                               // See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
 * 7. save(file); // JSON file
 * 8. load(file); // JSON file
 *
 *
 * @author boaz.benmoshe
 *
 */

/**
 * Inits the graph on which this set of algorithms operates on.
 * @param
 */
public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    MyDirectedWeightedGraph graph;
    @Override
    public void init(DirectedWeightedGraph g) {
        graph = (MyDirectedWeightedGraph)g;
    }
    /**
     * Returns the underlying graph of which this class works.
     * @return
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     * @return
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph new_graph = new MyDirectedWeightedGraph();
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            MyNodeData node = (MyNodeData)iterNode.next();
            NodeData new_node = new MyNodeData(node);
            new_graph.addNode(new_node);
        }
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterNode.hasNext()) {
            EdgeData edge  = iterEdge.next();
            new_graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return new_graph;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * @return
     */
    @Override
    public boolean isConnected()
    {
        HashMap<Integer,Boolean> c = new HashMap<Integer, Boolean>();
        Iterator<NodeData> iterNode = graph.nodeIter();
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterNode.hasNext())
        {
            NodeData n = iterNode.next();
            c.put(n.getKey(),Boolean.FALSE);
        }
        while (iterEdge.hasNext())
        {
            if(c.isEmpty()) return true;
            EdgeData edge  = iterEdge.next();
            c.remove(edge.getDest());
        }
        if(!c.isEmpty()) return false;
        return true;

    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) { return 0;}

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        return null;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        return false;
    }
}
