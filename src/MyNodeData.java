import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * represents the set of operations applicable on a
 * node (vertex) in a (directional) weighted graph.
 */
public class MyNodeData implements NodeData {
    private int key;
    private GeoLocation location;
    private HashMap<Integer, EdgeData> fromMe;
    private HashMap<Integer, EdgeData> toMe;
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

    //return key
    @Override
    public int getKey() {
        return key;
    }

    // return GeoLocation obj witch has (x,y,z)
    @Override
    public GeoLocation getLocation() {
        return location;
    }

    // change the location by crate a new GeoLocation
    @Override
    public void setLocation(GeoLocation p) {
        this.location = new MyGeoLocation(p);
    }

    //we don't use this function(boaz said we don't need)
    @Override
    public double getWeight() {
        return 0;
    }

    //we don't use this function(boaz said we don't need)
    @Override
    public void setWeight(double w) {

    }

    //we don't use this function(boaz said we don't need)
    @Override
    public String getInfo() {
        return null;
    }

    //we don't use this function(boaz said we don't need)
    @Override
    public void setInfo(String s) {

    }

    //return a tag useful for the algorithms
    @Override
    public int getTag() {
        return tag;
    }

    //change the tag useful for the algorithms
    @Override
    public void setTag(int t) {
        tag = t;
    }
}
