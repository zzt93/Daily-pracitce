package methodology.dynamic;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zzt on 5/13/16.
 * <p>
 * <h3>Des</h3>
 * <p>Given a list of N coins, their values (V1, V2, … , VN),
 * and the total sum S. Find the minimum number of coins
 * the sum of which is S (we can use as many coins of one type as we want),
 * or report that it’s not possible to select coins in such a way that they sum up to S.</p>
 * <p>
 * <h3>Similar problem: {@link MinimumStepToOne}</h3>
 */
public class SumCoin {

    private static class Coins {
        private int num;
        private int value;

        public Coins(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }

    /**
     * backtrace to find the path
     * @param sum sum
     * @return
     */
    public ArrayList<String> sumWithSolution(int sum) {
        final int num = sum(sum);
//        values[num]
        ArrayList<String> res = new ArrayList<>();
        return res;
    }

    private final int[] values;

    public SumCoin(int[] values) {
        this.values = values;
    }

    private static final int MAX_SIZE = 1001;
    private static final int NOT_SET = 0;
    private static final int IMPOSSIBLE = Integer.MAX_VALUE;
    /**
     * memorization array, serve for same values
     */
    private int[] sum2coin = new int[MAX_SIZE];

    private int sum(int i) {
        return sum(values, i);
    }

    /**
     * @param values coin values
     * @param sum    coin sum
     *
     * @return minimum number of coin to sum as `sum` or negative if impossible
     */
    private int sum(int[] values, int sum) {
        if (sum < 0) {
            return IMPOSSIBLE;
        } else if (sum == 0) {
            return 0;
        } else if (sum < MAX_SIZE && sum2coin[sum] != NOT_SET) {
            return sum2coin[sum];
        }
        int[] tmp = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            tmp[i] = sum(values, sum - value);
        }
        final int res = Arrays.stream(tmp).filter(i -> i >= 0).min().orElse(IMPOSSIBLE) + 1;
        if (sum < MAX_SIZE) {
            sum2coin[sum] = res;
        }
        return res;
    }

    public static void main(String[] args) {
//        testMoney();
        final int[] ints = {3, 7};
        final SumCoin sumCoin = new SumCoin(ints);
        for (int i = 0; i < 100; i++) {
            System.out.println(sumCoin.sum(i));
        }
    }

    private static void testMoney() {
        int[] money = new int[]{1, 3, 5, 10, 20, 50, 100};
        final SumCoin sumCoin = new SumCoin(money);
        int[] realMoney = new int[]{1, 2, 5, 10, 20, 50, 100};
        final SumCoin sumCoin1 = new SumCoin(realMoney);
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            final int fake = sumCoin.sum(i);
            final int real = sumCoin1.sum(i);
            sum += (real - fake);
        }
        System.out.println(sum);
    }

}
