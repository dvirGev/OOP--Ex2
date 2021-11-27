import api.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

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
        Object obj = parser.parse(new FileReader("data/G1.json"));
        JSONObject Jobj =(JSONObject) obj;
        JSONArray edges = (JSONArray) Jobj.get("Nodes");
        //JSONObject edges = (JSONObject) Jobj.get("Nodes");
        for (Object o:edges)
        {
            JSONObject temp = (JSONObject) o;
            System.out.println(Integer.parseInt(((JSONObject)o).get("id").toString()));

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