import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class My_DirectedWeightedGraph implements DirectedWeightedGraph{
    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    public HashMap<Vector<Integer>, EdgeData> getEdges() {
        return edges;
    }

    private HashMap <Integer, NodeData> nodes;
    private HashMap <Vector<Integer>,  EdgeData> edges;
    private int mc;

    public My_DirectedWeightedGraph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        mc = 0;
    }
    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        return edges.get(vector);
    }

    @Override
    public void addNode(NodeData n) {
        if(n.getClass() !=  My_NodeData.class) {
            throw new RuntimeException("the NodeData class error");
        }
        nodes.put(n.getKey(), (My_NodeData)n);
        ++mc;
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdgeData edge = new MyEdgeData(src, dest, w);
        Vector<Integer> vector = buildVector(src, dest);
        edges.put(vector, edge);
        My_NodeData  node =  (My_NodeData)nodes.get(src);
        node.fromMe.put(dest, edge);
        node = (My_NodeData)nodes.get(dest);
        node.toMe.put(src, edge);
        ++mc;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        My_NodeData node = (My_NodeData)nodes.get(node_id);
        return node.fromMe.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        My_NodeData node = (My_NodeData)nodes.remove(key);
        node.fromMe.forEach((key2, value) -> {
            Vector<Integer> vector = buildVector(key, key2);
            edges.remove(vector);
            My_NodeData dest = (My_NodeData) nodes.get(key2);
            dest.toMe.remove(key);
        });
        node.toMe.forEach((key2, value) -> {
            Vector<Integer> vector = buildVector(key, key2);
            edges.remove(vector);
            My_NodeData dest = (My_NodeData) nodes.get(key);
            dest.toMe.remove(key2);
        });
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        My_NodeData node = (My_NodeData)nodes.get(src);
        node.fromMe.remove(dest);
        node = (My_NodeData)nodes.get(dest);
        node.fromMe.remove(src);
        ++mc;
        return edges.remove(vector);
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return mc;
    }
    private Vector<Integer> buildVector(int  src, int dest) {
        Vector<Integer> vector = new Vector<>();
        vector.add(src);
        vector.add(dest);
        return vector;
    }
}