package interview.leetcode._1xx._13x;

import java.util.Arrays;

/**
 * Created by zzt on 10/24/17.
 * <p>
 * <h3></h3>
 */
public class SingleNum2 {

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            if (i + 2 < nums.length && nums[i] == nums[i + 2]) {
                i += 3;
            } else {
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SingleNum2 num = new SingleNum2();
        System.out.println(num.singleNumber(new int[]{}));
        System.out.println(num.singleNumber(new int[]{1}));
        System.out.println(num.singleNumber(new int[]{1, 2, 1, 1}));
        System.out.println(num.singleNumber(new int[]{1, 1, 2, 2, 1, 3, 5, 2, 5, 5}));
        System.out.println(num.singleNumber(new int[]{1, 2, 2, 3, 3, 2, 5, 5, 5, 3}));
        System.out.println(num.singleNumber(new int[]{1, 2, 2, 3, 3, 2, 5, 1, 1, 3}));
    }
}
