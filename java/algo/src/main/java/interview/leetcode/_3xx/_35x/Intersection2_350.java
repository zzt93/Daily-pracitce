package interview.leetcode._3xx._35x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzt
 */
public class Intersection2_350 {

  public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    List<Integer> res = new ArrayList<>();
    int i = 0, j = 0;
    while (i < nums1.length && j < nums2.length) {
      while (i < nums1.length && nums1[i] < nums2[j]) i++;
      if (i >= nums1.length) break;
      while (j < nums2.length && nums1[i] > nums2[j]) j++;
      if (j >= nums2.length) break;
      if (nums1[i] == nums2[j]) {
        res.add(nums1[i]);
        i++;
        j++;
      }
    }
    return res.stream().mapToInt(Integer::intValue).toArray();

  }

}
