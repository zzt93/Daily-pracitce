package interview.leetcode._10x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class ConvertSortedArray2BST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length);
    }

    private TreeNode buildTree(int[] nums, int s, int e) {
        if (s >= e) {
            return null;
        }
        int mid = (s + e) / 2;
        TreeNode res = new TreeNode(nums[mid]);
        res.left = buildTree(nums, s, mid);
        res.right = buildTree(nums, mid + 1, e);
        return res;
    }
}
