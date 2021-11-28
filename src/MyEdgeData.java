import api.EdgeData;

public class MyEdgeData implements EdgeData {
    int src;
    int dest;
    double weight;
    String info;
    int tag;

    public MyEdgeData(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.info = null;
        this.tag = Integer.MIN_VALUE;

    }

    /**
     * The id of the source node of this edge.
     *
     * @return
     */
    @Override
    public int getSrc() {
        return this.src;
    }

    /**
     * The id of the destination node of this edge
     *
     * @return
     */
    @Override
    public int getDest() {
        return this.dest;
    }

    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;

    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

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
