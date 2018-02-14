package interview.leetcode._3xx._30x;

/**
 * @author zzt
 */
public class StockWithCooldown {

  public static void main(String[] args) {
    StockWithCooldown s = new StockWithCooldown();
    System.out.println(s.maxProfit(new int[]{1, 9, 9, 3, 0, 3, 1, 5}));
    System.out.println(s.maxProfit(new int[]{1, 2, 3, 0, 2, 1, 9, 9, 3, 0, 3, 1, 5}));
    System.out.println(s.maxProfit(new int[]{}));
    System.out.println(s.maxProfit(new int[]{1, 2, 3, 0, 2}));
  }

  public int maxProfit(int[] prices) {
    int l = prices.length;
    if (l == 0) {
      return 0;
    }
    int[][] dp = new int[l][];
    // new int[] {have, sell, cool}
    dp[0] = new int[]{-prices[0], 0, 0};
    for (int i = 1; i < dp.length; i++) {
      dp[i] = new int[]{
          Math.max(dp[i - 1][2] - prices[i], dp[i - 1][0]),
          dp[i - 1][0] + prices[i],
          Math.max(dp[i - 1][1], dp[i - 1][2])
      };
    }
    return Math.max(dp[l - 1][1], dp[l - 1][2]);
  }

}
