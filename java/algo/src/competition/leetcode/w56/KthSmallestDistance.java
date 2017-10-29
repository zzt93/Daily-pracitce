package competition.leetcode.w56;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zzt on 10/29/17.
 * <p>
 * <h3></h3>
 */
public class KthSmallestDistance {

    public int smallestDistancePairMLE(int[] nums, int k) {
        int l = nums.length;
        List<Integer> list = new ArrayList<>((l * (l - 1)) / 2);
        for (int x = 0; x < nums.length; x++) {
            for (int y = x + 1; y < nums.length; y++) {
                list.add(Math.abs(nums[x] - nums[y]));
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(list);
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = queue.poll();
        }
        return res;
    }

    public int smallestDistancePair(int[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
        KthSmallestDistance dis = new KthSmallestDistance();
        System.out.println(dis.smallestDistancePair(new int[]{1, 2}, 1));
        System.out.println(dis.smallestDistancePair(new int[]{1, 3, 1}, 1));
        System.out.println(dis.smallestDistancePair(new int[]{1, 3, 1}, 3));
    }
}
