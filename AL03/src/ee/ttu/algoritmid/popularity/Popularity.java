package ee.ttu.algoritmid.popularity;

import java.util.HashMap;

public class Popularity {
    private HashMap<int[], Integer> points = new HashMap<>();
    private Integer maxPop = 0;

    public Popularity(int maxCoordinates) {
    }

    void addPoint(int x, int y) {
        int[] array = new int[]{x, y};
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
    int pointPopularity(Integer x, Integer y) {
        return points.get(new int[]{x, y});
    }


    /**
     * @return the number of occurrennces of the most popular point
     */
    int maxPopularity() {
        return maxPop;
    }
}
