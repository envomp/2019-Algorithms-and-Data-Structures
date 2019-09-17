public class test {

    static void calculate(long n) {
        long x = 0;
        for (long j = 1; j <= n / 2; j = j * 2)
            for (long k = 1; k <= j; k++)
                x++;
        System.out.println(x);

    }

    public static void main(String[] args) {
        calculate(100);
        calculate(100000000);
    }

}
