package competition.practice.roundE2018;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Yoghurt {

  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/yog3.in");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    long res;
    for (int i = 0; i < trail; i++) {
      int n = in.nextInt(), k = in.nextInt();
      int[] a = new int[n];
      for (int x = 0; x < n; x++) {
        a[x] = in.nextInt();
      }
      res = solve(a, k);
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static long solve(int[] a, int k) {
    int res = 0;
    Arrays.sort(a);
    int day = 0;
    for (int i = 0; i < a.length; ) {
      if (a[i] > day) {
        res += Math.min(k, a.length-i);
        i += k;
        day++;
      } else {
        while (i < a.length && a[i] <= day) i++;
      }
    }
    return res;
  }

}
