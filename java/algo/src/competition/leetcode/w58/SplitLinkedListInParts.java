package competition.leetcode.w58;

import interview.leetcode.ListNode;

import java.util.Arrays;

import static interview.leetcode.ListNode.makeList;
import static interview.leetcode.ListNode.print;

/**
 * Created by zzt on 11/12/17.
 * <p>
 * <h3></h3>
 */
public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int l = len(root);
        int min = l / k;
        int more = l - k * min;
        ListNode now = root;
        for (int i = 0; i < res.length; i++) {
            res[i] = now;
            now = split(now, min + (more > 0 ? 1 : 0));
            more--;
        }
        return res;
    }

    private ListNode split(ListNode now, int c) {
        ListNode pre = null;
        while (c > 0) {
            pre = now;
            now = now.next;
            c--;
        }
        if (pre != null) {
            pre.next = null;
        }
        return now;
    }

    private int len(ListNode root) {
        int c = 0;
        while (root != null) {
            c++;
            root = root.next;
        }
        return c;
    }

    public static void main(String[] args) {
        SplitLinkedListInParts s = new SplitLinkedListInParts();
//        Arrays.stream(s.splitListToParts(makeList(""), 2)).forEach(ListNode::print);
//        Arrays.stream(s.splitListToParts(makeList("1"), 2)).forEach(ListNode::print);
//        Arrays.stream(s.splitListToParts(makeList("1,2,3"), 2)).forEach(ListNode::print);
//        Arrays.stream(s.splitListToParts(makeList("1,2,3"), 5)).forEach(ListNode::print);
//        Arrays.stream(s.splitListToParts(makeList("1,2,3,4,5,6,7,8,9,10"), 2)).forEach(ListNode::print);
        Arrays.stream(s.splitListToParts(makeList("1,2,3,4,5,6,7,8,9,10"), 3)).forEach(ListNode::print);
    }
}
