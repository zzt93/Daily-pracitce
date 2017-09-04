package interview.leetcode._10x;

import competition.leetcode.TreeNode;
import interview.leetcode.ListNode;

import static competition.leetcode.TreeNode.printBT;
import static interview.leetcode.ListNode.makeList;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class ConvertSortedList2BST {

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode head, ListNode end) {
        if (head == end) {
            return null;
        }
        ListNode mid = middle(head, end);
        TreeNode res = new TreeNode(mid.val);
        res.left = buildTree(head, mid);
        res.right = buildTree(mid.next, end);
        return res;
    }

    private ListNode middle(ListNode head, ListNode end) {
        ListNode f = head, s = head;
        while (s != end) {
            s = s.next;
            if (s == end) {
                return f;
            }
            s = s.next;
            f = f.next;
        }
        return f;
    }

    public static void main(String[] args) {
        ConvertSortedList2BST bst = new ConvertSortedList2BST();
        printBT(bst.sortedListToBST(makeList("1,2,3,4,5")));
        printBT(bst.sortedListToBST(makeList("1,2,3,4,5,6,7")));
        printBT(bst.sortedListToBST(makeList("1,2,3,4,5,6,7,8")));
        printBT(bst.sortedListToBST(makeList("1,2,3,4,5,6,7,8,9")));
    }
}
