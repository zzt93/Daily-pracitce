package interview.leetcode._2xx._20x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 12/6/17.
 * <p>
 * <h3></h3>
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head, now = head.next;
        pre.next = null;
        while (now != null) {
            ListNode next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }
}
