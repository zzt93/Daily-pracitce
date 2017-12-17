package interview.leetcode._1xx._12x;

/**
 * Created by zzt on 9/14/17.
 * <p>
 * <h3></h3>
 */
public class Stock {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                int x = prices[i] - min;
                if (x > max) {
                    max = x;
                }
            } else {
                min = prices[i];
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public static void main(String[] args) {
        Stock stock = new Stock();
        System.out.println(stock.maxProfit(new int[]{2, 4, 1}));
        System.out.println(stock.maxProfit(new int[]{7, 4, 1}));
        System.out.println(stock.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
