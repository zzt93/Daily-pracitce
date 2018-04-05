package competition.leetcode.w77;

import java.util.Arrays;

/**
 * @author zzt
 */
public class NumOfLines {

  public int[] numberOfLines(int[] w, String S) {
    char[] cs = S.toCharArray();
    int r = 0, n = 0;
    for (char c: cs) {
      int t = w[c-'a'];
      if (t + n > 100) {
        n = t;
        r++;
      } else {
        n += t;
      }
    }
    return new int[]{r+1, n};
  }

  public static void main(String[] args) {
    NumOfLines n = new NumOfLines();
    System.out.println(Arrays.toString(n.numberOfLines(
        new int[]{10, 10, 10, 4, 10, 10, 10, 10, 10, 5, 10, 10, 10, 10, 6, 10, 10, 10, 10, 7, 10,
            10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz")));
    System.out.println(Arrays.toString(n.numberOfLines(
        new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
            10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz")));
    System.out.println(Arrays.toString(n.numberOfLines(
        new int[]{4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
        "bbbcccdddaaa"
    )));
  }
}
