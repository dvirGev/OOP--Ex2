import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class My_DirectedWeightedGraph implements DirectedWeightedGraph{
    private HashMap <Integer, My_NodeData> nodes;
    private HashMap <Vector<Integer>,  MyEdgeData> edges;
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
        ++mc;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        My_NodeData node = nodes.remove(key);
        edges.forEach((key2, value) -> {
            if (value.dest == key || value.src == key) {
                edges.remove(key2);
            }
        });
        mc++;
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> vector = buildVector(src, dest);
        mc++;
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
        // TODO Auto-generated method stub
        return mc;
    }
    private Vector<Integer> buildVector(int  src, int dest) {
        Vector<Integer> vector = new Vector<>();
        vector.add(src);
        vector.add(dest);
        return vector;
    }
}
