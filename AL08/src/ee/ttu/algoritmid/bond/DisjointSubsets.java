package ee.ttu.algoritmid.bond;

import java.util.HashMap;

public class DisjointSubsets {

	public HashMap<String, Node8> groups = new HashMap<>();

	public String find(String element) throws IllegalArgumentException {
		// TODO
		// should throw IllegalArgumentException if the element is not present
		try {
			return groups.get(element).parent;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	// should throw IllegalArgumentException if any of elements is not present
	public void union(String element1, String element2) throws IllegalArgumentException {
		try {
			groups.get(element1).parent = groups.get(element2).parent;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public void addSubset(String element) throws IllegalArgumentException {

		if (groups.containsKey(element)) {
			throw new IllegalArgumentException();
		} else {
			groups.put(element, new Node8(element, element));

		}

	}

}

class Node8 {

	String parent;
	String key;


	public Node8(String parent, String key) {
		this.parent = parent;
		this.key = key;
	}


}
