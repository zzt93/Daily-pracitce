package methodology.dp;

import java.util.Scanner;

public class CutRod {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int x = s.nextInt(), y = s.nextInt(), z = s.nextInt();
      long cnr = cut1(n, x, y, z);
      System.out.println(cnr);
    }
  }

  private static int cut1(int n, int x, int y, int z) {
    int[] dp = new int[n+1];
    // base case
    if (x <= n) dp[x] = 1;
    if (y <= n) dp[y] = 1;
    if (z <= n) dp[z] = 1;
    for (int i = Math.min(Math.min(x, y), z); i < n+1; i++) {
      // valid cut
      if (i >= x && dp[i-x] > 0) dp[i] = Math.max(dp[i], dp[i-x]+1);
      if (i >= y && dp[i-y] > 0) dp[i] = Math.max(dp[i], dp[i-y]+1);
      if (i >= z && dp[i-z] > 0) dp[i] = Math.max(dp[i], dp[i-z]+1);
    }
    return dp[n];
  }

}
