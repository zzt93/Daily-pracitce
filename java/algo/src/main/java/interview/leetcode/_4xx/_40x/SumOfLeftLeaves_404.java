package interview.leetcode._4xx._40x;

import interview.leetcode.TreeNode;

/**
 * @author zzt
 */
public class SumOfLeftLeaves_404 {

  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    return sum(root, false);
  }

  private int sum(TreeNode root, boolean left) {
    if (root.left == null && root.right == null) {
      if (left) return root.val;
    }
    int s = 0;
    if (root.left != null) s += sum(root.left, true);
    if (root.right != null) s += sum(root.right, false);

    return s;
  }

}
