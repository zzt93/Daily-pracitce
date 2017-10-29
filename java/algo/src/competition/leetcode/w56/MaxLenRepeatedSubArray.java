package competition.leetcode.w56;

/**
 * Created by zzt on 10/29/17.
 * <p>
 * <h3></h3>
 */
public class MaxLenRepeatedSubArray {

    public int findLength(int[] A, int[] B) {
        int al = A.length;
        int bl = B.length;
        if (al == 0 || bl == 0) return 0;

        int[][] dp = new int[al][bl];
        dp[0][0] = A[0] == B[0] ? 1 : 0;
        for (int i = 1; i < al; i++) {
            dp[i][0] = A[i] == B[0] ? 1 : 0;
        }
        for (int i = 1; i < bl; i++) {
            dp[0][i] = B[i] == A[0] ? 1 : 0;
        }
        int max = 0;
        for (int i = 1; i < al; i++) {
            for (int s = 1; s < bl; s++) {
                if (A[i] == B[s]) {
                    dp[i][s] = dp[i - 1][s - 1] + 1;
                } else {
                    dp[i][s] = 0;
                }
                if (dp[i][s] > max) {
                    max = dp[i][s];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxLenRepeatedSubArray len = new MaxLenRepeatedSubArray();
        System.out.println(len.findLength(new int[]{0,1,1,1,1}, new int[]{1,0,1,0,1}));
        System.out.println(len.findLength(new int[]{}, new int[]{}));
        System.out.println(len.findLength(new int[]{1, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
        System.out.println(len.findLength(new int[]{1, 3, 2, 1, 2, 1, 4, 7}, new int[]{3, 2, 1, 4, 7}));
    }
}
