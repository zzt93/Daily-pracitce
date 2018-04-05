package interview.leetcode._3xx._34x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzt
 */
public class ReverseVowels_345 {

  public String reverseVowels(String s) {
    List<Integer> list = new ArrayList<>(s.length());
    char[] cs = s.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      char c = Character.toLowerCase(cs[i]);
      if (c == 'a' || c == 'e' || c == 'i'
          || c == 'o' || c == 'u') {
        list.add(i);
      }
    }
    for (int i = 0; i < list.size() / 2; i++) {
      int o = list.size() - 1 - i;
      char t = cs[list.get(i)];
      cs[list.get(i)] = cs[list.get(o)];
      cs[list.get(o)] = t;
    }
    return new String(cs);
  }

}
