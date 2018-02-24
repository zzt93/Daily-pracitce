package interview.leetcode._3xx._31x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzt
 *
 * <h3>Longest Path of un-rooted tree</h3>
 * <li>dfs/bfs twice: longest distance</li>
 * <li>tree dp: dp[u] = max(height[u], acc--longest path with one endpoint is `u`)</li>
 */
public class MinHeightTree {

  public static void main(String[] args) {
    MinHeightTree m = new MinHeightTree();
    System.out.println(m.findMinHeightTrees(6,
        new int[][]{new int[]{0, 3}, new int[]{1, 3}, new int[]{2, 3}, new int[]{4, 3},
            new int[]{5, 4}}));
    System.out.println(m.findMinHeightTrees(4,
        new int[][]{new int[]{1, 0}, new int[]{1, 2}, new int[]{1, 3}}));
  }

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) {
      return Collections.singletonList(0);
    }
    Set<Integer>[] graph = new Set[n];
    HashSet<Integer> res = new HashSet<>();
    for (int i = 0; i < n; i++) {
      graph[i] = new HashSet<>();
      res.add(i);
    }
    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      graph[edge[1]].add(edge[0]);
    }
    List<Integer> leaf = new ArrayList<>();
    for (int i = 0; i < graph.length; i++) {
      if (graph[i].size() == 1) {
        leaf.add(i);
      }
    }
    while (res.size() > 2) {
      List<Integer> newLeaf = new ArrayList<>();
      for (Integer i : leaf) {
        Set<Integer> nei = graph[i];
        assert nei.size() == 1;
        int j = nei.iterator().next();
        graph[j].remove(i);
        if (graph[j].size() == 1) newLeaf.add(j);
        res.remove(i);
      }
      leaf = newLeaf;
    }
    return new ArrayList<>(res);
  }


  public List<Integer> findMinHeightTreesTLE(int n, int[][] edges) {
    if (n == 1) {
      return Collections.singletonList(0);
    }
    int min = n + 1;
    List<Integer> list = new ArrayList<>();
    List<Integer>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      graph[edge[1]].add(edge[0]);
    }
    for (int i = 0; i < n; i++) {
      int h = height(i, graph, new HashSet<>(), min);
      if (h < min) {
        min = h;
        list.clear();
        list.add(i);
      } else if (h == min) {
        list.add(i);
      }
    }
    return list;
  }

  private int height(int i, List<Integer>[] graph, HashSet<Integer> visited, int min) {
    visited.add(i);
    int max = 0;
    for (Integer nei : graph[i]) {
      if (!visited.contains(nei)) {
        max = Math.max(max, height(nei, graph, visited, min));
        if (max > min) {
          return max;
        }
      }
    }
    return max + 1;
  }
}
