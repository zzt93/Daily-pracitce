package interview.leetcode._2xx._21x;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zzt on 12/8/17.
 * <p>
 * <h3></h3>
 */
public class CourseSchedule2 {

    public int[] findOrder(int n, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[n];
        int[] in = new int[n];
        HashSet<Integer> nows = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
            nows.add(i);
        }
        for (int[] c : prerequisites) {
            graph[c[1]].add(c[0]);
            in[c[0]]++;
            nows.remove(c[0]);
        }
        int[] res = new int[n];
        int visited = 0, i = 0;
        while (!nows.isEmpty()) {
            HashSet<Integer> tmp = new HashSet<>();
            for (Integer now : nows) {
                visited++;
                res[i++] = now;
                for (Integer nei : graph[now]) {
                    in[nei]--;
                    if (in[nei] == 0) {
                        tmp.add(nei);
                    }
                }
            }
            nows.clear();
            nows.addAll(tmp);
        }
        return visited < n ? new int[0] : res;
    }
}
