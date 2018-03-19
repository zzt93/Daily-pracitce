package interview.google.course;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class MaxVacation {

    public static int maxVacation(int[][] flights, int[][] days) {
        int n = days.length, k = days[0].length;
        int[][] dp = new int[k][n];
        LinkedList<Integer> queue = new LinkedList<>();
        // start at 0
        queue.add(0);
        for (int i = 0; i < k; i++) {
            int[] map = new int[n];
            LinkedList<Integer> tmp = new LinkedList<>();
            while (!queue.isEmpty()) {
                int now = queue.removeFirst();
                for (int nei = 0; nei < flights[now].length; nei++) {
                    if (flights[now][nei] == 0 && nei != now) continue;
                    dp[i][nei] = Math.max(dp[i][nei], i == 0 ? days[nei][i] : dp[i - 1][now] +
                            days[nei][i]);
                    if (map[nei] == 0) {
                        map[nei] = 1;
                        tmp.add(nei);
                    }
                }
            }
            // next day possible start index
            queue = tmp;
        }
        return Arrays.stream(dp[k - 1]).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(maxVacation(new int[][]{new int[]{0, 1, 1}, new int[]{1, 0, 1}, new
                int[]{1, 1, 0}}, new int[][]{new int[]{1, 3, 1}, new int[]{6, 0, 3}, new int[]{3,
                3, 3}}));
        System.out.println(maxVacation(new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}, new
                int[]{0, 0, 0}}, new int[][]{new int[]{1, 1, 1}, new int[]{7, 7, 7}, new int[]{7,
                7, 7}}));
        System.out.println(maxVacation(new int[][]{new int[]{0, 1, 1}, new int[]{1, 0, 1}, new
                int[]{1, 1, 0}}, new int[][]{new int[]{7, 0, 0}, new int[]{0, 7, 0}, new int[]{0,
                0, 7}}));
    }
}
