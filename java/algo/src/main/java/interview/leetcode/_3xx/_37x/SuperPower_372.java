package interview.leetcode._3xx._37x;

/**
 * @author zzt
 */
public class SuperPower_372 {

  private int M = 1337;
  public int superPow(int a, int[] b) {
    return pow(sp(a, b, b.length-1),10) * pow(a, b[b.length-1])%M;
  }

  private int sp(int a, int[] b, int e) {
    if (e == 0) return 1;
    return pow(sp(a, b, e-1),10) * pow(a, b[e-1])%M;
  }

  private int pow(int a, int k) {
    a = a % M;
    int res = 1;
    for (int i = 0; i < k; i++) {
      res = res * a % M;
    }
    return res;
  }

  public static void main(String[] args) {
    SuperPower_372 s = new SuperPower_372();
    System.out.println(s.superPow(2, new int[]{3}));
  }

}
