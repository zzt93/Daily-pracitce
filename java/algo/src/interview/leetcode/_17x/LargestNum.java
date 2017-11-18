package interview.leetcode._17x;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zzt on 11/18/17.
 * <p>
 * <h3></h3>
 */
public class LargestNum {

    public String largestNumber(int[] nums) {
        if (Arrays.stream(nums).allMatch(i -> i == 0)) {
            return "0";
        }
        return Arrays.stream(nums).mapToObj(i -> i + "").sorted((s1, s2) -> -(s1 + s2).compareTo(s2 + s1)).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        LargestNum l = new LargestNum();
        System.out.println(l.largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(l.largestNumber(new int[]{1}));
        System.out.println(l.largestNumber(new int[]{1,0,0}));
        System.out.println(l.largestNumber(new int[]{0,0}));
        System.out.println(l.largestNumber(new int[]{121,12}));
        System.out.println(l.largestNumber(new int[]{123, 1231231, 1231234, 1230}));
    }
}
