package methodology.dp;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WordWrap {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[] a = new int[n];
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
      }
      List<Integer> solve = solve(a, s.nextInt());
      for (Integer integer : solve) {
        System.out.print(integer + " ");
      }
      System.out.println();
    }
  }

  private static List<Integer> solve(int[] ws, int line) {
    int n = ws.length;
    // cost for ws[s, e)
    int[][] minCost = new int[n + 1][n + 1];
    int[][] minCostI = new int[n + 1][n + 1];
    // len for ws[s, e)
    int[][] len = new int[n + 1][n + 1];
    for (int x = 0; x < n; x++) {
      for (int y = x + 1; y <= n; y++) {
        if (y == x + 1) len[x][y] = ws[x];
        else len[x][y] = len[x][y - 1] + ws[y - 1] + 1;
      }
    }
    for (int i = 0; i + 1 < n; i++) {
      minCost[i][i + 1] = cost(line - ws[i]);
      minCostI[i][i + 1] = 0;
    }

    for (int step = 2; step <= n; step++) {
      for (int x = 0; x + step <= n; x++) {
        int y = x + step;
        minCost[x][y] = Integer.MAX_VALUE;
        if (len[x][y] <= line) {
          minCostI[x][y] = 0;
          minCost[x][y] = cost(line - len[x][y]);
        }
        for (int k = x + 1; k < y; k++) {
          if (minCost[x][k] + minCost[k][y] < minCost[x][y]) {
            minCost[x][y] = minCost[x][k] + minCost[k][y];
            minCostI[x][y] = k;
          }
        }
      }
    }
    List<Integer> res = new LinkedList<>();
    res.add(1);
    res.add(n);
    LinkedList<int[]> s = new LinkedList<>();
    s.add(new int[]{0, n});
    while (!s.isEmpty()) {
      int[] e = s.poll();
      int i = minCostI[e[0]][e[1]];
      if (i != 0) {
        assert i > e[0] && i < e[1];
        res.add(i);
        res.add(i+1);
        s.add(new int[]{e[0], i});
        s.add(new int[]{i, e[1]});
      }
    }
    res.sort(null);
    return res;
  }

  private static int cost(int a) {
    return a * a * a;
  }


}
