package competition.mis;

import java.util.Arrays;
import java.util.Scanner;

public class FlipSequence {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int[] a = new int[n];
    int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = s.nextInt();
      b[i] = a[i];
    }
    Arrays.sort(b);
    System.out.println(flip(a, b, 0));
  }

  private static int flip(int[] a, int[] b, int now) {
    if (now == a.length) {
      return 0;
    }
    int last = a[now];
    if (last == b[now]) {
      return flip(a, b, now + 1);
    } else {
      int flipCount = 1;
      for (int i = now + 1; i < a.length; i++) {
        if (a[i] == b[now]) {
          if (i != a.length-1) {
            flipCount = 2;
            reverse(a, i, a.length-1);
          }
          reverse(a, now, a.length-1);
          break;
        }
      }
      return flip(a, b, now + 1) + flipCount;
    }
  }

  private static void reverse(int[] a, int s, int e) {
    for (int i = s, j = e; i < j; i++, j--) {
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
  }

}
