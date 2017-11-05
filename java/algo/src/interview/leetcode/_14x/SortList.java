package interview.leetcode._14x;

import interview.leetcode.ListNode;

import static interview.leetcode.ListNode.makeList;
import static interview.leetcode.ListNode.print;

/**
 * Created by zzt on 11/2/17.
 * <p>
 * <h3></h3>
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head, pre = slow;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;
        ListNode f = sortList(head);
        ListNode s = sortList(slow);
        return merge(f, s);
    }

    private ListNode merge(ListNode f, ListNode s) {
        ListNode fake = new ListNode(0), now = fake;
        while (f != null && s != null) {
            if (f.val < s.val) {
                now.next = f;
                now = f;
                f = f.next;
            } else {
                now.next = s;
                now = s;
                s = s.next;
            }
        }
        if (f != null) {
            now.next = f;
        } else {
            now.next = s;
        }
        return fake.next;
    }


    public static void main(String[] args) {
        SortList list = new SortList();
        print(list.sortList(makeList("6,5,3,1,8,7,2")));
        print(list.sortList(makeList("")));
        print(list.sortList(makeList("1")));
        print(list.sortList(makeList("1,2")));
        print(list.sortList(makeList("1,2,3,4")));
        print(list.sortList(makeList("4,3,2,1")));
    }
}
