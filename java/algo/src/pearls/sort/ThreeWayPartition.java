package pearls.sort;

import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import competition.utility.ArrayUtility;
import competition.utility.Swap;

import java.util.Arrays;

/**
 * Created by zzt on 7/25/16.
 * <p>
 * <h3></h3>
 */
public class ThreeWayPartition {

    /**
     * @param integers input array
     * @param start    inclusive
     * @param end      exclusive
     *
     * @return index s and i, [start, s): small, [s, i): equal, [i, end): large
     */
    public static int[] threeWayPartition(Integer[] integers, int start, int end) {
        final int len = end - start;
        Preconditions.checkArgument(len > 0);
        int s = start, i = start + 1, l = end - 1;
        int pivot = integers[start];
        while (i <= l) {
            if (integers[i] == pivot) {
                i++;
            } else if (integers[i] > pivot) {
                Swap.swap(integers, i, l);
                l--;
            } else {
                Swap.swap(integers, i, s);
                s++;
                i++;
            }
        }
        return new int[]{s, i};
    }

    public static void quickSort(Integer[] integers, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        final int[] ints = threeWayPartition(integers, start, end);
        quickSort(integers, start, ints[0]);
        quickSort(integers, ints[1], end);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final Integer[] integers = ArrayUtility.randomIntegers(i, 1000, 0, 100);
//            System.out.println(Arrays.toString(integers));
            quickSort(integers, 0, integers.length);
//            System.out.println(Arrays.toString(integers));
            assert Ordering.natural().isOrdered(Arrays.asList(integers));
        }
    }
}
