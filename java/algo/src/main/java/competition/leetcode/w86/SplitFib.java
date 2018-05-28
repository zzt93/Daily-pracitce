package competition.leetcode.w86;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class SplitFib {

  public List<Integer> splitIntoFibonacci(String str) {
    LinkedList<Integer> res = new LinkedList<>();
    char[] cs = str.toCharArray();
    for (int fe = 1; fe < cs.length; fe++) {
      if (cs[0] == '0' && fe > 1) break;
      int f = 0;
      try {
        f = Integer.parseInt(new String(cs, 0, fe));
      } catch (NumberFormatException e) {
        break;
      }
      res.clear(); res.addLast(f);
      for (int se = fe + 1; se < cs.length; se++) {
        if (cs[fe] == '0' && se > fe + 1) break;
        int s;
        try {
          s = Integer.parseInt(new String(cs, fe, se-fe));
        } catch (NumberFormatException e) {
          break;
        }
        res.addLast(s);
        int aim = f + s; String as = "" + aim;
        int ft;
        int ts = se;
        while (ts + as.length() <= cs.length && as.equals(new String(cs, ts, as.length()))) {
          ts = ts + as.length();
          res.addLast(aim);

          ft = s;
          s = aim;
          aim = ft + s;
          as = "" + aim;
        }
        if (ts == cs.length) return res;
        res.clear();
        res.addLast(f);
      }
    }
    return Collections.emptyList();
  }

  public static void main(String[] args) {
    SplitFib s = new SplitFib();
    System.out.println(s.splitIntoFibonacci("214748364721474836422147483641"));
    System.out.println(s.splitIntoFibonacci("1101111"));
    System.out.println(s.splitIntoFibonacci("11235813"));
    System.out.println(s.splitIntoFibonacci("112358130"));
    System.out.println(s.splitIntoFibonacci(""));
    System.out.println(s.splitIntoFibonacci("0123"));
    System.out.println(s.splitIntoFibonacci("123456579"));
  }

}
