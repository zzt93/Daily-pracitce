package interview.leetcode._4xx._41x;

/**
 * @author zzt
 */
public class ArithmeticSlices_413 {

  public int numberOfArithmeticSlices(int[] A) {
    if (A.length < 3) return 0;
    int gap = A[1] - A[0], c = 2;
    int res = 0;
    for (int i = 2; i < A.length; i++) {
      if (A[i] - A[i-1] == gap) {
        c++;
      } else {
        if (c >= 3) {
          res += cal(c);
        }
        gap = A[i] - A[i-1];
        c = 2;
      }
    }
    if (c >= 3) res += cal(c);
    return res;
  }

  private int cal(int c) {
    int n = c - 3 + 1;
    return n * (n + 1) / 2;
  }

  public static void main(String[] args) {
    ArithmeticSlices_413 a = new ArithmeticSlices_413();
    System.out.println(a.numberOfArithmeticSlices(new int[]{1, 2}));
    System.out.println(a.numberOfArithmeticSlices(new int[]{1, 2, 3}));
    System.out.println(a.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    System.out.println(a.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
    System.out.println(a.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 6, 8, 10}));
  }
}
