package interview.leetcode;

import java.util.stream.Stream;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }


    public static ListNode makeList(String string) {
        String trim = string.trim();
        if (trim.isEmpty() || trim.equals("null")) {
            return null;
        }
        String[] ints = Stream.of(string.split(",")).map(String::trim).toArray(String[]::new);
        if (ints.length == 0) {
            return null;
        }
        ListNode tmp = new ListNode(0);
        ListNode res = tmp;
        for (String anInt : ints) {
            tmp.next = new ListNode(Integer.parseInt(anInt));
            tmp = tmp.next;
        }
        return res.next;
    }
}
