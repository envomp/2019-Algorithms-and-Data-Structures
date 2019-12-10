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
			return groups.get(element).getParent().key;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	// should throw IllegalArgumentException if any of elements is not present
	public void union(String element1, String element2) throws IllegalArgumentException {
		try {
			Node8 parent1 = groups.get(element1).getParent();
			Node8 parent2 = groups.get(element2).getParent();

			if (parent1.key.equals("A") || parent1.key.equals("U")) {
				parent2.parent = parent1.parent;
			} else {
				parent1.parent = parent2.parent;
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

	public Node8 getParent() {
		if (this.key.equals(parent.key)) {
			return this;
		}
		return parent.getParent();
	}
}
