import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

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
    private DirectedWeightedGraph graph;
    private double[][] shortPath;
    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
        MyDirectedWeightedGraph temp = (MyDirectedWeightedGraph)g;
        int size =temp.getNodes().size()+1; //the index 0 in the matrix will be unrelevant
        this.shortPath = new double[size][size];  ///Lines is the src, columns the dst.
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while(iterEdge.hasNext()) //type all the edge in the matrix
        {
            MyEdgeData n = (MyEdgeData) iterEdge.next(); //make the my profile edge
            if (this.shortPath[n.getSrc()][n.getDest()]!=0) // if the nodes connected already..there is two edge between them
            {
                // check what is the min weight to get between them
                this.shortPath[n.getSrc()][n.getDest()] = (this.shortPath[n.getSrc()][n.getDest()] > n.getWeight()) ? this.shortPath[n.getSrc()][n.getDest()]:n.getWeight() ;
            }
            else {
                // if they aren't connected, so set weight
                this.shortPath[n.getSrc()][n.getDest()] = n.getWeight() ;
            }
        }
        int k=1;
        // moving from the lines&Columns 1 to the end and find the shortest path.
        while(k>=size)
        {
            for (int i=1; i<size;i++)
            {
                for (int j=1; j<size; j++)
                {
                    if(i!=j && j!=k &&k!=i)
                    {
                        if(this.shortPath[i][k]!=0 && this.shortPath[k][j]!=0)   //from src i to dst j if we visit k
                        {
                            this.shortPath[i][j] = (this.shortPath[i][j]==0) ? this.shortPath[i][k]+this.shortPath[k][j]: Math.min(this.shortPath[i][j],this.shortPath[i][k]+this.shortPath[k][j]);
                        }
                    }
                }
            }
            k++; //update the k value;
        }
        System.out.println(Arrays.deepToString(this.shortPath));
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
        DirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            MyNodeData node = (MyNodeData)iterNode.next();
            NodeData newNode = new MyNodeData(node);
            newGraph.addNode(newNode);
        }
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterNode.hasNext()) {
            EdgeData edge  = iterEdge.next();
            newGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return newGraph;
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
    public double shortestPathDist(int src, int dest) {
        double ans =this.shortPath[src][dest];
        return ((ans==0) ? -1:ans);
    }

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
    } // i need to do some changes to save the path. will be continue;

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

    private DirectedWeightedGraph reverseGraph(DirectedWeightedGraph graph) {
        DirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            MyNodeData node = (MyNodeData)iterNode.next();
            NodeData newNode = new MyNodeData(node);
            newGraph.addNode(newNode);
        }
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterNode.hasNext()) {
            EdgeData edge  = iterEdge.next();
            newGraph.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
        }
        return newGraph;
    }
}
