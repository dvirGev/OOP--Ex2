package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;


/**
 * represents a Directional Weighted Graph,
 */
public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Vector<Integer>, EdgeData> edges;
    private HashMap<Integer, Degree> edgeByNode;
    private int mc;

    //constructor
    public MyDirectedWeightedGraph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        edgeByNode = new HashMap<>();
        mc = 0;
    }

    //getter
    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    //getter
    public HashMap<Vector<Integer>, EdgeData> getEdges() {
        return edges;
    }

    //getter
    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    //getter
    @Override
    public EdgeData getEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        return edges.get(vector);
    }

    //add node(vertical) to the graph
    @Override
    public void addNode(NodeData n) {
        if (nodes.get(n.getKey()) != null) {
            throw new RuntimeException("the Node key already exists");
        }
        nodes.put(n.getKey(), (MyNodeData) n);
        edgeByNode.put(n.getKey(), new Degree());
        ++mc;
    }

    //connect between two nodes (verticals)
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

    //crate node iterator
    @Override
    public Iterator<NodeData> nodeIter() {
        return new NodeIterator();
    }

    //crate edge iterator
    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
    }

    //crate edge iterator
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return edgeByNode.get(node_id).fromMe.values().iterator();
    }

    //remove node(vertical) from the graph
    @Override
    public NodeData removeNode(int key) {
        ++mc;
        NodeData node = nodes.remove(key);
        if (node == null) {
            return null;
        }
        Iterator<EdgeData> iter = edgeByNode.get(key).fromMe.values().iterator();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            removeEdge(key, edge.getDest());
        }
        iter = edgeByNode.get(key).toMe.values().iterator();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            removeEdge(edge.getSrc(), key);
        }
        edgeByNode.remove(key);
        return node;
    }

    //remove edge from the graph
    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        edges.remove(vector);
        if (edgeByNode.get(src) != null) {
            edgeByNode.get(src).fromMe.remove(dest);
        }
        if (edgeByNode.get(dest) != null) {
            edgeByNode.get(dest).toMe.remove(src);
        }
//        edgeByNode.get(src).fromMe.remove(dest);
//        edgeByNode.get(dest).toMe.remove(src);
        ++mc;
        return edges.remove(vector);
    }

    //return num of nodes(verticals)
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    //return num of edges
    @Override
    public int edgeSize() {
        return edges.size();
    }

    //return num of changing in the graph
    @Override
    public int getMC() {
        return mc;
    }

    //build vector useful in the graph
    private Vector<Integer> buildVector(int src, int dest) {
        Vector<Integer> vector = new Vector<>();
        vector.add(src);
        vector.add(dest);
        return vector;
    }

    // crate the degree all the edges going out from node(vertical) and to the node(vertical)
    private class Degree {
        public HashMap<Integer, EdgeData> fromMe;
        public HashMap<Integer, EdgeData> toMe;

        public Degree() {
            this.fromMe = new HashMap<>();
            this.toMe = new HashMap<>();
        }
    }

    private class NodeIterator implements Iterator<NodeData> {
        private int myMC;
        private Iterator<NodeData> iter;
        private NodeData curr;
        public NodeIterator() {
            myMC = mc;
            iter = (Iterator<NodeData>) nodes.values().iterator();
        }

        private void isValide() {
            if (myMC != mc) {
                throw new RuntimeException("the iterator has change!");
            }
        }
        @Override
        public boolean hasNext() {
            isValide();
            return iter.hasNext();
        }
        @Override
        public NodeData next() {
            isValide();
            curr = iter.next();
            return curr;
        }
        @Override
        public void remove() {
            ++myMC;
            iter.remove();
            removeNode(curr.getKey());
        }
    }
}