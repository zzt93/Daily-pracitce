package competition.leetcode.w31;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Created by zzt on 5/7/17.
 * <p>
 * <h3></h3>
 */
public class SubTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public boolean equals(TreeNode f, TreeNode s) {
        if (f == s) return true;
        if (f == null || s == null) return false;

        if (f.val != s.val) return false;
        if (f.left != null ? !equals(f.left, s.left) : s.left != null) return false;
        return f.right != null ? equals(f.right, s.right) : s.right == null;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            if (equals(poll, t)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SubTree subTree = new SubTree();
        System.out.println(subTree.isSubtree(
                makeTree("3, 4, 5, 1, 2,null,null,0"),
                makeTree("4, 1, 2")
        ));
    }

    public static TreeNode makeTree(String string) {
        if (string.trim().isEmpty()) {
            return null;
        }
        String[] ints = Stream.of(string.split(",")).map(String::trim).toArray(String[]::new);
        if (ints.length == 0) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(ints[0]));
        Queue<TreeNode> queue = new ArrayDeque<>(ints.length);
        queue.add(treeNode);
        int i = 1;
        while (!queue.isEmpty() && i < ints.length) {
            TreeNode poll = queue.poll();
            if (ints[i].equals("null")) {
                poll.left = null;
            } else {
                poll.left = new TreeNode(Integer.parseInt(ints[i]));
                queue.add(poll.left);
            }
            i++;
            if (i >= ints.length) break;
            if (ints[i].equals("null")) {
                poll.right = null;
            } else {
                poll.right = new TreeNode(Integer.parseInt(ints[i]));
                queue.add(poll.right);
            }
            i++;
        }
        return treeNode;
    }
}
