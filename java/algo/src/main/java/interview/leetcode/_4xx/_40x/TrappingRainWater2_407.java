package interview.leetcode._4xx._40x;

import java.util.LinkedList;

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
    System.out.println(t.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    System.out.println(t.trap(new int[]{}));
  }

}
