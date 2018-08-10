package interview.leetcode._4xx._40x;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zzt
 */
public class TrappingRainWater2_407 {


  /**
   * max/min in range: queue; bst; 1,-1
   * https://leetcode.com/problems/trapping-rain-water/description/
   */
  public int trap(int[] h) {
    if (h.length == 0) {
      return 0;
    }
    // pre: s[i]: [0, i)
    int[] s = new int[h.length+1];
    s[1] = h[0];
    for (int i = 1; i < h.length; i++) {
      s[i+1] = h[i] + s[i];
    }

    LinkedList<Integer> q = new LinkedList<>();
    int all = 0;
    for (int i = 0; i < h.length; i++) {
      if (q.isEmpty() || h[q.peekLast()] > h[i]) {
        q.addLast(i);
      } else {
        int left = 0;
        while (!q.isEmpty() && h[q.peekLast()] < h[i]) {
          left = q.removeLast();
        }
        if (q.isEmpty()) {
          all += cal(h, s, left, i + 1);
          q.addLast(i);
        } else {
          q.addLast(i);
        }
      }
    }
    int size = q.size();
    for (int i = 0; i < size -1; i++) {
      all += cal(h, s, q.pollFirst(), q.peekFirst()+1);
    }
    return all;
  }

  private int cal(int[] h, int[] s, int left, int e) {
    // pre: h [left, e)
    return Math.min(h[left], h[e-1]) * (e-left-2) - (s[e-1] - s[left+1]);
  }

  public static void main(String[] args) {
    TrappingRainWater2_407 t = new TrappingRainWater2_407();
//    System.out.println(t.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
//    System.out.println(t.trap(new int[]{}));
    System.out.println(t.trapRainWater(new int[][]{new int[]{12,13,1,12},new int[]{13,4,13,12},new int[]{13,8,10,12},new int[]{12,13,12,12},new int[]{13,13,13,13}}));
    System.out.println(t.trapRainWater(new int[][]{new int[]{1,4,3,1,3,2},new int[]{3,2,1,3,2,4},new int[]{2,3,3,2,3,1}}));
    System.out.println(t.trapRainWater(new int[][]{new int[]{1,4,3,1,3,2},new int[]{3,2,1,3,2,4},new int[]{3,2,1,2,2,4},new int[]{3,2,1,3,2,4},new int[]{2,3,3,2,3,1}}));
  }

  public int trapRainWater(int[][] heightMap) {
    if (heightMap == null || heightMap.length <= 1 || heightMap[0].length <= 1) {
      return 0;
    }
    int m = heightMap.length;
    int n = heightMap[0].length;
    boolean[][] visited = new boolean[m][n];
    // priority queue store the cell in format of [row, col, height]
    PriorityQueue<int[]> q = new PriorityQueue<>((c1, c2) -> c1[2] - c2[2]);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          visited[i][j] = true;
          q.offer(new int[]{i, j, heightMap[i][j]});
        }
      }
    }
    // bfs from outside
    int res = 0;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    while (!q.isEmpty()) {
      int[] cell = q.poll();
      for (int[] dir : dirs) {
        int x = cell[0] + dir[0];
        int y = cell[1] + dir[1];
        if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
          visited[x][y] = true;
          res += Math.max(0, cell[2] - heightMap[x][y]);
          q.offer(new int[]{x, y, Math.max(cell[2], heightMap[x][y])});
        }
      }
    }
    return res;
  }
}
