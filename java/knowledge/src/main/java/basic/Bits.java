package basic;

/**
 * Created by zzt on 3/15/18.
 *
 * <h3></h3>
 */
public class Bits {

  public static int countBits(int i) {
    if (i == 0) {
      return 0;
    }
    int c = 1;
    while ((i & (i - 1)) != 0) {
      i = i & (i - 1);
      c++;
    }
    return c;
  }

  private static int bit(int n, int i) {
    if (i >= 32 || i < 0) {
      throw new IllegalArgumentException();
    }
    return (n >>> i) & 1;
  }

  public static boolean isPanlin(int n) {
    for (int i = 0; i < 16; i++) {
      if (bit(n, i) != bit(n, 31 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
//    testCount();
    System.out.println(isPanlin(-1));
    System.out.println(isPanlin(Integer.MIN_VALUE + 1));
    System.out.println(isPanlin(1));
  }

  private static void testCount() {
    System.out.println(countBits(5));
    System.out.println(countBits(7));
    System.out.println(countBits(0));
    System.out.println(countBits(1));
    System.out.println(countBits(-1));
    System.out.println(countBits(Integer.MAX_VALUE));
    System.out.println(countBits(Integer.MIN_VALUE));
  }
}
