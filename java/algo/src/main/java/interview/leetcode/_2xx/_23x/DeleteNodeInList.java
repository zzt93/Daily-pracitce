package interview.leetcode._2xx._23x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 12/28/17.
 * <p>
 * <h3></h3>
 */
public class DeleteNodeInList {

    public void deleteNode(ListNode node) {
        ListNode pre = node, now = pre.next, next = now.next;
        for (pre.val = now.val; next != null; pre = now, now = next, next = next.next, pre.val = now.val);
        pre.next = null;
    }
}
