package ee.ttu.algoritmid.fibonacci;

public class AL01A {

    /**
     * Compute the Fibonacci sequence number. Trying to push again.
     *
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public String iterativeF(int n) {
        if (n < 3) {
            return "1";
        }
        int temp = 0;
        int x = 1;
        int y = 1;
        for (int i = 0; i < n - 2; i++) {
            temp = x;
            x = y + x;
            y = temp;
        }
        return String.valueOf(x);
    }
}
