package competition.leetcode.week24;

/**
 * Created by zzt on 3/19/17.
 * <p>
 * <h3></h3>
 */
public class DiameterOfBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] v = diameterAndDepth(root);
        return v[1];
    }

    private int[] diameterAndDepth(TreeNode root) {
        int[] left = {0, 0};
        int[] right = {0, 0};
        int child = 0;
        if (root.left != null) {
            left = diameterAndDepth(root.left);
            child++;
        }
        if (root.right != null) {
            right = diameterAndDepth(root.right);
            child++;
        }
        int maxDepth = (left[0] > right[0] ? left[0] : right[0]) + (child != 0 ? 1 : 0);
        int maxDiameter = Math.max(Math.max(left[1], right[1]), left[0] + right[0] + child);
        System.out.println(root.val + ":" + maxDepth + "," + maxDiameter);
        return new int[]{maxDepth, maxDiameter};
    }
}
