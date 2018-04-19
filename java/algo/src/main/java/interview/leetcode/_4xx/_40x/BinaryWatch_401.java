package interview.leetcode._4xx._40x;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class BinaryWatch_401 {

  private int[][] one = {
      {1, 0},{2, 0},{4, 0},{8, 0},
      {0, 1},{0, 2},{0,4},{0,8},{0,16},{0,32}
  };
  public List<String> readBinaryWatch(int num) {
    List<String> res = new LinkedList<>();
    if (num == 0) {
      res.add("0:00");
      return res;
    }
    LinkedList<int[]> tmp = new LinkedList<>();
    cnm(num, 0, res, tmp);
    return res;
  }

  private void cnm(int n, int s, List<String> res, LinkedList<int[]> q) {
    if (n == 0) {
      String t = toStr(q);
      if (t != null) res.add(t);
      return;
    }
    if (s >= one.length) return;
    cnm(n, s+1, res, q);
    q.addLast(one[s]);
    cnm(n-1, s+1, res, q);
    q.removeLast();
  }

  private String toStr(LinkedList<int[]> q) {
    if (q.isEmpty()) return null;
    int h = 0, m = 0;
    for (int[] next : q) {
      h += next[0];
      m += next[1];
    }
    if (h > 11 || m > 59) return null;
    return String.format("%d:%02d", h, m);
  }

//  public List<String> readBinaryWatch(int num) {
//    List<String> times = new ArrayList<>();
//    for (int h=0; h<12; h++)
//      for (int m=0; m<60; m++)
//        if (Integer.bitCount(h * 64 + m) == num)
//          times.add(String.format("%d:%02d", h, m));
//    return times;
//  }

  public static void main(String[] args) {
    BinaryWatch_401 b = new BinaryWatch_401();
    System.out.println(b.readBinaryWatch(0));
    System.out.println(b.readBinaryWatch(1));
    System.out.println(b.readBinaryWatch(3));
  }
}
