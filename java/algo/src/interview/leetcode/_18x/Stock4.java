package interview.leetcode._18x;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 11/21/17.
 * <p>
 * <h3></h3>
 */
public class Stock4 {

    public int maxProfit(int aim, int[] p) {
        int l = p.length;
        if (l <= 1) {
            return 0;
        }
        if (aim >= l / 2) return quick(aim, p);

        int[][] dp = new int[aim + 1][l];
        for (int k = 1; k < aim + 1; k++) {
            int buyLast = -p[0];
            for (int j = 1; j < l; j++) {
                // sell current
                dp[k][j] = Math.max(dp[k][j - 1], buyLast + p[j]);
                // buy current
                buyLast = Math.max(buyLast, dp[k - 1][j - 1] - p[j]);
            }
        }
        return dp[aim][l - 1];
    }

    public int maxProfit2(int aim, int[] p) {
        int l = p.length;
        if (l <= 1) {
            return 0;
        }
        if (aim >= l / 2) return quick(aim, p);

        int[][] hold = new int[aim + 1][l];
        hold[0][0] = -p[0];
        for (int i = 1; i < l; i++) {
            // 0 transaction, buy at some position
            hold[0][i] = Math.max(hold[0][i - 1], -p[i]);
        }
        for (int i = 0; i < aim + 1; i++) {
            // i transaction, buy stock[0]
            hold[i][0] = -p[0];
        }
        int[][] unhold = new int[aim + 1][l];
        for (int k = 1; k < aim + 1; k++) {
            for (int j = 1; j < l; j++) {
                // unhold use k because buying will not inc transaction
                hold[k][j] = Math.max(hold[k][j - 1], unhold[k][j - 1] - p[j]);
                unhold[k][j] = Math.max(unhold[k][j - 1], hold[k - 1][j - 1] + p[j]);
            }
        }
        return unhold[aim][l - 1];
    }

    private int quick(int aim, int[] p) {
        int all = 0;
        for (int i = 1; i < p.length; i++) {
            if (p[i] > p[i - 1]) {
                all += (p[i] - p[i - 1]);
            }
        }
        return all;
    }

    public static void main(String[] args) {
        Stock4 stock = new Stock4();
        System.out.println(stock.maxProfit(4, new int[]{1, 2, 3, 4, 2, 3, 4, 5, 3, 2, 3, 5}));
        System.out.println(stock.maxProfit2(4, new int[]{1, 4, 2, 3, 4, 5, 3, 2, 3, 5}));
        System.out.println(stock.maxProfit(1, new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(stock.maxProfit2(1, new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(stock.maxProfit(1, new int[]{2, 4, 1}));
        System.out.println(stock.maxProfit2(1, new int[]{2, 4, 1}));
        System.out.println(stock.maxProfit(1, new int[]{7, 4, 1}));
        System.out.println(stock.maxProfit2(1, new int[]{7, 4, 1}));
    }
}
