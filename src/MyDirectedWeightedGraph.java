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
    private HashMap<Integer, Degree> edgeByNode;
    private int mc;

    public MyDirectedWeightedGraph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        edgeByNode = new HashMap<>();
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
        if (nodes.get(n.getKey()) != null) {
            throw new RuntimeException("the Node key already exists");
        }
        nodes.put(n.getKey(), (MyNodeData)n);
        edgeByNode.put(n.getKey(), new Degree());
        ++mc;
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdgeData edge = new MyEdgeData(src, dest, w);
        Vector<Integer> vector = buildVector(src, dest);
        removeEdge(src, dest);
        edges.put(vector, edge);
        edgeByNode.get(src).fromMe.put(dest, edge);
        edgeByNode.get(dest).toMe.put(src, edge);
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
        return edgeByNode.get(node_id).fromMe.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        ++mc;
        NodeData node =  nodes.remove(key);
        if (node == null) {
            return null;
        }
        Iterator<EdgeData> iter = edgeByNode.get(key).fromMe.values().iterator();
        while(iter.hasNext()){
            EdgeData edge = iter.next();
            edges.remove(edge);
            edgeByNode.get(edge.getDest()).toMe.remove(key);
        }
        iter = edgeByNode.get(key).toMe.values().iterator();
        while(iter.hasNext()){
            EdgeData edge = iter.next();
            edges.remove(edge);
            edgeByNode.get(key).fromMe.remove(edge.getDest());
        }
        edgeByNode.remove(key);
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        edges.remove(vector);
        edgeByNode.get(src).fromMe.remove(dest);
        edgeByNode.get(dest).toMe.remove(src);
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
    
    private void stam(){
        Degree temp = new Degree();
        
    }
    private class Degree {
        public HashMap <Integer,  EdgeData> fromMe;
        public HashMap <Integer,  EdgeData> toMe;
        public Degree() {
            this.fromMe = new HashMap<>();
            this.toMe = new HashMap<>();
        }
    } 
}