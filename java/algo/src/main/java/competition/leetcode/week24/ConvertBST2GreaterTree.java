package competition.leetcode.week24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zzt on 3/19/17.
 * <p>
 * <h3></h3>
 */
public class ConvertBST2GreaterTree {


    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        collectVal(root, list);
        list.sort(Comparator.comparingInt(t -> t.val));
        int sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            final TreeNode treeNode = list.get(i);
            int tmp = treeNode.val;
            treeNode.val += sum;
            sum += tmp;
        }
        return root;
    }

    private void collectVal(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        collectVal(root.left, list);
        collectVal(root.right, list);
        list.add(root);
    }
}
