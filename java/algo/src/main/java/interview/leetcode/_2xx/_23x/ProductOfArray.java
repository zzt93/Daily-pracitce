package interview.leetcode._2xx._23x;

/**
 * Created by zzt on 12/30/17.
 * <p>
 * <h3></h3>
 */
public class ProductOfArray {

    public int[] productExceptSelf(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        int right = 1;
        for (int i = l - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
