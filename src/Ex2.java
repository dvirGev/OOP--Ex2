import api.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import javax.management.RuntimeErrorException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
// data/G1.json
public class Ex2 {
    public static String json_file;
    public static void main(String[] args) {
        OpenScrean openScrean =new OpenScrean();
        while (openScrean.isVisible()) {
            System.out.println();//Please delete this line 
        }
        System.out.println(json_file);

        DirectedWeightedGraph graph = getGrapg(json_file);
        try {
            runGUI(json_file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            
            String message = "File name not found :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file)  {
        DirectedWeightedGraph ans = new MyDirectedWeightedGraph();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(json_file));
            JSONObject jobj =(JSONObject) obj;
            JSONArray edges = (JSONArray) jobj.get("Edges");
            JSONArray nodes = (JSONArray) jobj.get("Nodes");
            for (Object o:nodes)
            {
                JSONObject temp = (JSONObject) o;
                NodeData n = new MyNodeData(Integer.parseInt(temp.get("id").toString()),temp.get("pos").toString());
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        //DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        MyDirectedWeightedGraph graph = (MyDirectedWeightedGraph) getGrapg(json_file);
        new MyFrame(graph);
    }

}