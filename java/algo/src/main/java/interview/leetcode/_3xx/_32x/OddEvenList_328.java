package interview.leetcode._3xx._32x;

import interview.leetcode.ListNode;

/**
 * @author zzt
 */
public class OddEvenList_328 {

  public ListNode oddEvenList(ListNode head) {
    if (head == null) return null;
    ListNode oddE = head, even = head.next, evenH = head.next;
    // pre: oddE is last odd, even is last even or null
    while (even != null) {
      if (even.next == null) break;
      oddE.next = even.next;
      oddE = oddE.next;
      even.next = oddE.next;
      even = even.next;
    }
    oddE.next = evenH;
    return head;
  }

}
