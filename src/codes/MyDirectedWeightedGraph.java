package codes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
        if (nodes.get(src) == null || nodes.get(dest) == null) {
            throw new RuntimeException("The node does not exist");
        }
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
        return new EdgeIterator();
    }

    //crate edge iterator
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new EdgeIteratorByNode(node_id);
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
            edges.remove(buildVector(key, edge.getDest()));
            edgeByNode.get(edge.getDest()).toMe.remove(key);
        }
        iter = edgeByNode.get(key).toMe.values().iterator();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            edges.remove(buildVector(edge.getSrc(), key));
            edgeByNode.get(edge.getSrc()).fromMe.remove(key);
        }
        edgeByNode.remove(key);
        return node;
    }

    //remove edge from the graph
    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);

        if (edgeByNode.get(src) != null) {
            edgeByNode.get(src).fromMe.remove(dest);
        }
        if (edgeByNode.get(dest) != null) {
            edgeByNode.get(dest).toMe.remove(src);
        }
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
            iter = nodes.values().iterator();
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
            isValide();
            ++myMC;
            iter.remove();
            removeNode(curr.getKey());
        }
    }
    private class EdgeIterator implements Iterator<EdgeData> {
        private int myMC;
        private Iterator<EdgeData> iter;
        private EdgeData curr;
        public EdgeIterator() {
            myMC = mc;
            iter = edges.values().iterator();
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
        public EdgeData next() {
            isValide();
            curr = iter.next();
            return curr;
        }
        @Override
        public void remove() {
            isValide();
            ++myMC;
            iter.remove();
            removeEdge(curr.getSrc(), curr.getDest());
        }
    }
    private class EdgeIteratorByNode implements Iterator<EdgeData> {
        private int myMC;
        private Iterator<EdgeData> iter;
        private EdgeData curr;
        public EdgeIteratorByNode(int node_id) {
            myMC = mc;
            iter = edgeByNode.get(node_id).fromMe.values().iterator();
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
        public EdgeData next() {
            isValide();
            curr = iter.next();
            return curr;
        }
        @Override
        public void remove() {
            isValide();
            ++myMC;
            iter.remove();
            removeEdge(curr.getSrc(), curr.getDest());

        }
    }
}