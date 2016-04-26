package interview.leetcode;

import java.util.Arrays;

/**
 * Created by zzt on 3/21/16.
 * <p>
 * Thought:
 * A[m], B[n]
 * <li>median of a sorted array? O(1). make them sorted -- O(m+n)</li>
 * <li>selection problem -- O(n)</li>
 * <li>O(log(n)) -- binary search</li>
 * <li>median -- compare number</li>
 */
public class MedianOfTwoSorted {
    public int[] subArray(int[] a, int start, int end) {
        assert (start <= end);
        return Arrays.copyOfRange(a, start, end);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return getPlainMedian(nums2);
        } else if (nums2.length == 0) {
            return getPlainMedian(nums1);
        }
        int all = (nums1.length + nums2.length);
        if (all % 2 == 0) {
            double f = find(nums1, nums2, all / 2);
            double s = find(nums1, nums2, all / 2 - 1);
            return (f + s) / 2;
        } else {
            return find(nums1, nums2, all / 2);
        }
    }

    private double getPlainMedian(int[] ints) {
        return ints.length % 2 == 0 ? (ints[ints.length / 2] + ints[ints.length / 2 - 1]) / 2.0 : ints[ints.length / 2];
    }

    public double find(int[] half, int[] aim, int target) {
        if (half.length == 0) {
            return aim[target];
        } else if (aim.length == 0) {
            return half[target];
        }
        int index = half.length / 2;
        int median = half[index];
        int lessMedianCount = Arrays.binarySearch(aim, median);
        if (lessMedianCount >= 0) {// find a same one
            lessMedianCount = lessMedianCount + 1;
        } else {
            lessMedianCount = -lessMedianCount - 1;
        }
        if (lessMedianCount + index == target) {
            return median;
        } else if (lessMedianCount + index > target) {
            return find(subArray(aim, 0, lessMedianCount), subArray(half, 0, index), target);
        } else {
            // (lessMedianCount + index < target)
            return find(subArray(aim, lessMedianCount, aim.length), subArray(half, index, half.length), target - lessMedianCount - index);
        }
    }


    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSorted()
                .findMedianSortedArrays(new int[]{}, new int[]{2, 3}));
    }
}
