package ee.ttu.algoritmid.bond;

import java.util.HashMap;

public class DisjointSubsets {

	public HashMap<String, Node8> groups = new HashMap<>();


	public Node8 lessAutisticFind(String element) throws IllegalArgumentException {
		try {
			return groups.get(element);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}


	public String find(String element) throws IllegalArgumentException {
		try {
			return groups.get(element).parent.key;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	// should throw IllegalArgumentException if any of elements is not present
	public void union(String element1, String element2) throws IllegalArgumentException {
		try {
			Node8 duud1 = groups.get(element1).parent;
			Node8 duud2 = groups.get(element2).parent;
			if (duud1.parent.key.equals("A") || duud1.parent.key.equals("U")) {
				duud2.parent = duud1.parent;
			} else {
				duud1.parent = duud2.parent;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public void addSubset(String element) throws IllegalArgumentException {

		if (groups.containsKey(element)) {
			throw new IllegalArgumentException();
		} else {
			groups.put(element, new Node8(element));

		}

	}

}

class Node8 {

	Node8 parent;
	String key;


	public Node8(String key) {
		this.parent = this;
		this.key = key;
	}


}
