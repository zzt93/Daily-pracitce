package interview.leetcode._1xx._14x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 11/1/17.
 * <p>
 * <h3></h3>
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fake = new ListNode(Integer.MIN_VALUE);
        fake.next = head;
        ListNode pre = fake, now = pre.next;
        while (now != null) {
            ListNode next = now.next;
            if (pre.val > now.val) {
                ListNode n = pre.next, preN = pre;
                while (n.val > now.val) {
                    preN = n;
                    n = n.next;
                }
                preN.next = now;
                now.next = n;
            } else {
                // reverse
                now.next = pre;
                pre = now;
            }
            now = next;
        }
        ListNode nowHead = pre.next, p = pre;
        pre.next = null;
        while (nowHead != fake) {
            ListNode next = nowHead.next;
            nowHead.next = p;
            p = nowHead;
            nowHead = next;
        }
        nowHead.next = p;
        return fake.next;
    }

    public static void main(String[] args) {
        InsertionSortList list = new InsertionSortList();
        list.insertionSortList(ListNode.makeList("6,5,3,1,8,7,2,4"));
        list.insertionSortList(ListNode.makeList(""));
        list.insertionSortList(ListNode.makeList("1"));
        list.insertionSortList(ListNode.makeList("1,2,3,4"));
    }
}
