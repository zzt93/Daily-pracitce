package methodology.dynamic;

import java.util.ArrayList;

/**
 * Created by zzt on 5/13/16.
 * <p>
 * <h3>Des</h3>
 * <p>Given a list of N coins, their values (V1, V2, … , VN),
 * and the total sum S. Find the minimum number of coins
 * the sum of which is S (we can use as many coins of one type as we want),
 * or report that it’s not possible to select coins in such a way that they sum up to S.</p>
 *
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

    public static ArrayList<Coins> sum(int[] values, int sum) {
        for (int value : values) {

        }
        ArrayList<Coins> res = new ArrayList<>();
        return res;
    }
}
