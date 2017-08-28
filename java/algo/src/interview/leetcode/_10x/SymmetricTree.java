package interview.leetcode._10x;

import competition.leetcode.w31.SubTree;

import static competition.leetcode.w31.SubTree.TreeNode;

/**
 * Created by zzt on 8/28/17.
 * <p>
 * <h3>Divide and Conquer</h3>
 * <ul>
 *     <li>mirror the tree</li>
 *     <li>is the same with original</li>
 * </ul>
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        TreeNode mirror = mirror(root);
        return isSame(root, mirror);
    }

    private boolean isSame(TreeNode root, TreeNode mirror) {
        if (root == null && mirror == null) {
            return true;
        }
        if (root == null || mirror == null) {
            return false;
        }
        return root.val == mirror.val && isSame(root.left, mirror.left) && isSame(root.right, mirror.right);
    }

    private TreeNode mirror(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode res = new TreeNode(node.val);
        res.left = mirror(node.right);
        res.right = mirror(node.left);
        return res;
    }
    
}
