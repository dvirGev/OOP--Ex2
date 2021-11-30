import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph{
    private HashMap <Integer, NodeData> nodes;
    private HashMap <Vector<Integer>,  EdgeData> edges;
    private int mc;

    public MyDirectedWeightedGraph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        mc = 0;
    }
    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    public HashMap<Vector<Integer>, EdgeData> getEdges() {
        return edges;
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
        if(n.getClass() !=  MyNodeData.class) {
            throw new RuntimeException("the NodeData class error");
        }
        if (nodes.get(n.getKey()) != null) {
            throw new RuntimeException("the Node key already exists");
        }
        nodes.put(n.getKey(), (MyNodeData)n);
        ++mc;
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdgeData edge = new MyEdgeData(src, dest, w);
        Vector<Integer> vector = buildVector(src, dest);
        edges.remove(vector);
        edges.put(vector, edge);
        MyNodeData node = (MyNodeData) nodes.get(src);
        node.addFromeMe(dest, edge);
        node = (MyNodeData) nodes.get(dest);
        node.addToMe(src, edge);
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
        MyNodeData node = (MyNodeData) nodes.get(node_id);
        return node.getFromMeIterator();
    }

    @Override
    public NodeData removeNode(int key) {
        MyNodeData node = (MyNodeData) nodes.remove(key);
        Iterator<EdgeData> iter = node.getFromMeIterator();
        while(iter.hasNext()){
            EdgeData edge = iter.next();
            Vector<Integer> vector = buildVector(edge.getSrc(), edge.getDest());
            edges.remove(vector);
            MyNodeData dest = (MyNodeData) nodes.get(edge.getDest());
            dest.removeToMe(edge.getDest());
        }
        iter = node.getToMeIterator();
        while(iter.hasNext()){
            EdgeData edge = iter.next();
            Vector<Integer> vector = buildVector(edge.getSrc(), edge.getDest());
            edges.remove(vector);
            MyNodeData dest = (MyNodeData) nodes.get(edge.getSrc());
            dest.removeToMe(edge.getSrc());
        }
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        MyNodeData node = (MyNodeData) nodes.get(src);
        node.removeFromMe(dest);
        node = (MyNodeData) nodes.get(dest);
        node.removeToMe(src);
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

    private Vector<Integer> buildVector(int src, int dest) {
        Vector<Integer> vector = new Vector<>();
        vector.add(src);
        vector.add(dest);
        return vector;
    }
}