package interview.leetcode._1xx._17x;

import interview.leetcode.TreeNode;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 11/15/17.
 * <p>
 * <h3></h3>
 */
public class BSTIterator {

    private TreeNode start = null;

    public BSTIterator(TreeNode root) {
        if (root != null) {
            TreeNode[] nodes = preorder(root);
            start = nodes[0];
        }
    }

    private TreeNode[] preorder(TreeNode now) {
        TreeNode[] res = new TreeNode[2];
        if (now.left == null) {
            res[0] = now;
        } else {
            TreeNode[] l = preorder(now.left);
            l[1].right = now;
            res[0] = l[0];
        }
        if (now.right == null) {
            res[1] = now;
        } else {
            TreeNode[] r = preorder(now.right);
            now.right = r[0];
            res[1] = r[1];
        }
        return res;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return start != null;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        int res = start.val;
        start = start.right;
        return res;
    }

    public static void main(String[] args) {
        test("");
        test("8,4,10,2,6,9,11,1,3,5,7");
    }

    private static void test(String string) {
        BSTIterator b = new BSTIterator(makeTree(string));
        while (b.hasNext()) {
            System.out.print(b.next() + " ");
        }
        System.out.println();
    }
}
