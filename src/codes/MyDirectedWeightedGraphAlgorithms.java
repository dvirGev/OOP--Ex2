package codes;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.io.FileWriter;
import java.io.IOException;
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
    private HashMap<Integer, DijkstraAlgorithm> dijkstra;


    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
        dijkstra = new HashMap<>();
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
        Iterator<NodeData> n = graph.nodeIter();
        if(!n.hasNext()) return false;
        NodeData check = n.next();
        if(!BFS(this.graph,check)) return false;
        DirectedWeightedGraph Gt= reverseGraph();
        if(!BFS(Gt,check)) return false;
        return true;
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        DijkstraAlgorithm dijkstraAlgo = getDijkstraAlgorithm(src);
        return dijkstraAlgo.dists.get(dest);
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        DijkstraAlgorithm dijkstraAlgo = getDijkstraAlgorithm(src);
        dijkstraAlgo.addPath(dest);
        return dijkstraAlgo.paths.get(dest);
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
         * 1. we search the vertex that need minimum Radios to get to all the others vertical.
         * 2. we will be move about every vertical and search who have the min wight(Radious) for the biggest distance;
         * 3. save this index and return him.
         * if the graph not connect it will retun NUll.
         */
        double min = Double.MAX_VALUE;
        // will be the vertical with the min radios value to the most far vertical.
        NodeData ans = null; // there is some changes in the graph at the last time we chack the short path.
        Iterator<NodeData> iter1 = graph.nodeIter();
        // will move on every node and check if its potential to be the center
        while (iter1.hasNext()) {
            double max = Integer.MIN_VALUE;
            NodeData src = iter1.next();
            DijkstraAlgorithm dij = getDijkstraAlgorithm(src.getKey());
            //check the short path with all the other nodes
            Iterator<NodeData> iter2 = graph.nodeIter();
            while (iter2.hasNext()) {
                NodeData dst = iter2.next();
                max = Math.max(dij.dists.get(dst.getKey()),  max);
            }
            if (min > max) {
                min = max;
                ans = src;
            }
        }
        return ans;
    }
    public void swapCit(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public long factorialUsingForLoop(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
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
        for (NodeData i:cities) {
            getDijkstraAlgorithm(i.getKey()).update(); }
        double best = Double.POSITIVE_INFINITY;
        List<NodeData> permute = new ArrayList<>();
        for (NodeData i:cities){
            List<NodeData> cur =new ArrayList();
            double value;
            value = checkGreedy(i, new ArrayList<>(cities), cur);
            if(value < best)
            {
                best = value;
                permute = cur;
            }
        }

        return permute;
    }

    public double checkGreedy(NodeData i, List<NodeData> c, List<NodeData> ins)
    {
        NodeData first = i;
        ins.add(i);
        c.remove(i);
        NodeData index = null;
        double sum =0;

        while(!c.isEmpty())
        {
            double low = Double.POSITIVE_INFINITY;
            for (NodeData j:c) {
                if(getDijkstraAlgorithm(i.getKey()).dists.get(j.getKey()) < low)
                {
                    low = getDijkstraAlgorithm(i.getKey()).dists.get(j.getKey());
                    index = j;
                }
            }
            sum+=low;
            boolean f= true;
            for (NodeData j:shortestPath(i.getKey(),index.getKey()))
            {
                if(f==true)
                {
                    f =false;
                    continue;
                }
                ins.add(j);
            }
            i = index;
            c.remove(index);
        }

        ins.add(first);
        sum+= getDijkstraAlgorithm(i.getKey()).dists.get(first.getKey());
        return sum;
    }
    public List<NodeData> create(int [] arr, int s)
    {
        List<NodeData> n = new ArrayList<>();
        n.add(graph.getNode(s));
        for (int i=0; i<arr.length;i++)
        {
            n.add(graph.getNode(arr[i]));
        }
        n.add(graph.getNode(s));
        return n;
    }
    public void copy(int[] to, int[] from){
        for (int i = 0; i < to.length; i++) {
            to[i] = from[i];
        }
    }
    public double CalWay(int [] pre, int s)
    {
        DijkstraAlgorithm dijkstraAlgo = getDijkstraAlgorithm(s);
        dijkstraAlgo.update();
        double sum =0;
        sum += dijkstraAlgo.dists.get(pre[0]);
        for (int i=0; i<pre.length-1;i++)
        {
            DijkstraAlgorithm dijkstraAlgo2 = getDijkstraAlgorithm(pre[i]);
            dijkstraAlgo2.update();
            sum += dijkstraAlgo2.dists.get(pre[i+1]);
        }
        DijkstraAlgorithm dijkstraAlgo2 = getDijkstraAlgorithm(pre[pre.length-1]);
        dijkstraAlgo2.update();
        sum += dijkstraAlgo2.dists.get(s);
        return sum;
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
            e.printStackTrace();
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
            DirectedWeightedGraph newGraph = Ex2.readGRaphFromJson(file);
            init(newGraph);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean BFS(DirectedWeightedGraph myGraph, NodeData node)
    {
        boolean color[] = new boolean[graph.nodeSize()];
        int count =1;
        color[node.getKey()] = true;
        LinkedList<NodeData> Q = new LinkedList<>();
        Q.add(node);
        while (!Q.isEmpty())
        {
            NodeData cur = Q.pollFirst();
            Iterator<EdgeData> iter = graph.edgeIter(cur.getKey());
            while(iter.hasNext())
            {
                EdgeData e =iter.next();
                if(color[e.getDest()]==false){
                    count++;
                    color[e.getDest()]=true;
                    Q.add(graph.getNode(e.getDest()));
                }
            }
        }
        if(count==color.length) return true;
        return false;
    }
    // do DFS Algorithms
    private void DFSUtil(DirectedWeightedGraph myGraph, NodeData node) {
        // Mark the current node as visited and print it
        node.setTag(1);
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

    private DijkstraAlgorithm getDijkstraAlgorithm(int src) {
        if (dijkstra.get(src) == null) {
            dijkstra.put(src, new DijkstraAlgorithm(src));
        }
        DijkstraAlgorithm dijkstraAlgo = dijkstra.get(src);
        dijkstraAlgo.update();
        return dijkstraAlgo;
    }
    private class DijkstraAlgorithm {
        /*
        1. Greedy algo start from the src pos and find the sort path to every other node connected
        2. There is no negative edge in the graph hence when we check a way from vertical the value will be the min
        3. Priority Queue will choose the best chose of the next vertical to exam the path from it.
        4. we will save the way "prev" to get the short way. and will return it;
        5. we will add all the short path we found to the HashMap
        */
        public HashMap<Integer,Double> dists;
        public HashMap<Integer,List<NodeData>> paths;
        public double maxDis;
        private HashMap<Integer,Integer> dads = new HashMap<>();
        private int src;
        private int myMC;

        DijkstraAlgorithm(int src) {
            this.src = src;
            myMC = Integer.MIN_VALUE;
            dists = new HashMap<>();
            paths  = new HashMap<>();
        }
        public void update() {
            if (myMC == graph.getMC()) {
                return;
            }
            myMC = graph.getMC();
            alg();
        }

        private void alg() {
            ArrayList<Integer> Q = new ArrayList<>();
            initMaps(dads, Q);

            while (!Q.isEmpty()) {
                int u = minInList(Q);
                Iterator<EdgeData> iter = graph.edgeIter(u);
                while (iter.hasNext()) {
                    relax(iter.next());
                }
            }
        }
        private void relax(EdgeData edge) {
            int u = edge.getSrc();
            int v = edge.getDest();
            double newDist = dists.get(u) + edge.getWeight();
            if (dists.get(v) > newDist) {
                dists.replace(v, newDist);
                dads.replace(v, u);
            }
        }
        private void addPath(int node) {
            if (node == src) {
                paths.get(node).add(graph.getNode(node));
                return;
            }
            int dad = dads.get(node);
            if (dad == Integer.MIN_VALUE) {
                dists.replace(node, -1.0);
                return;
            }
            if(paths.get(dad).isEmpty()) {
                addPath(dad);
            }
            paths.get(node).addAll(paths.get(dad));
            paths.get(node).add(graph.getNode(node));
            maxDis = Math.max(maxDis, dists.get(node));
        }
        private void initMaps(HashMap<Integer,Integer> dads, ArrayList<Integer> Q) {
            myMC = Integer.MIN_VALUE;
            Iterator<NodeData> iter = graph.nodeIter();
            while (iter.hasNext()) {
                int key = iter.next().getKey();
                if(key != src) {
                    dists.put(key, Double.POSITIVE_INFINITY);
                    dads.put(key, Integer.MIN_VALUE);
                    Q.add(key);
                    paths.put(key, new ArrayList<>());
                }
            }
            dads.put(src, src);
            dists.put(src, 0.0);
            paths.put(src, new ArrayList<>());
            Q.add(src);
        }
        private int minInList(ArrayList<Integer> Q) {
            double min = Double.MAX_VALUE;
            int ans = Integer.MIN_VALUE;
            int index = Integer.MIN_VALUE;
            for (int i = 0; i < Q.size(); i++) {
                if (min>dists.get(Q.get(i))) {
                    ans = Q.get(i);
                    min = dists.get(Q.get(i));
                    index = i;
                }
            }
            Q.remove(index);
            return ans;
        }
    }
}