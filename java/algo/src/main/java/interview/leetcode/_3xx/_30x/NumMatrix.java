package interview.leetcode._3xx._30x;

/**
 * @author zzt
 */
public class NumMatrix {

  private int[][] dp;

  public NumMatrix(int[][] matrix) {
    int m = matrix.length;
    if (m == 0) {
      return;
    }
    int n = matrix[0].length;
    if (n == 0) {
      return;
    }
    dp = new int[m][n];
    dp[0][0] = matrix[0][0];
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + matrix[i][0];
    }
    for (int i = 1; i < n; i++) {
      dp[0][i] = dp[0][i - 1] + matrix[0][i];
    }
    for (int x = 1; x < m; x++) {
      for (int y = 1; y < n; y++) {
        dp[x][y] = dp[x - 1][y] + dp[x][y - 1] - dp[x - 1][y - 1] + matrix[x][y];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if (row1 == 0 && col1 == 0) {
      return dp[row2][col2];
    } else if (row1 == 0) {
      return dp[row2][col2] - dp[row2][col1 - 1];
    } else if (col1 == 0) {
      return dp[row2][col2] - dp[row1 - 1][col2];
    } else {
      return dp[row2][col2] - dp[row2][col1 - 1] - dp[row1 - 1][col2] + dp[row1 - 1][col1 - 1];
    }
  }

}
