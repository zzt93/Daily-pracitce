package interview.leetcode._2xx._21x;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zzt on 12/15/17.
 * <p>
 * <h3></h3>
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toSet()).size() == nums.length;
    }
}
