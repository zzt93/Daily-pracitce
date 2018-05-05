package interview.leetcode._4xx._40x;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * min in Range: BST; queue; -1,1
 * @author zzt
 */
public class RemoveKDigits_402 {

  public static void main(String[] args) {
    RemoveKDigits_402 r = new RemoveKDigits_402();
    System.out.println(r.removeKdigits("1107", 1));
    System.out.println(r.removeKdigits("1000", 1));
    System.out.println(r.removeKdigits("10001", 1));
    System.out.println(r.removeKdigits("10200", 1));
    System.out.println(r.removeKdigits("111222", 3));
    System.out.println(r.removeKdigits("222111", 3));
    System.out.println(r.removeKdigits("222111", 4));
    System.out.println(r.removeKdigits("111222", 4));
    System.out.println(r.removeKdigits("1432219", 3));
    System.out.println(r.removeKdigits("1432219", 0));
    System.out.println(r.removeKdigits("10", 2));
  }

  public String removeKdigits(String num, int k) {
    if (k == num.length()) {
      return "0";
    }
    Comparator<int[]> c1 = Comparator.comparingInt(is -> is[0]);
    TreeSet<int[]> set = new TreeSet<>(c1.thenComparing(is -> is[1]));
    int range = k + 1, s = 0, size = 0;
    char[] cs = num.toCharArray();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < cs.length && size < cs.length - k && range > 1; i++) {
      set.add(new int[]{cs[i], i});
      if (set.size() == range) {
        int[] min = set.first();
        size++;
        if (min[0] != '0' || res.length() != 0) {
          res.append((char) min[0]);
        }
        range = range - (min[1] - s);
        for (int x = s; x <= min[1]; x++) {
          set.remove(new int[]{cs[x], x});
        }
        s = min[1] + 1;
      }
    }
    if (size < cs.length - k) {
      for (int i = s; i < cs.length; i++) {
        if (res.length() == 0 && cs[i] == '0') {
          continue;
        }
        res.append(cs[i]);
      }
    }
    return res.length() == 0 ? "0" : res.toString();
  }

}
