package competition.leetcode.w77;

import java.util.Arrays;

/**
 * @author zzt
 */
public class MaxIncSkyline {

  public int maxIncreaseKeepingSkyline(int[][] grid) {
    int res = 0;
    int r = grid.length, c = grid[0].length;
    int[] lr = new int[r];
    int[] tb = new int[c];
    for (int i = 0; i < r; i++) {
      lr[i] = Arrays.stream(grid[i]).max().getAsInt();
    }
    for (int i = 0; i < c; i++) {
      int t = Integer.MIN_VALUE;
      for (int x = 0; x < r; x++) t = Math.max(t, grid[x][i]);
      tb[i] = t;
    }
    for (int i = 0; i < r; i++) {
      for (int x = 0; x < c; x++) {
        res += (Math.min(lr[i], tb[x]) - grid[i][x]);
      }
    }
    return res;
  }

}
