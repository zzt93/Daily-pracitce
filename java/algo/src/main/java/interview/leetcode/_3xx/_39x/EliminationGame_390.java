package interview.leetcode._3xx._39x;

/**
 * @author zzt
 */
public class EliminationGame_390 {

  public int lastRemaining(int n) {
    int i = 1, gap = 1;
    boolean l = true;
    while (n > 1) {
      if (n % 2 == 1 || l) i += gap;
      n /= 2;

      l = !l;
      gap *= 2;
    }
    return i;
  }
}
