import api.*;
import com.google.gson.GsonBuilder;
import api.DirectedWeightedGraph;

import java.io.FileReader;
import java.util.Iterator;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    public MyDirectedWeightedGraph(String jsonFile) {
        try {
            FileReader fr = new FileReader(jsonFile);
            GsonBuilder gb = new GsonBuilder();
        }
        catch (Exception e) {
            System.out.println("hello word");
        }
    }

    @Override
    public NodeData getNode(int key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(int src, int dest, double w) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
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
