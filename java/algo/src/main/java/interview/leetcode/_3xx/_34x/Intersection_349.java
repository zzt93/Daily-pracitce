package interview.leetcode._3xx._34x;

import java.util.HashSet;

/**
 * @author zzt
 */
public class Intersection_349 {

  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<>();
    HashSet<Integer> res = new HashSet<>();
    for (int i: nums1) {
      set.add(i);
    }
    for (int i: nums2) {
      if (set.contains(i)) res.add(i);
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
  }

}
