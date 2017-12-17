package interview.leetcode._1xx._13x;

import java.util.Arrays;

/**
 * Created by zzt on 10/24/17.
 * <p>
 * <h3></h3>
 */
public class SingleNum {

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i+=2;
            } else {
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SingleNum num = new SingleNum();
        System.out.println(num.singleNumber(new int[]{}));
        System.out.println(num.singleNumber(new int[]{1}));
        System.out.println(num.singleNumber(new int[]{1,2,1}));
        System.out.println(num.singleNumber(new int[]{1,2,1,3,2,5,5}));
        System.out.println(num.singleNumber(new int[]{1,2,3,2,5,5,3}));
    }
}
