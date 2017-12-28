package interview.leetcode._1xx._10x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 8/31/17.
 * <p>
 * <h3></h3>
 */
public class ConstructBT {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);

    }

    private TreeNode buildTree(int[] preorder, int s, int e, int[] inorder, int ls, int le) {
        if (s >= e) {
            return null;
        }
        int r = preorder[s];
        TreeNode root = new TreeNode(r);
        int i;
        for (i = ls; i < le; i++) {
            int val = inorder[i];
            if (val == r) {
                break;
            }
        }
        int leftLen = i - ls;
        root.left = buildTree(preorder, s + 1, s + leftLen + 1, inorder, ls, i);
        root.right = buildTree(preorder, s + leftLen + 1, e, inorder, i + 1, le);
        return root;
    }
}
