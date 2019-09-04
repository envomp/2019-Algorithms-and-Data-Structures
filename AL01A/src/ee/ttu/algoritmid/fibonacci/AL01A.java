package ee.ttu.algoritmid.fibonacci;

import java.math.BigDecimal;

public class AL01A {

    /**
     * Compute the Fibonacci sequence number. Trying to push again.
     *
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public String iterativeF(int n) {
        if (n == 0) {
            return "0";
        }
        if (n < 3) {
            return "1";
        }
        BigDecimal temp = BigDecimal.valueOf(0);
        BigDecimal x = BigDecimal.valueOf(1);
        BigDecimal y = BigDecimal.valueOf(1);
        for (int i = 0; i < n - 2; i++) {
            temp = x;
            x = y.add(x);
            y = temp;
        }
        return String.valueOf(x);
    }
}
