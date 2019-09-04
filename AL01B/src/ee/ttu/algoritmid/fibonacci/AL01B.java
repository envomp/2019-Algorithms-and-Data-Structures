package ee.ttu.algoritmid.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class AL01B {

    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     *
     * @param n The n-th number to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public String timeToComputeRecursiveFibonacci(int n) {
        BigDecimal processorSpeed = BigDecimal.valueOf(0.0000001);
        BigDecimal amountOfOperations = BigDecimal.valueOf(2);
        BigDecimal branches = iterativeF(n);
        BigDecimal secondsInAYear = BigDecimal.valueOf(31556926);
        return String.valueOf(branches.multiply(amountOfOperations).multiply(processorSpeed).divide(secondsInAYear, 10, RoundingMode.HALF_UP));
    }

    private BigDecimal iterativeF(int n) {
        if (n < 3) {
            return BigDecimal.valueOf(n);
        }
        BigDecimal temp = BigDecimal.valueOf(0);
        BigDecimal x = BigDecimal.valueOf(1);
        BigDecimal y = BigDecimal.valueOf(1);
        for (int i = 0; i < n - 2; i++) {
            temp = x;
            x = y.add(x).add(BigDecimal.ONE);
            y = temp;
        }
        return x;
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

    public static void main(String[] args) {
        AL01B test = new AL01B();
        System.out.println(test.timeToComputeRecursiveFibonacci(50));
        System.out.println(test.timeToComputeRecursiveFibonacci(69));
    }

}
