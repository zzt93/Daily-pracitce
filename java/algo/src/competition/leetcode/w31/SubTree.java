package competition.leetcode.w31;

import competition.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by zzt on 5/7/17.
 * <p>
 * <h3></h3>
 */
public class SubTree {

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
                TreeNode.makeTree("3, 4, 5, 1, 2,null,null,0"),
                TreeNode.makeTree("4, 1, 2")
        ));
    }

}
