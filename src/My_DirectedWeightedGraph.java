import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class My_DirectedWeightedGraph implements DirectedWeightedGraph{
    private HashMap <Integer, My_NodeData> nodes;
    private HashMap <Vector<Integer>, My_NodeData> edges;
    private int edgeSize , mc;

    public My_DirectedWeightedGraph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        edgeSize = 0;
        mc = 0;
    }
    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return nodes.get(src).edgeSend.get(dest);
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
        nodes.get(dest).addSend(edge);
        nodes.get(src).addRecived(edge);
        ++edgeSize;
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
        HashMap <Integer, MyEdgeData> list =  node.edgeRecived;
        list.forEach((key2, value) -> {
            nodes.get(value.src).edgeSend.remove(value.dest);
            --edgeSize;
        });
        list =  node.edgeSend;
        list.forEach((key2, value) -> {
            nodes.get(value.dest).edgeRecived.remove(value.src);
        });
        mc++;
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        --edgeSize;
        nodes.get(src).edgeSend.remove(dest);
        mc++;
        return nodes.get(dest).edgeRecived.remove(src);
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return mc;
    }
    
}
