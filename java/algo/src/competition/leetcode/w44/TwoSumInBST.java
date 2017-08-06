package competition.leetcode.w44;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static competition.leetcode.w31.SubTree.TreeNode;

/**
 * Created by zzt on 8/6/17.
 * <p>
 * <h3></h3>
 */
public class TwoSumInBST {

    public boolean findTarget(TreeNode root, int k) {
        List<TreeNode> nodeList = new ArrayList<>();
        traversal(root, nodeList);
        TreeNode[] treeNodes = nodeList.toArray(new TreeNode[0]);
        int len = treeNodes.length;
        for (int i = 0; i < len; i++) {
            int val = nodeList.get(i).val;
            int t = k - val;
            int x = Arrays.binarySearch(treeNodes, i + 1, len, new TreeNode(t), Comparator
                    .comparingInt
                    (n -> n.val));
            if (x > 0) {
                return true;
            }
        }
        return false;
    }

    private void traversal(TreeNode root, List<TreeNode> nodeList) {
        if (root == null) {
            return;
        }
        traversal(root.left, nodeList);
        nodeList.add(root);
        traversal(root.right, nodeList);
    }

}
