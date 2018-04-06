package interview.leetcode._3xx._38x;

import interview.leetcode.ListNode;

import java.util.Random;

/**
 * Created by zzt on 4/5/18.
 * <p>
 * <h3></h3>
 */
public class LinkedListRandom_382 {

    private ListNode head;
    private Random r = new Random(12);
    public LinkedListRandom_382(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode h = head;
        int res = 0;
        for (int i = 0; h != null; i++) {
            if (r.nextInt(i+1) == i) res = h.val;
            h = h.next;
        }
        return res;
    }
}
