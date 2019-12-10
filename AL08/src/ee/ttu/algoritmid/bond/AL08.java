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

		disjointSubsets.lessAutisticFind(name).parent.key = "A";

	}

	public void unfriendly(String name) {
		disjointSubsets.lessAutisticFind(name).parent.key = "U";
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
		al08.talkedToEachOther("a", "b");
		al08.friendly("a");
		System.out.println(al08.disjointSubsets.find("a"));
		System.out.println(al08.disjointSubsets.find("b"));
		System.out.println(al08.disjointSubsets.groups.get("b").parent.key);
		System.out.println(al08.memberOfNetwork("b"));
		System.out.println(al08.memberOfNetwork("a"));
	}
}
