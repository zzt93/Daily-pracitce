package methodology.dp;

import java.util.Scanner;

public class PalindromicPartition {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    s.nextLine();
    for (int i = 0; i < t; i++) {
      String s1 = s.nextLine();
      int n = s1.length();
      char[] cs = s1.toCharArray();
      Integer[][] dp = new Integer[n + 1][n + 1];
      boolean[][] p = getPalin(n, cs);
      int time = partition2(n, p);
//      int time = partition(0, n, dp, p);
      System.out.println(time);
    }
  }

  /**
   * @return palin[s][e]: for str[s, e]
   */
  private static boolean[][] getPalin(int n, char[] cs) {
    boolean[][] p = new boolean[n + 1][n + 1];
    for (int step = 0; step < n - 1; step++) {
      for (int x = 0; x + step < n; x++) {
        if (step == 0) p[x][x] = true;
        else if (step == 1) p[x][x + step] = cs[x] == cs[x + step];
        else {
          p[x][x + step] = p[x + 1][x + step - 1] && cs[x] == cs[x + step];
        }
      }
    }
    return p;
  }

  /**
   * for str[s, e), return min cut
   */
  private static int partition(int s, int e, Integer[][] dp, boolean[][] p) {
    if (p[s][e - 1]) return dp[s][e] = 0;
    if (dp[s][e] != null) return dp[s][e];

    int min = Integer.MAX_VALUE;
    for (int i = s + 1; i < e; i++) {
      min = Math.min(min, partition(s, i, dp, p) + partition(i, e, dp, p) + 1);
    }
    return dp[s][e] = min;
  }

  private static int partition2(int n, boolean[][] p) {
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      if (p[0][i]) {
        dp[i] = 0;
      } else {
        int min = Integer.MAX_VALUE;
        // longest increasing sub-sequence?
        for (int x = 0; x < i; x++) { // cut after 'x'
          if (p[x + 1][i]) min = Math.min(min, dp[x] + 1);
        }
        dp[i] = min;
      }
    }
    return dp[n - 1];
  }
}
