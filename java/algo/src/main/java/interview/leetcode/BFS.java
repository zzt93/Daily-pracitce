package interview.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 8/6/17.
 * <p>
 * <h3></h3>
 */
public class BFS {

    public static void bfs(Consumer<TreeNode> consumer, TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        deque.add(null);
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            consumer.accept(poll);
            if (poll == null) {
                if (!deque.isEmpty()) {
                    deque.add(null);
                }
                continue;
            }
            if (poll.left != null) {
                deque.add(poll.left);
            }
            if (poll.right != null) {
                deque.add(poll.right);
            }
        }
    }

    public static void main(String[] args) {
        Consumer<TreeNode> print = treeNode -> {
            if (treeNode == null) {
                System.out.println();
            } else {
                System.out.print(treeNode.val + " ");
            }
        };
        bfs(print, makeTree("1,2,3,4,null,null,null,5,null"));
    }
}
