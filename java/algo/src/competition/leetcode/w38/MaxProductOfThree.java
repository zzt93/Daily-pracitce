package competition.leetcode.w38;

import java.util.Arrays;

/**
 * Created by zzt on 6/25/17.
 * <p>
 * <h3></h3>
 */
public class MaxProductOfThree {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int f = nums[0] * nums[1] * nums[2];

        int l = nums.length;
        int s = nums[l - 1] * nums[l - 2] * nums[l - 3];

        int t = nums[0] * nums[1] * nums[l - 1];

        return Math.max(f, Math.max(s, t));
    }

    public static void main(String[] args) {
        MaxProductOfThree max = new MaxProductOfThree();
        System.out.println(max.maximumProduct(new int[]{1, 2, 3}));
        System.out.println(max.maximumProduct(new int[]{1, 2, 3, 4}));
        System.out.println(max.maximumProduct(new int[]{-4, -5, -1, 1, 2, 3}));
        System.out.println(max.maximumProduct(new int[]{-4, -5, 1, 2, 3}));
        System.out.println(max.maximumProduct(new int[]{-4, -1, 1, 2, 3}));
    }
}
