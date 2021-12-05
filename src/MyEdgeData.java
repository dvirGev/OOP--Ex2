import api.EdgeData;

/**
 * represents the set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph.
 */
public class MyEdgeData implements EdgeData {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;

    //constructor
    public MyEdgeData(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.info = null;
        this.tag = Integer.MIN_VALUE;
    }

    //copy constructor
    public MyEdgeData(MyEdgeData other) {
        this.src = other.src;
        this.dest = other.dest;
        this.weight = other.weight;
        this.info = null;
        this.tag = Integer.MIN_VALUE;
    }

    //return The id of the source node of this edge.
    @Override
    public int getSrc() {
        return this.src;
    }

    // return the id of the destination node of this edge
    @Override
    public int getDest() {
        return this.dest;
    }

    //return the weight of this edge (positive value)
    @Override
    public double getWeight() {
        return this.weight;
    }

    //Returns the remark (meta data) associated with this edge
    @Override
    public String getInfo() {
        return this.info;
    }

    //Allows changing the remark (meta data) associated with this edge.
    @Override
    public void setInfo(String s) {
        this.info = s;

    }

    /*
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     */
    @Override
    public int getTag() {
        return tag;
    }

    /*
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    //to string
    @Override
    public String toString() {
        return "MyEdgeData{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                '}';
    }
}
