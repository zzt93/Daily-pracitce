package interview.leetcode._2xx._29x;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zzt
 */
public class WordPattern {

  public boolean wordPattern(String pattern, String str) {
    String[] split = str.split(" ");
    char[] cs = pattern.toCharArray();
    if (split.length != cs.length) {
      return false;
    }
    HashMap<Object, Integer> w2i = new HashMap<>();
    for (int i = 0; i < split.length; i++) {
      String s = split[i];
      if (!Objects.equals(w2i.put(s, i), w2i.put(cs[i], i))) {
        return false;
      }
    }
    return true;
  }

}
