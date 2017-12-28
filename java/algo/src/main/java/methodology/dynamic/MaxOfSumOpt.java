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
     * include x[n]?</p>
     * <p>the maximum-sum is either in x[0, n-1] or it ends at x[i]</p>
     * <p>
     * <p>Suppose that we have found that max-sum end at x[n-1],
     * how can we extend that to include x[n]</p>
     * <p>choose in (x[n], maxEndHere + x[n], 0), for maxEndHere >= 0,
     * so change to (maxEndHere + x[n], 0)</p>
     *
     * @param nums input array
     *
     * @return max sum
     */
    public static int maxSumDynamic(int[] nums) {
        int max = 0;
        int maxEndHere = 0;
        for (int num : nums) {
            /**
             * update now state from last max:
             */
            maxEndHere = Math.max(0, maxEndHere + num);
            /**
             * verification:
             * i == 0, maxEndHere is max sum end at 0
             * suppose i == k-1, maxEndHere is max sum end at i-1
             * when i == k, suppose the the sum([x, i]) larger than
             * max(0, maxEndHere + num), so
             * - if maxEndHere + num < 0 (i.e num < 0) and sum([x, i-1]) + num > 0,
             * so sum([x, i-1]) must larger than maxEndHere -- contradiction
             * - if maxEndHere + num >= 0 and sum([x, i-1]) + num > maxEndHere + num
             * so sum([x, i-1]) must larger than maxEndHere -- contradiction
             * In conclusion, max(0, maxEndHere + num) is maxEndHere
             */
            // choose the max from all sum
            max = Math.max(maxEndHere, max);
        }
        return max;
    }

    /**
     * Suppose we have solved sub-problem x[0, n/2], x[n/2 + 1, n],
     * how can we get max sum of x[0, n]:
     * the maximum-sum is max(first half, second half, cross the middle)
     *
     * @param nums input array
     *
     * @return max sum
     */
    public static int maxSumDivideConquer(int[] nums) {
        return maxSumDivideConquer(nums, 0, nums.length);
    }

    private static int maxSumDivideConquer(int[] nums, int start, int end) {
        if (start == end - 1) {// one element
            /**
             * Invariant: always return the max sum of nums[start, end)
             */
            return Math.max(0, nums[start]);
        }
        if (start == end) {// empty
            return 0;
        }
        int half = (start + end) / 2;
        int lmax = maxSumDivideConquer(nums, start, half);
        int rmax = maxSumDivideConquer(nums, half, end);

        /**
         * Invariant:
         * partSum -- sum end at nums[half - 1]
         * left -- max of partSum
         */
        int left = 0, partSum = 0;
        for (int i = half - 1; i >= start; i--) {
            partSum += nums[i];
            left = Math.max(partSum, left);
        }
        /**
         * Invariant:
         * partSum -- sum start at nums[half]
         * right -- max of partSum
         */
        int right = 0;
        partSum = 0;
        for (int i = half; i < end; i++) {
            partSum += nums[i];
            right = Math.max(partSum, right);
        }
        return Math.max(lmax, Math.max(rmax, left + right));
    }

    public static int maxSumPreCompute(int[] nums) {
        // set up pre computed array
        int[] pre = new int[nums.length + 1];
        int now = 0;
        // a state for no element, which prepare for start from beginning
        pre[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            now += num;
            pre[i + 1] = now;
        }

        /**
         * special case for test:
         * <li>empty</li>
         * <li>1 1 1</li>
         * <li>-1 -1 -1</li>
         * <li>1 1 -1 -1</li>
         * <li>-1 -1 1 1</li>
         *
         */
        // find max of sum of sub-vector by finding max gap(maxI >= minI)
        // TODO: 7/6/16 make it clear
        int min = Integer.MAX_VALUE, minI = -1;
        int max = Integer.MIN_VALUE, maxI = -1;
        int maxOf = 0;
        for (int i = 0; i < pre.length; i++) {
            int n = pre[i];
            if (min > n) {
                min = n;
                minI = i;
            }
            if (max < n) {
                max = n;
                maxI = i;
            }
            if (maxI >= minI) {
                if (max - min > maxOf) {
                    maxOf = max - min;
                }
            } else { // reset max when minI is ahead of maxI
                max = min;
            }
        }
        return maxOf;
    }
}
