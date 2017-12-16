package interview.leetcode._2xx._21x;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 12/14/17.
 * <p>
 * <h3></h3>
 */
public class KLargestElement {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        Comparator<Integer> tComparator = Comparator.naturalOrder();
        PriorityQueue<Integer> max = new PriorityQueue<>(nums.length, tComparator.reversed());
        for (int num : nums) {
            max.add(num);
        }
        while (min.size() < k) {
            min.add(max.poll());
        }
        return min.peek();
    }
}
