package interview.leetcode._3xx._34x;

/**
 * @author zzt
 */
public class IntegerBreak_343 {

  public int integerBreak(int n) {
    long res = 0;
    // i num, sum to n
    for (int i = 2; i <= Math.max(n/2, 2); i++) {
      int mean = n / i;
      int left = n - i * mean;
      long tmp = 1;
      for (int c = 0; c < i; c++) {
        if (left > 0) {
          tmp *= (mean + 1);
          left--;
        } else {
          tmp *= mean;
        }
      }
      res = Math.max(res, tmp);
    }
    return (int) res;
  }

  public static void main(String[] args) {
    IntegerBreak_343 i = new IntegerBreak_343();
    System.out.println(i.integerBreak(2));
    System.out.println(i.integerBreak(20));
    System.out.println(i.integerBreak(58));
  }

}
