import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.HashMap;

public class My_NodeData implements NodeData {
    private int key;
    private HashMap <Integer, EdgeData> edge;
    private HashMap <Integer,EdgeData> edgeDst;
    private GeoLocation location;
    Color c = Color.white;

    public My_NodeData(int key, String loc)
    {
        this.key = key;
        edge = new HashMap<>();
        edgeDst = new HashMap<>();
        location = new My_GeoLocation(loc);
    }
    @Override
    public int getKey() {
        return key;
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
