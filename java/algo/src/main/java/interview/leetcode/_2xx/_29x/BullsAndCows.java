package interview.leetcode._2xx._29x;

import java.util.Arrays;

/**
 * @author zzt
 */
public class BullsAndCows {

  public String getHint(String secret, String guess) {
    char[] gs = guess.toCharArray();
    char[] ss = secret.toCharArray();
    int bull = 0, cows = 0;
    for (int i = 0; i < ss.length; i++) {
      if (gs[i] == ss[i]) {
        bull++;
      }
    }
    Arrays.sort(gs);
    Arrays.sort(ss);
    for (int i = 0, j = 0; i < ss.length && j < ss.length; ) {
      if (gs[i] == ss[j]) {
        cows++; i++; j++;
      } else if (gs[i]<ss[j]) {
        i++;
      } else {
        j++;
      }
    }
    cows-=bull;
    return bull+"A"+ cows +"B";
  }

  public static void main(String[] args) {
    BullsAndCows b = new BullsAndCows();
    System.out.println(b.getHint("0122", "2145"));
    System.out.println(b.getHint("0123", "1145"));
    System.out.println(b.getHint("1870", "7810"));
    System.out.println(b.getHint("0123", "1145"));
    System.out.println(b.getHint("", ""));
    System.out.println(b.getHint("1123", "0111"));
    System.out.println(b.getHint("01322", "21245"));
  }

}
