package interview.leetcode._2xx._27x;

/**
 * @author zzt
 */
public class PerfectSquare {

  public static void main(String[] args) {
    PerfectSquare perfectSquare = new PerfectSquare();
    for (int i = 1; i < 20; i++) {
      System.out.println(i+":"+perfectSquare.numSquares(i));
    }
  }

  public int numSquares(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      int min = n;
      for (int x = 1; x * x <= n && i - x * x >= 0; x++) {
        min = Math.min(min, dp[i - x * x] + 1);
      }
      dp[i] = min;
    }
    return dp[n];
  }

}
