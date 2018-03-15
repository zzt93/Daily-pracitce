package interview.google.course;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by zzt on 3/15/18.
 * <p>
 * <h3></h3>
 */
public class ArrayRelated {

    public int maxSpan(int[] nums) {
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[1] = i;
            } else {
                map.put(nums[i], new int[]{i, i});
            }
        }

        int max = 0;
        for (int i: map.keySet()) {
            int[] m = map.get(i);
            max = Math.max(max, m[1]-m[0]+1);
        }
        return max;
    }

    public static boolean canBalance(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            sum[i] = sum[i-1] + nums[i];
        }
        return (sum[nums.length-1]&1) == 0 && Arrays.binarySearch(sum, sum[nums.length-1]/2) >= 0;
    }

    public static void main(String[] args) {
        System.out.println(canBalance(new int[]{2, 3, 4, 1, 2}));
    }

}
