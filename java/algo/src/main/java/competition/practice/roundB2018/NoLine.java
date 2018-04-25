package competition.practice.roundB2018;

import competition.utility.MyIn;
import competition.utility.MyOut;
import java.io.FileNotFoundException;

/**
 * @author zzt
 */
public class NoLine {

  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/noline.in");//TODO add file name
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    long res;
    for (int i = 0; i < trail; i++) {
      res = noLine(in.nextLong(), in.nextLong());
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static long noLine(long f, long l) {
    long times = (l + 8) / 9 - (f + 8) / 9;
    long contain = contain(f, l);
    return l - f + 1 - times - contain;
  }

  private static long contain(long f, long l) {
    String fs = "" + f, ls = "" + l;
    int min = fs.length();
    int max = ls.length();
    long res = 0;
    for (int nine = 1; nine < max; nine++) {
      if (min == max) {
        res = count(fs, ls, nine);
      } else {
        if (min > nine) {
          res = count(fs, max(nine, min), nine);
        }
        for (int digits = Math.max(min, nine) + 1; digits < max; digits++) {
          res += (8 * Math.pow(10, digits - nine - 1) + Math.pow(10, digits - nine) - 1);
        }
        res += count(min(nine, max), ls, nine);
      }
    }
    return res;
  }

  private static long count(String fs, String ls, int nine) {
    if (fs.compareTo(ls) > 0) return 0;
    return recur(fs.toCharArray(), ls.toCharArray(), 0, nine, 0);
  }

  private static long recur(char[] lower, char[] upper, int s, int nine, int state) {
    if (lower.length - s < nine) {
      return 0;
    }
    if (s == lower.length && nine == 0) {
      return 1;
    }
    switch (state) {
      case 0:
        if (upper[s] > lower[s]) {
          return (upper[s] - lower[s] - 1) * recur(lower, upper, s + 1, nine, 1)
              + recur(lower, upper, s + 1, nine, 2)
              + recur(lower, upper, s + 1, upper[s] == '9' ? nine - 1 : nine, 3)
              ;
        } else {
          return 2 * recur(lower, upper, s + 1, upper[s] == '9' ? nine - 1 : nine, 0);
        }
      case 1:
        return (long) Math.pow(10, lower.length - s - nine);
      case 2:
        if ('9' > lower[s]) {
          return
              recur(lower, upper, s + 1, nine, 2)
                  + recur(lower, upper, s + 1, nine, 1) * ('9' - lower[s] - 1)
                  + recur(lower, upper, s + 1, nine - 1, 1);
        } else {
          return recur(lower, upper, s + 1, nine-1, 2);
        }
      case 3:
        return recur(lower, upper, s + 1, nine, 1) * (upper[s] - '0')
            + recur(lower, upper, s + 1, upper[s] == '9' ? nine - 1 : nine, 3);
    }
    throw new IllegalStateException();
  }

  private static String max(int nine, int all) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nine; i++) {
      sb.append("9");
    }
    for (int i = 0; i < all - nine; i++) {
      sb.append("8");
    }
    return sb.toString();
  }

  private static String min(int nine, int all) {
    StringBuilder sb = new StringBuilder().append("1");
    for (int i = 1; i < all - nine; i++) {
      sb.append("0");
    }
    for (int i = 0; i < nine; i++) {
      sb.append("9");
    }
    return sb.toString();
  }


}
