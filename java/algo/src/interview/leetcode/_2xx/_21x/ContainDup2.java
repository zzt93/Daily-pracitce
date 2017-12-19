package interview.leetcode._2xx._21x;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzt on 12/18/17.
 * <p>
 * <h3></h3>
 */
public class ContainDup2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>(2 * k, 0.75f,
                false) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > k;
            }
        };
        int i;
        for (i = 0; i < nums.length && !cache.containsKey(nums[i]); i++) {
            cache.put(nums[i], 1);
        }
        return i < nums.length && cache.containsKey(nums[i]);
    }

    public static void main(String[] args) {
        ContainDup2 c = new ContainDup2();
        System.out.println(c.containsNearbyDuplicate(new int[]{1,2,3,4,1}, 3));
        System.out.println(c.containsNearbyDuplicate(new int[]{1,2,1,3}, 3));
        System.out.println(c.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(c.containsNearbyDuplicate(new int[]{1,2,3}, 1));
        System.out.println(c.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(c.containsNearbyDuplicate(new int[]{}, 3));
        System.out.println(c.containsNearbyDuplicate(new int[]{-1,-1}, 1));
    }
}
