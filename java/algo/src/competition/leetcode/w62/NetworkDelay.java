package competition.leetcode.w62;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zzt on 12/10/17.
 * <p>
 * <h3>dfs with timestamp</h3>
 * <h3>graph --> dp: min time from k node</h3>
 */
public class NetworkDelay {

    private static final int INF = (int) 1e8;

    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] dp = new int[N][N];
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }
        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }
        for (int[] time : times) {
            dp[time[0] - 1][time[1] - 1] = time[2];
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        K--;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (dp[K][i] == INF) {
                return -1;
            }
            max = Math.max(max, dp[K][i]);
        }
        return max;
    }

    @Deprecated
    private void dfs(LinkedList<int[]>[] graph, int k, int time, HashMap<Integer, Integer>
            visited) {
        visited.put(k, time);
        for (int[] node : graph[k]) {
            int nei = node[0];
            int w = node[1];
            if (!visited.containsKey(nei)) {
                dfs(graph, nei, time + w, visited);
            } else {
                if (visited.get(nei) > time + w) {
                    dfs(graph, nei, time + w, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        NetworkDelay n = new NetworkDelay();
        System.out.println(n.networkDelayTime(new int[][]{new int[]{1, 2, 1}, new int[]{2, 3, 2},
                new int[]{1, 3, 2}}, 3, 1));
        System.out.println(n.networkDelayTime(new int[][]{new int[]{2, 3, 2}, new int[]{2, 1, 1},
                new int[]{1, 4, 1},
                new int[]{3, 4, 1}}, 4, 2));
    }
}
