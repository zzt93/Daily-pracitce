package competition.leetcode.w66;

/**
 * Created by zzt on 1/7/18. <p> <h3></h3>
 */
public class SpecialBinaryString {

  public static void main(String[] args) {
    SpecialBinaryString s = new SpecialBinaryString();
    System.out.println(s.makeLargestSpecial("1101101001110000"));
    System.out.println(s.makeLargestSpecial("10110100"));
    System.out.println(s.makeLargestSpecial("11011000"));
    System.out.println(s.makeLargestSpecial("1011100100101100"));
    System.out.println(s.makeLargestSpecial("1011011000"));
  }

  public String makeLargestSpecial(String s) {
    StringBuilder sb;
    StringBuilder last = new StringBuilder(s);
    int l = s.length();
    while (true) {
      sb = new StringBuilder(last);

      for (int i = 0; i < l; i++) {
        if (sb.charAt(i) == '0') {
          continue;
        }
        int m, oneC = 0, zC = 0;
        for (m = i; m < l; m++) {
          if (sb.charAt(m) == '0') {
            zC++;
          } else {
            oneC++;
          }
          if (oneC == zC) {
            break;
          }
        }
        if (m + 1 >= l || sb.charAt(m + 1) == '0') {
          continue;
        }
        oneC = zC = 0;
        int e;
        for (e = m + 1; e < l; e++) {
          if (sb.charAt(e) == '0') {
            zC++;
          } else {
            oneC++;
          }
          if (oneC == zC) {
            break;
          }
        }
        if (e >= l) {
          continue;
        }
        String f = sb.substring(i, m + 1);
        String st = sb.substring(m + 1, e + 1);
        if (f.compareTo(st) < 0) {
          sb.replace(i, e + 1, st + f);
        }
      }

      if (sb.toString().equals(last.toString())) {
        break;
      }
      last = sb;
    }
    return sb.toString();
  }
}
