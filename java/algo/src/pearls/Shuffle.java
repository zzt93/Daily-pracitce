package pearls;

import competition.utility.Swap;

import java.util.Random;

/**
 * Created by zzt on 8/21/16.
 * <p>
 * <h3></h3>
 */
public class Shuffle {

    private static Random random = new Random(32);

    /**
     * time: O(m)
     * space: O(m)
     *
     * @return a set with size m is chosen from [0, n) without duplicate
     */
    public static int[] shuffle(int n, int m) {
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = i;
        }
        for (int i = 0; i < res.length; i++) {
            final int r = random.nextInt(n);
            if (r >= m) {
                res[i] = r;
            } else {
                Swap.swap(res, i, r);
            }
        }
        return res;
    }
}
