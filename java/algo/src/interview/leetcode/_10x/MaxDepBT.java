package interview.leetcode._10x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 8/31/17.
 * <p>
 * <h3></h3>
 */
public class MaxDepBT {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l, r) + 1;
    }
}
