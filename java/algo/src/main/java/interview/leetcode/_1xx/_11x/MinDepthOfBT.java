package interview.leetcode._1xx._11x;

import interview.leetcode.TreeNode;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 9/5/17.
 * <p>
 * <h3></h3>
 */
public class MinDepthOfBT {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        return Math.min(l, r) + 1;
    }

    public static void main(String[] args) {
        MinDepthOfBT bt = new MinDepthOfBT();
        System.out.println(bt.minDepth(makeTree("1,2,null,3,null,4,5,6")));
        System.out.println(bt.minDepth(makeTree("1,2,null,3,null,4,5")));
        System.out.println(bt.minDepth(makeTree("1,2")));
        System.out.println(bt.minDepth(makeTree("1")));
    }
}
