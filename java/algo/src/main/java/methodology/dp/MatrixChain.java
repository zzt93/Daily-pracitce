package methodology.dp;


import java.util.Scanner;


public class MatrixChain {

  public static void main(String[] args) throws Exception {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[] a = new int[n];
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
      }
      int[][] dp = new int[a.length][a.length];
      int min = minCost(a, 0, a.length - 1, dp);
      System.out.println(min);
    }
  }

  /**
   * min cost if s & e is the last two matrix to multiply
   */
  private static int minCost(int[] a, int s, int e, int[][] dp) {
    if (dp[s][e] != 0) return dp[s][e];
    if (s + 1 == e) { // only one matrix
      return 0;
    } else if (s + 2 == e) { // two matrix, base case
      return dp[s][e] = a[s] * a[e] * a[s + 1];
    }
    int min = Integer.MAX_VALUE;
    for (int i = s + 1; i < e; i++) {
      min = Math.min(min, minCost(a, s, i, dp) + minCost(a, i, e, dp) + a[s] * a[e] * a[i]);
    }
    return dp[s][e] = min;
  }


}
