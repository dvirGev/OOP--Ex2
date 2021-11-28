import api.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    public static void main(String[] args) throws IOException, ParseException {
        getGrapg("data/G1.json");
        System.out.println("hello");
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws IOException, ParseException {
        DirectedWeightedGraph ans = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json_file));
        JSONObject jobj =(JSONObject) obj;
        JSONArray edges = (JSONArray) jobj.get("Edges");
        JSONArray nodes = (JSONArray) jobj.get("Nodes");
        //JSONObject edges = (JSONObject) Jobj.get("Nodes");
        for (Object o:nodes)
        {
            JSONObject temp = (JSONObject) o;
            My_NodeData n = new My_NodeData(Integer.parseInt(temp.get("id").toString()),temp.get("pos").toString());
            ans.addNode(n);
        }
        for (Object o:edges)
        {
            JSONObject temp = (JSONObject) o;
            int src = Integer.parseInt(temp.get("src").toString());
            int dst = Integer.parseInt(temp.get("dst").toString());
            double w = Integer.parseInt(temp.get("w").toString());
            ans.connect(src,dst,w);
//            MyEdgeData e = new MyEdgeData(src,
//                    Integer.parseInt(temp.get("w").toString()),dst);
//            My_NodeData s = (My_NodeData) ans.getNode(src);
//            s.addSend(dst, e);
//            My_NodeData d = (My_NodeData) ans.getNode(dst);
//            s.addRecived(dst, e);
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