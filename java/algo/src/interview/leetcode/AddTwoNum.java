package interview.leetcode;

/**
 * Created by zzt on 3/19/16.
 * <p>
 * Usage:
 */
public class AddTwoNum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode now = new ListNode(0);
        ListNode start = now;
        do {
            int v = l1.val + l2.val + add;
            if (v >= 10) {
                add = 1;
                v -= 10;
            } else {
                add = 0;
            }
            now.next = new ListNode(v);
            now = now.next;

            l1 = l1.next;
            l2 = l2.next;
        } while (l1 != null && l2 != null);

        if (add == 1) {
            if (l1 == null && l2 == null) {
                now.next = new ListNode(add);
            } else if (l1 == null) {
                now.next = addTwoNumbers(new ListNode(add), l2);
            } else {
                now.next = addTwoNumbers(new ListNode(add), l1);
            }
        } else {
            if (l1 == null) {
                now.next = l2;
            } else {
                now.next = l1;
            }
        }
        return start.next;
    }

}
