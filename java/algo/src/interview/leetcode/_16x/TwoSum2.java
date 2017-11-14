package interview.leetcode._16x;

import java.util.Arrays;

/**
 * Created by zzt on 11/14/17.
 * <p>
 * <h3></h3>
 */
public class TwoSum2 {

    public int[] twoSum(int[] n, int target) {
        for (int i = 0; i < n.length; i++) {
            int x = Arrays.binarySearch(n, i + 1, n.length, target - n[i]);
            if (x > 0) {
                return new int[]{i + 1, x + 1};
            }
        }
        return null;
    }
}
