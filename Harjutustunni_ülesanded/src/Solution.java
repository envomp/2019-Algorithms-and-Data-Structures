import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    /*
     * Complete the runningMedian function below.
     */

    static PriorityQueue<Integer> decreasing = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> increasing = new PriorityQueue<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(scanner.nextLine().trim());

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int aCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < aCount; i++) {
            int next = Integer.parseInt(scanner.nextLine().trim());
//            int next = Integer.parseInt(reader.readLine());
            decreasing.add(next);
            if (decreasing.size() - increasing.size() > 1) {
                increasing.add(decreasing.poll());
            }
            if (!increasing.isEmpty() && decreasing.peek() > increasing.peek()) {
//                System.out.println(increasing);
//                System.out.println(decreasing);
//                System.out.println(decreasing.peek());
//                System.out.println(increasing.peek());
                decreasing.add(increasing.poll());
                increasing.add(decreasing.poll());
            }

            if (decreasing.size() > increasing.size()) {
                System.out.println((double) decreasing.peek());
                bufferedWriter.write(String.valueOf((double) decreasing.peek()));
            } else {
                System.out.println((double) (decreasing.peek() + increasing.peek()) / 2);
                bufferedWriter.write(String.valueOf((double) (decreasing.peek() + increasing.peek()) / 2));
            }
            bufferedWriter.write("\n");
//            System.out.println(increasing);
//            System.out.println(decreasing);

        }
        bufferedWriter.close();

    }
}
