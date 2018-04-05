package competition.leetcode.w77;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author zzt
 */
public class SameAve {

  public static void main(String[] args) {
    SameAve s = new SameAve();
    System.out.println(s.splitArraySameAverage(new int[]{5,3,11,19,2}));
    System.out.println(s.splitArraySameAverage(new int[]{17,5,5,1,14,10,13,1,6}));
    System.out.println(s.splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    System.out.println(s.splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 88}));
    int[] a = IntStream.range(0, 900).filter(i->new Random().nextBoolean()).limit(30).toArray();
    System.out.println(s.splitArraySameAverage(a));
  }

  public boolean splitArraySameAverage(int[] a) {
    Arrays.sort(a);
    int s = Arrays.stream(a).sum();
    int n = a.length;
    for (int k = 1; k <= n / 2; k++) {
      int aim = s * k / n;
      if (s * k % n != 0) {
        continue;
      }
      if (can(a, k, aim, 0, 0)) {
        return true;
      }
    }
    return false;
  }

  /**
   * choose next number in [i, a.length)
   */
  private boolean can(int[] a, int k, int aim, int i, int now) {
    if (k == 1) {
      return Arrays.binarySearch(a, i, a.length, aim - now) >= 0;
    }
    for (int chosen = i; chosen < a.length; chosen++) {
      if (can(a, k - 1, aim, chosen+1, now + a[chosen])) {
        return true;
      }
    }
    return false;
  }
}
