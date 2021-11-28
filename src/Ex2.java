import api.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    public static void main(String[] args) throws IOException, ParseException {
        My_DirectedWeightedGraph graph = (My_DirectedWeightedGraph) getGrapg("data/G1.json");
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            My_NodeData node = (My_NodeData)iterNode.next();
            System.out.println(node.getKey());
        }
        System.out.println("------------");
        //move all edges start node 0
        Iterator<EdgeData> iterEdge = graph.edgeIter(0);
        while (iterEdge.hasNext()) {
            MyEdgeData edge = (MyEdgeData) iterEdge.next();
            System.out.println(edge.dest);
        }

    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws IOException, ParseException {
        DirectedWeightedGraph ans = new My_DirectedWeightedGraph();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json_file));
        JSONObject jobj =(JSONObject) obj;
        JSONArray edges = (JSONArray) jobj.get("Edges");
        JSONArray nodes = (JSONArray) jobj.get("Nodes");
        for (Object o:nodes)
        {
            JSONObject temp = (JSONObject) o;
            My_NodeData n = new My_NodeData(Integer.parseInt(temp.get("id").toString()),temp.get("pos").toString());
            ans.addNode(n);
        }
        for (Object o:edges)
        {
            JSONObject temp = (JSONObject) o;
            if((temp.get("src")!=null) && temp.get("dest")!=null && temp.get("w")!=null)
            {
                int src = Integer.parseInt(temp.get("src").toString());
                int dest = Integer.parseInt(temp.get("dest").toString());
                double w =Double.parseDouble(temp.get("w").toString());
                ans.connect(src,dest,w);
            }
        }
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

}