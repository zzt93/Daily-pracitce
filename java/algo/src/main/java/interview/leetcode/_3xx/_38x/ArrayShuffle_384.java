package interview.leetcode._3xx._38x;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zzt
 */
public class ArrayShuffle_384 {

  private int[] n;
  private Random r = new Random(12);
  public ArrayShuffle_384(int[] nums) {
    n = nums;
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    return n;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    int[] res = new int[n.length];
    System.arraycopy(n, 0, res, 0, n.length);
    for (int i = n.length-1; i >= 0; i--) {
      int j;
      if ((j = r.nextInt(i+1)) < i) {
        int t = res[i];
        res[i] = res[j];
        res[j] = t;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    ArrayShuffle_384 s = new ArrayShuffle_384(new int[]{1, 2, 3});
    for (int i = 0; i < 100; i++) {
      System.out.println(Arrays.toString(s.shuffle()));
    }
  }
}
