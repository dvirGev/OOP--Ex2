package codes;
import api.*;
import codes.*;
import gui.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.HashMap;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */

public class Ex2 {
    public static String json_file ="data/G1.json" ; //= "data/G1.json";
    public static HashMap<String, DirectedWeightedGraph> graphs;

    public static void main(String[] args) {
        graphs = new HashMap<>();
        do {
            OpenScreen openScrean = new OpenScreen();
            while (openScrean.isVisible()) {
                System.out.print("");//Please delete this line
            }
            System.out.println(json_file);
            try {
                graphs.put(json_file, getGrapg(json_file));
            } catch (Exception e) {
                String message = "File name not found :( \n Please try again:";
                JOptionPane.showMessageDialog(new JFrame(), message, "File Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } while (graphs.get(json_file) == null);

        runGUI(json_file);

    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws FileNotFoundException, IOException, ParseException {
        DirectedWeightedGraph ans = new MyDirectedWeightedGraph();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json_file));
        JSONObject jobj = (JSONObject) obj;
        JSONArray edges = (JSONArray) jobj.get("Edges");
        JSONArray nodes = (JSONArray) jobj.get("Nodes");
        for (Object o : nodes) {
            JSONObject temp = (JSONObject) o;
            NodeData n = new MyNodeData(Integer.parseInt(temp.get("id").toString()), temp.get("pos").toString());
            ans.addNode(n);
        }
        for (Object o : edges) {
            JSONObject temp = (JSONObject) o;
            if ((temp.get("src") != null) && temp.get("dest") != null && temp.get("w") != null) {
                int src = Integer.parseInt(temp.get("src").toString());
                int dest = Integer.parseInt(temp.get("dest").toString());
                double w = Double.parseDouble(temp.get("w").toString());
                ans.connect(src, dest, w);
            }
        }
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms ans = new MyDirectedWeightedGraphAlgorithms();
        ans.init(getGrapg(json_file));
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        //DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new MyFrame(graphs.get(json_file));
    }

}