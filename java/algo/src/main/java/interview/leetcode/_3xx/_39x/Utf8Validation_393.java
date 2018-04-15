package interview.leetcode._3xx._39x;

/**
 * @author zzt
 */
public class Utf8Validation_393 {

  public boolean validUtf8(int[] data) {
    int state = 0;
    for (int i = 0; i < data.length; i++) {

    }
    for (int aData : data) {
      switch (state) {
        case 0:
        case -1:
          state = bits(aData);
          if (state == 1 || state > 4) {
            return false;
          }
          state--;
          break;
        default:
          state--;
          if (bits(aData) != 1) {
            return false;
          }
      }
    }
    return state == 0 || state == -1;
  }

  private int bits(int i) {
    int m = 0x80;
    for (int x = 0; x < 8; x++, m >>= 1) {
      if ((m & i) == 0) return x;
    }
    return 8;
  }

  public static void main(String[] args) {
    Utf8Validation_393 u = new Utf8Validation_393();
    System.out.println(u.validUtf8(new int[]{237}));
    System.out.println(u.validUtf8(new int[]{197,130,1}));
    System.out.println(u.validUtf8(new int[]{235, 140, 4}));
  }

}
