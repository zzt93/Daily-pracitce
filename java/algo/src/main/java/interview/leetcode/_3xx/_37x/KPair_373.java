package interview.leetcode._3xx._37x;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zzt
 *
 * <ul> <li>split sum</li> <li>index heap: (0,0) (0,1) (1,0) ...</li> </ul>
 */
public class KPair_373 {

  public static void main(String[] args) {
    KPair_373 k = new KPair_373();
    System.out.println(k.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 17));
    System.out.println(k.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2}, 2));
    System.out.println(k.kSmallestPairs(new int[]{}, new int[]{}, 1));
  }

  public List<int[]> kSmallestPairs(int[] n1, int[] n2, int k) {
    int l1 = n1.length, l2 = n2.length;
    List<int[]> res = new LinkedList<>();
    PriorityQueue<Object[]> q = new PriorityQueue<>(Comparator.comparingInt(is -> (Integer) is[0]));

    for (int x = 0; x < Math.min(k, l1 * l2); x++) {
      for (int[] is : split(x, l1, l2)) {
        q.add(new Object[]{n1[is[0]] + n2[is[1]], new int[]{n1[is[0]], n2[is[1]]}});
      }
      res.add((int[]) q.poll()[1]);
    }
    return res;
  }

  private List<int[]> split(int s, int l1, int l2) {
    List<int[]> res = new LinkedList<>();
    for (int i = Math.max(0, s - l2 + 1); i <= Math.min(s, l1 - 1); i++) {
      res.add(new int[]{i, s - i});
    }
    return res;
  }

}
