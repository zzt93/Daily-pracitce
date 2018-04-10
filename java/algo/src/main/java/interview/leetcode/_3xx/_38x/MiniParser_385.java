package interview.leetcode._3xx._38x;

import interview.leetcode.NestedInteger;

/**
 * @author zzt
 */
public class MiniParser_385 {

  public NestedInteger deserialize(String s) {
    if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
    return parse(s.toCharArray(), 0, s.length());
  }

  // pre: "[xxx]" cs[s] == '[' cs[e-1] == ']'
  private NestedInteger parse(char[] cs, int s, int e) {
    NestedInteger res = new NestedInteger();
    if (e - s == 2) return res;
    StringBuilder sb = new StringBuilder();
    for (int i = s+1; i < e;i++) {
      char c = cs[i];
      if (c == '[') {
        int lc = 1;
        for (int x = i+1; x < e-1; x++) {
          if (cs[x] == '[') lc++;
          else if (cs[x] == ']') lc--;
          if (lc == 0) {
            res.add(parse(cs, i, x+1));
            i = x+1; // skip ','
            break;
          }
        }
      } else {
        if (c != ',' && c != ']') sb.append(c);
        else {
          int x = Integer.parseInt(sb.toString());
          sb.setLength(0);
          res.add(new NestedInteger(x));
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    MiniParser_385 m = new MiniParser_385();
    System.out.println(m.deserialize("[123,[456,[789]]]"));
  }
}
