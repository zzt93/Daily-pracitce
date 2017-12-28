package competition.leetcode.w63;

/**
 * Created by zzt on 12/17/17.
 * <p>
 * <h3></h3>
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int l = cost.length;
        int[] dp = new int[l];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < l; i++) {
            dp[i] = Math.min(dp[i-1],dp[i-2]) + cost[i];
        }
        return Math.min(dp[l-1],dp[l-2]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs m = new MinCostClimbingStairs();
        System.out.println(m.minCostClimbingStairs(new int[]{1,2}));
        System.out.println(m.minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println(m.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
