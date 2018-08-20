package methodology.dp;


import java.util.Scanner;


public class MatrixChain {

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

  }


}
