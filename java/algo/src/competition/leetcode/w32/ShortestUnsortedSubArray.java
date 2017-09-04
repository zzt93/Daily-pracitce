package competition.leetcode.w32;

import java.util.Arrays;

/**
 * Created by zzt on 5/14/17.
 * <p>
 * <h3></h3>
 */
public class ShortestUnsortedSubArray {

    public int findUnsortedSubarray(int[] nums) {
        int i, start = 0, max = Integer.MIN_VALUE;
        for (i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (max < n) {
                max = n;
            } else if (max > n) {
                start = i - 1;
                break;
            }
        }
        int min = max, end = 0;
        for (; i < nums.length; i++) {
            int n = nums[i];
            if (n < max) {
                if (n < min) {
                    int in = Arrays.binarySearch(nums, 0, start, n);
                    if (in < 0) {
                        start = -1 - in;
                    } else {
                        while (nums[in + 1] == n) {
                            in++;
                        }
                        start = in + 1;
                    }
                }
                end = i + 1;
            } else if (n > max) {
                max = n;
            }
        }
        return end - start;
    }

    public static void main(String[] args) {
        ShortestUnsortedSubArray array = new ShortestUnsortedSubArray();
        //        System.out.println(array.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{1}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{1, 2, 3, 4}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{1, 2, 3, 4, 6, 5, 4, 3}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{1, 2, 3, 4, 6, 5, 4, 3,
        // 4, 5}));
        //        System.out.println(array.findUnsortedSubarray(new int[]{1, 2, 3, 4, 6, 6, 5, 5,
        // 4, 3, 4, 5}));
        System.out.println(array.findUnsortedSubarray(new int[]{1, 2, 3, 3, 4, 6, 5, 4, 3, 4, 5}));
        System.out.println(array.findUnsortedSubarray(new int[]{3, 3, 3, 3, 4, 6, 5, 4, 3, 4, 5}));
        System.out.println(array.findUnsortedSubarray(new int[]{3, 3, 3, 3, 3}));
        System.out.println(array.findUnsortedSubarray(new int[]{3, 3, 3, 3, 3, 5, 4}));
        System.out.println(array.findUnsortedSubarray(new int[]{-1, -1, -1}));
    }
}
