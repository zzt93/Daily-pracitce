package competition.leetcode.w64;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zzt on 12/24/17.
 * <p>
 * <h3></h3>
 */
public class LargestNum {

    public int dominantIndex(int[] n) {
        int l = n.length;
        if (l == 1) {
            return 0;
        }
        int[][] ints = new int[l][2];
        for (int i = 0; i < n.length; i++) {
            ints[i] = new int[]{n[i], i};
        }
        Arrays.sort(ints, Comparator.comparingInt(is->is[0]));
        if (ints[l-1][0] >= ints[l-2][0] * 2) {
            return ints[l-1][1];
        }
        return -1;
    }
}
