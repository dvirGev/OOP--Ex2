import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;

public class My_NodeData implements NodeData {
    int key;
    HashMap <Integer, EdgeData> edge;
    HashMap <Integer,EdgeData> edgeDst;
    GeoLocation location;

    public My_NodeData(int key)
    {
        this.key = key;
        edge = new HashMap<>();
        edgeDst = new HashMap<>();
    }
    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public GeoLocation getLocation() {
        return null;
    }

    @Override
    public void setLocation(GeoLocation p) {
        location = new GeoLocation(p);
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
