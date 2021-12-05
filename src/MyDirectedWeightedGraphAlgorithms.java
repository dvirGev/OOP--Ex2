import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.sql.Array;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<NodeData> shortestPath(int src, int dest);
 * 5. NodeData center(); // finds the NodeData which minimizes the max distance to all the other nodes.
 * // Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
 * 6. List<NodeData> tsp(List<NodeData> cities); // computes a list of consecutive nodes which go over all the nodes in cities.
 * // See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
 * 7. save(file); // JSON file
 * 8. load(file); // JSON file
 */

/**
 * Inits the graph on which this set of algorithms operates on.
 */
public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private FloydWarshallAlgorithm floydWarshall;

    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
        floydWarshall = new FloydWarshallAlgorithm();
    }

    /**
     * Returns the underlying graph of which this class works.
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            NodeData node = iterNode.next();
            NodeData newNode = new MyNodeData(node);
            newGraph.addNode(newNode);
        }
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterEdge.hasNext()) {
            EdgeData edge = iterEdge.next();
            newGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return newGraph;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each other node.
     * NOTE: assume directional graph (all n*(n-1) ordered pairs).
     */
    @Override
    public boolean isConnected() {
        // set evry tag of node to 0 and find the first node
        Iterator<NodeData> iter = graph.nodeIter();
        NodeData first = iter.next();
        first.setTag(0);
        while (iter.hasNext())
            iter.next().setTag(0);

        // apply DFS on the graph
        DFSUtil(graph, first);
        // Checks if there is a node that has not been visited
        iter = graph.nodeIter();
        while (iter.hasNext()) {
            if (iter.next().getTag() == 0) {
                return false;
            }
        }
        System.out.println();

        // set evry tag of node to 0 and find the first node in the reverseGraph
        DirectedWeightedGraph myReverseGraph = reverseGraph();
        iter = myReverseGraph.nodeIter();
        first = iter.next();
        first.setTag(0);
        while (iter.hasNext())
            iter.next().setTag(0);
        // apply DFS on the reverseGraph
        DFSUtil(myReverseGraph, first);
        // Checks if there is a node that has not been visited in the reverseGraph
        iter = myReverseGraph.nodeIter();
        while (iter.hasNext()) {
            if (iter.next().getTag() == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        Vector vector = buildVector(src, dest);
        if (this.floydWarshall.update()) {
            this.floydWarshall = new FloydWarshallAlgorithm();
        }
        double dist = floydWarshall.shortPathDis.get(vector);
        return (dist != Double.MAX_VALUE) ? dist : -1;
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Vector vector = buildVector(src, dest);
        if (this.floydWarshall.update()) {
            this.floydWarshall = new FloydWarshallAlgorithm();
        } //check if there is any changes in the graph.
        return floydWarshall.shortPathNodes.get(vector);

    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     * return the Node data to which the max shortest path to all the other nodes
     * is minimized.
     */
    @Override
    public NodeData center() {

        /**
         * 1. we want to check if the graph is connect and we know the short path to from v to other one;
         * 2. we search the vertex that need minimum Radios to get to all the others vertical.
         * 2. we will be move about every vertical and search who have the min wight(Radious) for the biggest distance;
         * 3. save this index and return him.
         * if the graph not connect it will retun NUll.
         */
        if (isConnected()) {
            double min = Double.MAX_VALUE;
            // will be the vertical with the min radios value to the most far vertical.
            int index = -1;
            if (this.floydWarshall.update())  // there is some changes in the graph at the last time we chack the short path.
            {
                this.floydWarshall = new FloydWarshallAlgorithm();
            }
            Iterator<NodeData> node = graph.nodeIter();
            // will move on every node and check if its potential to be the center
            while (node.hasNext()) {
                double max = 0;
                NodeData src = node.next();
                //check the short path with all the other nodes
                Iterator<NodeData> temp = graph.nodeIter();
                while (temp.hasNext()) {
                    NodeData dst = node.next();
                    if (graph.getEdge(src.getKey(), dst.getKey()) != null) {
                        max = (graph.getEdge(src.getKey(), dst.getKey()).getWeight() > max) ? graph.getEdge(src.getKey(), dst.getKey()).getWeight() : max;
                    }
                }
                if (min > max) {
                    min = max;
                    index = src.getKey();
                }
            }
            if (index != -1) return graph.getNode(index);
        }
        return null;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution -
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
     * param jasonFile - the file name (may include a relative path).
     * return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        FileWriter jsonFile;
        Map<String, JSONArray> mainMap = new HashMap<>();

        JSONArray edgeArray = new JSONArray();
        Iterator<EdgeData> iterEdges = graph.edgeIter();
        while (iterEdges.hasNext()) {
            EdgeData edge = iterEdges.next();
            Map<String, String> edgeMap = new HashMap<>();
            edgeMap.put("src", edge.getSrc() + "");
            edgeMap.put("w", edge.getWeight() + "");
            edgeMap.put("dest", edge.getDest() + "");
            JSONObject obj = new JSONObject();
            obj.putAll(edgeMap);
            edgeArray.add(obj);
        }
        mainMap.put("Edges", edgeArray);

        JSONArray nodeArray = new JSONArray();
        Iterator<NodeData> iterNodes = graph.nodeIter();
        while (iterNodes.hasNext()) {
            NodeData node = iterNodes.next();
            GeoLocation pos = node.getLocation();
            Map<String, String> nodesMap = new HashMap<>();
            nodesMap.put("pos", pos.x() + "," + pos.y() + "," + pos.z());
            nodesMap.put("id", node.getKey() + "");
            JSONObject obj = new JSONObject();
            obj.putAll(nodesMap);
            nodeArray.add(obj);
        }
        mainMap.put("Nodes", nodeArray);

        try {

            // Constructs a FileWriter given a file name, using the platform's default
            // charset
            jsonFile = new FileWriter(file);
            JSONObject temp = new JSONObject();
            temp.putAll(mainMap);
            jsonFile.write(temp.toJSONString());
            jsonFile.flush();
            jsonFile.close();

        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
        // } finally {

        // try {

        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        return true;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            DirectedWeightedGraph newGraph = Ex2.getGrapg(file);
            graph = newGraph;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // do DFS Algorithms
    private void DFSUtil(DirectedWeightedGraph myGraph, NodeData node) {
        // Mark the current node as visited and print it
        node.setTag(1);
        System.out.print(node.getKey() + " ");

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<EdgeData> iter = myGraph.edgeIter(node.getKey());
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            NodeData nodeSon = myGraph.getNode(edge.getDest());
            if (nodeSon.getTag() == 0) {
                DFSUtil(myGraph, nodeSon);
            }
        }
    }

    // creat new graph that is reverse graph
    private DirectedWeightedGraph reverseGraph() {
        DirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            NodeData node = iterNode.next();
            NodeData newNode = new MyNodeData(node);
            newGraph.addNode(newNode);
        }
        Iterator<EdgeData> iterEdge = graph.edgeIter();
        while (iterEdge.hasNext()) {
            EdgeData edge = iterEdge.next();
            newGraph.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
        }
        return newGraph;
    }

    /*
     * the algo need just get from the graph nodeSize we can also give him graph,
     * and it takes the nodeSize
     */
    private static ArrayList<int[]> permutationsMain(int nodesize) {
        int[] arr = new int[nodesize];
        ArrayList<int[]> permutations = new ArrayList<int[]>();
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        permute(permutations, arr, k);
        return permutations;
    }

    /*
     * the algorithm to add all the permutations to arrayList
     */
    private static void permute(ArrayList<int[]> arrayList, int[] arr, int k) {
        for (int i = k; i < arr.length; i++) {
            swap(arr, i, k);
            permute(arrayList, arr, k + 1);
            swap(arr, k, i);
        }
        if (k == arr.length - 1) {
            arrayList.add(deepCopy(arr));
        }
    }

    /*
     * deep copy just for the array list add new array and not the same array
     */
    private static int[] deepCopy(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /*
     * simple swap between two index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private Vector<Integer> buildVector(int src, int dest) {
        Vector<Integer> vector = new Vector<>();
        vector.add(src);
        vector.add(dest);
        return vector;
    }

    // do Floyd-Warshall Algorithm
    private class FloydWarshallAlgorithm {
        public HashMap<Vector<Integer>, Double> shortPathDis;
        public HashMap<Vector<Integer>, ArrayList<NodeData>> shortPathNodes;
        int update = -1;

        public boolean update() {
            return (update != graph.getMC());
        }

        FloydWarshallAlgorithm() {
            update = graph.getMC();
            initMaps();
            //rebuild the HashMaps to check again the shortest path to again

            Iterator<NodeData> iter1 = graph.nodeIter();
            while (iter1.hasNext()) {
                int k = iter1.next().getKey();
                Iterator<NodeData> iter2 = graph.nodeIter();
                while (iter2.hasNext()) {
                    int src = iter2.next().getKey();
                    Iterator<NodeData> iter3 = graph.nodeIter();
                    while (iter3.hasNext()) {
                        int dest = iter3.next().getKey();

                        Vector srcDest = buildVector(src, dest);
                        Vector srcK = buildVector(src, k);
                        Vector kDest = buildVector(k, dest);
                        if (shortPathDis.get(srcK) != Double.MAX_VALUE && shortPathDis.get(kDest) != Double.MAX_VALUE) {
                            double sum = shortPathDis.get(srcK) + shortPathDis.get(srcK);
                            if (shortPathDis.get(srcDest) > sum) {
                                shortPathDis.remove(srcDest);
                                shortPathDis.put(srcDest, sum);
                                shortPathNodes.get(srcDest).add(graph.getNode(k));
                            }
                        }
                    }
                }
            }
        }

        private void initMaps() {
            shortPathDis = new HashMap<>();
            shortPathNodes = new HashMap<>();
            Iterator<NodeData> iter1 = graph.nodeIter();
            while (iter1.hasNext()) {
                int src = iter1.next().getKey();
                Iterator<NodeData> iter2 = graph.nodeIter();
                while (iter2.hasNext()) {
                    int dest = iter2.next().getKey();

                    Vector vector = buildVector(src, dest);
                    double weight = (graph.getEdge(src, dest) == null) ? Double.MAX_VALUE : graph.getEdge(src, dest).getWeight();

                    shortPathDis.put(vector, weight);
                    shortPathNodes.put(vector, new ArrayList<>());
                }
            }
        }
    }
}
