// Java program to traverse through
// a hashmap using iterator

import java.util.*;

class GfG {
	public static void main(String[] args)
	{
		// Consider the hashmap contains
		// student name and their marks
		HashMap<Integer, MyEdgeData> hm = new HashMap<Integer, MyEdgeData>();
        hm.put(0, new MyEdgeData(1, 2, 0));
        hm.put(1, new MyEdgeData(1, 3, 0));
        hm.put(2, new MyEdgeData(1, 4, 0));
        hm.values().iterator();
		Iterator<Map.Entry<Vector<Integer,MyEdgeData>> iter = hm.entrySet().iterator();
	}
}
