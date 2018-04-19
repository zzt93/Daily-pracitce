package interview.leetcode._3xx._39x;

import java.util.Random;

/**
 * @author zzt
 */
public class RandomIndex_398 {

  private int[] ns;
  private Random r = new Random();
  public RandomIndex_398(int[] nums) {
    ns = nums;
  }

  public int pick(int target) {
    int c = 0, res = 0;
    for (int i = 0; i < ns.length; i++) {
      int n = ns[i];
      if (n == target) {
        c++;
        if (r.nextInt(c) == 0) res = i;
      }
    }
    return res;
  }


  public static void main(String[] args) {
    System.out.println(new RandomIndex_398(new int[]{1,2,3,3,3}).pick(1));
    System.out.println(new RandomIndex_398(new int[]{1,2,3,3,3}).pick(2));
    System.out.println(new RandomIndex_398(new int[]{1,2,3,3,3,4,4}).pick(4));
    System.out.println(new RandomIndex_398(new int[]{1,2,3,3,3}).pick(3));
  }

}
