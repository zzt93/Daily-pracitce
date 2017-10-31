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
            ListNode next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return now == head;
    }

//    public boolean hasCycle(ListNode head) {
//        if(head==null) return false;
//        ListNode walker = head;
//        ListNode runner = head;
//        while(runner.next!=null && runner.next.next!=null) {
//            walker = walker.next;
//            runner = runner.next.next;
//            if(walker==runner) return true;
//        }
//        return false;
//    }
}
