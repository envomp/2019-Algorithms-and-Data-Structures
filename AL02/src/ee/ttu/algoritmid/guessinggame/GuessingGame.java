package ee.ttu.algoritmid.guessinggame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuessingGame {

    Oracle oracle;

    public GuessingGame(Oracle oracle) {
        this.oracle = oracle;
    }

    /**
     * @param cityArray - All the possible cities.
     * @return the name of the city.
     */
    public String play(City[] cityArray) {

        ArrayList<City> cities = new ArrayList<>(Arrays.asList(cityArray)).stream().sorted(Comparator.comparing(City::getPopulation)).collect(Collectors.toCollection(ArrayList::new));
        Integer start = 0;
        Integer end = cities.size();

        while (true) {
            Integer guess = (end - start) / 2 + start;
            String answer = oracle.isIt(cities.get(guess));
            if (answer.equals("correct!")) {
                return cities.get(guess).getName();
            } else if (answer.equals("higher population")) {
                start = guess;
            } else {
                end = guess;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {

        City[] cities = new City[]{
                new City("Delhi", 16787941),
                new City("Berlin", 3671000),
                new City("Madrid", 3207247),
                new City("Hong Kong", 7298600),
                new City("New York", 8537673),
                new City("Tokyo", 13513734),
                new City("Giza", 4239988),
                new City("Yokohama", 3726167)
        };


        GuessingGame guessingGame = new GuessingGame(new Oracle(cities[0]));
        System.out.println(guessingGame.play(cities));
    }
}
