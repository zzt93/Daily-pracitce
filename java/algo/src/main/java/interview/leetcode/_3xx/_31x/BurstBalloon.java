package interview.leetcode._3xx._31x;

/**
 * @author zzt
 *
 * DP: sub-problem; dimension: nums's dimension or action dimension; direction: reverse? right to
 * left?
 */
public class BurstBalloon {

  public int maxCoins(int[] nums) {
    int l = nums.length;
    int[] n = new int[l + 2];
    int i = 1;
    for (int num : nums) {
      if (num > 0) {
        n[i++] = num;
      }
    }
    n[0] = n[l + 1] = 1;

    int[][] dp = new int[l + 2][l + 2];
    for (int r = 1; r <= l; r++) {
      for (int left = 0; left + r < l + 1; left++) {
        int right = left + r + 1;
        for (int k = left + 1; k < right; k++) {
          dp[left][right] = Math.max(dp[left][right],
              dp[left][k] + n[left] * n[k] * n[right] + dp[k][right]);
        }
      }
    }
    return dp[0][l + 1];
  }

  public static void main(String[] args) {
    BurstBalloon b = new BurstBalloon();
    System.out.println(b.maxCoins(new int[]{3, 1, 5, 8}));
  }

}
