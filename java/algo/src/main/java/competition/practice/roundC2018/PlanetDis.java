package competition.practice.roundC2018;

import competition.utility.MyIn;
import competition.utility.MyOut;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zzt
 */
public class PlanetDis {

  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/PlanetDis.bin");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    String res;
    for (int i = 0; i < trail; i++) {
      int p = in.nextInt();
      LinkedList<Integer>[] graph = new LinkedList[p];
      for (int x = 0; x < p; x++) {
        graph[x] = new LinkedList<>();
      }
      for (int x = 0; x < p; x++) {
        int f = in.nextInt()-1, s= in.nextInt()-1;
        graph[f].addLast(s);
        graph[s].addLast(f);
      }
      res = dis(graph);
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static String dis(LinkedList<Integer>[] graph) {
    int[] res = new int[graph.length];
    Arrays.fill(res, Integer.MAX_VALUE);
    LinkedList<Integer> cycle = new LinkedList<>();
    dfs(graph, 0, new HashSet<>(), cycle, -1);
    int dis = 0;
    HashSet<Integer> set = new HashSet<>();
    while (!cycle.isEmpty()) {
      set.addAll(cycle);
      LinkedList<Integer> next = new LinkedList<>();
      while (!cycle.isEmpty()) {
        Integer i = cycle.pollFirst();
        res[i] = dis;
        for (Integer n : graph[i]) {
          if (!set.contains(n)) next.addLast(n);
        }
      }
      cycle = next;
      dis++;
    }
    return Arrays.stream(res).boxed().map(Object::toString).collect(Collectors.joining(" "));
  }

  private static Integer dfs(LinkedList<Integer>[] graph, int now,
      HashSet<Integer> visited, LinkedList<Integer> cycle, Integer last) {
    if (visited.contains(now)) {
      return now;
    }
    visited.add(now);
    Integer res = null;
    for (Integer next : graph[now]) {
      if (Objects.equals(next, last)) continue;
      Integer dfs = dfs(graph, next, visited, cycle, now);
      if (dfs != null) {
        res = dfs;
        break;
      }
    }
    visited.remove(now);
    if (res != null) {
      cycle.addLast(now);
      if (res.equals(now)) return null;
    }
    return res;
  }

}
