package interview.leetcode._12x;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by zzt on 10/5/17.
 * <p>
 * <h3></h3>
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            int l = num - 1;
            int r = num + 1;
            if (!count.containsKey(num)) {
                count.put(num, 1);
            } else {
                continue;
            }

            boolean containL = count.containsKey(l);
            boolean containR = count.containsKey(r);
            if (containL && containR) {
                Integer ll = count.get(l);
                Integer rl = count.get(r);
                int sum = ll + rl + 1;
                count.put(num - ll, sum);
                count.put(num + rl, sum);
            } else if (containL) {
                Integer ll = count.get(l);
                count.put(num - ll, ll + 1);
                count.put(num, ll + 1);
            } else if (containR) {
                Integer rl = count.get(r);
                count.put(num + rl, rl + 1);
                count.put(num, rl + 1);
            }
        }
        return count.values().stream().max(Comparator.naturalOrder()).orElse(0);
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence longest = new LongestConsecutiveSequence();
        System.out.println(longest.longestConsecutive(new int[]{100, 4, 200, 1, 3, 3, 2, 2}));
        System.out.println(longest.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longest.longestConsecutive(new int[]{100, 5, 4, 200, 1, 3, 2}));
        System.out.println(longest.longestConsecutive(new int[]{100, 5, 6, 4, 200, 1, 3, 2}));
        System.out.println(longest.longestConsecutive(new int[]{100, 5, 6, 200, 1, 3, 2}));
        System.out.println(longest.longestConsecutive(new int[0]));
    }
}
