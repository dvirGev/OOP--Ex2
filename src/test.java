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
        Vector<Integer> key2 = new Vector<>();
        key2.add(5);
        key2.add(2);
        System.out.println(edges.get(key2));

    }
}
