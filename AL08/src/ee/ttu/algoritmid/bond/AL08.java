package ee.ttu.algoritmid.bond;

public class AL08 {

	public enum Network {
		FRIENDLY, UNFRIENDLY, UNKNOWN;
	}

	private DisjointSubsets disjointSubsets = new DisjointSubsets();

	public AL08() {
		// don't remove
	}

	public DisjointSubsets getDisjointSubsets() {
		return disjointSubsets;
	}

	public void talkedToEachOther(String name1, String name2) {
		disjointSubsets.union(name1, name2);
	}

	public void addPerson(String name) {
		disjointSubsets.addSubset(name);
	}

	public void friendly(String name) {
		disjointSubsets.lessAutisticFind(name).getParent().key = "A";

	}

	public void unfriendly(String name) {
		disjointSubsets.lessAutisticFind(name).getParent().key = "U";
	}

	public Network memberOfNetwork(String name) {
		String parent = disjointSubsets.find(name);
		if (parent.equals("A")) {
			return Network.FRIENDLY;
		} else if (parent.equals("U")) {
			return Network.UNFRIENDLY;
		}
		return Network.UNKNOWN;
	}


	public static void main(String[] args) {
		AL08 al08 = new AL08();
		al08.disjointSubsets.addSubset("a");
		al08.disjointSubsets.addSubset("b");
		al08.disjointSubsets.addSubset("x");
		al08.disjointSubsets.addSubset("c");
		al08.disjointSubsets.addSubset("d");
		al08.unfriendly("d");
		al08.talkedToEachOther("a", "b");
		al08.talkedToEachOther("a", "x");
		al08.talkedToEachOther("c", "a");
		al08.talkedToEachOther("a", "d");
		System.out.println(al08.memberOfNetwork("a"));
		System.out.println(al08.memberOfNetwork("b"));
		System.out.println(al08.memberOfNetwork("c"));
		System.out.println(al08.memberOfNetwork("d"));
		System.out.println(al08.memberOfNetwork("x"));
		System.out.println();
		System.out.println(al08.getDisjointSubsets().find("a"));
		System.out.println(al08.getDisjointSubsets().find("b"));
		System.out.println(al08.getDisjointSubsets().find("c"));
		System.out.println(al08.getDisjointSubsets().find("d"));
		System.out.println(al08.getDisjointSubsets().find("x"));
	}
}
