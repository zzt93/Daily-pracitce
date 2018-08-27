package competition.practice.roundE2018;

import competition.utility.MyIn;
import competition.utility.MyOut;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.*;

public class Milk {


  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/milk3.in");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    long res;
    for (int i = 0; i < trail; i++) {
      int n = in.nextInt(), m = in.nextInt(), p = in.nextInt();
      in.nextLine();
      String[] fr = new String[n]; HashSet<String> f = new HashSet<>(m);
      for (int x = 0; x < n; x++) {
        fr[x] = in.nextLine();
      }
      for (int x = 0; x < m; x++) {
        f.add(in.nextLine());
      }
      res = solve(fr, f, p);
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static long solve(String[] fr, HashSet<String> f, int p) {
    int n = fr.length;
    Comparator<int[]> c = Comparator.comparingInt(is -> is[0]);
    Comparator<int[]> cc = c.thenComparingInt(is -> is[1]);
    int[][] q = new int[p][2];
    StringBuilder sb = new StringBuilder(p);
    long res = 0;
    for (int i = 0; i < p; i++) {
      int zc = 0;
      for (String s : fr) {
        if (s.charAt(i) == '0') {
          zc++;
        }
      }
      if (n - zc > zc) {
        sb.append('1');
        res += zc;
        q[i] = new int[]{n - zc - zc, i};
      } else {
        sb.append('0');
        res += (n - zc);
        q[i] = new int[]{zc - (n - zc), i};
      }
    }
    Arrays.sort(q, cc);

    PriorityQueue<Object[]> qq = getCombinationAscSum(p, q);

    int tmp = 0;
    StringBuilder ss = new StringBuilder(sb);
    while (f.contains(ss.toString())) {
      ss = new StringBuilder(sb);
      Object[] poll = qq.poll();
      tmp = (int) poll[0];
      for (Integer i : ((List<Integer>) poll[1])) {
        char c1 = ss.charAt(i);
        ss.setCharAt(i, c1 == '0' ? '1' : '0');
      }
    }
    return res + tmp;
  }

  private static PriorityQueue<Object[]> getCombinationAscSum(int p, int[][] q) {
    PriorityQueue<Object[]> qq = new PriorityQueue<>(Comparator.comparingInt(os-> (int) os[0]));

    for (int i = 1; i <= p; i++) {
      for (List<Integer> ints : comb(p, i)) {
        List<Integer> tmp = new LinkedList<>();
        for (Integer anInt : ints) {
          tmp.add(q[anInt][1]);
        }
        qq.add(new Object[]{sum(ints, q), tmp});
      }
    }
    return qq;
  }

  private static int sum(List<Integer> ints, int[][] q) {
    int s = 0;
    for (Integer integer : ints) {
      s += q[integer][0];
    }
    return s;
  }

  private static ArrayList<ArrayList<Integer>> comb(int n, int k) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    if (n <= 0 || n < k)
      return result;

    LinkedList<Integer> item = new LinkedList<>();
    dfs(n, k, 0, item, result); // because it need to begin from 1

    return result;
  }

  private static void dfs(int n, int k, int start, LinkedList<Integer> item,
                   ArrayList<ArrayList<Integer>> res) {
    if (item.size() == k) {
      res.add(new ArrayList<>(item));
      return;
    }

    for (int i = start; i < n; i++) {
      item.add(i);
      dfs(n, k, i + 1, item, res);
      item.removeLast();
    }
  }

}
