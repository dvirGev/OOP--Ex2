package api;

import java.util.HashMap;

public class My_NodeData implements NodeData {
    HashMap <Integer,EdgeData> edge = new HashMap();
    HashMap <Integer,EdgeData> edgeDst = new HashMap();
    int key;

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
