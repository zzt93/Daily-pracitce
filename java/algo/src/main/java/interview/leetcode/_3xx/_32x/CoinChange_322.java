package interview.leetcode._3xx._32x;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zzt
 */
public class CoinChange_322 {

  public static void main(String[] args) {
    CoinChange_322 c = new CoinChange_322();
    System.out.println(c.coinChange(new int[]{1,2}, 2));
    System.out.println(c.coinChange(new int[]{456,117,5,145}, 1459));
    System.out.println(c.coinChange(new int[]{1, 2, 5}, 11));
    System.out.println(c.coinChange(new int[]{1, 2, 5}, 1111));
    System.out.println(c.coinChange(new int[]{2}, 11));
    System.out.println(c.coinChange(new int[]{1}, 0));
    System.out.println(c.coinChange(new int[]{100}, 10));
    System.out.println(c.coinChange(new int[]{2147483647}, 2));
    System.out.println(c.coinChange(new int[]{1,2147483647}, 2));
  }

  public int coinChange(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    Arrays.sort(coins);
    ArrayList<Integer> cs = new ArrayList<>();
    for (int coin : coins) {
      if (coin < amount + 1) {
        cs.add(coin);
      }
    }

    int maxCoin = -1;
    for (int coin : cs) {
      if (coin < amount + 1) {
        dp[coin] = 1;
        maxCoin = coin;
      }
    }
    if (maxCoin == -1) return -1;
    for (int i = cs.get(0) + 1; i < dp.length; i++) {
      int min = dp[i];
      for (int coin : cs) {
        if (i > coin) {
          min = Math.min(dp[i - coin], min);
        }
      }
      dp[i] = min == dp[i] ? dp[i] : (min + 1);
    }
    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
  }

}
