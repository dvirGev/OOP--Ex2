import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.HashMap;

public class My_NodeData implements NodeData {
    private int key;
    private HashMap <Integer, MyEdgeData> edgeSend;
    private HashMap <Integer,MyEdgeData> edgeRecived;
    private GeoLocation location;
    Color c = Color.white;

    public My_NodeData(int key, String loc)
    {
        this.key = key;
        edgeSend = new HashMap<>();
        edgeRecived = new HashMap<>();
        location = new My_GeoLocation(loc);
    }
    @Override
    public int getKey() {
        return key;
    }
    public void addSMap(Integer dst ,MyEdgeData e) { this.edgeSend.put(dst,e); }
    public void addDMap(Integer src ,MyEdgeData e)
    {
        this.edgeRecived.put(src,e);
    }
    public void RemoveEdge(int key)
    {
        this.edgeRecived.remove(key);
        this.edgeSend.remove(key);
    }
    @Override
    public GeoLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = new My_GeoLocation(p);
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
