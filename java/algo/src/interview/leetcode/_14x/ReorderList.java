package interview.leetcode._14x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 10/31/17.
 * <p>
 * <h3></h3>
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        ListNode node = head;

        int c = 0;
        while (node != null) {
            c++;
            node = node.next;
        }
        int mid;
        if (c % 2 == 0) {
            mid = c / 2;
        } else {
            mid = (c - 1) / 2;
        }

        ListNode pre = head;
        if (head == null) {
            return;
        }
        ListNode now = head.next;
        ListNode midNode = head;
        int i = 0;
        while (now != null) {
            i++;
            ListNode next = now.next;
            if (i > mid) {
                now.next = pre;
            } else if (i == mid) {
                midNode = now;
            }
            pre = now;
            now = next;
        }

        ListNode first = head, last = pre;
        while (last != midNode) {
            ListNode tmp = first.next;
            ListNode next = last.next;
            first.next = last;
            last.next = tmp;
            first = tmp;
            last = next;
        }
        midNode.next = null;
    }

    public static void main(String[] args) {
        ReorderList re = new ReorderList();
        re.reorderList(ListNode.makeList("1"));
        re.reorderList(ListNode.makeList("0,1,2,3,4,5"));
        re.reorderList(ListNode.makeList("0,1,2,3,4"));
    }
}
