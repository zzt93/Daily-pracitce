package competition.leetcode.w73;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzt
 */
public class CustomSort {

  public String customSortString(String S, String T) {
    char[] chars = S.toCharArray();
    int[] map = new int[26];
    for (int i = 0; i < chars.length; i++) {
      map[chars[i]-'a'] = i;
    }
    Character[] cs = new Character[T.length()];
    for (int i = 0; i < T.toCharArray().length; i++) {
      cs[i] = T.toCharArray()[i];
    }
    Arrays.sort(cs, Comparator.comparingInt(c -> map[c - 'a']));
    char[] res = new char[T.length()];
    for (int i = 0; i < cs.length; i++) {
      res[i] = cs[i];
    }
    return new String(res);
  }

  public static void main(String[] args) {
    CustomSort c = new CustomSort();
    System.out.println(c.customSortString("cba", "abcd"));
  }

}
