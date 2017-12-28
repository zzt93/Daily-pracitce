package competition.leetcode.w51;

import java.util.*;

/**
 * Created by zzt on 9/24/17.
 * <p>
 * <h3>Check circle in undirected graph: dfs</h3>
 *
 */
public class RedundantTree {

    public static final int NOT_VISITED = 1;
    public static final int VISITED = -1;
    public static final int VISITING = 0;

    public int[] findRedundantConnection(int[][] edges) {
        HashSet[] graph = new HashSet[2001];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet();
        }
        int start = edges[0][0];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            if (graph[v].contains(u)) {
                return edge;
            }
            graph[v].add(u);
        }
        Comparator<int[]> comparator = Comparator.comparingInt(e -> e[0]);
        TreeSet<int[]> circle = new TreeSet<>(comparator.thenComparingInt(e -> e[1]));
        HashMap<Integer, Integer> visit = new HashMap<>();
        dfs(start, 0, graph, circle, visit);
        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (circle.contains(edge)) {
                return edge;
            } else if (circle.contains(new int[]{edge[1], edge[0]})) {
                return edge;
            }
        }
        throw new AssertionError();
    }

    private int dfs(int now, int parent, HashSet[] graph, TreeSet<int[]> circle, HashMap<Integer,
            Integer> visit) {
        visit.put(now, VISITING);
        for (Object o : graph[now]) {
            Integer next = (Integer) o;
            if (next == parent) {
                continue;
            }
            switch (visit.getOrDefault(next, NOT_VISITED)) {
                case VISITING:
                    // find circle
                    circle.add(new int[]{now, next});
                    return next;
                case VISITED:
                    continue;
                case NOT_VISITED:
                    int dfs = dfs(next, now, graph, circle, visit);
                    if (next == dfs) {
                        return 0;
                    }
                    if (dfs > 0) {
                        circle.add(new int[]{now, next});
                        visit.put(now, VISITED);
                        return dfs;
                    }
                    break;
            }
        }
        visit.put(now, VISITED);
        return 0;
    }

    public static void main(String[] args) {
        RedundantTree redundantTree = new RedundantTree();
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {3, 1}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {3, 4}, {1, 4}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1, 3}, {3, 4}, {1, 4}, {1, 2}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1, 3}, {3, 2}, {1, 4}, {4, 2}})));
    }
}
