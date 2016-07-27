package pearls.sort;

import com.google.common.collect.Ordering;
import competition.utility.ArrayUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.swap;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public class SelectionSort {

    public static void selectionSort(ArrayList<Integer> ints) {
        for (int i = 0; i < ints.size(); i++) {
            int min = ints.get(i), minI = i;
            for (int inner = i; inner < ints.size(); inner++) {
                if (min > ints.get(inner)) {
                    min = ints.get(inner);
                    minI = inner;
                }
            }
            swap(ints, i, minI);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final ArrayList<Integer> integers = ArrayUtility.randomIntList(i, 1000, 0, 10000);
            selectionSort(integers);
            assert Ordering.natural().isOrdered(integers);
        }
    }
}
