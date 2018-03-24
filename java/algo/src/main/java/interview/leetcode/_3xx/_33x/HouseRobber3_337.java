package interview.leetcode._3xx._33x;

import interview.leetcode.TreeNode;
import java.util.HashMap;

/**
 * @author zzt
 */
public class HouseRobber3_337 {

  public int rob(TreeNode root) {
    HashMap<TreeNode, Integer> dpT = new HashMap<>();
    HashMap<TreeNode, Integer> dpF = new HashMap<>();
    return Math.max(rob(root, true, dpT, dpF), rob(root, false, dpT, dpF));
  }

  private int rob(TreeNode root, boolean rob, HashMap<TreeNode, Integer> dpT, HashMap<TreeNode, Integer> dpF) {
    if (root == null) return 0;
    if (rob && dpT.containsKey(root)) return dpT.get(root);
    if (!rob && dpF.containsKey(root)) return dpF.get(root);

    int l = rob(root.left, false, dpT, dpF), r = rob(root.right, false, dpT, dpF);
    int res = l + r;
    if (rob) {
      res += root.val;
      dpT.put(root, res);
    } else {
      int lt = rob(root.left, true, dpT, dpF), rt = rob(root.right, true, dpT, dpF);
      res = Math.max(l, lt) + Math.max(r, rt);
      dpF.put(root, res);
    }
    return res;
  }

}
