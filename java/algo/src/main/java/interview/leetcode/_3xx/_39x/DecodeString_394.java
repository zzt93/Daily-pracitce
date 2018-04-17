package interview.leetcode._3xx._39x;

/**
 * @author zzt
 */
public class DecodeString_394 {
  public String decodeString(String str) {
    char[] cs = str.toCharArray();
    int s, f = 0;
    StringBuilder sb = new StringBuilder();
    do {
      for (s = f; s < cs.length; s++) {
        if (cs[s] == '[') break;
        else if (!Character.isDigit(cs[s])) {
          sb.append(cs[s]);
          f++;
        }
      }
      int lc = 1, t;
      for (t = s+1; t < cs.length; t++) {
        if (cs[t] == ']') lc--;
        else if (cs[t] == '[') lc++;
        if (lc == 0) break;
      }
      for (int x = 0; f < s && x < Integer.parseInt(str.substring(f, s)); x++) {
        sb.append(decodeString(str.substring(s+1, t)));
      }
      f = t+1;
    } while (s != cs.length);
    return sb.toString();
  }

  public static void main(String[] args) {
    DecodeString_394 d = new DecodeString_394();
    System.out.println(d.decodeString("2[abc]3[cd]ef"));
    System.out.println(d.decodeString("3[a2[c]]"));
    System.out.println(d.decodeString("3[a]2[bc]"));
  }

}
