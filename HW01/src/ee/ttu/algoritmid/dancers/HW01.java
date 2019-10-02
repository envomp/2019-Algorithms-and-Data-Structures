package ee.ttu.algoritmid.dancers;

import java.util.ArrayList;
import java.util.List;

import static ee.ttu.algoritmid.dancers.Dancer.Gender.FEMALE;
import static ee.ttu.algoritmid.dancers.Dancer.Gender.MALE;

public class HW01 implements Dancers {

    private BinarySearchTree maleBinarySearchTree = new BinarySearchTree();
    private BinarySearchTree femaleBinarySearchTree = new BinarySearchTree();

    @Override
    public DancingCouple findPartnerFor(Dancer candidate) throws IllegalArgumentException {
        if (candidate != null && candidate.getName().equals("") || candidate.getHeight() <= 0) {
            throw new IllegalArgumentException(candidate.toString());
        }
        if (candidate.getGender().equals(Dancer.Gender.MALE)) {
            return getDancingCouple(candidate, femaleBinarySearchTree, maleBinarySearchTree);
        } else {
            return getDancingCouple(candidate, maleBinarySearchTree, femaleBinarySearchTree);
        }
    }

    private DancingCouple getDancingCouple(Dancer candidate, BinarySearchTree self, BinarySearchTree opposite) {
        Dancer match = opposite.getMatch(candidate);
        if (match == null) {
            self.insert(candidate);
            return null;
        }
        if (match.getGender().equals(MALE)) {
            return new DancingCoupleImpl(match, candidate);
        } else {
            return new DancingCoupleImpl(candidate, match);
        }
    }

    @Override
    public List<Dancer> returnWaitingList() {
        ArrayList<Dancer> mergedSortedList = new ArrayList<>();
        ArrayList<Node> females = maleBinarySearchTree.getSortedList();
        ArrayList<Node> males = femaleBinarySearchTree.getSortedList();
        while (true) {
            if (males.isEmpty()) {
                if (females.isEmpty()) {
                    return mergedSortedList;
                }
                mergedSortedList.addAll(females.remove(0).data);
            } else {
                if (females.isEmpty()) {
                    mergedSortedList.addAll(males.remove(0).data);
                } else if (females.get(0).key <= males.get(0).key) {
                    mergedSortedList.addAll(females.remove(0).data);
                } else {
                    mergedSortedList.addAll(males.remove(0).data);
                }
            }
        }
    }

    public static void testCustom() throws Exception {
        List<Dancer> requests = new ArrayList<>();
        List<Integer> responds = new ArrayList<>();

        requests.add(new DancerImpl("M", MALE, 145));
        responds.add(null);
        requests.add(new DancerImpl("M", MALE, 146));
        responds.add(null);
        requests.add(new DancerImpl("M", MALE, 130));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 145));
        responds.add(null);
        requests.add(new DancerImpl("F", FEMALE, 145));
        responds.add(null);
        requests.add(new DancerImpl("F", FEMALE, 146));
        responds.add(null);

        testTreeEndToEnd(requests, responds);
    }

    public static void testMaleTreeEndToEndPublic() throws Exception {
        List<Dancer> requests = new ArrayList<>();
        List<Integer> responds = new ArrayList<>();

        requests.add(new DancerImpl("M", MALE, 150));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 130));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 135));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 149));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 200));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 170));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 160));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 133));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 125));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 190));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 140));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 195));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 148));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 210));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 138));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 205));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 165));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 163));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 168));
        responds.add(null);


        requests.add(new DancerImpl("F", FEMALE, 145));
        responds.add(150);

        requests.add(new DancerImpl("F", FEMALE, 134));
        responds.add(140);

        requests.add(new DancerImpl("F", FEMALE, 159));
        responds.add(165);

        requests.add(new DancerImpl("F", FEMALE, 140));
        responds.add(148);

        requests.add(new DancerImpl("F", FEMALE, 156));
        responds.add(163);


        requests.add(new DancerImpl("M", MALE, 169));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 139));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 180));
        responds.add(null);

        requests.add(new DancerImpl("M", MALE, 134));
        responds.add(null);


        requests.add(new DancerImpl("F", FEMALE, 164));
        responds.add(169);

        requests.add(new DancerImpl("F", FEMALE, 134));
        responds.add(139);

        requests.add(new DancerImpl("F", FEMALE, 129));
        responds.add(134);

        requests.add(new DancerImpl("F", FEMALE, 175));
        responds.add(180);


        testTreeEndToEnd(requests, responds);
    }

    public static void testFemaleTreeEndToEndPublic() throws Exception {
        List<Dancer> requests = new ArrayList<>();
        List<Integer> responds = new ArrayList<>();

        requests.add(new DancerImpl("F", FEMALE, 110));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 90));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 95));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 109));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 160));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 130));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 120));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 93));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 85));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 150));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 100));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 155));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 108));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 170));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 98));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 165));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 125));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 123));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 128));
        responds.add(null);


        requests.add(new DancerImpl("M", MALE, 115));
        responds.add(110);

        requests.add(new DancerImpl("M", MALE, 107));
        responds.add(100);

        requests.add(new DancerImpl("M", MALE, 132));
        responds.add(125);

        requests.add(new DancerImpl("M", MALE, 113));
        responds.add(108);

        requests.add(new DancerImpl("M", MALE, 130));
        responds.add(123);


        requests.add(new DancerImpl("F", FEMALE, 129));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 99));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 140));
        responds.add(null);

        requests.add(new DancerImpl("F", FEMALE, 94));
        responds.add(null);


        requests.add(new DancerImpl("M", MALE, 134));
        responds.add(129);

        requests.add(new DancerImpl("M", MALE, 106));
        responds.add(99);

        requests.add(new DancerImpl("M", MALE, 99));
        responds.add(94);

        requests.add(new DancerImpl("M", MALE, 147));
        responds.add(140);


        testTreeEndToEnd(requests, responds);
    }

    private static void testTreeEndToEnd(List<Dancer> requests, List<Integer> responds) throws Exception {
        HW01 solution = new HW01();

        for (int i = 0; i < requests.size(); i++) {
            testRequestResponse(solution, requests.get(i), responds.get(i));
        }
        System.out.println(solution.returnWaitingList());
    }

    private static void testRequestResponse(HW01 solution, Dancer dancer, Integer expectedPartnerHeight) throws Exception {

        DancingCouple couple = solution.findPartnerFor(dancer);

        if (couple == null) {
            if (expectedPartnerHeight != null) {
                fail("Partner wasn't found, but should have");
            }
        } else {
            if (expectedPartnerHeight == null) {
                fail("Partner was found, but shouldn't have");
            } else {
                Dancer partner = dancer.getGender() == MALE ? couple.getFemaleDancer() : couple.getMaleDancer();

                if (partner.getHeight() != expectedPartnerHeight) {
                    fail("Partner of wrong height found");
                }
            }
        }
    }

    private static void fail(String s) throws Exception {
        throw new Exception(s);
    }


    public static void main(String[] args) {
        try {
            testMaleTreeEndToEndPublic();
            testFemaleTreeEndToEndPublic();
            testCustom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}