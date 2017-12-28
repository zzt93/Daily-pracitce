package competition.leetcode.w45;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by zzt on 8/13/17.
 * <p>
 * <h3></h3>
 */
public class SplitArrayIntoConsecutiveSequence {

    public boolean isPossible(int[] n) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i : n) {
            int len = 1;
            if (map.containsKey(i - 1)) {
                PriorityQueue<Integer> queue = map.get(i - 1);
                len += queue.poll();
                if (queue.isEmpty()) {
                    map.remove(i - 1);
                }
            }
            PriorityQueue<Integer> orDefault = map.getOrDefault(i, new PriorityQueue<>());
            orDefault.add(len);
            map.put(i, orDefault);
        }
        for (Integer integer : map.keySet()) {
            PriorityQueue<Integer> queue = map.get(integer);
            while (!queue.isEmpty()) {
                if (queue.poll() < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSequence sequence = new
                SplitArrayIntoConsecutiveSequence();
        System.out.println(sequence.isPossible(new int[]{0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 3, 4, 5, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 3, 4, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 3, 3, 4, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 3, 4, 4, 5}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 2, 3, 3, 4, 4}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 2, 3, 3, 3, 4, 4}));
        System.out.println(sequence.isPossible(new int[]{1, 2, 2, 2, 3, 3, 3, 4, 4}));
        System.out.println(sequence.isPossible(new int[]{2, 2, 3, 3, 3, 4, 4}));
    }
}
