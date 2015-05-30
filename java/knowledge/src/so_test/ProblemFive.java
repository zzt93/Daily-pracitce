package so_test;

/**
 * Created by zzt on 5/27/15.
 * <p>
 * Description:
 */
public class ProblemFive {

    // Counts the number of numbers that the entry is evenly divisible by, as max is 20
    int twentyDivCount(int a) {    // Change to static int.... when using it directly
        int count = 0;
        for (int i = 1; i<21; i++) {

            if (a % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long startT = System.currentTimeMillis();
        int start = 500000000;
        int result = start;

        ProblemFive ff = new ProblemFive();

        for (int i = start; i > 0; i--) {

            int temp = ff.twentyDivCount(i); // Faster way
            // twentyDivCount(i) - slower

            if (temp == 20) {
                result = i;
                System.out.println(result);
            }
        }

        System.out.println(result);

        long end = System.currentTimeMillis();
        System.out.println((end - startT) + " ms");
    }
}