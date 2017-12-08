package interview.leetcode._2xx._20x;

import interview.leetcode.ListNode;

/**
 * Created by zzt on 12/6/17.
 * <p>
 * <h3></h3>
 */
public class RemoveListElement {

    public ListNode removeElements(ListNode head, int val) {
        ListNode fake = new ListNode(0);
        fake.next = head;

        ListNode pre = fake, now = head;
        while (now!=null) {
            if (now.val == val) {
                pre.next = now.next;
            } else {
                pre = now;
            }
            now = now.next;
        }
        return fake.next;
    }
}
