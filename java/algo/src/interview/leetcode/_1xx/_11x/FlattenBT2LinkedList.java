package interview.leetcode._1xx._11x;

import interview.leetcode.TreeNode;

import static interview.leetcode.TreeNode.makeTree;
import static interview.leetcode.TreeNode.printBT;

/**
 * Created by zzt on 9/6/17.
 * <p>
 * <h3></h3>
 */
public class FlattenBT2LinkedList {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        recur(root);
    }

    private TreeNode recur(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left == null) {
            return recur(root.right);
        }
        if (root.right == null) {
            root.right = root.left;
            TreeNode tl = root.left;
            root.left = null;
            return recur(tl);
        }

        TreeNode tr = root.right;
        TreeNode tl = root.left;

        root.right = root.left;
        root.left = null;

        TreeNode last = recur(tl);
        last.right = tr;
        TreeNode res = recur(tr);
        return res;
    }

    public static void main(String[] args) {
        FlattenBT2LinkedList list = new FlattenBT2LinkedList();
        test(list, "1,2");
        test(list, "1,2,5,3,4,null,6");
        test(list, "");
        test(list, "1,2,5,3,4,6,7");
    }

    private static void test(FlattenBT2LinkedList list, String s) {
        TreeNode root = makeTree(s);
        list.flatten(root);
        printBT(root);
    }
}
