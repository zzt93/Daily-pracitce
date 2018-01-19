package interview.leetcode._2xx._28x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzt
 */
public class ExprAddOp {

  public static void main(String[] args) {
    ExprAddOp e = new ExprAddOp();
    System.out.println(e.addOperators("105", 5));
    System.out.println(e.addOperators("105", 6));
    System.out.println(e.addOperators("00", 0));
    System.out.println(e.addOperators("123", 6));
    System.out.println(e.addOperators("232", 8));
    System.out.println(e.addOperators("1232", 18));
    System.out.println(e.addOperators("12321", 28));
    System.out.println(e.addOperators("2321",26));
    System.out.println(e.addOperators("3456237490", 9191));
    System.out.println(e.addOperators("3456237490", 9272));
    System.out.println(e.addOperators("3456237490", 9278));
    System.out.println(e.addOperators("3456237490", 9197));
  }

  public List<String> addOperators(String num, int target) {
    return new ArrayList<>(recur(num.toCharArray(), num.length(), target, null));
  }

  Set<String> recur(char[] cs, int e, long target, Long extra) {
    Set<String> res = new HashSet<>();
    for (int i = e - 1; i >= 0; i--) {
      if (cs[i] == '0' && e - i >= 2) {
        continue;
      }

      String s1 = new String(cs, i, e - i);
      long l = Long.parseLong(s1);
      long n = l;
      if (extra != null) {
        n *= extra;
      }
      if (i == 0) {
        if (target == n) {
          res.add(s1);
        }
        return res;
      }
      for (String sub : recur(cs, i, target - n, null)) {
        res.add(sub + "+" + (extra == null ? n : l));

      }
      for (String sub : recur(cs, i, target + n, null)) {
        res.add(sub + "-" + (extra == null ? n : l));

      }
      for (String sub : recur(cs, i, target, n)) {
        res.add(sub + "*" + (extra == null ? n : l));
      }
    }
    return res;
  }
}
