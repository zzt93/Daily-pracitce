package competition.leetcode.w46;

import java.util.HashMap;

import static competition.leetcode.w31.SubTree.TreeNode;


/**
 * Created by zzt on 8/25/17.
 * <p>
 * <h3></h3>
 */
public class EqualTreePartition {

    public boolean checkEqualTree(TreeNode root) {
        HashMap<TreeNode, Long> sum = new HashMap<>();
        dfs(root, sum);
        Long all = sum.get(root);
        for (TreeNode node : sum.keySet()) {
            if (node == root) {
                continue;
            }
            Long s = sum.get(node);
            if (s * 2 == all) {
                return true;
            }
        }
        return false;
    }

    private long dfs(TreeNode root, HashMap<TreeNode, Long> sum) {
        if (root == null) {
            return 0;
        }
        long left = dfs(root.left, sum);
        long right = dfs(root.right, sum);
        long l = left + right + root.val;
        sum.put(root, l);
        return l;
    }
}
