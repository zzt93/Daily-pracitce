package pearls;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 5/3/16.
 * <p>
 * Usage:
 */
public class BinarySearch {

    /**
     * return a arbitrary one if multiple same element appear
     *
     * @param a      input array
     * @param target target
     * @param <T>    type
     *
     * @return index or -1 if not found
     */
    public static <T extends Comparable<T>> int binarySearch(T[] a, T target) {
        assert ArrayUtility.isSorted(Arrays.asList(a), true);
        int left = 0;
        int right = a.length;
        int mid;
        /**
         * Invariant: target may in [left, right)
         */
        while (left < right) {
            mid = (left + right) / 2;
            final int i = a[mid].compareTo(target);
            if (i > 0) {
                right = mid;
            } else if (i < 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * return first appearance if multiple same element appear
     *
     * @param a      input array
     * @param target target
     * @param <T>    type
     *
     * @return index or -1 if not found
     */
    public static <T extends Comparable<T>> int binarySearchFirst(T[] a, T target) {
        assert ArrayUtility.isSorted(Arrays.asList(a), true);
        int left = 0;
        int right = a.length;
        int mid;
        int res = -1;
        /**
         * Invariant:
         * <li>target may in [left, right)</li>
         * <li>res may hold the index of first appearance of target</li>
         */
        while (left < right) {
            mid = (left + right) / 2;
            final int i = a[mid].compareTo(target);
            if (i > 0) {
                right = mid;
            } else if (i < 0) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final int size = 100;
        Integer[] t = new Integer[size];
        for (int i = 0; i < size; i++) {
            t[i] = i;
        }
        for (int i = 0; i < size; i++) {
            assert binarySearch(t, i) == i;
        }
        for (int i = 0; i < size; i++) {
            assert binarySearch(t, i + size) == -1;
        }
        // test find first
        final Integer[] t1 = new Integer[size];
        for (int i = 0; i < t1.length; i++) {
            t1[i] = 1;
        }
        for (int i = 0; i < size; i++) {
            assert binarySearchFirst(t, i) == i;
        }
        assert binarySearchFirst(t1, 1) == 0;
        final Integer[] t2 = new Integer[size * 2];
        for (int i = 0; i < size; i++) {
            t2[i] = i;
        }
        for (int i = size; i < size * 2; i++) {
            t2[i] = size;
        }
        assert binarySearchFirst(t2, size) == size;
    }
}
