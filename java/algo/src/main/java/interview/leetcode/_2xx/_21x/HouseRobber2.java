package interview.leetcode._2xx._21x;

/**
 * Created by zzt on 12/12/17.
 * <p>
 * <h3></h3>
 */
public class HouseRobber2 {

    public int rob(int[] n) {
        int l = n.length;
        if (l == 0) return 0;
        if (l == 1) return n[0];
        if (l == 2) return Math.max(n[0], n[1]);
        if (l == 3) return Math.max(n[0], Math.max(n[1], n[2]));

        int[] dp = new int[l];
        dp[0] = n[0];
        dp[1] = n[1];
        dp[2] = Math.max(n[0] + n[2], n[1]);
        for (int i = 3; i < l - 1; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + n[i];
        }
        int max = Math.max(dp[l - 2], dp[l - 3]);
        dp[2] = n[2];
        dp[3] = Math.max(n[1]+n[3],n[2]);
        for (int i = 4; i < l; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + n[i];
        }
        return Math.max(max, Math.max(dp[l - 2], dp[l - 1]));
    }

    public static void main(String[] args) {
        HouseRobber2 h = new HouseRobber2();
        System.out.println(h.rob(new int[]{1,2,3,4}));
        System.out.println(h.rob(new int[]{}));
        System.out.println(h.rob(new int[]{1}));
        System.out.println(h.rob(new int[]{1,2}));
        System.out.println(h.rob(new int[]{1,2,3}));
        System.out.println(h.rob(new int[]{1,2,3,4,5}));
        System.out.println(h.rob(new int[]{1,2,3,4,5,6}));
        System.out.println(h.rob(new int[]{1,2,3,4,5,6,7}));
        System.out.println(h.rob(new int[]{51,82,31,44,25,67,57}));
    }
}
