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
    //constructor
    public MyNodeData(int key, String loc) {
        this.key = key;
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
        location = new MyGeoLocation(loc);
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
    }
    //copy constructor
    public MyNodeData(MyNodeData other) {
        this.key = other.key;
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
        location = new MyGeoLocation(other.location);
        fromMe = new HashMap<>();
        toMe = new HashMap<>();
    }
    public void addFromeMe(int key, EdgeData edge) {
        fromMe.put(key, edge);
    }
    public void addToMe(int key, EdgeData edge) {
        toMe.put(key, edge);
    }
    public EdgeData getFromeMe(int key) {
        return fromMe.get(key);
    }
    public EdgeData toFromeMe(int key) {
        return toMe.get(key);
    }
    public Iterator<EdgeData> getFromMeIterator() {
        return fromMe.values().iterator();
    }
    public Iterator<EdgeData> getToMeIterator() {
        return toMe.values().iterator();
    }
    public EdgeData removeFromMe(int key) {
        return fromMe.remove(key);
    }
    public EdgeData removeToMe(int key) {
        return toMe.remove(key);
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
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
