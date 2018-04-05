package interview.leetcode._3xx._35x;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author zzt
 */
public class RussianDollEnv_354 {

  public static void main(String[] args) {
    RussianDollEnv_354 r = new RussianDollEnv_354();
    System.out.println(r.maxEnvelopes(
        new int[][]{new int[]{2, 100}, new int[]{3, 200}, new int[]{4, 300}, new int[]{5, 500},
            new int[]{5, 400}, new int[]{5, 250}, new int[]{6, 370}, new int[]{6, 360},
            new int[]{7, 380}}));
    System.out.println(r.maxEnvelopes(
        new int[][]{new int[]{5, 4}, new int[]{6, 4}, new int[]{6, 7}, new int[]{2, 3}}));
    System.out.println(r.maxEnvelopes(
        new int[][]{new int[]{5, 4}, new int[]{6, 7}, new int[]{2, 3}}));
  }

//  public int maxEnvelopes(int[][] es) {
//    int n = es.length;
//    if (n == 0) {
//      return 0;
//    }
//    Comparator<int[]> c1 = Comparator.comparingInt(is -> is[0]);
//    Comparator<int[]> c2 = c1.thenComparingInt(is -> is[1]);
//    Arrays.sort(es, c2);
//
//    // TODO 18/3/28 min height
//    int c, lastJ = 0;
//    for (c = 0; c < n - 1; c++) {
//      boolean has = false;
//      OUT:
//      for (int i = lastJ; i < n; i++) {
//        int[] key = {es[i][0] + 1, es[i][1] + 1};
//        int j = Arrays.binarySearch(es, i + 1, n, key, c2);
//        if (j < 0) {
//          j = -j - 1;
//        }
//        if (i == lastJ) {
//          lastJ = j;
//        }
//        while (j < n) {
//          if (es[j][1] > es[i][1]) {
//            has = true;
//            break OUT;
//          } else {
//            key[0] = es[j][0];
//            j = Arrays.binarySearch(es, j + 1, n, key, c2);
//            if (j < 0) {
//              j = -j - 1;
//            }
//          }
//        }
//      }
//      if (!has) {
//        break;
//      }
//    }
//    return c + 1;
//  }

  public int maxEnvelopes(int[][] es) {
    int n = es.length;
    if (n == 0) {
      return 0;
    }
    Comparator<int[]> c1 = Comparator.comparingInt(is -> is[0]);
    Comparator<int[]> c2 = c1.thenComparingInt(is -> is[1]);
    Arrays.sort(es, c2);

    List[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new LinkedList();
      for (int x = i+1; x < n; x++) {
        if (es[i][0] < es[x][0] && es[i][1] < es[x][1]) graph[i].add(x);
      }
    }
    Set<Integer> now = new HashSet<>(n);
    for (int i = 0; i < n; i++) now.add(i);
    int c;
    for (c = 0; !now.isEmpty() && c < n; c++) {
      Set<Integer> tmp = new HashSet<>(n);
      for (Integer i: now) {
        tmp.addAll(graph[i]);
      }
      now = tmp;
    }
    return c;
  }

  public int maxEnvelopesTLE(int[][] es) {
    int n = es.length;
    if (n == 0) return 0;

    boolean[][] dp = new boolean[n][n];
    for (int i = 0; i < n; i++) dp[0][i] = true;
    int c;
    for (c = 0; c < n-1; c++) {
      boolean has = false;
      for (int i = 0; i < n; i++) {
        if (!dp[c][i]) continue;
        for (int j = 0; j < n; j++) {
          if (j == i) continue;
          if (es[j][0] > es[i][0] && es[j][1] > es[i][1]) {
            has = true;
            dp[c + 1][j] = true;
          }
        }
      }
      if (!has) break;
    }
    return c + 1;
  }
}
