package interview.leetcode._3xx._33x;

/**
 * @author zzt
 *
 * better: f[i] = f[i>>1] + (i&1);
 */
public class CountingBits_338 {

  public int[] countBits(int num) {
    int[] res = new int[num+1];
    res[0] = 0;
    int pow = 1, c = 0;
    for (int i = 1; i <= num; i++) {
      res[i] = res[i - pow] + 1;
      c++;
      if (c == pow) {
        pow *= 2;
        c = 0;
      }
    }
    return res;
  }

}
