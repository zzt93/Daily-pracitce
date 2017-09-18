package interview.leetcode._12x;

/**
 * Created by zzt on 9/14/17.
 * <p>
 * <h3></h3>
 */
public class Stock2 {

    public int maxProfit(int[] ints) {
        if (ints.length == 0) {
            return 0;
        }
        int[] prices = new int[ints.length + 1];
        System.arraycopy(ints, 0, prices, 0, ints.length);
        prices[ints.length] = Integer.MIN_VALUE;

        int min = prices[0], max = prices[0], sum = 0;
        boolean asc = false;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max = prices[i];
                asc = true;
            } else {
                if (asc) {
                    sum += (max - min);
                }
                min = prices[i];
                asc = false;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Stock2 stock2 = new Stock2();
        System.out.println(stock2.maxProfit(new int[]{}));
        System.out.println(stock2.maxProfit(new int[]{4,3,2,1,2,3,4}));
        System.out.println(stock2.maxProfit(new int[]{1, 2}));
        System.out.println(stock2.maxProfit(new int[]{1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 5, 6, 4}));
        System.out.println(stock2.maxProfit(new int[]{4, 3, 2, 1, 2, 3, 4, 3, 2, 1}));
        System.out.println(stock2.maxProfit(new int[]{4, 3, 2, 1}));
        System.out.println(stock2.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
