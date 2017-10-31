package interview.leetcode._14x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 10/30/17.
 * <p>
 * <h3></h3>
 */
public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        ListNode f = head, s = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (f == s) {
                ListNode ss = head;
                while (ss != s) {
                    ss = ss.next;
                    s = s.next;
                }
                return s;
            }
        }
        return null;
    }
}
