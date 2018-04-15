package interview.leetcode._3xx._39x;

/**
 * @author zzt
 */
public class isSubsequence_392 {

  public boolean isSubsequence(String s, String t) {
    char[] tc = t.toCharArray();
    char[] sc = s.toCharArray();
    int j = 0;
    for (int i = 0; i < tc.length && j < sc.length; i++) {
      if (tc[i] == sc[j]) j++;
    }
    return j == sc.length;
  }

}
