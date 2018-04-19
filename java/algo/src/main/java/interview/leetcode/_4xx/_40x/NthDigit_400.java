package interview.leetcode._4xx._40x;

/**
 * @author zzt
 */
public class NthDigit_400 {

  public int findNthDigit(int n) {
    int i = 1, start = 1;
    long c = 9;
    while (n > c * i) {
      n -= c * i;
      i++;
      start *= 10;
      c *= 10;
    }

    n--;
    start = start + n/i;
    return (start+"").charAt(n%i)-'0';
  }


  public static void main(String[] args) {
    NthDigit_400 n = new NthDigit_400();
    System.out.println(n.findNthDigit(189));
    System.out.println(n.findNthDigit(190));
    System.out.println(n.findNthDigit(191));
    System.out.println(n.findNthDigit(192));
    System.out.println(n.findNthDigit(193));
    System.out.println(n.findNthDigit(1));
    System.out.println(n.findNthDigit(11));
    System.out.println(n.findNthDigit(9));
    System.out.println(n.findNthDigit(10));
    System.out.println(n.findNthDigit(1));
    System.out.println(n.findNthDigit(3));
    System.out.println(n.findNthDigit(335426543));
  }

}
