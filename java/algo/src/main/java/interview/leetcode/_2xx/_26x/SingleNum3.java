package interview.leetcode._2xx._26x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzt
 */
public class SingleNum3 {

  public int[] singleNumber(int[] nums) {
    int s = 0;
    for (int num : nums) {
      s ^= num;
    }
    int hb = Integer.highestOneBit(s);
    List<Integer> l = new ArrayList<>();
    List<Integer> r = new ArrayList<>();
    for (int num : nums) {
      if ((num & hb) != 0) {
        l.add(num);
      } else r.add(num);
    }
    int f = 0, ss = 0;
    for (Integer i : l) {
      f ^= i;
    }
    for (Integer i : r) {
       ss ^= i;
    }
    return new int[]{f, ss};
  }

  public static void main(String[] args) {
    SingleNum3 s = new SingleNum3();
    System.out.println(Arrays.toString(s.singleNumber(new int[]{1,1,2,2,3,4,4,5})));
  }

}
