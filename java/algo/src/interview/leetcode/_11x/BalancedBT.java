package interview.leetcode._11x;

import competition.leetcode.TreeNode;

/**
 * Created by zzt on 9/5/17.
 * <p>
 * <h3></h3>
 */
public class BalancedBT {

    public boolean isBalanced(TreeNode root) {
        int[] hs = new int[1];
        return height(root, hs);
    }

    private boolean height(TreeNode root, int[] hs) {
        if (root == null) {
            hs[0] = 0;
            return true;
        }
        int[] h1 = new int[1];
        boolean balance = height(root.left, h1);
        if (!balance) {
            return false;
        }
        int[] h2 = new int[1];
        boolean b2 = height(root.right, h2);
        if (!b2) {
            return false;
        }
        hs[0] = Math.max(h1[0], h2[0]) + 1;
        return Math.abs(h1[0] - h2[0]) <= 1;
    }
}
