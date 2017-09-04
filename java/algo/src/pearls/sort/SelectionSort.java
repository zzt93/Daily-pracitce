package pearls.sort;

import com.google.common.collect.Ordering;
import competition.utility.ArrayUtility;
import competition.utility.Swap;

import java.util.Arrays;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public class SelectionSort {

    public static void selectionSort(Integer[] ints) {
        for (int i = 0; i < ints.length; i++) {
            int min = ints[i], minI = i;
            for (int inner = i; inner < ints.length; inner++) {
                if (min > ints[inner]) {
                    min = ints[inner];
                    minI = inner;
                }
            }
            Swap.swap(ints, i, minI);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final Integer[] integers = ArrayUtility.randomIntegers(i, 1000, 0, 10000);
            selectionSort(integers);
            assert Ordering.natural().isOrdered(Arrays.asList(integers));
        }
    }
}
