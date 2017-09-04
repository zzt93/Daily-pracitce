package interview.leetcode._9x;


import competition.leetcode.TreeNode;

import static competition.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 8/10/17.
 * <p>
 * <h3></h3>
 */
public class ValidBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        int[] res = recur(root);
        return res[0] == 1;
    }

    private int[] recur(TreeNode root) {
        int max = root.val, min = root.val;
        if (root.left == null && root.right == null) {
            return new int[]{1, max, min};
        }
        if (root.left == null) {
            int[] right = recur(root.right);
            boolean b = right[0] == 1 && root.val < right[2];
            return new int[]{b ? 1 : 0, max = right[1], min};
        }
        if (root.right == null) {
            int[] left = recur(root.left);
            boolean b = left[0] == 1 && root.val > left[1];
            return new int[]{b ? 1 : 0, max, min = left[2]};
        }
        int[] left = recur(root.left);
        if (left[0] == 1) {
            int[] right = recur(root.right);
            boolean b = right[0] == 1 && root.val > left[1] && root.val < right[2];
            return new int[]{b ? 1 : 0, right[1], left[2]};
        }
        return new int[]{0, 0, 0};
    }

    public static void main(String[] args) {
        ValidBST validBST = new ValidBST();
        System.out.println(validBST.isValidBST(makeTree("")));
        System.out.println(validBST.isValidBST(makeTree("2,2,3")));
        System.out.println(validBST.isValidBST(makeTree("2,1,3")));
        System.out.println(validBST.isValidBST(makeTree("1,2,3")));
    }
}
