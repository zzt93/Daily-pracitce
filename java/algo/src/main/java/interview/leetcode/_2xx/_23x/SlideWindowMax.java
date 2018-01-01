package interview.leetcode._2xx._23x;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zzt on 12/30/17.
 * <p>
 * <h3>Max in some changed range</h3>
 * <ul>
 * <li>BST track all number in range</li>
 * <li>queue, keep the largest number and numbers after it</li>
 * </ul>
 */
public class SlideWindowMax {

    public int[] maxSlidingWindow(int[] n, int k) {
        int l = n.length;
        if (l == 0) {
            return new int[0];
        }
        int[] res = new int[l - k + 1];
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            addToRange(n, queue, i);
        }
        for (int i = 0; i < res.length; i++) {
            int[] max = queue.peekFirst();
            res[i] = max[0];
            if (max[1] == i) queue.removeFirst();
            if (i + k < l) {
                addToRange(n, queue, i + k);
            }
        }
        return res;
    }

    private void addToRange(int[] n, LinkedList<int[]> queue, int i) {
        if (queue.isEmpty()) {
            queue.addLast(new int[]{n[i], i});
        } else {
            while (!queue.isEmpty() && queue.peekLast()[0] < n[i]) {
                queue.removeLast();
            }
            queue.addLast(new int[]{n[i], i});
        }
    }

    public static void main(String[] args) {
        SlideWindowMax s = new SlideWindowMax();
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 8)));
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 1)));
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{1,2,3,4,5,4,3,2,3,7,9,3}, 3)));
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{}, 0)));
    }
}
