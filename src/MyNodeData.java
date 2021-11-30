import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class MyNodeData implements NodeData {
    private int key;
    private GeoLocation location;
    public HashMap <Integer,  EdgeData> fromMe;
    public HashMap <Integer,  EdgeData> toMe;
    Color c = Color.white;
    //constructor
    public MyNodeData(int key, String loc) {
        this.key = key;
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
        location = new My_GeoLocation(loc);
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
    }
    //copy constructor
    public MyNodeData(MyNodeData other) {
        this.key = other.key;
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
        location = new My_GeoLocation(other.location);
        fromMe = new HashMap<>();
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
