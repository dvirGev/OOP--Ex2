import java.util.HashMap;
import java.util.Vector;

public class test {
    public static void main(String[] args) {
        HashMap <Vector<Integer>, Integer> edges = new HashMap<>();
        Vector<Integer> key = new Vector<>();
        key.add(2);
        key.add(5);
        System.out.println(key);
        edges.put(key, 5);
        key = new Vector<>();
        key.add(2);
        key.add(5);
        System.out.println(edges.get(key));

    }
}
