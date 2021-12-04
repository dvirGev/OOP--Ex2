import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class MyNodeData implements NodeData {
    private int key;
    private GeoLocation location;
    private HashMap <Integer,  EdgeData> fromMe;
    private HashMap <Integer,  EdgeData> toMe;
    private Color c = Color.white;
    private int tag;
    //constructor
    public MyNodeData(int key, String loc) {
        this.key = key;
        location = new MyGeoLocation(loc);
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
        tag = 0;
    }
    //copy constructor
    public MyNodeData(NodeData other) {
        this.key = other.getKey();
        location = new MyGeoLocation(other.getLocation());
        toMe = new HashMap<>();
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
        this.location = new MyGeoLocation(p);
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
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag = t;
    }
}
