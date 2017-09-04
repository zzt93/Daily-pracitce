package competition.leetcode.week25;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class BoundaryOfBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        LinkedHashSet<TreeNode> set = new LinkedHashSet<>();
        left(set, root);
        leaves(set, root);
        right(set, root);
        return set.stream().map(treeNode -> treeNode.val).collect(Collectors.toCollection
                (ArrayList::new));
    }

    private void leaves(LinkedHashSet<TreeNode> set, TreeNode root) {
        if (root != null) {
            leaves(set, root.left);
            leaves(set, root.right);
            if (root.left == null && root.right == null) {
                set.add(root);
            }
        }
    }

    private void right(LinkedHashSet<TreeNode> set, TreeNode root) {
        if (root != null && root.right == null) {
            set.add(root);
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null) {
            stack.push(root);
            if (root.right == null) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }
    }

    private void left(LinkedHashSet<TreeNode> set, TreeNode root) {
        if (root != null && root.left == null) {
            set.add(root);
            return;
        }
        while (root != null) {
            set.add(root);
            if (root.left == null) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }
}
