package methodology.dynamic;

/**
 * Created by zzt on 5/15/16.
 * <p>
 * <h3>Explanation</h3>
 * <li>将问题明确为：找出以 end(0 ... n-1)为终点的子序列的最值, max of sum is the max of them</li>
 * <li>当然也可以理解为：找出以start(n-1 ... 0)为起点的子序列</li>
 * <p>
 * <h3>关键是子问题的选择：不是 max of subset 而是 max to end(而其实这个问题包含了max of subset)</h3>
 *
 * @see LCSequence
 * @see LongestIncreasing
 */
public class MaxOfSumOpt {

    /**
     * <p>Suppose that we've solved the problem for x[0, n-1]; how can we extend that to
     * include x[i]?</p>
     * <p>the maximum-sum is either in x[0, n-1] or it ends at x[i]</p>
     *
     * @param nums input array
     *
     * @return max sum
     */
    public static int maxSumDynamic(int[] nums) {
        int max = 0;
        int maxEndHere = 0;
        for (int num : nums) {
            // update now state from last max
            maxEndHere = Math.max(0, maxEndHere + num);
            // remember the max
            max = Math.max(maxEndHere, max);
        }
        return max;
    }

    /**
     * Suppose we have solved sub-problem x[0, n/2], x[n/2 + 1, n],
     * how can we get max sum of x[0, n]:
     * the maximum-sum is max(first half, second half, cross the middle)
     * @param nums input array
     *
     * @return max sum
     */
    public static int maxSumDivideConquer(int[] nums) {
        return maxSumDivideConquer(nums, 0, nums.length);
    }

    private static int maxSumDivideConquer(int[] nums, int start, int end) {
        if (start == end) {// empty
            return 0;
        }
        int half = (start + end) / 2;
        int lmax = maxSumDivideConquer(nums, start, half);
        int rmax = maxSumDivideConquer(nums, half, end);

        int left = 0, partSum = 0;
        for (int i = start; i < half; i++) {
            left = Math.max(partSum + nums[i], left);
        }
        int right = 0;
        partSum = 0;
        for (int i = half; i < end; i++) {
            right = Math.max(partSum + nums[i], right);
        }
        return Math.max(lmax, Math.max(rmax, left + right));
    }
}
