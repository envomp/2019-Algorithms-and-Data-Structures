package ee.ttu.algoritmid.bond;

import java.util.ArrayList;
import java.util.HashMap;

public class DisjointSubsets {

	public HashMap<String, Node8> groups = new HashMap<>();

	public DisjointSubsets() {
		addSubset("A");
		addSubset("U");
	}


	public String find(String element) throws IllegalArgumentException {
		try {
			Node8 guy = groups.get(element);
			guy.parent = guy.getParent();
			return guy.parent.key;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

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
		Node8 last = this;
		ArrayList<Node8> toChange = new ArrayList<>();
		while (!last.parent.key.equals(last.key)) {
			toChange.add(last);
			last = last.parent;
		}
		for (Node8 el : toChange) {
			el.parent = last;
		}
		return last;
	}

	@Override
	public String toString() {
		return "Node8{" +
				"parent=" + parent.key +
				", key='" + key + '\'' +
				'}';
	}
}
