package interview.google.foobar;

/**
 * @author zzt
 */
public class HandWrite {


  public static String toHex(int r, int g, int b) {
    return String.format("%02x%02x%02x", r, g, b);
  }

  public static String hex(int i) {
    if (i > 255 || i < 0) {
      throw new IllegalArgumentException();
    }
    int f = i >> 4, s = i & 0x0f;
    String res = "";
    if (f >= 0 && f <= 9) {
      res += f;
    } else {
      res += (char) ('A' + (f - 10));
    }
    if (s >= 0 && s <= 9) {
      res += s;
    } else {
      res += (char) ('A' + (s - 10));
    }
    return res;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 256; i++) {
      System.out.println(hex(i));
    }
    System.out.println(toHex(255, 0, 255));
  }

}
