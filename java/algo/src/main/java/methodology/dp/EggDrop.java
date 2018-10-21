package methodology.dp;


import java.util.Scanner;


public class EggDrop {



  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int k = s.nextInt();
      int[][] dp = new int[n+1][k+1];
      long cnr = solve(n, k, dp);
      System.out.println(cnr);
    }
  }

  private static int solve(int n, int k, int[][] dp) {
    if (k == 0) return 0;
    if (n == 1 || k == 1) return dp[n][k] = k;

    if (dp[n][k] == 0) {
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < k; i++) {
        min = Math.min(Math.max(solve(n-1, i, dp), solve(n, k-i-1,dp)), min);
      }
      dp[n][k] = min + 1;
    }
    return dp[n][k];
  }


}
