package interview.leetcode._14x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 10/30/17.
 * <p>
 * <h3></h3>
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode now = head.next;
        ListNode pre = head;
        while(now != null && now != head) {
            ListNode tmp = now.next;
            now.next = pre;
            pre = now;
            now = tmp;
        }
        return now == head;
    }
}
