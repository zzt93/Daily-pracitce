package interview.leetcode._3xx._36x;

/**
 * @author zzt
 */
public class ValidPerfectSquare_367 {

  public boolean isPerfectSquare(int num) {
    int x = Math.max(1, num/2);
    for (int i = 1; i <= x; i++) if (i*i == num) return true;
    return false;
  }

  public static void main(String[] args) {
    ValidPerfectSquare_367 v = new ValidPerfectSquare_367();
    System.out.println(v.isPerfectSquare(1));
    System.out.println(v.isPerfectSquare(2));
    System.out.println(v.isPerfectSquare(4));
    System.out.println(v.isPerfectSquare(8));
    System.out.println(v.isPerfectSquare(1000000));
  }
}
