package competition.leetcode.w42;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by zzt on 7/23/17.
 * <p>
 * <h3></h3>
 */
public class CountFrom1 {

    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int f = 1, s = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                f = num;
            } else {
                set.add(num);
            }
        }
        int i = 0;
        for (Integer integer : set) {
            if (integer != i + 1) {
                s = i + 1;
                break;
            }
            i++;
        }
        return new int[]{f, s};
    }

    public static void main(String[] args) {
        CountFrom1 countFrom1 = new CountFrom1();
        System.out.println(Arrays.toString(countFrom1.findErrorNums(new int[]{1, 5, 3, 2, 2, 7,
                6, 4, 8, 9})));
        System.out.println(Arrays.toString(countFrom1.findErrorNums(new int[]{1, 2, 2, 4})));
        System.out.println(Arrays.toString(countFrom1.findErrorNums(new int[]{1, 2, 4, 5, 5})));
        System.out.println(Arrays.toString(countFrom1.findErrorNums(new int[]{1, 1})));
        System.out.println(Arrays.toString(countFrom1.findErrorNums(new int[]{2, 2})));
    }
}
