package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import api.*;
import codes.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class Test10000 {
    private DirectedWeightedGraphAlgorithms algoGraph;
    Test10000() {
        //******** important for the tests work *********// |
        //                                                  V
        //graph = Ex2.getGrapg("data/G1.json"); // enter here the path for G1 json file
        algoGraph = Ex2.getGrapgAlgo("C:/Users/dvir_/.vscode/OOP_2021/OOP--Ex2/data/1000Nodes.json");
        //algoGraph.init(graph);
    }
    @Test
    void findCenter(){
        int center = algoGraph.center().getKey();
        assertEquals(362, center);
    }
}
