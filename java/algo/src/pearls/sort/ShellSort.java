package pearls.sort;

import com.google.common.collect.Ordering;
import competition.utility.ArrayUtility;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public class ShellSort {

    private static int[] gaps = {7, 3, 1};

    public static void shell(Integer[] ints) {
        for (int gap : gaps) {
            gapInsertionSort(ints, gap);
        }
    }

    private static void gapInsertionSort(Integer[] ints, int gap) {
        for (int j = gap; j < ints.length; j++) {
            int last = ints[j];
            int i;
            for (i = j; i >= gap; i -= gap) {
                if (last < ints[i - gap]) {
                    ints[i] = ints[i - gap];
                } else {
                    break;
                }
            }
            ints[i] = last;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final Integer[] integers = ArrayUtility.randomIntegers(i, 100, -100, 200);
            System.out.println(Arrays.toString(integers));
            shell(integers);
//            System.out.println(Arrays.toString(integers));
            assert Ordering.natural().isOrdered(Arrays.asList(integers));
        }
    }
}
