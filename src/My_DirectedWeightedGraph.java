import java.util.HashMap;
import java.util.Iterator;



import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class My_DirectedWeightedGraph implements DirectedWeightedGraph{
    private HashMap <Integer, My_NodeData> nodes;

    public My_DirectedWeightedGraph() {
        nodes = new HashMap<>();
    }
    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return nodes.get(src).edgeSrc.get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        if(n.getClass() !=  My_NodeData.class) {
            throw new RuntimeException("the NodeData class error");
        }
        nodes.put(n.getKey(), (My_NodeData)n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdgeData edge = new MyEdgeData(src, dest, w);
        nodes.get(src).edgeSrc.put(dest, edge);
        nodes.get(dest).edgeSrc.put(src, edge);
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
        My_NodeData node = nodes.get(key);
        HashMap<Integer, MyEdgeData> edges = node.edgeSrc;
        edges.forEach((key, value) -> {

            nodes.get(value.getDest()).edgeDest = null;
          });

        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int nodeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int edgeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
