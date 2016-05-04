package pearls;

import competition.utility.ArrayUtility;
import competition.utility.Swap;

import java.util.Arrays;

/**
 * Created by zzt on 5/3/16.
 * <p>
 * Usage:
 */
public class Sort {

    public static <T extends Comparable<T>> void mergeSortLoop(T[] a) {
        int number = 1;
        int group = a.length / number;
        while (group > 1) {
            number *= 2;
        }
    }

    public static <T extends Comparable<T>> int split(T[] a, int start, int end) {
        T pivot = a[start];
        final int small = start + 1;
        int large = end;
        int now = small;
        /**
         * invariant: should be hold when loop start/end
         * <li>a[small, now) <= pivot</li>
         * <li>a[large, a.length) > pivot</li>
         * <li>a[now, large) not tested</li>
         */
        while (true) {
            while (now < large && a[now++].compareTo(pivot) <= 0) ;
            // now == large or a[now - 1] > pivot
            while (now < large && a[--large].compareTo(pivot) > 0) ;
            // now == large or a[large] <= pivot
            if (now == large) {
                if (a[now - 1].compareTo(pivot) > 0) {
                    // a[now-1] > pivot, invariant broken, so fix it
                    now--;
                    large--;
                }
                break;
            }
            Swap.swap(a, now - 1, large);
        }
        // when finished, now == large
        assert large == now;
        Swap.swap(a, start, now - 1);
        return now - 1;
    }

    public static void main(String[] args) {
        Integer[] t1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(split(t1, 0, t1.length));
        System.out.println(Arrays.toString(t1));
        Integer[] t2 = {7, 6, 5, 4, 3, 2, 1};
        System.out.println(split(t2, 0, t2.length));
        System.out.println(Arrays.toString(t2));
        Integer[] t3 = ArrayUtility.randomInts(32, 10, 0, 10);
        System.out.println(split(t3, 0, t3.length));
        System.out.println(Arrays.toString(t3));
    }
}