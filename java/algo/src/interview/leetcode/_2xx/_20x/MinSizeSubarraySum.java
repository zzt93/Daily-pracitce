package interview.leetcode._2xx._20x;

import java.util.Arrays;

/**
 * Created by zzt on 12/7/17.
 * <p>
 * <h3></h3>
 */
public class MinSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int l = nums.length + 1;
        int[] sum = new int[l];
        sum[0] = 0;
        for (int i = 1; i < l; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int min = l;
        for (int i = 0; i < l; i++) {
            int x = Arrays.binarySearch(sum, i + 1, l, sum[i] + s);
            if (x < 0) {
                x = -x - 1;
            }
            if (x == l) {
                break;
            }
            if (min > x - i) {
                min = x - i;
            }
        }
        return min == l ? 0 : min;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum s = new MinSizeSubarraySum();
        System.out.println(s.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(s.minSubArrayLen(7, new int[]{7, 1, 2, 3, 4}));
        System.out.println(s.minSubArrayLen(17, new int[]{7, 1, 2, 3, 4}));
        System.out.println(s.minSubArrayLen(117, new int[]{7, 1, 2, 3, 4}));
        System.out.println(s.minSubArrayLen(117, new int[]{}));
    }
}
