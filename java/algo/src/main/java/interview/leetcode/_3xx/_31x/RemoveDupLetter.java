package interview.leetcode._3xx._31x;

/**
 * @author zzt
 */
public class RemoveDupLetter {

  public static void main(String[] args) {
    RemoveDupLetter r = new RemoveDupLetter();
    System.out.println("bca:" + r.removeDuplicateLetters("bccaab"));
    System.out.println("bca:" + r.removeDuplicateLetters("bcbcaab"));
    System.out.println("bacd:" + r.removeDuplicateLetters("bcacdca"));
    System.out.println("bca:" + r.removeDuplicateLetters("bcab"));
    System.out.println(r.removeDuplicateLetters(""));
    System.out.println("acb:" + r.removeDuplicateLetters("bacb"));
    System.out.println("acdb:" + r.removeDuplicateLetters("cbacdcbc"));
    System.out.println("abc:" + r.removeDuplicateLetters("bcabc"));
  }


  public String removeDuplicateLetters(String s) {
    char[] cs = s.toCharArray();
    int[] m = new int[26];
    for (char c : cs) {
      m[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    recur(sb, s, m);
    return sb.toString();
  }

  public void recur(StringBuilder sb, String s, int[] m) {
    if (s.isEmpty()) {
      return;
    }
    int i = 0;
    char[] cs = s.toCharArray();
    if (m[cs[i] - 'a'] == 1) {
      sb.append(cs[i]);
      recur(sb, s.substring(1), m);
    } else {
      int minI = i;
      char minC = cs[i];
      int x;
      for (x = i; x < cs.length && m[cs[x] - 'a'] > 1; x++) {
        m[cs[x] - 'a']--;
        if (minC > cs[x]) {
          minC = cs[x];
          minI = x;
        }
      }
      if (cs[x] < minC) {
        minC = cs[x];
        minI = x;
      }
      sb.append(cs[minI]);
      for (int y = minI + 1; y < x; y++) {
        m[cs[y] - 'a']++;
      }
      m[minC - 'a'] = 0;
      recur(sb, s.substring(minI + 1).replace("" + minC, ""), m);
    }
  }


}
