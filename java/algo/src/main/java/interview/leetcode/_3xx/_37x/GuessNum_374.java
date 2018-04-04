package interview.leetcode._3xx._37x;

/**
 * @author zzt
 */
public class GuessNum_374 {
  public int guessNumber(int n) {
    long s = 1, e = n+1L, r;
    while ((r = guess((int) ((s+e)/2))) != 0) {
      if (r == -1) e = (s+e)/2;
      else {
        s = (s+e)/2 + 1;
      }
    }
    return (int) ((s+e)/2);
  }

  private int guess(int i) {
    return Integer.compare(2147483647, i);
  }


  public static void main(String[] args) {
    GuessNum_374 g = new GuessNum_374();
    System.out.println(g.guessNumber(2147483647));
  }

}
