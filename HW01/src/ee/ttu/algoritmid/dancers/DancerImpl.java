package ee.ttu.algoritmid.dancers;

public class DancerImpl implements Dancer {

    private String name;
    private Gender gender;
    private Integer height;

    DancerImpl(String name, Gender gender, int height) {
        this.name = name;
        this.gender = gender;
        this.height = height;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return height.toString() + " " + name;
    }
}
