package interview.leetcode._2xx._23x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 12/28/17.
 * <p>
 * <h3></h3>
 */
public class LCAofBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        assert root != null;
        if ((p.val < root.val && q.val > root.val)
                || (p.val > root.val && q.val < root.val)) {
            return root;
        } else if (p.val == root.val || q.val == root.val) {
            return root;
        } else if (p.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }
}
