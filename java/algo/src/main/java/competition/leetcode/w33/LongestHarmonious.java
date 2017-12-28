package competition.leetcode.w33;

import java.util.TreeMap;

/**
 * Created by zzt on 5/21/17.
 * <p>
 * <h3></h3>
 */
public class LongestHarmonious {

    public int findLHS(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int max = 0;
        for (Integer i : map.keySet()) {
            Integer integer = map.get(i);
            int sum = integer + map.getOrDefault(i + 1, -integer);
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestHarmonious harmonious = new LongestHarmonious();
        System.out.println(harmonious.findLHS(new int[]{}));
        System.out.println(harmonious.findLHS(new int[]{1}));
        System.out.println(harmonious.findLHS(new int[]{1, 1, 1, 1}));
        System.out.println(harmonious.findLHS(new int[]{1, 2, 3, 4, 6, 7, 2, 3, 4, 3, 2}));
        System.out.println(harmonious.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
    }
}
