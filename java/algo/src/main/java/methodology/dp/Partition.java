package methodology.dp;


import java.util.*;

public class Partition {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[] a = new int[n];
      int sum = 0;
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
        sum += a[j];
      }
      if ((sum & 1) == 1) System.out.println("NO");
      else {
        Arrays.sort(a);
        System.out.println(solve(a, a.length-1,sum / 2, sum / 2) ? "YES" : "NO");
      }
    }
  }

  private static boolean solve(int[] a, int i, int left, int right) {
    if (left < 0 || i < 0) return false;
    if (left == 0) return true;

    if (solve(a, i-1, left - a[i], right)) return true;
    if (solve(a, i-1, left, right - a[i])) return true;

    return false;
  }

}
