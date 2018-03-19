package competition.leetcode.w76;

import java.util.*;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class SafeState {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (!inCyclePath(graph, i, visited, new HashSet<>())) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * @return whether this node is in the path has cycle
     * Side-effect: also check other node in this path's state and add to `visited`
     */
    private boolean inCyclePath(int[][] graph, int i, Map<Integer, Boolean> visited,
                                Set<Integer> path) {
        if (visited.containsKey(i)) return visited.get(i);
        if (path.contains(i)) {
            visited.put(i, true);
            return true;
        }
        path.add(i);
        boolean in = false;
        for (int nei: graph[i]) {
            in = inCyclePath(graph, nei, visited, path) || in;
        }
        visited.put(i, in);
        return in;
    }
}
