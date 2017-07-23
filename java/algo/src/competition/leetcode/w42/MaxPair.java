package competition.leetcode.w42;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by zzt on 7/23/17.
 * <p>
 * <h3></h3>
 */
public class MaxPair {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(p -> p[0]));
        HashMap<int[], Integer> map = new HashMap<>();
        map.put(pairs[0], 1);
        for (int i = 1; i < pairs.length; i++) {
            int[] pair = pairs[i];
            int max = 1;
            for (int[] ints : map.keySet()) {
                if (pair[0] > ints[1]) {
                    if (map.get(ints) + 1 > max) {
                        max = map.get(ints) + 1;
                    }
                }
            }
            map.put(pair, max);
        }
        return map.values().stream().max(Comparator.naturalOrder()).orElse(1);
    }

    public static void main(String[] args) {
        MaxPair maxPair = new MaxPair();
        System.out.println(maxPair.findLongestChain(new int[][]{new int[]{-6, 9}, new int[]{1, 6}, new int[]{8, 10}, new int[]{-1, 4}, new int[]{-6, -2}, new int[]{-9, 8}, new int[]{-5, 3}, new int[]{0, 3}}));
        System.out.println(maxPair.findLongestChain(new int[][]{new int[]{1, 2}, new int[]{3, 300}, new int[]{3, 4}, new int[]{5, 6}}));
        System.out.println(maxPair.findLongestChain(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}}));
    }
}
