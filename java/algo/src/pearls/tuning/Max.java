package pearls.tuning;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 7/10/16.
 * <p>
 * <h3></h3>
 */
public class Max {

    public static int sequentialMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i : a) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    //    public static int sentinelMax(int[] a) {
    //        final int last = a.length - 1;
    //        int max = a[last];
    //        a[last] = Integer.MAX_VALUE;
    //        for (int i = 0; ; ) {
    //            if (max < a[i]) {
    //                max = i;
    //                i++;
    //                if (a[i] == Integer.MAX_VALUE) {
    //                    break;
    //                }
    //            } else {
    //                i++;
    //            }
    //        }
    //        return max;
    //    }

    public static int whileSentinel(int[] a) {
        int i = 0;
        int max = 0;
        int last = a.length - 1;
        int tmp = a[last];
        // find max in [0, last)
        while (i < last) {
            max = a[i];
            a[last] = max;
            i++;
            while (a[i] < max) {
                i++;
            }
        }
        return tmp > max ? tmp : max;
    }

    public static void main(String[] args) {
        int size = 10000;
        //        correctness(10);
        for (int i = size; i < size + 100; i++) {
            testTime(i);
        }
    }

    private static void correctness(int size) {
        final int[] ints = ArrayUtility.randomInts(23, size, -size, size);
        System.out.println(Arrays.toString(ints));

        final int i = whileSentinel(ints);
        final int i1 = sequentialMax(ints);
        assert i == i1;
    }

    private static void testTime(int size) {
        final int[] ints = ArrayUtility.randomInts(23 + size, size, -size, size);
        System.out.println("size: " + size);
        // warm up
        whileSentinel(ints);
        sequentialMax(ints);

        long start = System.nanoTime();
        whileSentinel(ints);
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        sequentialMax(ints);
        System.out.println(System.nanoTime() - start);

    }
}
