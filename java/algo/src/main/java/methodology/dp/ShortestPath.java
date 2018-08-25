package methodology.dp;

import java.util.Scanner;

/**
 */
public class ShortestPath {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[][] a = new int[n][n];
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          a[j][k] = s.nextInt();
        }
      }
      Integer[][] shortest = new Integer[n][n];
      shortest(a, shortest);
      for (Integer[] row : shortest) {
        for (Integer x : row) {
          System.out.print(x + " ");
        }
      }
      System.out.println();
    }
  }

  private static void shortest(int[][] g, Integer[][] dp) {
    int n = g.length;
    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[0].length; j++) {
        dp[i][j] = g[i][j];
      }
    }

    for (int k = 0; k < n; k++) {
      for (int i = 0; i < g.length; i++) {
        for (int j = 0; j < g[0].length; j++) {
          if (dp[i][k] + dp[k][j] < dp[i][j] ) {
            dp[i][j] = dp[i][k] + dp[k][j];
          }
        }
      }
    }
  }

}
