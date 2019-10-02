package ee.ttu.algoritmid.dancers;

public class DancingCoupleImpl implements DancingCouple {

    private Dancer male;
    private Dancer female;

    public DancingCoupleImpl(Dancer male, Dancer female) {
        this.female = female;
        this.male = male;
    }

    @Override
    public Dancer getMaleDancer() {
        return male;
    }

    @Override
    public Dancer getFemaleDancer() {
        return female;
    }
}
