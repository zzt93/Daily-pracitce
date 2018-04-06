package competition.leetcode.w76;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class MinSwap {

    private static final int SWAP = 1;
    private static final int NOT = 0;

    public int minSwap(int[] A, int[] B) {
        int[][] dp = new int[A.length][2];
        dp[0] = new int[]{0, 1};
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1] || B[i] <= B[i - 1]) {
                dp[i][SWAP] = dp[i - 1][NOT] + 1;
                dp[i][NOT] = dp[i - 1][SWAP];
            } else if (A[i] <= B[i - 1] || B[i] <= A[i - 1]) {
                dp[i][SWAP] = dp[i - 1][SWAP] + 1;
                dp[i][NOT] = dp[i - 1][NOT];
            } else {
                dp[i][NOT] = Math.min(dp[i - 1][SWAP], dp[i - 1][NOT]);
                dp[i][SWAP] = Math.min(dp[i - 1][NOT], dp[i - 1][SWAP]) + 1;
            }
        }
        return Math.min(dp[A.length - 1][0], dp[A.length - 1][1]);
    }

    public static void main(String[] args) {
        MinSwap s = new MinSwap();
        System.out.println(s.minSwap(new int[]{1, 2, 7, 7, 8}, new int[]{3, 5, 6, 9, 10}));
        System.out.println(s.minSwap(new int[]{1, 2, 7, 7, 8,11,13,15,14}, new int[]{3, 5, 6, 9, 10,11,12,13,17}));
        System.out.println(s.minSwap(new int[]{1, 2, 3, 7}, new int[]{1, 3, 5, 4}));
    }
}
