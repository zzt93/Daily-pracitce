package pearls;

import competition.utility.ArrayUtility;
import methodology.dynamic.MaxOfSum;
import methodology.dynamic.MaxOfSumOpt;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3></h3>
 */
public class TimeMaxSum {

    public static void main(String[] args) {
        System.out.printf("%8s:%8s:%10s:dynamic%n", "size", "divide", "my version");
        for (int i = 10; i <= 1000000; i *= 10) {
            warmUp(i);
            timeTest(i, true);
        }
    }

    private static void timeTest(int size, boolean print) {
        final int[] ints = ArrayUtility.randomInts(123, size, -10000, 10000);

        long start = System.nanoTime();
        final int i = MaxOfSumOpt.maxSumDivideConquer(ints);
        if (print) {
            System.out.printf("%8d:%8d:", size, System.nanoTime() - start);
        }

        start = System.nanoTime();
        final int i2 = MaxOfSum.myMaxSum(ints);
        if (print) {
            System.out.printf("%10d:", System.nanoTime() - start);
        }
        assert i == i2;

        start = System.nanoTime();
        final int i1 = MaxOfSumOpt.maxSumDynamic(ints);
        if (print) {
            System.out.printf("%d%n", System.nanoTime() - start);
        }
        assert i == i1;
    }

    private static void warmUp(int i) {
        timeTest(i, false);
    }
}
