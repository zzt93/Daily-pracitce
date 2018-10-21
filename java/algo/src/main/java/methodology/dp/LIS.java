package methodology.dp;


import java.util.Scanner;


public class LIS {

  public static void main(String[] args) throws Exception {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int[] a = new int[n];
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
      }
      solve(a);
    }
  }

  private static void solve(int[] a) {
    if (a.length == 0) return;
    int max = 1;
    int[] end = new int[a.length];
    end[0] = 1;
    for (int i = 1; i < a.length; i++) {
      for (int x = 0; x < i; x++) {
        if (a[i] > a[x]) end[i] = Math.max(end[x]+1, end[i]);
      }
      end[i] = Math.max(1, end[i]);
      max = Math.max(max, end[i]);
    }
    System.out.println(max);
  }


}
