package interview.leetcode._1xx._10x;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class ConstructBT2 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length,
                postorder, 0, postorder.length);
    }

    private TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is >= ie) {
            return null;
        }
        int r = postorder[pe - 1];
        TreeNode res = new TreeNode(r);
        int i;
        for (i = is; i < ie; i++) {
            if (inorder[i] == r) {
                break;
            }
        }
        int leftLen = i - is;
        res.left = buildTree(inorder, is, i, postorder, ps, ps + leftLen);
        res.right = buildTree(inorder, i + 1, ie, postorder, ps + leftLen, pe - 1);
        return res;
    }
}
