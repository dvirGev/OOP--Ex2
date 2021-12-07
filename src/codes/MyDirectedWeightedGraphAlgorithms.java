package codes;

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
    private DijkstraAlgorithm D;


    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
//        floydWarshall = new FloydWarshallAlgorithm();
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

        // set every tag of node to 0 and find the first node in the reverseGraph
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
     *
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        D = new DijkstraAlgorithm(src,dest);
        return D.Table.get(src).get(dest);
//        Vector vector = buildVector(src, dest);
//        this.floydWarshall.update();
//        double dist = floydWarshall.shortPathDis.get(vector);
//        return (dist != Double.MAX_VALUE) ? dist : -1;
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
        List<NodeData> Path = new ArrayList<>();
        D = new DijkstraAlgorithm(src,dest);
        System.out.println(D.Way.get(src).get(dest).id);
//        NodeD t = new NodeD(D.Way.get(src).get(dest));
//        while(t.p!=null)
//        {
//            Path.add(0,graph.getNode(t.id));
//            System.out.println(Path.get(0).getKey());
//            t = t.p;
//        }
        return Path;
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
            this.floydWarshall.update();  // there is some changes in the graph at the last time we chack the short path.
            Iterator<NodeData> node = graph.nodeIter();
            // will move on every node and check if its potential to be the center
            while (node.hasNext()) {
                double max = 0;
                NodeData src = node.next();
                //check the short path with all the other nodes
                Iterator<NodeData> temp = graph.nodeIter();
                while (temp.hasNext()) {
                    NodeData dst = temp.next();
                    max = (shortestPathDist(src.getKey(), dst.getKey()) > max) ? shortestPathDist(src.getKey(), dst.getKey()) : max;
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
    public void swapCit(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        int[] arr = new int[cities.size()];
        int startAndEnd = cities.get(cities.size()-1).getKey();
        cities.remove(cities.get(cities.size()-1));
        int i = 0;
        for (NodeData n :cities) {
            arr[i] = n.getKey();
            i++;
        }
        int indexi;
        int indexj;
        do {
            indexi = (int)(Math.random()* (arr.length-1));
            indexj = (int)(Math.random()* (arr.length-1));
        }while (indexi != indexj);
        double T = 100;
        double bestWay = Double.MAX_VALUE;
        int [] best = new int[arr.length-1];
        int [] prev  = new int[arr.length-1];
        int[] cur = copy(arr);
        while(T>0.1)
        {
            prev = copy(cur);
            swap(cur);
            double curWay = CalWay(cur,startAndEnd);
            if(curWay < bestWay)
            {
                bestWay = curWay;
            }
            else if(Math.exp((bestWay-curWay)/T) < Math.random())
            {
                cur = copy(prev);
            }
            T*=0.9;
        }
        return create(best, startAndEnd);
    }
    public List<NodeData> create(int [] arr, int s)
    {
        List<NodeData> n = new ArrayList<>();
        n.add(graph.getNode(s));
        for (int i=0; i<arr.length;i++)
        {
            n.add(graph.getNode(i));
        }
        n.add(graph.getNode(s));
        return n;
    }
    public int[] copy(int[] arr){
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }
    public double CalWay(int [] pre, int s)
    {
        double sum =0;
        if (this.D == null){
            D = new DijkstraAlgorithm(s,s);
        }
        if(D.Table.get(s) == null)
        {
            D = new DijkstraAlgorithm(s,s);
        }
        sum += D.Table.get(s).get(pre[0]);
        for (int i=0; i<pre.length-1;i++)
        {
            if(D.Table.get(pre[i]) == null){
                D = new DijkstraAlgorithm(pre[i],pre[i]);
            }
            sum += D.Table.get(pre[i]).get(pre[i+1]);
        }
        if(D.Table.get(pre[pre.length-1])==null)
        {
            D = new DijkstraAlgorithm(pre[pre.length-1],pre[pre.length-1]);
        }
        sum+=D.Table.get(pre[pre.length-1]).get(s);
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

    /*
     * the algo need just get from the graph nodeSize we can also give him graph,
     * and it takes the nodeSize
     */
//    private static ArrayList<int[]> permutationsMain(int nodesize) {
//        int[] arr = new int[nodesize];
//        ArrayList<int[]> permutations = new ArrayList<int[]>();
//        int k = 0;
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//        }
//        permute(permutations, arr, k);
//        return permutations;
//    }

    /*
     * the algorithm to add all the permutations to arrayList
     */
//    private static void permute(ArrayList<int[]> arrayList, int[] arr, int k) {
//        for (int i = k; i < arr.length; i++) {
//            swap(arr);
//            permute(arrayList, arr, k + 1);
//            swap(arr, k, i);
//        }
//        if (k == arr.length - 1) {
//            arrayList.add(deepCopy(arr));
//        }
//    }

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
    private static void swap(int[] arr) {
        int i;
        int j;
        do {
            i = (int)(Math.random()* (arr.length-1));
            j = (int)(Math.random()* (arr.length-1));
        }while (i != j);
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
    public class NodeD {
        int id;
        double value;
        NodeD p;

        public NodeD(int id, double v, NodeD p) {
            this.id = id;
            this.value = v;
            this.p =p;
        }
        public NodeD(NodeD other)
        {
            this.p = other.p;
            this.id = other.id;
            this.value = other.value;
        }
    }
    private class DijkstraAlgorithm {
        /*
        1. Greedy algo start from the src pos and find the sort path to every other node connected
        2. There is no negative edge in the graph hence when we check a way from vertical the value will be the min
        3. Priority Queue will choose the best chose of the next vertical to exam the path from it.
        4. we will save the way "prev" to get the short way. and will return it;
        5. we will add all the short path we found to the HashMap
         */
        HashMap<Integer,HashMap<Integer,Double>> Table = new HashMap<>();
        HashMap<Integer, HashMap<Integer,NodeD>> Way = new HashMap<>();
        HashMap<Integer, Integer> update;
        PriorityQueue<NodeD> pos;


        class NodeDCom implements Comparator<NodeD>
        {
            @Override
            public int compare(NodeD n1, NodeD n2) {
                if (n1.value < n2.value) return -1;
                if (n1.value == n2.value) return 0;
                return 1;
            }
        }



        DijkstraAlgorithm(int src, int dst) {
            if(Table.get(src)!=null)
            {
                return;
            }
            alg(src,dst);

        }

        public void alg(int src, int dst) {

            pos = new PriorityQueue<NodeD>(5, new NodeDCom());   /// init the Queue and the comperator
            update = new HashMap<>();  // init the hashmap of the elements that be in the Q already
            pos.add(new NodeD(src,0,null));  // put the src in Q
            Table.put(src,new HashMap<>());  // init the src map
            Table.get(src).put(src,0.0); //put the first element in the map
            while(!pos.isEmpty())
            {
                NodeD n =pos.poll();
                update.put(n.id,1);
                Way.put(src,new HashMap<>());
                Way.get(src).put(n.id,n);
                Iterator<EdgeData> temp = graph.edgeIter(n.id);
                while (temp.hasNext())
                {
                    EdgeData t = temp.next();
                    if(update.get(t.getDest())!=null)
                    {
                        continue;
                    }
                    if(Table.get(src).get(t.getDest())==null)
                    {
                        Table.get(src).put(t.getDest(),n.value +t.getWeight());
                        pos.add(new NodeD(t.getDest(),n.value+t.getWeight(),n));
                    }
                    else {
                        double val = n.value +t.getWeight();
                        if(Table.get(src).get(t.getDest()) > val)
                        {
                            Table.get(src).remove(t.getDest());
                            Table.get(src).put(t.getDest(),val);
                            pos.add(new NodeD(t.getDest(),val,n));
                        }
                    }
                }
            }
        }
    }








    // do Floyd-Warshall Algorithm
    private class FloydWarshallAlgorithm {
        public HashMap<Vector<Integer>, Double> shortPathDis;
        public HashMap<Vector<Integer>, ArrayList<NodeData>> shortPathNodes;
        int update = -1;

        public FloydWarshallAlgorithm() {
            doFloyd();
        }

        public void update() {
            if (update == graph.getMC()){
                return;
            }
            doFloyd();
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
                    //shortPathNodes.get(vector).add(graph.getNode(src));
                }
            }
        }

        private void doFloyd() {
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
                            double sum = shortPathDis.get(srcK) + shortPathDis.get(kDest);
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
    }
}
