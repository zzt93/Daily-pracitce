package interview.leetcode._2xx._22x;

import java.util.Arrays;

/**
 * Created by zzt on 12/19/17.
 * <p>
 * <h3>DP</h3>
 * <li>dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1</li>
 * <li>dp[i][j] = min(xLen, yLen, dp[i-1][j-1]</li>
 */
public class MaxSquare {

    public int maximalSquare(char[][] mn) {
        int m = mn.length;
        if (m == 0) {
            return 0;
        }
        int n = mn[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int[][] xl = new int[m][n];
        for (int i = 0; i < m; i++) {
            xl[i][0] = mn[i][0] - '0';
        }
        int[][] yl = new int[m][n];
        for (int i = 0; i < n; i++) {
            yl[0][i] = mn[0][i] - '0';
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                xl[i][j] = mn[i][j] == '0' ? 0 : xl[i][j - 1] + 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                yl[i][j] = mn[i][j] == '0' ? 0 : yl[i - 1][j] + 1;
            }
        }


        int max = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                max = Math.max(recur(xl, yl, dp, i, j), max);
            }
        }
        return max * max;
    }

    private int recur(int[][] xl, int[][] yl, int[][] dp, int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (dp[x][y] >= 0) {
            return dp[x][y];
        }

        int max = recur(xl, yl, dp, x - 1, y - 1) + 1;
        return dp[x][y] = Math.min(max, Math.min(xl[x][y], yl[x][y]));
    }

    public static void main(String[] args) {
        MaxSquare m = new MaxSquare();
        System.out.println(m.maximalSquare(new char[][]{new char[]{'0', '0', '1', '0'}, new
                char[]{'1', '1', '1', '1'}, new char[]{'1', '1', '1', '1'}, new char[]{'1', '1',
                '1', '0'}, new char[]{'1', '1', '0', '0'}, new char[]{'1', '1', '1', '1'}, new
                char[]{'1', '1', '1', '0'}}));
    }
}
