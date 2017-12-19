package interview.leetcode._2xx._22x;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zzt on 12/19/17.
 * <p>
 * <h3></h3>
 */
public class ContainsDup3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            Map.Entry<Integer, Integer> larger = map.ceilingEntry(n);
            Map.Entry<Integer, Integer> smaller = map.floorEntry(n);
            while (larger != null && i - larger.getValue() > k) {
                map.remove(larger.getKey());
                larger = map.ceilingEntry(n);
            }
            if (larger != null && larger.getKey() - (long) n <= t) {
                return true;
            }

            while (smaller != null && i - smaller.getValue() > k) {
                map.remove(smaller.getKey());
                smaller = map.floorEntry(n);
            }
            if (smaller != null && (long) n - smaller.getKey() <= t) {
                return true;
            }
            map.put(n, i);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDup3 c = new ContainsDup3();
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{-1, 2147483647}, 1, 2147483647));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{2147483647, -1}, 1, 2147483647));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 1));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{10, 100, 11, 9}, 1, 2));
    }
}
