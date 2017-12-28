package interview.leetcode._2xx._20x;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zzt on 12/6/17.
 * <p>
 * <h3>Visiting vs Visited!</h3>
 * <ul>
 *     <li>Visiting is for circle detection</li>
 *     <li>Visited is for opt</li>
 * </ul>
 */
public class CourseSchedule {

    public boolean canFinish(int n, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] c : prerequisites) {
            graph[c[0]].add(c[1]);
        }
        HashSet<Integer> visiting = new HashSet<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (hasCircle(graph, i, visiting, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCircle(LinkedList<Integer>[] graph, int now, HashSet<Integer> visiting,
                              HashSet<Integer> visited) {
        if (visited.contains(now)) {
            return false;
        }
        visiting.add(now);
        for (Integer nei : graph[now]) {
            if (visiting.contains(nei) || hasCircle(graph, nei, visiting, visited)) {
                return true;
            }
        }
        visited.add(now);
        visiting.remove(now);
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule c = new CourseSchedule();
        System.out.println(c.canFinish(4, new int[][]{new int[]{1, 0}, new int[]{2, 1}, new
                int[]{3, 2}, new int[]{1, 3}}));
    }
}
