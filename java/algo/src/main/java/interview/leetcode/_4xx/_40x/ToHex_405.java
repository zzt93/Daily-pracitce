package interview.leetcode._4xx._40x;

/**
 * @author zzt
 */
public class ToHex_405 {

  public String toHex(int num) {
    StringBuilder sb = new StringBuilder();
    do {
      int i = num & 0xf;
      if (i < 10) {
        sb.insert(0,i);
      } else {
        sb.insert(0,(char)('a' + (i-10)));
      }
      num >>>= 4;
    } while (num != 0);
    return sb.toString();
  }

  public static void main(String[] args) {
    ToHex_405 t = new ToHex_405();
    System.out.println(t.toHex(0));
    System.out.println(t.toHex(Integer.MIN_VALUE));
    System.out.println(t.toHex(Integer.MAX_VALUE));
    System.out.println(t.toHex(-1));
    System.out.println(t.toHex(26));
    System.out.println(t.toHex(126));
  }

}
