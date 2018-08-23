package methodology.dp;

import java.util.Scanner;

/**
 * variation: what if first decreasing then increasing is also fine
 * dp[i][3] = Math.max(dp[i][3], Math.max(dp[x][1], dp[x][3]) + 1);
 */
public class Bitonic {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[] a = new int[n];
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
      }
      System.out.println(solve(a));
    }
  }

  private static int solve(int[] a) {
    int[][] dp = new int[a.length][3];
    int max = 1;
    for (int i = 0; i < 2; i++) {
      dp[0][i] = 1;
    }
    for (int i = 1; i < a.length; i++) {
      dp[i][0] = dp[i][1] = 1;
      for (int x = 0; x < i; x++) {
        if (a[i] > a[x]) {
          dp[i][0] = Math.max(dp[i][0], dp[x][0] + 1);
        } else if (a[i] < a[x]) {
          dp[i][1] = Math.max(dp[i][1], dp[x][1] + 1);
          dp[i][2] = Math.max(dp[i][2], Math.max(dp[x][0], dp[x][2]) + 1);
        }
      }
      for (int x = 0; x < dp[i].length; x++) {
        max = Math.max(max, dp[i][x]);
      }
    }
    return max;
  }
}
