package ee.ttu.algoritmid.popularity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Popularity {
    private HashMap<ArrayList<Integer>, Integer> points = new HashMap<>();
    private Integer maxPop = 0;

    public Popularity(int maxCoordinates) {
    }

    void addPoint(Integer x, Integer y) {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(x, y));
        if (points.containsKey(array)) {
            int now = points.get(array) + 1;
            if (now > maxPop) {
                maxPop = now;
            }
            points.put(array, now);
        } else {
            points.put(array, 1);
            if (maxPop == 0) {
                maxPop = 0;
            }
        }
    }

    /**
     * @param x, y - coordinates
     * @return the number of occurrennces of the point
     */
    int pointPopularity(int x, int y) {
        try {
            return points.get(new ArrayList<>(Arrays.asList(x, y)));
        } catch (NullPointerException e) {
            return 0;
        }
    }


    /**
     * @return the number of occurrennces of the most popular point
     */
    int maxPopularity() {
        return maxPop;
    }

    public static void main(String[] args) {
        Popularity popularity = new Popularity(1);
        popularity.addPoint(5, 5);
        popularity.addPoint(5, 5);
        System.out.println(popularity.maxPop);
        System.out.println(popularity.pointPopularity(5, 5));
    }
}
