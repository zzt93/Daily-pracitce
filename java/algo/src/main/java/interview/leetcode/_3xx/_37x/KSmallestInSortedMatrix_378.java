package interview.leetcode._3xx._37x;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author zzt
 */
public class KSmallestInSortedMatrix_378 {

  public int kthSmallest(int[][] m, int k) {
    int n = m.length;
    PriorityQueue<Object[]> q = new PriorityQueue<>(Comparator.comparingInt(o->(Integer)o[0]));
    Comparator<int[]> c1 = Comparator.comparingInt(is -> is[0]);
    TreeSet<int[]> set = new TreeSet<>(c1.thenComparingInt(is->is[1]));
    int[] ints = {0, 0};
    q.add(new Object[]{m[0][0], ints});
    set.add(ints);
    for (int i = 1; i < k; i++) {
      int[] is = (int[]) q.poll()[1];
      int[] is1 = {is[0], is[1]+1}, is2 = {is[0]+1, is[1]};
      if (in(is1, n) && set.add(is1)) q.add(new Object[]{m[is1[0]][is1[1]], is1});
      if (in(is2, n) && set.add(is2)) q.add(new Object[]{m[is2[0]][is2[1]], is2});
    }
    return (int) q.poll()[0];
  }

  private boolean in(int[] is, int n) {
    return is[0] < n && is[1] < n;
  }

}
