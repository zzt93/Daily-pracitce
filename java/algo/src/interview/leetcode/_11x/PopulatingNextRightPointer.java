package interview.leetcode._11x;

import interview.leetcode.TreeLinkNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zzt on 9/12/17.
 * <p>
 * <h3></h3>
 */
public class PopulatingNextRightPointer {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeLinkNode> deque = new LinkedList<>();
        deque.add(root);
        deque.add(null);
        TreeLinkNode last = null;
        while (!deque.isEmpty()) {
            TreeLinkNode poll = deque.poll();
            if (poll == null) {
                if (!deque.isEmpty()) {
                    deque.add(null);
                }
                last = null;
                continue;
            }
            if (poll.left != null) {
                deque.add(poll.left);
            }
            if (poll.right != null) {
                deque.add(poll.right);
            }

            if (last != null) {
                last.next = poll;
            }
            last = poll;
        }
    }
}
