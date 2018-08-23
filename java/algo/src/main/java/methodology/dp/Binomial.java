package methodology.dp;


import java.util.Scanner;


public class Binomial {


  private static final int mod = ((int) (1e9 + 7));

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      long cnr = solve(s.nextInt(), s.nextInt());
      System.out.println(cnr);
    }
  }

  private static long solve(int n, int r) {
    if (n < r) return 0;
    long[][] dp = new long[n][r];
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i < dp.length; i++) {
      for (int x = 1; x < dp[0].length; x++) {
        if (x == i) dp[i][x] = 1;
        else dp[i][x] = (dp[i-1][x] + dp[i-1][x-1])%mod;
      }
    }
    return dp[n-1][r-1];
  }


}
