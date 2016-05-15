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

    public static int compute(int[] nums) {
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
}
