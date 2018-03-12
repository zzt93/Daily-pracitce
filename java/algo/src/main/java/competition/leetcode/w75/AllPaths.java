package competition.leetcode.w75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class AllPaths {

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(0, graph.length-1, graph, new LinkedList<>(), res);
    return res;
  }

  private void dfs(int s, int e, int[][] graph,
      LinkedList<Integer> path, List<List<Integer>> res) {
    path.add(s);
    if (s == e) {
      res.add(new ArrayList<>(path));
    } else {
      for (int i : graph[s]) {
        dfs(i, e, graph, path, res);
      }
    }
    path.removeLast();
  }

  public static void main(String[] args) {
    AllPaths a = new AllPaths();
    System.out.println(a.allPathsSourceTarget(
        new int[][]{new int[]{1, 2}, new int[]{3}, new int[]{3}, new int[]{}}));
    System.out.println(a.allPathsSourceTarget(
        new int[][]{new int[]{1, 2}, new int[]{}, new int[]{}, new int[]{}}));
  }
}
