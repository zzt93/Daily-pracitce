package competition.leetcode.w73;

/**
 * @author zzt
 */
public class RotateDigit {

  public static void main(String[] args) {
    RotateDigit a = new RotateDigit();
    System.out.println(a.rotatedDigits(10));
    System.out.println(a.rotatedDigits(100));
  }

  public int rotatedDigits(int N) {
    int res = 0;
    Out:
    for (int j = 1; j <= N; j++) {
      boolean diff = false;
      int i = j;
      while (i > 0) {
        int r = i % 10;
        i /= 10;
        if (r == 3 || r == 4 || r == 7) {
          continue Out;
        } else if (r == 0 || r == 1 || r == 8) {
          continue;
        } else {
          diff = true;
        }
      }
      if (diff) {
        res++;
      }
    }
    return res;
  }

}
