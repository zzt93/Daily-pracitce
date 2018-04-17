package interview.leetcode._3xx._39x;

/**
 * @author zzt
 */
public class RotateFunction_396 {

  public int maxRotateFunction(int[] a) {
    long max, last = 0;
    // ss[j] - ss[i]: sum of [i, j)
    long[] ss = new long[a.length+1];
    for (int i = 1; i < a.length+1; i++) ss[i] = a[i-1] + ss[i-1];
    for (int i = 0; i < a.length; i++) {
      last += (i * a[i]);
    }
    max = last;
    for (int s = a.length-1; s > 0; s--) {
      long sum = last - (a.length-1)*a[s] + (ss[s] - ss[0] + ss[a.length]-ss[s+1]);
      last = sum;
      max = Math.max(sum, max);
    }
    return (int)max;
  }

  public static void main(String[] args) {
    RotateFunction_396 r = new RotateFunction_396();
    System.out.println(r.maxRotateFunction(new int[]{4, 3, 2, 6}));
  }

}
