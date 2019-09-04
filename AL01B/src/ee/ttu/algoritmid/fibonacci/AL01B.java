package ee.ttu.algoritmid.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AL01B {

    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     *
     * @param n The n-th number to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public String timeToComputeRecursiveFibonacci(int n) {
        BigDecimal processorSpeed = BigDecimal.valueOf(0.0000000005);
        BigDecimal amountOfOperations = BigDecimal.valueOf(2);
        BigDecimal branches = iterativeF(n);
        BigDecimal secondsInAYear = BigDecimal.valueOf(31556926);
        return String.valueOf(branches.multiply(amountOfOperations).multiply(processorSpeed).divide(secondsInAYear));
    }

    public static BigDecimal iterativeF(int n) {
        if (n < 3) {
            return BigDecimal.valueOf(n);
        }
        int temp = 0;
        int x = 1;
        int y = 1;
        for (int i = 0; i < n - 2; i++) {
            temp = x;
            x = y + x;
            y = temp;
        }
        return BigDecimal.valueOf(x);
    }

    /**
     * Compute the Fibonacci sequence number recursively.
     * (You need this in the timeToComputeRecursiveFibonacci(int n) function!)
     *
     * @param n The n-th number to compute.
     * @return The n-th Fibonacci number as a string.
     */
    public BigInteger recursiveF(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        return recursiveF(n - 1).add(recursiveF(n - 2));
    }
}