package interview.leetcode._2xx._26x;

/**
 * @author zzt
 */
public class UglyNum2 {

  public static void main(String[] args) {
    UglyNum2 u = new UglyNum2();
    System.out.println(u.nthUglyNumber(1690));
    for (int i = 0; i < 20; i++) {
      System.out.println(u.nthUglyNumber(i + 1));
    }
  }

  public int nthUglyNumber(int n) {
    int[] dp = new int[n];
    dp[0] = 1;
    int i2 = 0, i3 = 0, i5 = 0;
    for (int i = 1; i < n; i++) {
      dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
      if (dp[i] == dp[i2] * 2) {
        i2++;
      }
      if (dp[i3] * 3 == dp[i]) {
        i3++;
      }
      if (dp[i5] * 5 == dp[i]) {
        i5++;
      }
    }
    return dp[n - 1];
  }

}
