package interview.leetcode._4xx._41x;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author zzt
 */
public class ThirdMax_414 {

  public int thirdMax(int[] n) {
    HashSet<Integer> all = new HashSet<>();
    for (int i: n) all.add(i);
    if (all.size() < 3) {
      int max = Integer.MIN_VALUE;
      for (int x: all) max = Math.max(max, x);
      return max;
    }
    PriorityQueue<Integer> min = new PriorityQueue<>();
    for (Integer i : all) {
      if (min.size() < 3) {
        min.add(i);
      } else {
        if (min.peek() < i) {
          min.poll();
          min.add(i);
        }
      }
    }
    return min.peek();
  }

  public static void main(String[] args) {
    ThirdMax_414 t = new ThirdMax_414();
    System.out.println(t.thirdMax(new int[]{1,1,1,1,2}));
    System.out.println(t.thirdMax(new int[]{1,2,3}));
    System.out.println(t.thirdMax(new int[]{1,2,3,4,5}));
    System.out.println(t.thirdMax(new int[]{10,2,36,4,15}));
    System.out.println(t.thirdMax(new int[]{1,2}));
    System.out.println(t.thirdMax(new int[]{1,2,3,2}));
  }

}
