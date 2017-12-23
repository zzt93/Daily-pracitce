package interview.leetcode._2xx._22x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 12/22/17.
 * <p>
 * <h3></h3>
 */
public class CountTreeNode {

    public int countNodesSlow(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesSlow(root.left) + countNodesSlow(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + height(root.left);
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = height(root);
        return height(root.right) == h - 1 ?
                (1 << (h - 1)) + countNodes(root.right) :
                (1 << (h - 2)) + countNodes(root.left);
    }
}
