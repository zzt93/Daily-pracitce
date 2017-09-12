package interview.leetcode._10x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 8/25/17.
 * <p>
 * <h3></h3>
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
