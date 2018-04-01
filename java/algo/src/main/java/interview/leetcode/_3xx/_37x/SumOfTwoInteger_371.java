package interview.leetcode._3xx._37x;

/**
 * @author zzt
 */
public class SumOfTwoInteger_371 {

  public int getSum(int a, int b) {
    int c = 0;
    int mask = 1;
    int res = 0;
    for (int i = 0; i < 32; i++) {
      int x = a & mask, y = b & mask;
      if (x == y) {
        if (x != 0 && c == 1) {
          c = 1; res |= mask;
        } else if (x == 0 && c == 1) {
          c = 0; res |= mask;
        } else if (x != 0 && c == 0) {
          c = 1;
        } else {
          c = 0;
        }
      } else {
        if (x != 0 && c == 1) {
          c = 1;
        } else if (x == 0 && c == 1) {
          c = 1;
        } else if (x != 0 && c == 0) {
          c = 0; res |= mask;
        } else {
          c = 0; res |= mask;
        }
      }
      mask = mask << 1;
    }
    return res;
  }

  public static void main(String[] args) {
    SumOfTwoInteger_371 s = new SumOfTwoInteger_371();
    System.out.println(s.getSum(3, 1));
    System.out.println(s.getSum(0, 0));
    System.out.println(s.getSum(0, 1));
    System.out.println(s.getSum(2, 1));
    System.out.println(s.getSum(Integer.MAX_VALUE, 2));
    System.out.println(Integer.MAX_VALUE + 2);
  }

}
