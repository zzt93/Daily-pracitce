package interview.leetcode._3xx._34x;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zzt
 */
public class TopK_347 {

  public List<Integer> topKFrequent(int[] nums, int k) {
    Comparator<int[]> comparator = Comparator.comparingInt(it -> -it[1]);
    PriorityQueue<int[]> pq = new PriorityQueue<>(comparator.thenComparing(it -> -it[0]));
    HashMap<Integer, LongAdder> map = new HashMap<>();
    for (int num : nums) {
      map.computeIfAbsent(num, n->new LongAdder()).increment();
    }
    for (Entry<Integer, LongAdder> e : map.entrySet()) {
      pq.add(new int[]{e.getKey(), e.getValue().intValue()});
    }
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      res.add(pq.poll()[0]);
    }
    return res;
  }

}
