package interview.leetcode._1xx._11x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 9/5/17.
 * <p>
 * <h3></h3>
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right ==null) {
            return sum == root.val;
        }
        int aim = sum - root.val;
        if (root.left == null) {
            return hasPathSum(root.right, aim);
        }
        if (root.right == null) {
            return hasPathSum(root.left, aim);
        }
        return hasPathSum(root.left, aim)
                || hasPathSum(root.right, aim);
    }
}
