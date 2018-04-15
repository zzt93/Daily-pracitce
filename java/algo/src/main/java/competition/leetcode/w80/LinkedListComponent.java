package competition.leetcode.w80;

import interview.leetcode.ListNode;
import java.util.HashSet;

/**
 * @author zzt
 */
public class LinkedListComponent {

  public int numComponents(ListNode head, int[] G) {
    HashSet<Integer> s = new HashSet<>();
    for (int i : G) {
      s.add(i);
    }
    boolean last = false;
    int c = 0;
    while (head != null) {
      if (s.contains(head.val)) {
        if (!last) {
          c++;
          last = true;
        }
      } else last = false;
      head = head.next;
    }
    return c;
  }

}
