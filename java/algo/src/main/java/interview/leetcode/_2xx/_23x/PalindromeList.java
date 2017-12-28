package interview.leetcode._2xx._23x;

import interview.leetcode.ListNode;

import static interview.leetcode.ListNode.makeList;

/**
 * Created by zzt on 12/28/17.
 * <p>
 * <h3></h3>
 */
public class PalindromeList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode f = head, s = head;
        while (f != null) {
            s = s.next;
            f = f.next;
            if (f == null) {
                break;
            }
            f = f.next;
        }

        ListNode last = s, now = last.next;
        last.next = null;
        while (now != null) {
            ListNode next = now.next;
            now.next = last;
            last = now;
            now = next;
        }

        while (last != null) {
            if (last.val != head.val) return false;
            last = last.next;
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeList p = new PalindromeList();
        System.out.println(p.isPalindrome(makeList("1,2,1,1")));
        System.out.println(p.isPalindrome(makeList("1,2,2,1")));
        System.out.println(p.isPalindrome(makeList("1,2,2,2,1")));
        System.out.println(p.isPalindrome(makeList("1,2,2,2,2,1")));
        System.out.println(p.isPalindrome(makeList("1,2,1")));
        System.out.println(p.isPalindrome(makeList("1,1")));
        System.out.println(p.isPalindrome(makeList("1")));
        System.out.println(p.isPalindrome(makeList("")));
    }
}
