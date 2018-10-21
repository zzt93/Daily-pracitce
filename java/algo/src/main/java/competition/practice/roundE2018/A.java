package competition.practice.roundE2018;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.List;

public class A {

  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/para.in");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    long res;
    for (int i = 0; i < trail; i++) {
      int n = in.nextInt(), k = in.nextInt();
      int[][] ar = new int[4][k];
      ar[0] = new int[n];
      ar[1] = new int[n];
      for (int[] is : ar) {
        is[0] = in.nextInt();
        is[1] = in.nextInt();
        long a = in.nextLong(), b = in.nextLong(), c = in.nextLong(), m = in.nextLong();
        for (int y = 2; y < is.length; y++) {
          is[y] = (int) ((a * is[y - 1] + b * is[y - 2] + c) % m + 1);
        }
      }
      res = solve(ar[0], ar[1], ar[2], ar[3]);
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static long solve(int[] p, int[] h, int[] x, int[] y) {
    int n = p.length, k = x.length;
    RangeMap<Integer, List<Integer>> right = TreeRangeMap.create();
    RangeMap<Integer, List<Integer>> left = TreeRangeMap.create();
    for (int i = 0; i < n; i++) {
      Range<Integer> r = Range.closed(p[i], p[i] + h[i]);

      if (right.get(p[i]) == null && right.get(p[i] + h[i]) == null) {
        right.put(r, Lists.newArrayList(i));
      } else {

      }
//      left.put(Range.closed(p[i] - h[i], p[i]), i);
    }
    int res = 0;
//    for (int i = 0; i < k; i++) {
//      Integer r = right.get(x[i] + y[i]);
//      if (r != null)  {
//        res++;
//      } else {
//        Integer l = left.get(x[i] - y[i]);
//      }
//    }
    return res;
  }
}
