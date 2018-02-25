package competition.leetcode.w73;

/**
 * @author zzt
 */
public class Domino {

  private static int M = (int) (1e9 + 7);

  public int numTilings(int N) {
    int[] dp = new int[1001];
    dp[0] = dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= N; i++) {
      int sum = (dp[i - 1] + dp[i - 2])%M;
      for (int x = 3; x <= i; x++) {
        sum = (sum + dp[i-x])%M;
        sum = (sum + dp[i-x])%M;
      }
      dp[i] = sum;
    }
    return dp[N];
  }

  public static void main(String[] args) {
    Domino d = new Domino();
    for (int i = 1; i <= 100; i++) {
      System.out.println(d.numTilings(i));
    }
    System.out.println(d.numTilings(1000));
  }
}
