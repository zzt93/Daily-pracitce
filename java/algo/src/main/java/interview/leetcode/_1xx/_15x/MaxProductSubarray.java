package interview.leetcode._1xx._15x;

/**
 * Created by zzt on 11/8/17.
 * <p>
 * <h3></h3>
 */
public class MaxProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = max[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            max[i] = Math.max(n, Math.max(max[i - 1] * n, min[i - 1] * n));
            res = Math.max(max[i], res);
            min[i] = Math.min(n, Math.min(max[i - 1] * n, min[i - 1] * n));
        }
        return res;
    }

    public static void main(String[] args) {
        MaxProductSubarray m = new MaxProductSubarray();
        System.out.println(m.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(m.maxProduct(new int[]{}));
        System.out.println(m.maxProduct(new int[]{1}));
        System.out.println(m.maxProduct(new int[]{-2}));
        System.out.println(m.maxProduct(new int[]{2, 3, -2, 4, 4, 4, -3, -2}));
        System.out.println(m.maxProduct(new int[]{2, -3, -2, 4, 4, 4, -3, -2}));
    }
}
