package interview.leetcode._3xx._33x;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zzt on 3/23/18.
 * <p>
 * <h3></h3>
 * <ul>
 *     <li>Use Queue to hold multiple edges between nodes & every edge visit once (dfs or bfs)</li>
 *     <li></li>
 * </ul>
 */
public class Reconstruct_332 {

    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] t: tickets) {
            map.computeIfAbsent(t[0], k -> new PriorityQueue<>()).add(t[1]);
        }
        List<String> res = new LinkedList<>();
        dfs(res, map, "JFK");
        return res;
    }

    private void dfs(List<String> res, HashMap<String, PriorityQueue<String>> map, String now) {
        while (map.containsKey(now) && !map.get(now).isEmpty()) {
            dfs(res, map, map.get(now).poll());
        }
        res.add(0, now);
    }

    public static void main(String[] args) {
        Reconstruct_332 r = new Reconstruct_332();
        System.out.println(r.findItinerary(new String[][]{new String[]{"JFK", "KUL"}, new
                String[]{"JFK", "NRT"}, new String[]{"NRT", "JFK"}}));
    }
}
