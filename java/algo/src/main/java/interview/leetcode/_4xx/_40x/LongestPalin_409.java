package interview.leetcode._4xx._40x;

import java.util.HashMap;

/**
 * @author zzt
 */
public class LongestPalin_409 {

  public int longestPalindrome(String s) {
    HashMap<Character, Integer> m = new HashMap<>();
    for (char c: s.toCharArray()) {
      if (m.containsKey(c)) m.put(c, m.get(c)+1);
      else m.put(c, 1);
    }
    int all = 0, odd = 0;
    for (char c: m.keySet()) {
      int v = m.get(c);
      if (v % 2 == 0) {
        all += v;
      } else {
        all += v-1;
        odd = 1;
      }
    }
    return all + odd;
  }

  public static void main(String[] args) {
    LongestPalin_409 l = new LongestPalin_409();
    System.out.println(l.longestPalindrome("abc"));
    System.out.println(l.longestPalindrome("abcabcddef"));
  }

}
