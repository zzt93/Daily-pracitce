package interview.leetcode._16x;

import interview.leetcode.ListNode;

import static interview.leetcode.ListNode.makeList;

/**
 * Created by zzt on 11/11/17.
 * <p>
 * <h3></h3>
 */
public class IntersectionOfTwoLinkedList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedList l = new IntersectionOfTwoLinkedList();
        System.out.println(l.getIntersectionNode(makeList(""), makeList("1,3")));
        System.out.println(l.getIntersectionNode(makeList(""), makeList("1")));
        System.out.println(l.getIntersectionNode(makeList(""), makeList("")));
    }
}
