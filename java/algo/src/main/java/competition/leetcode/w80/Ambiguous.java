package competition.leetcode.w80;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class Ambiguous {

  public static void main(String[] args) {
    Ambiguous a = new Ambiguous();
//    System.out.println(a.ambiguousCoordinates("()"));
    System.out.println(a.ambiguousCoordinates("(0010)"));
    System.out.println(a.ambiguousCoordinates("(00011)"));
    System.out.println(a.ambiguousCoordinates("(100)"));
    System.out.println(a.ambiguousCoordinates("(1234)"));
    System.out.println(a.ambiguousCoordinates("(0123)"));
    System.out.println(a.ambiguousCoordinates("(01234)"));
    System.out.println(a.ambiguousCoordinates("(0001234)"));
  }

  public List<String> ambiguousCoordinates(String s) {
    char[] cs = s.toCharArray();
    List<String> res = new LinkedList<>();
    int l = cs.length;
    for (int i = 2; i < l - 1; i++) {
      List<String> le = digit(cs, 0, i);
      List<String> ri = digit(cs, i, l);
      for (String s1 : le) {
        for (String s2 : ri) {
          res.add(s1 + ", " + s2);
        }
      }
    }
    return res;
  }

  private List<String> digit(char[] cs, int s, int e) {
    List<String> res = new LinkedList<>();
    if (e - s == 2) {
      res.add(new String(cs, s, e - s));
      return res;
    }
    if (s == 0) {
      if (cs[1] == '0') {
        if (cs[e - 1] != '0') {
          res.add(new String(cs, s, 2) + "." + new String(cs, 2, e - 2));
        }
      } else if (cs[e - 1] != '0') {
        res.add(new String(cs, s, e - s));
        for (int i = 2; i < e; i++) {
          String s1 = new String(cs, i, e - i);
          res.add(new String(cs, s, i) + "." + s1);
        }
      }
    } else {
      if (cs[s] == '0') {
        if (cs[e - 2] != '0') {
          res.add(new String(cs, s, 1) + "." + new String(cs, s + 1, e - s - 1));
        }
      } else if (cs[e - 2] != '0') {
        res.add(new String(cs, s, e - s));
        for (int i = s + 1; i < e - 1; i++) {
          String s1 = new String(cs, i, e - i);
          res.add(new String(cs, s, i - s) + "." + s1);
        }
      }
    }
    return res;
  }


}
