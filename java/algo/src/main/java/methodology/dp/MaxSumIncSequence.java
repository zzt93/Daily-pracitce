package methodology.dp;

import java.util.Scanner;

public class MaxSumIncSequence {

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
    int[] dp = new int[a.length];
    int max = 0;
    for (int i = 0; i < a.length; i++) {
      dp[i] = a[i];
      for (int x = 0; x < i; x++) {
        if (a[i] > a[x]) dp[i] = Math.max(dp[i], dp[x] + a[i]);
      }
      max = Math.max(max, dp[i]);
    }
    return max;
  }


}
